package org.acme.countries.dao;


import org.acme.countries.dtos.CountryDto;
import org.acme.countries.dtos.PaginationDto;
import org.acme.countries.utils.Continents;
import org.acme.countries.utils.Regions;

import java.util.Collection;

public interface CountryDao {


    Collection<CountryDto> findAll();

    PaginationDto findByPage(int page, int pageSize);

    Collection<CountryDto> findByContinent(Continents continent);

    PaginationDto findByContinentByPage(Continents continent, int page, int pageSize);

    Collection<CountryDto> findByRegion(Regions region);

    PaginationDto findByRegionByPage(Regions region, int page, int pageSize);

    Collection<CountryDto> findByPopulation(Integer population);

    Collection<CountryDto> findByIndep(Boolean isIndep);


}
