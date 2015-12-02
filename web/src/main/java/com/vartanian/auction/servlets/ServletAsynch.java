package com.vartanian.auction.servlets;

import com.vartanian.auction.model.FacadeLocal;
import com.vartanian.auction.model.Person;

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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by super on 12/1/15.
 */
@WebServlet(name = "ServletAsynch", urlPatterns = "/asynch")
public class ServletAsynch extends HttpServlet {

    @EJB
    private FacadeLocal facadeLocal;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("<html>");
        writer.print("<hr/>");

//        facadeLocal.invokeAsynch();
        Future<Person> personFuture = facadeLocal.invokeAsynchWithFuture();
        Person person = null;
        try {
            person = personFuture.get(6, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("------------------- end servlet + person = " + person);
        writer.print(person);
        writer.print("</html>");
        writer.close();
    }
}
