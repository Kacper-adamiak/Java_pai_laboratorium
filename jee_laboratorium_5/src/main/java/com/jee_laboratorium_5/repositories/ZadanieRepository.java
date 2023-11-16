package com.jee_laboratorium_5.repositories;

import com.jee_laboratorium_5.entities.Zadanie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ZadanieRepository extends CrudRepository<Zadanie, Long>{
    List<Zadanie> findByWykonane(Boolean wykonane);
    List<Zadanie> findByKosztLessThan(Double koszt);
    List<Zadanie> findByKosztBetween(Double koszt, Double koszt2);
}
