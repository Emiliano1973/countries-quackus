package org.acme.countries.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.countries.services.CountryLanguageService;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/countries/languages")
public class CountryLanguagesController {
    private final CountryLanguageService countryLanguageService;

    @Inject
    public CountryLanguagesController(final CountryLanguageService countryLanguageService) {
        this.countryLanguageService = countryLanguageService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLanguagesByCountryCode(@QueryParam("country_code") final String countryCode){
        return RestResponse.ResponseBuilder
                .ok(this.countryLanguageService.getLanguagesByCountryId(countryCode),
                        MediaType.APPLICATION_JSON_TYPE).build().toResponse();
    }

}
