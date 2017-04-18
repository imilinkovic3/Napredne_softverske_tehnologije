package rs.silab.nst.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import rs.silab.nst.model.Company;

/**
 * Created by kuzmanom on 3/6/2017.
 */
@Component
public class CompanyFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Company.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Company company = (Company) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pib", "NotEmpty.company.pib");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.company.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "NotEmpty.company.country");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "NotEmpty.company.city");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "postalcode", "NotEmpty.company.postalcode");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.company.address");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fax", "NotEmpty.company.fax");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.company.phone");

        if (company.getPib() == null) {
            errors.rejectValue("pib", "NotEmpty.company.pib");
        }

        try {
            if (((int) Math.log10(Integer.parseInt(company.getPib())) + 1) != 9) {
                errors.rejectValue("pib", "Invalid.company.pib");
            }
        } catch (NumberFormatException e){
            errors.rejectValue("pib", "Invalid.company.pib");
        }

        if (company.getName() == null) {
            errors.rejectValue("name", "NotEmpty.company.name");
        }

        if (company.getCountry() == null) {
            errors.rejectValue("country", "NotEmpty.company.country");
        }

        if (company.getCity() == null) {
            errors.rejectValue("city", "NotEmpty.company.city");
        }

        if (company.getPostalcode().equals(null)) {
            errors.rejectValue("postalcode", "NotEmpty.company.postalcode");
        }

        if (company.getAddress() == null) {
            errors.rejectValue("address", "NotEmpty.company.address");
        }

        if (company.getFax() == null) {
            errors.rejectValue("fax", "NotEmpty.company.fax");
        }

        if (company.getPhone() == null) {
            errors.rejectValue("phone", "NotEmpty.company.phone");
        }
    }
}
