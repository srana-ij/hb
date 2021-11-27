package com.bz.hb.converter.form;

import com.bz.hb.model.common.UserStatus;
import org.springframework.core.convert.converter.Converter;

/**
 * created by srana on 07/10/20 at 10.45 AM
 */


public class UserStatusConverter implements Converter<String, UserStatus> {
    @Override public UserStatus convert(String source) {
        try {
            return UserStatus.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
