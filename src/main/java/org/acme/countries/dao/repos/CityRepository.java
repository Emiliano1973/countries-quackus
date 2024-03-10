package org.acme.countries.dao.repos;

import org.acme.countries.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
