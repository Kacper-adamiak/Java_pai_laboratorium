package com.example.jee_laboratorium_1;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "CalcServlet", value = "/CalcServlet")
public class CalcServlet extends HttpServlet {

    private Double oblicz(Double val1, Double val2, String opr){
        switch (opr) {
            case "+":
                return val1 + val2;
            case "-":
                return val1 - val2;
            case "/":
                return val1 / val2;
            case "*":
                return val1 * val2;

        }
        return (double) 0;
    }

    private ArrayList<String> getHistory(HttpSession session){

        ArrayList<String> history = (ArrayList<String>) session.getAttribute("history");

        if (history == null) {
            history = new ArrayList<String>();
            session.setAttribute("history", history);
        }

        return history;
    }

    private void printHistory(ArrayList<String> history, PrintWriter out){
        out.println("<h2>Historia</h2>");
        history.forEach(aDouble -> {
            out.println("<p>" + aDouble + "</p>");
        });
    }

    private void printHello(Boolean isUser, PrintWriter out){
        if(isUser){
            out.println("<p>Witaj po raz kolejny</p>");
        }else {
            out.println("<p>Witaj po raz pierwszy</p>");
        }
    }

    private Boolean isCookie(Cookie[] cookies, String nazwaCookie){
        if ( cookies != null )
        {
            for (int i=0; i<cookies.length; i++) {
                Cookie c=cookies[i];
                if (nazwaCookie.equals(c.getName()))
                    return true;
            }
        }
        return false;
    }

    private Boolean isRequestValid(String val1, String val2, String opr, PrintWriter out){
        Boolean isError = false;
        if (val1 == null || val1.trim().equals("")) {
            out.println("<p>Nieprawidlowa wartosc pierwszego parametru</p>");
            isError = true;
        }
        if (val2 == null || val2.trim().equals("")) {
            out.println("<p>Nieprawidlowa wartosc drugiego parametru</p>");
            isError = true;
        }
        if (opr.equals("/")  && Double.parseDouble(val2) == 0) {
            out.println("<p>Nie mozna dzielic przez 0</p>");
            isError = true;
        }
        return isError;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String clear = request.getParameter("clear");
        out.println("<html><body>");
        if(clear != null){
            out.println("Wyczyszczono historie!");
            out.println("<br>");
            out.println("<a href='http://localhost:8080/jee_laboratorium_1_war_exploded/calc.html'> powrot do formularza</a>");
        }
        out.println("</body></html>");
        HttpSession session = request.getSession(true);
        ArrayList<String> history = new ArrayList<String>();
        session.setAttribute("history", history);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String val1 = request.getParameter("val1");
        String val2 = request.getParameter("val2");
        String opr = request.getParameter("opr");
        Boolean isError = false;
        HttpSession session = request.getSession(true);
        ArrayList<String> history = getHistory(session);

        String nazwaCookie = "UserId";
        Cookie [ ] cookies = request.getCookies();
        Boolean isUser = isCookie(cookies, nazwaCookie);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        printHello(isUser, out);

        printHistory(history, out);

        out.println("<h2> Wynik obliczen </h2>");
        if(!isRequestValid(val1, val2, opr, out)) {
            Double result = oblicz(Double.parseDouble(val1), Double.parseDouble(val2), opr);

            out.println(val1 + opr + val2 + "=" + result);

            history.add(val1 + opr + val2 + "=" + result);
            session.setAttribute("history", history);
        }
        Cookie uiColorCookie = new Cookie("UserId", "red");
        response.addCookie(uiColorCookie);

        out.println("<br>");
        out.println("<a href='http://localhost:8080/jee_laboratorium_1_war_exploded/calc.html'> powrot do formularza</a>");
        out.println("<a href='http://localhost:8080/jee_laboratorium_1_war_exploded/CalcServlet?clear=true'> wyczysc historie</a>");

        out.println("</body></html>");
    }
}
