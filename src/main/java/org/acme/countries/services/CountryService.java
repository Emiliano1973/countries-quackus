package org.acme.countries.services;


import org.acme.countries.dtos.PaginationDto;
import org.acme.countries.dtos.ResponseDto;
import org.acme.countries.utils.Continents;
import org.acme.countries.utils.Regions;

public interface CountryService {
    ResponseDto findAll();

    PaginationDto findByPage(int page, int pageSize);

    ResponseDto findByContinent(Continents continent);

    PaginationDto findByContinentByPage(Continents continent, int page, int pageSize);

    ResponseDto findByRegion(Regions region);

    PaginationDto findByRegionByPage(Regions region, int page, int pageSize);


    ResponseDto findByPopulation(Integer population);

    ResponseDto findByEndip(String isIndepYear);
}
