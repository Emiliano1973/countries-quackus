package org.acme.countries.utils;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RegionsConverter implements ParamConverter<Regions> {
    @Override
    public Regions fromString(String source) {
        return Regions.getRegionByString(source).orElseThrow(() -> new IllegalArgumentException(
                "Error parameter in input with value [" + source + "] is not a region"));
    }

    @Override
    public String toString(Regions region) {
        return region.getRegionName();
    }
}
