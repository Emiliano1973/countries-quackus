package org.acme.countries.dao.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.acme.countries.dao.CountryLanguagesDao;
import org.acme.countries.dtos.CountryLanguageDto;
import org.acme.countries.entities.Country;
import org.acme.countries.entities.CountryLanguage;
import org.acme.countries.entities.CountryLanguagePk_;
import org.acme.countries.entities.Country_;

import java.util.Collection;

import static org.acme.countries.entities.CountryLanguagePk_.*;
import static org.acme.countries.entities.CountryLanguage_.*;

@ApplicationScoped
public class CountryLanguagesDaoImpl implements CountryLanguagesDao {

    @PersistenceContext
   private EntityManager em;

    @Override
    public Collection<CountryLanguageDto> getLanguagesByCountryIdAndIsOfficial(final String countryCode, final Boolean isOffcial) {
        CriteriaBuilder cb=this.em.getCriteriaBuilder();
        CriteriaQuery<CountryLanguageDto> criteriaQuery=cb.createQuery(CountryLanguageDto.class);
        Root<CountryLanguage> countryLanguageRoot=criteriaQuery.from(CountryLanguage.class);
        Join<CountryLanguage, Country> languageCountryJoin=countryLanguageRoot.join(country, JoinType.INNER);
        criteriaQuery.multiselect(languageCountryJoin.get(Country_.name),
                        countryLanguageRoot.get(countryLanguagePk)
                        .get(language),
                countryLanguageRoot.get(percentage), countryLanguageRoot.get(officialLanguage))
                .where(cb.equal(countryLanguageRoot.get(countryLanguagePk)
                .get(CountryLanguagePk_.countryCode), countryCode),
                        cb.equal(countryLanguageRoot.get(officialLanguage), isOffcial));
        return this.em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Collection<CountryLanguageDto> getLanguagesByCountryId(final String countryCode) {
        CriteriaBuilder cb=this.em.getCriteriaBuilder();
        CriteriaQuery<CountryLanguageDto> criteriaQuery=cb.createQuery(CountryLanguageDto.class);
        Root<CountryLanguage> countryLanguageRoot=criteriaQuery.from(CountryLanguage.class);
        Join<CountryLanguage, Country> languageCountryJoin=countryLanguageRoot.join(country, JoinType.INNER);
        criteriaQuery.multiselect(languageCountryJoin.get(Country_.name), countryLanguageRoot.get(countryLanguagePk)
                        .get(language),
                countryLanguageRoot.get(percentage), countryLanguageRoot.get(officialLanguage))
                .where(cb.equal(countryLanguageRoot.get(countryLanguagePk)
                        .get(CountryLanguagePk_.countryCode), countryCode));
        return this.em.createQuery(criteriaQuery).getResultList();
    }
}
