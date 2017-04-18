package rs.silab.nst.controller;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rs.silab.nst.email.Email;
import rs.silab.nst.email.EmailConfiguration;
import rs.silab.nst.email.EmailService;
import rs.silab.nst.model.User;
import rs.silab.nst.service.UserService;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@Scope("session")
@RequestMapping("/nst/forgotpassword/")
public class ForgotPasswordController {

    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping(value = {"/"}, method = RequestMethod.POST)
    public String forgetPassword(String emailOrUsername, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            User foundUser = userService.findByEmailOrUsername(emailOrUsername);

            if (foundUser == null) {
                redirectAttributes.addFlashAttribute("css", "danger");
                redirectAttributes.addFlashAttribute("message", "Account with this email does not exist!");
                return "redirect:/nst/forgotpassword/get/";
            }

            session.setAttribute("sessionUser", foundUser);
            sendResetPasswordEmail(foundUser);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        redirectAttributes.addFlashAttribute("css", "info");
        redirectAttributes.addFlashAttribute("message", "You will receive email with activation link!");
        return "redirect:/nst/forgotpassword/get/";
    }

    @RequestMapping(value = {"/saveResetPassword/"}, method = RequestMethod.POST)
    public String saveResetPassword(String password, String confirmPassword, RedirectAttributes redirectAttributes, HttpSession session) {
        if (password.length() < 6 || confirmPassword.length() < 6) {
            redirectAttributes.addFlashAttribute("css", "danger");
            redirectAttributes.addFlashAttribute("message", "Password and confirm password must have at least six characters!");
            return "redirect:/nst/forgotpassword/reset/";
        }

        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("css", "danger");
            redirectAttributes.addFlashAttribute("message", "Password and confirm password are different!");
            return "redirect:/nst/forgotpassword/reset/";
        }

        User user = (User) session.getAttribute("sessionUser");
        user.setPassword(password);

        userService.saveUser(user);

        redirectAttributes.addFlashAttribute("css", "info");
        redirectAttributes.addFlashAttribute("message", "Password is successfully changed!");
        return "redirect:/nst/";
    }

    @RequestMapping(value = "/reset/", method = RequestMethod.GET)
    public String resetPassword() throws IOException {
        return "login/resetpassword";
    }

    @RequestMapping(value = "/get/", method = RequestMethod.GET)
    public String forgotPassword() throws IOException {
        return "login/forgotpassword";
    }

    private void sendResetPasswordEmail(User user) throws AddressException, MessagingException {
        EmailConfiguration configuration = new EmailConfiguration();
        configuration.setProperty(EmailConfiguration.SMTP_HOST, "smtp.gmail.com");
        configuration.setProperty(EmailConfiguration.SMTP_AUTH, "true");
        configuration.setProperty(EmailConfiguration.SMTP_TLS_ENABLE, "true");
        configuration.setProperty(EmailConfiguration.SMTP_AUTH_USER, "nstprojekat@gmail.com");
        configuration.setProperty(EmailConfiguration.SMTP_AUTH_PWD, "nstprojekat2017");
        EmailService emailService = new EmailService(configuration);

        Email email = new Email();
        email.setFrom("kuzma.fon@gmail.com");
        email.setTo(user.getEmail());
        email.setSubject("Register confirmation");
        email.setText("Your username is: " + user.getUsername() + ". \n Please visit the following link in order to reset your password: " +
                "\n http://localhost:8080/nst/forgotpassword/reset/");
        email.setMimeType("text/html");
        emailService.sendEmail(email);
    }
}