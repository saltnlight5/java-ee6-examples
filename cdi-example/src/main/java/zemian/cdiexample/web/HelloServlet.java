/*
 *  Zemian Deng 2014
 */

package zemian.cdiexample.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import zemian.cdiexample.service.HelloService;
import zemian.cdiexample.service.Service;

/**
 *
 * @author zedeng
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Inject
    private HelloService helloService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = helloService.getMessage();
        
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<body>");
        writer.println("<p>" + message + "</p>");
        writer.println("</body>");
        writer.println("</html>");
    }
   
}
