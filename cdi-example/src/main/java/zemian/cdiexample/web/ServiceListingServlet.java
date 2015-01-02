/*
 *  Zemian Deng 2014
 */

package zemian.cdiexample.web;

import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import zemian.cdiexample.service.Application;
import zemian.cdiexample.service.Service;

/**
 *
 * @author zedeng
 */
@WebServlet("/service-listing")
public class ServiceListingServlet extends HttpServlet {

    @Inject
    private Application application;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Service> services = application.getServices();
        req.setAttribute("services", services);
        req.getRequestDispatcher("/service-listing.jsp").forward(req, resp);
    }
   
}
