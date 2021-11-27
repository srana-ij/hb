package com.bz.hb.validator;
/**
 * created by srana on 07/10/20 at 12.53 AM
 */
import com.bz.hb.service.UserService;
import com.bz.hb.controller.signUp.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class SignUpFormValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz)   {
        return SignUpForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        SignUpForm form = (SignUpForm) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"fullName","fullName.required","Please Enter Full Name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"userName","userName.required","Please Enter Login User Name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","email.required","Please Enter Email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"contactNo","contactNo.required","Please Enter Contact Number");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","password.required","Please Enter Password");

        if (userService.isUsernameExists(form.getUserName())) {
            errors.rejectValue("userName", "duplicate.userName");
        }
        if (userService.isUserEmailExists(form.getEmail())) {
            errors.rejectValue("email", "duplicate.email");
        }
    }
}
