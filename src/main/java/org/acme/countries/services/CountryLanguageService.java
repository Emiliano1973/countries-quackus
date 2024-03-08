package org.acme.countries.services;

import org.acme.countries.dtos.ResponseDto;

public interface CountryLanguageService {
    ResponseDto getLanguagesByCountryIdAndIsOfficial(String countryCode, Boolean isOffcial);

    ResponseDto getLanguagesByCountryId(String countryCode);

}