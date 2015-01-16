package zemian.servlet3example.web;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class HtmlWriterServlet extends HttpServlet {

    protected static final long serialVersionUID = 1L;
    
    protected HtmlWriter createHtmlWriter(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // Create a HtmlWriter that's customized for this application, such
            // as header and footer.
            HtmlWriter writer = new HtmlWriter();
            writer.setWriter(resp.getWriter());
            writer.setContextPath(req.getContextPath());
            writer.setHeader(header(writer));
            writer.setFooter(footer(writer));
            return writer;
        } catch (IOException e) {
            throw new RuntimeException("Failed to create HtmlWriter.", e);
        }
    }
    
    /** Return LoginSession if found in HttpSession scope, else return NULL value. */
    protected LoginSession getOptionalLoginSession(HttpServletRequest req) {
        LoginSession result = null;
        HttpSession session = req.getSession(false);
        if (session != null)
            result = (LoginSession)session.getAttribute(LoginSession.LOGIN_SESSION_KEY);
        return result;
    }
    
    protected String header(HtmlWriter html) {
        String contextPath = getServletContext().getContextPath();
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html>");
        sb.append("<header>");
        sb.append("  <link rel='stylesheet' type='text/css' href='" + contextPath + "/main.css'>");
        sb.append("</header>");
        sb.append("<body>");
        sb.append("<ul class='navlist'>");
        sb.append("<li>" + html.link("Home", "/index") + "</li>");
        sb.append("</ul>");
        return sb.toString();
    }

    protected String footer(HtmlWriter html) {
        StringBuilder sb = new StringBuilder();
        sb.append("<body>");
        sb.append("</html>");
        return sb.toString();
    }
}
