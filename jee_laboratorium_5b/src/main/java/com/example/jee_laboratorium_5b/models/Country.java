package com.example.jee_laboratorium_5b.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @Column(name = "Code", nullable = false, length = 3)
    private String code;

    @Column(name = "Name", nullable = false, length = 52)
    private String name;

    @Lob
    @Column(name = "Continent", nullable = false)
    private String continent;

    @Column(name = "Population", nullable = false)
    private Integer population;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return code + ", " + name + ", " +
                continent + ", " + population;
    }
}