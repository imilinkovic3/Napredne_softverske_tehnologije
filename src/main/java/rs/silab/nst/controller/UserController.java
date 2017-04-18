package rs.silab.nst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rs.silab.nst.model.Role;
import rs.silab.nst.model.User;
import rs.silab.nst.service.RoleService;
import rs.silab.nst.service.UserService;
import rs.silab.nst.validators.UserRegistrationValidator;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by milinkoi on 11.02.2017.
 */
@Controller
@Scope("session")
@RequestMapping("/nst/user/")
public class UserController {
    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "roleService")
    private RoleService roleService;

    @Autowired
    UserRegistrationValidator userRegistrationValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userRegistrationValidator);
    }

    @RequestMapping(value = "/details/{id}/", method = RequestMethod.GET)
    public String details(@PathVariable("id") int id, Model model) {
        User user = userService.findById(id);

        if (user == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "User not found");
        }

        model.addAttribute("user", user);
        return "details";
    }

    @RequestMapping(value = "/add/", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAllRoles());
        return "adduser";
    }

    @RequestMapping(value = "/update/{id}/", method = RequestMethod.GET)
    public String updateUser(@PathVariable("id") int id, Model model, HttpSession session) {
        User user = userService.findById(id);
        User sessionUser = ((User) session.getAttribute("sessionUser"));

        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAllStringRoles());

        for (Role role : sessionUser.getRoles()) {
            if (role.getName().equalsIgnoreCase("admin")) {
                return "adduser";
            }
        }
        return "notAdminDetails";
    }

    @RequestMapping(value = {"/save/"}, method = RequestMethod.POST)
    public String saveUser(@Validated User user, BindingResult result, Model model, HttpSession session, RedirectAttributes redirectAttributes) throws IOException {
        User sessionUser = ((User) session.getAttribute("sessionUser"));

        if (result.hasErrors()) {
            System.out.print("usao u eror");
            model.addAttribute("roles", roleService.findAllStringRoles());
            for (Role role : sessionUser.getRoles()) {
                if (role.getName().equalsIgnoreCase("admin")) {
                    System.out.print("usao u admin deo!!!!!!");
                    return "adduser";
                }
            }
            return "notAdminDetails";
        }

        user.setRoles(roleService.findRoles(user.getStringRoles()));
        userService.updateUser(user);

        if (sessionUser.getId() == user.getId()) {
            session.setAttribute("sessionUser", userService.findById(user.getId()));
        }

        redirectAttributes.addFlashAttribute("css", "info");
        if (user.getId() == 0) {
            redirectAttributes.addFlashAttribute("message", "User is added!");
        } else {
            redirectAttributes.addFlashAttribute("message", "User is updated!");
        }

        for (Role role : sessionUser.getRoles()) {
            if (role.getName().equalsIgnoreCase("admin")) {
                return "redirect:/nst/home/employees/";
            }
        }
        System.out.print("nije usao u admin deo!!!!!!");
        return "redirect:/nst/home/";
    }

    @RequestMapping(value = "/delete/{id}/", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("css", "info");
        redirectAttributes.addFlashAttribute("message", "User is deleted!");
        return "redirect:/nst/home/employees/";
    }
}