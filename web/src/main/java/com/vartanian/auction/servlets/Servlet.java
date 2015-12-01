package com.vartanian.auction.servlets;

import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by super on 12/1/15.
 */
@WebServlet(name = "Servlet", urlPatterns = "/view")
public class Servlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("<html>");
        writer.print("<hr/>");

        InitialContext initialContext;
        try {
            initialContext = new InitialContext();
            NamingEnumeration<NameClassPair> list = initialContext.list("");
            while (list.hasMore()){
                writer.print(list.next());
            }
        } catch (NamingException e) {
            writer.print(e);
        }
        writer.print("</html>");
        writer.close();
    }
}
