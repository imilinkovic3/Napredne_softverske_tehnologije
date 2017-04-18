package rs.silab.nst.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import rs.silab.nst.model.User;
import rs.silab.nst.service.UserService;

@Component
public class UserRegistrationValidator implements Validator {

    @Autowired
    @Qualifier("emailValidator")
    EmailValidator emailValidator;

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        User dbUser = userService.findByEmail(user);

        if (dbUser == null) {
            dbUser = userService.findByUsername(user);
        }

        if (dbUser == null) {
            dbUser = new User();
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "NotEmpty.user.firstname");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "NotEmpty.user.lastname");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.user.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.user.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.user.username");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyBean.pib", "NotEmpty.user.pib");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyBean.name", "NotEmpty.user.companyName");

        if (user.getFirstname() == null) {
            errors.rejectValue("firstname", "NotEmpty.user.firstname");
        }

        if (user.getLastname() == null) {
            errors.rejectValue("lastname", "NotEmpty.user.lastname");
        }

        if (!emailValidator.valid(user.getEmail())) {
            errors.rejectValue("email", "Pattern.user.email");
        }

        if (userService.findByEmail(user) != null && user.getId() != dbUser.getId()) {
            errors.rejectValue("email", "NotEmpty.user.email.exist");
        }

        if (userService.findByUsername(user) != null && user.getId() != dbUser.getId()) {
            errors.rejectValue("email", "NotEmpty.user.email.exist");
        }

        if (user.getPassword() == null) {
            errors.rejectValue("password", "NotEmpty.user.password");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Diff.user.confirmPassword");
        }

        if (user.getUsername() == null) {
            errors.rejectValue("username", "NotEmpty.user.username");
        }

        if (user.getCompanyBean().getPib() == null) {
            errors.rejectValue("companyBean.pib", "NotEmpty.user.pib");
        }

        try {
            if (((int) Math.log10(Integer.parseInt(user.getCompanyBean().getPib())) + 1) != 9) {
                errors.rejectValue("companyBean.pib", "Invalid.company.pib");
            }
        } catch (NumberFormatException e) {
            errors.rejectValue("companyBean.pib", "Invalid.company.pib");
        }

        if (user.getCompanyBean().getName() == null) {
            errors.rejectValue("companyBean.name", "NotEmpty.user.companyName");

        }
    }
}