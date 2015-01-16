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
            HtmlWriter writer = new HtmlWriter();
            writer.setWriter(resp.getWriter());
            writer.setContextPath(req.getContextPath());
            return writer;
        } catch (IOException e) {
            throw new RuntimeException("Failed to get response Writer.", e);
        }
    }
    
    protected LoginSession getOptionalSessionData(HttpServletRequest req) {
        LoginSession result = null;
        HttpSession session = req.getSession(false);
        if (session != null)
            result = (LoginSession)session.getAttribute(LoginSession.LOGIN_SESSION_KEY);
        return result;
    }
}
