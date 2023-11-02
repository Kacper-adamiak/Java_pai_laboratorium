package com.example.jee_laboratorium_1;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "HelloServlet", value = "/HelloServlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private Date data1;
    private SimpleDateFormat dataFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public void init() {
        message = "Hello World!";
        data1 = new Date();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<h2>Dane serwera</h2>");
        out.println("<p>request.getServerName(): " + request.getServerName() +
                "</p>");
        out.println("<p>request.getServerPort(): " + request.getServerPort() +
                "</p>");
        out.println("<p>request.getRemoteHost(): " + request.getRemoteHost() +
                "</p>");
        out.println("<p>request.getRemoteAddr(): " + request.getRemoteAddr() +
                "</p>");
        out.println("<h2>Szczegóły żądania</h2>");
        out.println("<p>request.getMethod(): " + request.getMethod() + " </p>");
        out.println("<p>request.getQueryString(): " + request.getQueryString() +
                "</p>");

        out.println("<p>Zadanie 1.3</p>");
        out.println("<p>data1 " + dataFormatter.format(data1) + "</p>");
        out.println("<p>data z processRequest " + new Date() + "</p>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
