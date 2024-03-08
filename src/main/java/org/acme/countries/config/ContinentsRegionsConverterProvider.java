package org.acme.countries.config;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;
import org.acme.countries.utils.Continents;
import org.acme.countries.utils.ContinentsConverter;
import org.acme.countries.utils.Regions;
import org.acme.countries.utils.RegionsConverter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class ContinentsRegionsConverterProvider implements ParamConverterProvider {
    @SuppressWarnings("unchecked")
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType,
                                              Type genericType,
                                              Annotation[] annotations) {
        if (rawType.isAssignableFrom(Continents.class)) {
            return (ParamConverter<T>) new ContinentsConverter();
        } else if (rawType.isAssignableFrom(Regions.class)) {
            return (ParamConverter<T>) new RegionsConverter();
        }
        return null;
    }
}
