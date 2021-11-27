package com.bz.hb.validator;

import com.bz.hb.service.MenuGroupService;
import com.bz.hb.controller.menu.MenuForm;
import com.bz.hb.model.security.MenuGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return MenuForm.class.equals(clazz);
    }

    @Autowired
    MenuGroupService menuGroupService;

    @Override
    public void validate(Object target, Errors errors) {
        MenuForm form = (MenuForm) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"groupName","groupName.required","Please Enter Group Name");

        if(form.isPersisted()){
            if(form.getSequenceNo() !=null){
                MenuGroup menuGroup=menuGroupService.getMenuGroup(form.getId());
                if(menuGroup.getSequenceNo() != form.getSequenceNo()){
                    if(menuGroupService.isSequenceNoExists(form.getSequenceNo())){
                        errors.rejectValue("sequenceNo","","This sequence already use");
                    }
                }
            }
        }

    }
}
