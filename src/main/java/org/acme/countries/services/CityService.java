package org.acme.countries.services;


import org.acme.countries.dtos.PaginationDto;
import org.acme.countries.dtos.ResponseDto;

public interface CityService {


    ResponseDto findByCountryCode(String countryCode);

    PaginationDto findByCountryCodeByPage(String countryCode, int page, int pageSize);
}
