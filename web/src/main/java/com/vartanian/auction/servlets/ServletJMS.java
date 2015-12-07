package com.vartanian.auction.servlets;

import com.vartanian.auction.model.FacadeLocal;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.*;
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
@WebServlet(name = "ServletJMS", urlPatterns = "/jms")
public class ServletJMS extends HttpServlet {

    @EJB
    private FacadeLocal facadeLocal;

    @Resource(mappedName = "jms/__defaultConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/testQueue")
    private Queue queue;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("<html>");
        writer.print("<hr/>");

//        Person person = new Person();
//        person.setName("Igor1");
//        person.setInfo("Info1");
        facadeLocal.updatePerson(1L, "Igor8", "name8");
        writer.print("</hr>" + connectionFactory);
        writer.print("</hr>" + queue);

        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(queue);
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("Change entity");
            producer.send(textMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            try {
                producer.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            try {
                session.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

        writer.print("</html>");
        writer.close();
    }
}
