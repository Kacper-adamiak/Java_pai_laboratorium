package com.example.jee_laboratorium_5b.repositories;

import com.example.jee_laboratorium_5b.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, String> {
    List<Country> findByContinent(String continent);
    List<Country> findByPopulationBetween(Integer populationStart, Integer populationEnd);
    List<Country> findByContinentAndPopulationBetween(String continent, Integer populationStart, Integer populationEnd);

}