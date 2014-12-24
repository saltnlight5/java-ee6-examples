package zemian.jpaexample.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zemian.jpaexample.service.UserService;
import zemian.service.logging.Logger;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static final Logger LOGGER = new Logger(UserServlet.class);

    private static final long serialVersionUID = 1L;

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        LOGGER.info("<h1>Most active users</h1>");
        out.println("<pre>");
        List<?> resultList = userService.findMostActiveUsers();
        for (Object obj : resultList) {
            out.println("  resultList object=" + obj);
        }
        out.println("</pre>");
        out.println("Server Time: " + new Date());
        out.flush();
    }
}
