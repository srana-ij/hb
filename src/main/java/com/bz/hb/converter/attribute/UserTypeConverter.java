package com.bz.hb.converter.attribute;

/**
 * created by srana on 07/10/20 at 10.47 AM
 */

import com.bz.hb.model.common.UserType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserTypeConverter implements AttributeConverter<UserType, Integer> {
    @Override public Integer convertToDatabaseColumn(UserType type) {
        return type.getId();
    }

    @Override public UserType convertToEntityAttribute(Integer id) {
        return UserType.getById(id);
    }
}
