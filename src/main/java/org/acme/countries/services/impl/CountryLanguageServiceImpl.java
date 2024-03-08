package org.acme.countries.services.impl;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.countries.dao.CountryLanguagesDao;
import org.acme.countries.dtos.CountryLanguageDto;
import org.acme.countries.dtos.ResponseDto;
import org.acme.countries.services.CountryLanguageService;

import java.util.Collection;

@ApplicationScoped
public class CountryLanguageServiceImpl  implements CountryLanguageService {

    private final CountryLanguagesDao countryLanguagesDao;

    @Inject
    public CountryLanguageServiceImpl(final CountryLanguagesDao countryLanguagesDao) {
        this.countryLanguagesDao = countryLanguagesDao;
    }

    @Override
    public ResponseDto getLanguagesByCountryIdAndIsOfficial(String countryCode, Boolean isOffcial) {
        Collection<CountryLanguageDto> countryLanguageDtos= this.countryLanguagesDao.getLanguagesByCountryIdAndIsOfficial(countryCode, isOffcial);
        return new ResponseDto( countryLanguageDtos.size(), countryLanguageDtos);
    }

    @Override
    public ResponseDto getLanguagesByCountryId(String countryCode) {
        Collection<CountryLanguageDto> countryLanguageDtos= this.countryLanguagesDao.getLanguagesByCountryId(countryCode);
        return new ResponseDto( countryLanguageDtos.size(), countryLanguageDtos);
    }
}