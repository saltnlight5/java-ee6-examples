package zemian.servlet3example.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class HtmlWriterServlet extends HttpServlet {

    protected static final long serialVersionUID = 1L;
       
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HtmlWriter writer = new HtmlWriter(resp.getWriter());
        writeContent(writer);
    }
    
    public String getContextPath() {
        return getServletContext().getContextPath();
    }
    
    abstract protected void writeContent(HtmlWriter html);    
}
