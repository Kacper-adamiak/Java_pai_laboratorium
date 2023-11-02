package com.jee_laboratorium_3;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet(name = "DetailsServlet", value = "/DetailsServlet")
public class DetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String code = request.getParameter("code");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?serverTimezone=UTC", "root", "");
            Statement st = conn.createStatement();
            String query="SELECT * FROM country WHERE code = '" + code + "'";
            ResultSet rs = st.executeQuery(query);

            HttpSession session=request.getSession(true);
            CountryBean country = new CountryBean();
            while (rs.next()) {
                country = new CountryBean();
                country.setName(rs.getString("name"));
                country.setCode(rs.getString("code"));
                country.setPopulation(rs.getLong("population"));
                country.setSurfaceArea(rs.getDouble("surfacearea"));
            }

            session.setAttribute("details", country);
            response.sendRedirect("details.jsp");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
