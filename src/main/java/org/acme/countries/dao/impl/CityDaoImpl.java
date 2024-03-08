package org.acme.countries.dao.impl;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.acme.countries.dao.CityDao;
import org.acme.countries.dtos.CityDto;
import org.acme.countries.dtos.PaginationDto;
import org.acme.countries.dtos.PaginatorDtoBuilder;
import org.acme.countries.entities.City;
import org.acme.countries.entities.Country;
import org.acme.countries.entities.Country_;

import java.util.ArrayList;
import java.util.Collection;

import static org.acme.countries.entities.City_.*;


@ApplicationScoped
public class CityDaoImpl implements CityDao {
    @PersistenceContext
   private EntityManager em;

    @Override
    public Collection<CityDto> findByCountryCode(final String countryCode) {
        CriteriaBuilder cb=this.em.getCriteriaBuilder();
        CriteriaQuery<CityDto> cityDtoCriteriaQuery=cb.createQuery(CityDto.class);
        Root<City> cityRoot=cityDtoCriteriaQuery.from(City.class);
        Join<City, Country> cityCountryJoin=cityRoot.join(country, JoinType.INNER);
        setCityFields(cb, cityDtoCriteriaQuery, cityRoot, cityCountryJoin, countryCode);
        return this.em.createQuery(cityDtoCriteriaQuery).getResultList();
    }

    @Override
    public PaginationDto findByCountryCodeByPage(final String countryCode, final int page, final int pageSize) {
        int counts = countByCountryCode(countryCode);
        if (counts == 0) {
            return new PaginatorDtoBuilder().setCurrentPage(page).setTotalPages(0)
                    .setPageSize(pageSize).setTotalElements(counts)
                    .setElements(new ArrayList<>()).createPaginatorDto();
        }
        CriteriaBuilder cb=this.em.getCriteriaBuilder();
        CriteriaQuery<CityDto> cityDtoCriteriaQuery=cb.createQuery(CityDto.class);
        Root<City> cityRoot=cityDtoCriteriaQuery.from(City.class);
        Join<City, Country> cityCountryJoin=cityRoot.join(country, JoinType.INNER);
        setCityFields(cb, cityDtoCriteriaQuery, cityRoot, cityCountryJoin, countryCode);
        TypedQuery<CityDto> query=this.em.createQuery(cityDtoCriteriaQuery);
        query.setMaxResults(pageSize);
        query.setFirstResult((page - 1) * pageSize);
        Collection<CityDto> cityDtos = query.getResultList();
        int numPages = (counts / pageSize);
        if ((counts % pageSize) > 0) {
            numPages += 1;
        }
        return new PaginatorDtoBuilder().setCurrentPage(page).setTotalPages(numPages).setPageSize(pageSize).setTotalElements(counts).setElements(cityDtos).createPaginatorDto();
    }


    private  void setCityFields(final CriteriaBuilder cb,final CriteriaQuery<CityDto>  cityDtoCriteriaQuery,
                              final Root<City> cityRoot,final Join<City, Country> cityCountryJoin
            ,final String countryCode){
        cityDtoCriteriaQuery.
                multiselect(cityRoot.get(name), cityRoot.get(district),
                        cityRoot.get(population), cityCountryJoin.get(Country_.name))
                .where(cb.equal(cityCountryJoin.get(Country_.countryCode), countryCode))
                .groupBy( cityCountryJoin.get(Country_.name) ,cityRoot.get(district),
                        cityRoot.get(name), cityRoot.get(population))
                .orderBy(cb.desc(cityCountryJoin.get(Country_.name)),
                        cb.asc(cityRoot.get(district)),
                        cb.asc(cityRoot.get(name))) ;
    }

    private int countByCountryCode(final String countryCode){
        CriteriaBuilder cb=this.em.getCriteriaBuilder();
        CriteriaQuery<Long> cityDtoCriteriaQuery=cb.createQuery(Long.class);
        Root<City> cityRoot=cityDtoCriteriaQuery.from(City.class);
        Join<City, Country> cityCountryJoin=cityRoot.join(country, JoinType.INNER);
        cityDtoCriteriaQuery.select(cb.count(cityRoot.get(id)))
                .where(cb.equal(cityCountryJoin.get(Country_.countryCode), countryCode));
        return this.em.createQuery(cityDtoCriteriaQuery).getSingleResult().intValue();
    }
}
