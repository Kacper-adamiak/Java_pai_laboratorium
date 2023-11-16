package com.example.jee_laboratorium_5b.controllers;

import com.example.jee_laboratorium_5b.models.Country;
import com.example.jee_laboratorium_5b.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Comparator;
import java.util.List;

@Controller
public class CountryController {

    @Autowired
    public CountryRepository countryRepository;

    public String buildCountryTable(List<Country> countries) {
        StringBuilder odp = new StringBuilder();

        odp.append("<table>\n" +
                "  <tr>\n" +
                "    <th>Code</th>\n" +
                "    <th>Name</th>\n" +
                "    <th>Continent</th>\n" +
                "    <th>Population</th>\n" +
                "  </tr>");
        for(Country i: countries
        ) {
            odp.append( "  <tr>\n" +
                    "    <td>"+ i.getCode() +"</td>\n" +
                    "    <td>"+ i.getName() +"</td>\n" +
                    "    <td>"+ i.getContinent() +"</td>\n" +
                    "    <td>"+ i.getPopulation() +"</td>\n" +
                    "  </tr>");
        }
        odp.append("</table>\n");
        return odp.toString();
    }

    @RequestMapping("/countries")
    @ResponseBody
    public String countries() {

        return buildCountryTable(countryRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Country::getName))
                .toList()
        );
    }

    @RequestMapping("/countries/{continent}")
    @ResponseBody
    public String countriesByContinent(@PathVariable("continent") String continent) {

        return buildCountryTable(countryRepository.findByContinent(continent)
                .stream()
                .sorted(Comparator.comparing(Country::getName))
                .toList()
        );
    }

    @RequestMapping("/countries/{population}/{population2}")
    @ResponseBody
    public String countriesByPopulation(@PathVariable("population") Integer population, @PathVariable("population2") Integer population2) {

        return buildCountryTable(
                countryRepository.findByPopulationBetween(population, population2)
                .stream()
                .sorted(Comparator.comparing(Country::getPopulation))
                .toList()
        );
    }

    @RequestMapping("/countries/{continent}/{population}/{population2}")
    @ResponseBody
    public String countriesByContinentAndPopulation(@PathVariable("continent") String continent, @PathVariable("population") Integer population, @PathVariable("population2") Integer population2) {

        return buildCountryTable(
                countryRepository.findByContinentAndPopulationBetween(continent, population, population2)
                        .stream()
                        .sorted(Comparator.comparing(Country::getPopulation))
                        .toList()
        );
    }


}
