package org.acme.countries.dao;



import org.acme.countries.dtos.CityDto;
import org.acme.countries.dtos.PaginationDto;

import java.util.Collection;

public interface CityDao {

    Collection<CityDto> findByCountryCode(String countryCode);

    PaginationDto findByCountryCodeByPage(String countryCode, int page, int pageSize);

}
