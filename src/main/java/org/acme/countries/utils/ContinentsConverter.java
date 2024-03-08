package org.acme.countries.utils;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ContinentsConverter implements ParamConverter<Continents> {

    @Override
    public Continents fromString(String source) {
        return Continents.getContinentByCode(source).orElseThrow(()->new IllegalArgumentException("Error parameter in input with value ["+source+"] is not a continent"));
    }

    @Override
    public String toString(Continents continent) {
        return continent.getCode();
    }
}
