package rs.silab.nst.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Scope("session")
@RequestMapping("/nst/role/")
public class RoleController {
    @RequestMapping(value = "/change/{name}/", method = RequestMethod.GET)
    public String updateUser(@PathVariable("name") String name) {
        System.out.println("*****************************************************");
        if (name.equals("Admin")) {
            return "homeAdmin";
        } else if (name.equals("Business")) {
            return "homeBusiness";
        } else if (name.equals("User")) {
            return "homeUser";
        }

        return "redirect:/nst/home/";
    }
}