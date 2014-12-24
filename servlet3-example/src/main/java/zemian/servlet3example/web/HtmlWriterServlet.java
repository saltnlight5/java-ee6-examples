package zemian.servlet3example.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class HtmlWriterServlet extends HttpServlet {

    protected static final long serialVersionUID = 1L;
       
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HtmlWriter writer = new HtmlWriter(req, resp.getWriter());
        writeContent(writer);
    }
    
    protected SessionData getOptionalSessionData(HttpServletRequest req) {
        SessionData result = null;
        HttpSession session = req.getSession(false);
        if (session != null)
            result = (SessionData)session.getAttribute(SessionData.SESSION_DATA_KEY);
        return result;
    }
    
    abstract protected void writeContent(HtmlWriter html);    
}
