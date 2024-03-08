package org.acme.countries.services.impl;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.countries.dao.CountryDao;
import org.acme.countries.dtos.CountryDto;
import org.acme.countries.dtos.PaginationDto;
import org.acme.countries.dtos.ResponseDto;
import org.acme.countries.services.CountryService;
import org.acme.countries.utils.Continents;
import org.acme.countries.utils.Regions;

import java.util.Collection;


@ApplicationScoped
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;

    @Inject
    public CountryServiceImpl(final CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public ResponseDto findAll() {
        Collection<CountryDto> countryDtos=this.countryDao.findAll();
        return new ResponseDto(countryDtos.size(), countryDtos);
    }
    @Override
    public PaginationDto findByPage(final int page, final int pageSize) {
        return  this.countryDao.findByPage(page, pageSize);
    }

    @Override
    public ResponseDto findByContinent(final Continents continent) {
        Collection<CountryDto> countryDtos=this.countryDao.findByContinent(continent);
        return new ResponseDto(countryDtos.size(), countryDtos );
    }

    @Override
    public PaginationDto findByContinentByPage(final Continents continent, final int page, final int pageSize) {
        return this.countryDao.findByContinentByPage(continent, page, pageSize);
    }

    @Override
    public ResponseDto findByRegion(final Regions region) {
        Collection<CountryDto> countryDtos=this.countryDao.findByRegion(region);
        return new ResponseDto(countryDtos.size(), countryDtos);
    }

    @Override
    public PaginationDto findByRegionByPage(final Regions region, final  int page, final int pageSize) {
        return this.countryDao.findByRegionByPage(region, page, pageSize);
    }
    @Override
    public ResponseDto findByPopulation(final Integer population) {
        Collection<CountryDto> countryDtos=this.countryDao.findByPopulation(population);
        return new ResponseDto(countryDtos.size(), countryDtos);
    }

    @Override
    public ResponseDto findByEndip(final String isIndepYear) {
        boolean isEndYear=(isIndepYear.equals("Y"));
        Collection<CountryDto> countryDtos=this.countryDao.findByIndep(isEndYear);
        return new ResponseDto(countryDtos.size(), countryDtos);
    }

}
