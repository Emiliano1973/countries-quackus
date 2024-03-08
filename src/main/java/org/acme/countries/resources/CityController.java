package org.acme.countries.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.countries.services.CityService;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/cities")
public class CityController {

    private final CityService cityService;

    @Inject
    public CityController(final CityService cityService) {
        this.cityService = cityService;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("country_code") final String countryCode){
       return RestResponse.ResponseBuilder
                .ok(this.cityService.findByCountryCode(countryCode),
                        MediaType.APPLICATION_JSON_TYPE).build().toResponse();
      
    }

    @Path("/pages")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllByPage(@QueryParam("country_code") final String countryCode,
                                 @QueryParam("page") final int page,
                                      @QueryParam("pageSize")  int pageSize){
        return  RestResponse.ResponseBuilder
                .ok(this.cityService.findByCountryCodeByPage(countryCode, page, pageSize),
                        MediaType.APPLICATION_JSON_TYPE).build().toResponse();
    }
    
}
