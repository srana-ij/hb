package com.bz.hb.converter.form;

import com.bz.hb.model.bloodType.BloodType;
import org.springframework.core.convert.converter.Converter;

public class BloodTypeConverter implements Converter<String, BloodType> {
    @Override
    public BloodType convert(String source) {
        try {
            return BloodType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}