package rs.silab.nst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.silab.nst.captcha.VerifyUtils;
import rs.silab.nst.email.Email;
import rs.silab.nst.email.EmailConfiguration;
import rs.silab.nst.email.EmailService;
import rs.silab.nst.model.User;
import rs.silab.nst.service.CompanyService;
import rs.silab.nst.service.RoleService;
import rs.silab.nst.service.UserService;
import rs.silab.nst.validators.UserRegistrationValidator;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by kuzmanom on 2/28/2017.
 */
@Controller
@Scope("session")
@RequestMapping("/nst/register/")
public class RegisterController {

    @Resource(name = "roleService")
    private RoleService roleService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "companyService")
    private CompanyService companyService;

    @Autowired
    UserRegistrationValidator userRegistrationValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userRegistrationValidator);
    }

    @RequestMapping(value = "/registerform/", method = RequestMethod.GET)
    public String logout(Model model) throws IOException {
        model.addAttribute("roles", roleService.findAllRoles());
        model.addAttribute("user", new User());
        return "login/register";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.POST)
    public String register(HttpServletRequest request, ModelMap model, HttpSession session) {
        try {
            int codeFromUser = Integer.parseInt(String.valueOf(request.getParameter("code")));
            int confirmationCode = (Integer) request.getSession().getAttribute("confirmationcode");

            if (codeFromUser != confirmationCode) {
                model.addAttribute("confirmationCodeError", "Confirmation code is not correct!");
                return "login/confirmregistration";
            }
        } catch (NumberFormatException e) {
            model.addAttribute("confirmationCodeError", "Confirmation code error!");
            return "login/confirmregistration";
        }

        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("roles", roleService.findAllRoles());

        User user = (User) session.getAttribute("sessionUser");
        userService.saveUser(user);

        return "redirect:/nst/";
    }

    @RequestMapping(value = {"/confirmregistration/"}, method = RequestMethod.POST)
    public String confirmRregistration(@ModelAttribute("user") @Validated User user, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {
        try {
            if (result.hasErrors()) {
                return "login/register";
            }

            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            boolean valid = VerifyUtils.verify(gRecaptchaResponse);
            if (!valid) {
                result.addError(new ObjectError("captchaError", "Check the checkbox!"));
                model.addAttribute("captchaError", result.getAllErrors().get(0).getDefaultMessage());
                return "login/register";
            }

            session.setAttribute("sessionUser", user);
            sendConfirmationEmail(request);
        } catch (MessagingException e) {
            e.printStackTrace();
            return "login/register";
        }
        return "redirect:/nst/register/confirmregistrationForm/";
    }

    @RequestMapping(value = "/confirmregistrationForm/", method = RequestMethod.GET)
    public String confirmregistrationform() throws IOException {
        return "login/confirmregistration";
    }

    public void sendConfirmationEmail(HttpServletRequest request) throws AddressException, MessagingException {
        EmailConfiguration configuration = new EmailConfiguration();
        configuration.setProperty(EmailConfiguration.SMTP_HOST, "smtp.gmail.com");
        configuration.setProperty(EmailConfiguration.SMTP_AUTH, "true");
        configuration.setProperty(EmailConfiguration.SMTP_TLS_ENABLE, "true");
        configuration.setProperty(EmailConfiguration.SMTP_AUTH_USER, "nstprojekat@gmail.com");
        configuration.setProperty(EmailConfiguration.SMTP_AUTH_PWD, "nstprojekat2017");
        EmailService emailService = new EmailService(configuration);

        Email email = new Email();
        email.setFrom("kuzma.fon@gmail.com");
        email.setTo("kuzma.fon@gmail.com");

        int confirmationCode = (int) Math.round(Math.random() * 10000);

        request.getSession().setAttribute("confirmationcode", confirmationCode);

        email.setSubject("Register confirmation");
        email.setText("Please, confirm your registretion with folowing code " + confirmationCode);
        email.setMimeType("text/html");

        emailService.sendEmail(email);
    }
}
