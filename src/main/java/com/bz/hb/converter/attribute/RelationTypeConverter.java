package com.bz.hb.converter.attribute;

import com.bz.hb.model.relationType.RelationType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RelationTypeConverter implements AttributeConverter<RelationType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RelationType type) {
        return type.getId();
    }

    @Override
    public RelationType convertToEntityAttribute(Integer id) {
        return RelationType.getById(id);
    }
}
