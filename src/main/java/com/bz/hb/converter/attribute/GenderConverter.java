package com.bz.hb.converter.attribute;

import com.bz.hb.model.gender.Gender;

import javax.persistence.AttributeConverter;

public class GenderConverter implements AttributeConverter<Gender, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Gender type) {
        return type.getId();
    }


    @Override
    public Gender convertToEntityAttribute(Integer id) {
        return Gender.getById(id);
    }
}
