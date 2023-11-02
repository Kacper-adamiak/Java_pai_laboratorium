package lab.jee_laboratorium_4.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lab.jee_laboratorium_4.beans.Pracownik;
import lab.jee_laboratorium_4.dao.PracownikDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PracownikController {
    @Autowired
    PracownikDao dao; //wstrzyknięcie dao z pliku XML

    /* Wynikiem działania metody jest przekazanie danych w modelu do
     * strony widoku addForm.jsp, która wyświetla formularz
     * wprowadzania danych, a „command” jest zastrzeżonym atrybutem
     * żądania, umożliwiającym wyświetlenie danych obiektu pracownika
     * w formularzu.
     */
    @RequestMapping("/addForm")
    public String showform(Model m){
        m.addAttribute("command", new Pracownik());
        return "addForm"; //przekiekrowanie do addForm.jsp
    }
    /* Metoda obsługuje zapis pracownika do BD. @ModelAttribute
     * umozliwia pobranie danych z żądania do obiektu pracownika.
     * Jawnie wskazano RequestMethod.POST (domyślnie jest to GET)
     */
    @RequestMapping(value="/save",method =
            RequestMethod.POST)
    public String save(@ModelAttribute("pr") Pracownik pr){
        dao.save(pr);
        return "redirect:/viewAll";
        //przekierowanie do endpointa o URL: /viewAll
    }
    /* Metoda pobiera listę pracowników z BD i umieszcza je w modelu */
    @RequestMapping("/viewAll")
    public String viewAll(Model m){
        List<Pracownik> list=dao.getAll();
        m.addAttribute("list",list);
        return "viewAll"; //przejście do widoku viewAll.jsp
    }

    @RequestMapping("/delete/{id}")
    public String showform(Model m, @PathVariable int id){
        dao.delete(id);
        return "redirect:/viewAll";
    }

    @RequestMapping("/edit/{id}")
    public String editForm(Model m, @PathVariable int id){
        Pracownik tempP = dao.getPracownikById(id);
        m.addAttribute("command", tempP);
        return "editForm";
    }

    @RequestMapping(value="/edit/save",method = RequestMethod.POST)
    public String editsave(@ModelAttribute("pr") Pracownik pr){
        dao.update(pr);
        return "redirect:/viewAll";
        //przekierowanie do endpointa o URL: /viewAll
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req,
                                    Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}