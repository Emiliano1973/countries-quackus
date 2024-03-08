package org.acme.countries.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import org.acme.countries.dao.CityDao;
import org.acme.countries.dtos.CityDto;
import org.acme.countries.dtos.PaginationDto;
import org.acme.countries.dtos.ResponseDto;
import org.acme.countries.services.CityService;
import org.springframework.stereotype.Service;

import java.util.Collection;
@ApplicationScoped
public class CityServiceImpl implements CityService {

    private final CityDao cityDao;

    @Inject
    public CityServiceImpl(final CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public ResponseDto findByCountryCode(String countryCode) {
        Collection<CityDto> cities= this.cityDao.findByCountryCode(countryCode);
        return new ResponseDto(cities.size(), cities);
    }

    @Override
    public PaginationDto findByCountryCodeByPage(String countryCode, int page, int pageSize) {
        return this.cityDao.findByCountryCodeByPage(countryCode, page, pageSize);
    }
}
