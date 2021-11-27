package com.bz.hb.converter.attribute;


import com.bz.hb.model.bloodType.BloodType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BloodTypeConverter implements AttributeConverter<BloodType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BloodType type) {
        return type == null ? null : type.getId();
    }


    @Override
    public BloodType convertToEntityAttribute(Integer id) {
        return id == null ? null : BloodType.getById(id);
    }


}
