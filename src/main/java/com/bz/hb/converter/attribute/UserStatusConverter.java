package com.bz.hb.converter.attribute;

/**
 * created by srana on 07/10/20 at 10..46 AM
 */

import com.bz.hb.model.common.UserStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserStatusConverter implements AttributeConverter<UserStatus, Integer> {
    @Override public Integer convertToDatabaseColumn(UserStatus status) {
        return status.getId();
    }

    @Override public UserStatus convertToEntityAttribute(Integer id) {
        return UserStatus.getById(id);
    }
}
