package com.vartanian.auction.servlets;

import com.vartanian.auction.model.FacadeLocal;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by super on 12/1/15.
 */
@WebServlet(name = "ServletJobStop", urlPatterns = "/stop")
public class ServletJobStop extends HttpServlet {

    @EJB
    private FacadeLocal facadeLocal;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("<html>");
        writer.print("<hr/>");

        facadeLocal.stopJob();
        System.out.println("!!!!!!!!!!!!!! job stopped");
        writer.print("</html>");
        writer.close();
    }
}
