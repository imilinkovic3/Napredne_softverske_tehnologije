package rs.silab.nst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.silab.nst.model.User;
import rs.silab.nst.service.RoleService;
import rs.silab.nst.service.UserService;
import rs.silab.nst.validators.UserLoginValidator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@Scope("session")
@RequestMapping("/nst/")
public class LoginController {

    @Resource(name = "roleService")
    private RoleService roleService;

    @Resource(name = "userService")
    private UserService userService;

    @Autowired
    UserLoginValidator userLoginValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userLoginValidator);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start(ModelMap model, HttpSession session) {
        model.addAttribute("roles", roleService.findAllRoles());
        model.addAttribute("user", new User());
        return "login/login";
    }

    @RequestMapping(value = {"/login/"}, method = RequestMethod.POST)
    public String login(@ModelAttribute("user") @Validated User user, BindingResult result, ModelMap model, HttpSession session) {
        if (result.hasErrors()) {
            return "login/login";
        }
        User foundUser = userService.findByEmailOrUsername(user.getUsername());
        session.setAttribute("sessionUser", foundUser);

       System.out.println(foundUser.getRoles());
        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            return "redirect:/nst/home/";
        }

        return "redirect:/nst/";
    }

    @RequestMapping(value = "/logout/", method = RequestMethod.GET)
    public String logout(Model model, HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException {
        if (session.getAttribute("sessionUser") == null) {
            return "redirect:/nst/";
        }

        session.invalidate();
        return "redirect:/nst/";
    }
}