package org.acme.countries.services;


import org.acme.countries.dtos.CityDto;
import org.acme.countries.dtos.PaginationDto;
import org.acme.countries.dtos.ResponseDto;

import java.util.Collection;

public interface CityService {


    ResponseDto findByCountryCode(String countryCode);

    PaginationDto findByCountryCodeByPage(String countryCode, int page, int pageSize);
}
