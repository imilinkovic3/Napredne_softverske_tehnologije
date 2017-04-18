package rs.silab.nst.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.silab.nst.model.DocumentType;
import rs.silab.nst.model.Role;
import rs.silab.nst.model.User;
import rs.silab.nst.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by kuzmanom on 2/28/2017.
 */
@Controller
@Scope("session")
@RequestMapping("/nst/home/")
public class HomeController {
    @Resource(name = "userService")
    private UserService userService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homeAdmin(HttpSession session) {

       User sessionUser = (User) session.getAttribute("sessionUser");

        for (Role role : sessionUser.getRoles()) {
            if (role.getName().equalsIgnoreCase("admin")) {
                return "homeAdmin";
            }
        }
        for (Role role : sessionUser.getRoles()) {
            if (role.getName().equalsIgnoreCase("user")) {
                return "homeBusiness";
            }
        }
        for (Role role : sessionUser.getRoles()) {
            if (role.getName().equalsIgnoreCase("processcreator")) {
                return "homeUser";
            }
        }


        return "homeUser";
    }

    @RequestMapping(value = "/employees/", method = RequestMethod.GET)
    public String usercreation(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("sessionUser"));
        model.addAttribute("users", userService.findAllUsers());
        return "usercreation";
    }

}
