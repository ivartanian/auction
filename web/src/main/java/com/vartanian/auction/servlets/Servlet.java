package com.vartanian.auction.servlets;

import com.vartanian.auction.model.BeanWithoutInterface;
import com.vartanian.auction.model.FacadeLocal;
import com.vartanian.auction.model.StateFull;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.inject.Inject;
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
@WebServlet(name = "Servlet", urlPatterns = "/view")
public class Servlet extends HttpServlet {

    @EJB
    private FacadeLocal facadeLocal;

    @EJB
    private BeanWithoutInterface beanWithoutInterface;

//    @Resource(mappedName = "jdbc/auction")
//    private DataSource dataSource;

    @EJB
    private StateFull stateFull;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("<html>");
        writer.print("<hr/>");

//        writer.print(facadeLocal + " info() = " + facadeLocal.info());
//        writer.print("<hr/>" + dataSource);
//        writer.print("<hr/>" + beanWithoutInterface.info());
//        writer.print("<hr/>" + beanWithoutInterface.addItem("7"));
        writer.print("<hr/>" + stateFull.info());
        writer.print("</html>");
        writer.close();
    }
}
