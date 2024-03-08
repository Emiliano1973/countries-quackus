package org.acme.countries.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.countries.services.CountryService;
import org.acme.countries.utils.Continents;
import org.acme.countries.utils.Regions;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/countries")
public class CountryController {

    private final CountryService countryService;

    @Inject
    public CountryController(final CountryService countryService) {
        this.countryService = countryService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
       return RestResponse.ResponseBuilder.ok(this.countryService.findAll(),
               MediaType.APPLICATION_JSON_TYPE).build().toResponse();
    }

    @Path("/pages")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllByPage(@QueryParam("page")  final int page,
                                      @QueryParam("pageSize") final int pageSize){
        return RestResponse.ResponseBuilder
                .ok(this.countryService.findByPage(page, pageSize),
                        MediaType.APPLICATION_JSON_TYPE).build().toResponse();
    }

    @Path("/continent")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllByContinent(@QueryParam("continent") final Continents continent){
        return RestResponse.ResponseBuilder
                .ok(this.countryService.findByContinent(continent),
                        MediaType.APPLICATION_JSON_TYPE).build().toResponse();
    }

    @Path("/region")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllByRegion(@QueryParam("region") final Regions region){
        return RestResponse.ResponseBuilder
                .ok(this.countryService.findByRegion(region),
                        MediaType.APPLICATION_JSON_TYPE).build().toResponse();
    }

    @Path("/region/pages")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllByRegionPage(@QueryParam("region") final Regions region,
                                            @QueryParam("page") final int page,
                                            @QueryParam("pageSize") final int pageSize){
        return  RestResponse.ResponseBuilder
                .ok(this.countryService.findByRegionByPage(region, page, pageSize),
                        MediaType.APPLICATION_JSON_TYPE).build().toResponse();
    }

    @Path( "/continent/pages")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByContinentPage(@QueryParam("continent") final Continents continent,
                                            @QueryParam("page") final int page,
                                            @QueryParam("pageSize") final  int pageSize) {
        return RestResponse.ResponseBuilder
                .ok(this.countryService.findByContinentByPage(continent, page, pageSize),
                        MediaType.APPLICATION_JSON_TYPE).build().toResponse();
    }

    @Path("/population")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByPopulation(@QueryParam("population") final Integer population){
        return  RestResponse.ResponseBuilder
                .ok(this.countryService.findByPopulation(population),
                        MediaType.APPLICATION_JSON_TYPE).build().toResponse();
    }

    @Path("/indepYear")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByIndepYear(@QueryParam("indepYear") final String indepYear){
        return RestResponse.ResponseBuilder
                .ok(this.countryService.findByEndip(indepYear),
                        MediaType.APPLICATION_JSON_TYPE).build().toResponse();
    }

}