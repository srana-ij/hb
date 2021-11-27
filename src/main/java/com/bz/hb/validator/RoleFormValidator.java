package com.bz.hb.validator;

import com.bz.hb.controller.role.RoleForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * created by srana on 31/12/20 at 12.53 AM
 */
@Component
public class RoleFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return RoleForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RoleForm form=(RoleForm) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"roleName","roleName.required","Please Enter Role Name");
    }
}
