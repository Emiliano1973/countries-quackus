package org.acme.countries.dao.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.SingularAttribute;
import org.acme.countries.dao.CountryDao;
import org.acme.countries.dtos.CountryDto;
import org.acme.countries.dtos.PaginationDto;
import org.acme.countries.dtos.PaginatorDtoBuilder;
import org.acme.countries.entities.City;
import org.acme.countries.entities.City_;
import org.acme.countries.entities.Country;
import org.acme.countries.entities.Country_;
import org.acme.countries.utils.Continents;
import org.acme.countries.utils.Regions;

import java.util.ArrayList;
import java.util.Collection;

import static org.acme.countries.entities.Country_.capital;
import static org.acme.countries.entities.Country_.continent;
import static org.acme.countries.entities.Country_.countryCode;
import static org.acme.countries.entities.Country_.countryCode2;
import static org.acme.countries.entities.Country_.gnp;
import static org.acme.countries.entities.Country_.gnpOld;
import static org.acme.countries.entities.Country_.governmentForm;
import static org.acme.countries.entities.Country_.headOfState;
import static org.acme.countries.entities.Country_.indepYear;
import static org.acme.countries.entities.Country_.lifeExpectancy;
import static org.acme.countries.entities.Country_.localName;
import static org.acme.countries.entities.Country_.name;
import static org.acme.countries.entities.Country_.population;
import static org.acme.countries.entities.Country_.region;
import static org.acme.countries.entities.Country_.surfaceArea;


@ApplicationScoped
public class CountryDaoImpl implements CountryDao {
    @PersistenceContext
   private EntityManager em;

    @Override
    public Collection<CountryDto> findAll() {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(capital, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        return this.em.createQuery(countryDtoCriteriaQuery).getResultList();
    }

    @Override
    public PaginationDto findByPage(final int page, final int pageSize) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        int counts = countAll(cb);
        if (counts == 0) {
            return new PaginatorDtoBuilder().setCurrentPage(page).setPageTotalElements(0)
                    .setPageTotalElements(0).setPageSize(pageSize)
                    .setTotalElements(counts).setElements(new ArrayList<>()).createPaginatorDto();
        }
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(capital, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        TypedQuery<CountryDto> query = this.em.createQuery(countryDtoCriteriaQuery);
        query.setMaxResults(pageSize);
        query.setFirstResult((page - 1) * pageSize);
        Collection<CountryDto> countryDtos = query.getResultList();
        int numPages = (counts / pageSize);
        if ((counts % pageSize) > 0) {
            numPages += 1;
        }
        return new PaginatorDtoBuilder().setCurrentPage(page).setTotalPages(numPages)
                .setPageTotalElements(countryDtos.size()).setPageSize(pageSize)
                .setTotalElements(counts).setElements(countryDtos).createPaginatorDto();
    }

    @Override
    public Collection<CountryDto> findByContinent(final Continents continent) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(capital, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        countryDtoCriteriaQuery
                .where(cb.equal(cb.upper(countryRoot.get(Country_.continent)),
                        continent.getContinentName().toUpperCase()));
        return this.em.createQuery(countryDtoCriteriaQuery).getResultList();
    }

    @Override
    public PaginationDto findByContinentByPage(final Continents continent, final int page,
                                               final int pageSize) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        int counts = countByFieldName(cb,Country_.continent, continent.getContinentName());
        if (counts == 0) {
            return new PaginatorDtoBuilder().setCurrentPage(page).setPageTotalElements(0).setTotalPages(0).setPageSize(pageSize)
                    .setTotalElements(counts).setElements(new ArrayList<>()).createPaginatorDto();
        }
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(capital, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        countryDtoCriteriaQuery
                .where(cb.equal(countryRoot.get(Country_.continent), continent.getContinentName()));
        TypedQuery<CountryDto> query = this.em.createQuery(countryDtoCriteriaQuery);
        query.setMaxResults(pageSize);
        query.setFirstResult((page - 1) * pageSize);
        Collection<CountryDto> countryDtos = query.getResultList();
        int numPages = (counts / pageSize);
        if ((counts % pageSize) > 0) {
            numPages += 1;
        }
        return new PaginatorDtoBuilder().setCurrentPage(page).setTotalPages(numPages)
                .setPageTotalElements(countryDtos.size()).setPageSize(pageSize)
                .setTotalElements(counts).setElements(countryDtos).createPaginatorDto();
    }

    @Override
    public Collection<CountryDto> findByRegion(final Regions region) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(capital, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        countryDtoCriteriaQuery
                .where(cb.equal(countryRoot.get(Country_.region), region.getRegionName()));
        return this.em.createQuery(countryDtoCriteriaQuery).getResultList();


    }

    @Override
    public PaginationDto findByRegionByPage(final Regions region, final int page, final int pageSize) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        int counts = countByFieldName(cb, Country_.region, region.getRegionName());
        if (counts == 0) {
            return new PaginatorDtoBuilder().setCurrentPage(page).setPageTotalElements(0).setTotalPages(0).setPageSize(pageSize)
                    .setTotalElements(counts).setElements(new ArrayList<>()).createPaginatorDto();
        }
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(capital, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        countryDtoCriteriaQuery
                .where(cb.equal(countryRoot.get(Country_.region), region.getRegionName()));
        TypedQuery<CountryDto> query = this.em.createQuery(countryDtoCriteriaQuery);
        query.setMaxResults(pageSize);
        query.setFirstResult((page - 1) * pageSize);
        Collection<CountryDto> countryDtos = query.getResultList();
        int numPages = (counts / pageSize);
        if ((counts % pageSize) > 0) {
            numPages += 1;
        }
        return new PaginatorDtoBuilder().setCurrentPage(page).setTotalPages(numPages)
                .setPageSize(pageSize).setTotalElements(counts).setElements(countryDtos)
                .createPaginatorDto();
    }

    @Override
    public Collection<CountryDto> findByPopulation(final Integer population) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(capital, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        countryDtoCriteriaQuery
                .where(cb.lessThanOrEqualTo(countryRoot.get(Country_.population), population));
        return this.em.createQuery(countryDtoCriteriaQuery).getResultList();
    }

    @Override
    public Collection<CountryDto> findByIndep(final Boolean isIndep) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(capital, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        if(isIndep.booleanValue()) {
            countryDtoCriteriaQuery
                    .where(cb.isNotNull(countryRoot.get(indepYear)));
        }else {
            countryDtoCriteriaQuery
                    .where(cb.isNull(countryRoot.get(indepYear)));
        }
        return this.em.createQuery(countryDtoCriteriaQuery).getResultList();
    }

    private int countAll(final CriteriaBuilder cb ) {
        CriteriaQuery<Long> countryDtoCriteriaQuery = cb.createQuery(Long.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        countryDtoCriteriaQuery.select(cb.count(countryRoot.get(countryCode)));
        return this.em.createQuery(countryDtoCriteriaQuery).getSingleResult().intValue();
    }

    private int countByFieldName(final CriteriaBuilder cb , final SingularAttribute<Country, ?> fieldName, final String value) {
        CriteriaQuery<Long> countryDtoCriteriaQuery = cb.createQuery(Long.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        countryDtoCriteriaQuery.select(cb.count(countryRoot.get(countryCode)))
                .where(cb.equal(countryRoot.get(fieldName), value));
        return this.em.createQuery(countryDtoCriteriaQuery).getSingleResult().intValue();
    }


    private void setCountryField(final CriteriaBuilder cb ,final CriteriaQuery<CountryDto> countryDtoCriteriaQuery,
                                 final Root<Country> countryRoot, final Join<Country, City> countryCityJoin){
        countryDtoCriteriaQuery.multiselect
                        (countryRoot.get(countryCode),
                                countryRoot.get(name),
                                countryRoot.get(continent),
                                countryRoot.get(region),
                                countryRoot.get(surfaceArea),
                                countryRoot.get(indepYear),
                                countryRoot.get(population),
                                countryRoot.get(lifeExpectancy),
                                countryRoot.get(gnp),
                                countryRoot.get(gnpOld),
                                countryRoot.get(localName),
                                countryRoot.get(governmentForm),
                                countryRoot.get(headOfState),
                                countryRoot.get(countryCode2),
                                countryCityJoin.get(City_.name))
                .groupBy(countryRoot.get(continent),
                        countryRoot.get(region),
                        countryRoot.get(countryCode),
                        countryRoot.get(name))
                .orderBy(cb.asc(countryRoot.get(continent)),
                        cb.asc(countryRoot.get(region)),
                        cb.asc(countryRoot.get(countryCode)));
    }

}
