package rs.silab.nst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rs.silab.nst.model.DocumentType;
import rs.silab.nst.model.Status;
import rs.silab.nst.model.User;
import rs.silab.nst.service.DocumentDescriptorService;
import rs.silab.nst.service.DocumentService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by milinkoi on 08.03.2017.
 */
@Controller
@Scope("session")
@RequestMapping("/nst/documents/")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @Autowired
    DocumentDescriptorService documentDescriptorService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getDocuments(Model model, HttpSession session) {
        model.addAttribute("documents", documentService.findAll(((User) session.getAttribute("sessionUser")).getCompanyBean().getPib()));
        return "documents";
    }

    @RequestMapping(value = "/documentForm/{id}/", method = RequestMethod.GET)
    public String documentTypeCreation(@PathVariable("id") int id, Model model, HttpSession session) {
        try {
            DocumentType document = documentService.findById(id);
            model.addAttribute("document", document);

            if (document.getStatus() == Status.NOT_INSTANTIATED) {
                model.addAttribute("readOnly", Boolean.FALSE);
            }
        } catch (RuntimeException e) {
            model.addAttribute("document", new DocumentType());

        }

        model.addAttribute("descriptorTypes", getDescriptorTypes());
        return "documentForm";
    }

    @RequestMapping(value = {"/save/"}, method = RequestMethod.POST)
    public String confrimRregistration(@ModelAttribute DocumentType document, RedirectAttributes redirectAttributes) throws IOException {
        System.out.println(document);
        try {
            documentService.save(document);
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("css", "danger");
            redirectAttributes.addFlashAttribute("message", "Document cannot be saved!");
            return "redirect:/nst/documents/documentForm/" + document.getId() + "/";
        }


        redirectAttributes.addFlashAttribute("css", "info");
        redirectAttributes.addFlashAttribute("message", "Document is successfully saved!");

        return "redirect:/nst/documents/";
    }

    public Object getDescriptorTypes() {
        List<String> descriptorTypes = new ArrayList<>();
        descriptorTypes.add("Number");
        descriptorTypes.add("Decimal");
        descriptorTypes.add("Text");
        descriptorTypes.add("Date");
        descriptorTypes.add("Logical");
        return descriptorTypes;
    }
}
