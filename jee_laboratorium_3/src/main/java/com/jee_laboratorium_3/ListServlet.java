package com.jee_laboratorium_3;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "ListServlet", value = "/ListServlet")
public class ListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?serverTimezone=UTC", "root", "");
            Statement st = conn.createStatement();
            String query="SELECT * FROM country WHERE Continent = 'Europe'";
            ResultSet rs = st.executeQuery(query);

            HttpSession session=request.getSession(true);
            CountryBean country;
            ArrayList<CountryBean> list = new ArrayList<CountryBean>();

            while (rs.next()) {
                country = new CountryBean();
                country.setName(rs.getString("name"));
                country.setCode(rs.getString("code"));
                country.setPopulation(rs.getLong("population"));
                list.add(country);
            }
            session.setAttribute("list", list);
            response.sendRedirect("countryList.jsp");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
