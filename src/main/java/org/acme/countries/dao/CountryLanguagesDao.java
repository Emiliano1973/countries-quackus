package org.acme.countries.dao;


import org.acme.countries.dtos.CountryLanguageDto;

import java.util.Collection;

public interface CountryLanguagesDao {

    Collection<CountryLanguageDto> getLanguagesByCountryIdAndIsOfficial(String countryCode, Boolean isOffcial);

    Collection<CountryLanguageDto> getLanguagesByCountryId(String countryCode);

}
