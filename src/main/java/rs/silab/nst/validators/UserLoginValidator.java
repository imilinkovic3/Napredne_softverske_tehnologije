package rs.silab.nst.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import rs.silab.nst.model.User;
import rs.silab.nst.service.UserService;

/**
 * Created by kuzmanom on 3/1/2017.
 */
@Component
public class UserLoginValidator implements Validator {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        User userFromDb = userService.findByEmailOrUsername(user.getUsername());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.user.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.user.username");

        if (user.getPassword() == null) {
            errors.rejectValue("password", "NotEmpty.user.password");
        }

        if (userFromDb == null) {
            errors.rejectValue("username", "Login.user.credentials");
        } else if (!userFromDb.getPassword().equals(user.getPassword())) {
            errors.rejectValue("username", "Login.user.credentials");
        }
    }
}
