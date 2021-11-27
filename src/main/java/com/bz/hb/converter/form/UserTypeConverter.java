package com.bz.hb.converter.form;

/**
 * created by srana on 07/10/20 at 10.46 AM
 */

import com.bz.hb.model.common.UserType;
import org.springframework.core.convert.converter.Converter;

public class UserTypeConverter implements Converter<String, UserType> {
    @Override public UserType convert(String source) {
        try {
            return UserType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
