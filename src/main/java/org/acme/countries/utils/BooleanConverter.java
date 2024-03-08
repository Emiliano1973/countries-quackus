package org.acme.countries.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter
public class BooleanConverter implements AttributeConverter<Boolean, String> {

    private static final String TRUE="T";
    private static final String FALSE="F";
    @Override
    public String convertToDatabaseColumn(final Boolean attribute) {
        Objects.requireNonNull(attribute, "Error, Object boolean in input cannot be null");
        if(attribute.booleanValue()){
            return TRUE;
        }
        return  FALSE;
    }

    @Override
    public Boolean convertToEntityAttribute(final String dbData) {
        if(TRUE.equalsIgnoreCase(dbData)){
            return Boolean.TRUE;
        }
        if(FALSE.equalsIgnoreCase(dbData)){
            return Boolean.FALSE;
        }
        throw new IllegalArgumentException("Attribute value in input is not enum of T or F");
    }
}
