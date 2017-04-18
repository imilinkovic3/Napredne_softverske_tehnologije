package rs.silab.nst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rs.silab.nst.model.Company;
import rs.silab.nst.model.User;
import rs.silab.nst.service.CompanyService;
import rs.silab.nst.validators.CompanyFormValidator;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by kuzmanom on 2/28/2017.
 */
@Controller
@Scope("session")
@RequestMapping("/nst/company/")
public class CompanyController {
    @Resource(name = "companyService")
    private CompanyService companyService;

    @Autowired
    CompanyFormValidator companyFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(companyFormValidator);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String company(Model model, HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        model.addAttribute("company", user.getCompanyBean());
        return "company";
    }

    @RequestMapping(value = {"/update/"}, method = RequestMethod.POST)
    public String updateCompany(@ModelAttribute("company") @Validated Company company, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) throws IOException {
        if (session.getAttribute("sessionUser") == null) {
            return "redirect:/nst/";
        }

        if (result.hasErrors()) {
            return "company";
        }

        User user = (User) session.getAttribute("sessionUser");
        user.setCompanyBean(company);
        session.setAttribute("sessionUser", user);

        companyService.updateCompany(company);

        redirectAttributes.addFlashAttribute("css", "info");
        redirectAttributes.addFlashAttribute("message", "Company is updated!");

        return "redirect:/nst/home/employees/";
    }
}
