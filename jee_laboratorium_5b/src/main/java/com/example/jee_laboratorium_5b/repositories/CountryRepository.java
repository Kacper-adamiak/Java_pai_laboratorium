package com.example.jee_laboratorium_5b.repository;

import com.example.jee_laboratorium_5b.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, String> {
    List<Country> findByContinentIgnoreCase(String continent);
    List<Country> findByPopulationBetween(Integer populationStart, Integer populationEnd);
    List<Country> findByContinentIgnoreCaseAndPopulationBetween(String continent, Integer populationStart, Integer populationEnd);

}