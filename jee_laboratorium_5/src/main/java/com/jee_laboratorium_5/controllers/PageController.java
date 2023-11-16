package com.jee_laboratorium_5.controllers;

import com.jee_laboratorium_5.entities.Zadanie;
import com.jee_laboratorium_5.repositories.ZadanieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PageController {

    @Autowired
    public ZadanieRepository rep;

    @RequestMapping("/")
    @ResponseBody
    public String mainPage() {
        return "Hello Spring Boot from mainPage() method!";
    }
    @RequestMapping("/hello")
    @ResponseBody
    public String pageTwo() {
        return "Hello Spring Boot from pageTwo() method!";
    }

    @RequestMapping("/listaZadan")
    @ResponseBody
    public String listaZadan() {
        StringBuilder odp = new StringBuilder();
        Zadanie zadanie = new Zadanie();

        rep.save(zadanie);

        for(Zadanie i: rep.findAll()) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }



    @RequestMapping("/dodajZadania")
    @ResponseBody
    public RedirectView dodajZadania() {
        Zadanie z;
        double k=1000;
        boolean wyk=false;
        for (int i=1;i<=10;i++) {
            z = new Zadanie();
            z.setNazwa("zadanie " + i);
            z.setOpis("Opis czynnosci do wykonania w zadaniu " + i);
            z.setKoszt(k);
            z.setWykonane(wyk);
            wyk = !wyk;
            k += 200.50;
            rep.save(z);
        }
        return new RedirectView("/listaZadan");
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public RedirectView listaZadan(@PathVariable("id") long id) {
        rep.deleteById(id);
        return new RedirectView("/listaZadan");
    }

    @RequestMapping("/wykonane/{is}")
    @ResponseBody
    public String wykonane(@PathVariable("is") boolean is) {
        StringBuilder odp = new StringBuilder();
        for(Zadanie i: rep.findByWykonane(is)) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @RequestMapping("/kosztMniejszy/{lessThan}")
    @ResponseBody
    public String kosztMniejszyNiz(@PathVariable("lessThan") double lessThan) {
        StringBuilder odp = new StringBuilder();
        for(Zadanie i: rep.findByKosztLessThan(lessThan)) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @RequestMapping("/kosztMiedzy/{val1}/{val2}")
    @ResponseBody
    public String kosztMiedzy(@PathVariable("val1") double val1,@PathVariable("val2") double val2) {
        StringBuilder odp = new StringBuilder();
        for(Zadanie i: rep.findByKosztBetween(val1, val2)) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }
}
