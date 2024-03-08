package org.acme.countries.dao.repos;

import org.acme.countries.entities.CountryLanguage;
import org.acme.countries.entities.CountryLanguagePk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguagesRepository extends JpaRepository<CountryLanguage, CountryLanguagePk> {
}
