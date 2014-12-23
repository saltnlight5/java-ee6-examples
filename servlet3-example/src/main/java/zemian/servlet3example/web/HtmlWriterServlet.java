package zemian.servlet3example.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletConfig;
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
    
    abstract protected void writeContent(HtmlWriter html);
    
    protected String link(String label, String url) {
        String contextPath = getServletContext().getContextPath();
        if (!url.startsWith("/"))
            contextPath += "/";
        return "<a href=\"" + contextPath + url + "\">" + label + "</a>";
    }
    
    public static class HtmlWriter {
        private PrintWriter writer;
        
        public HtmlWriter(PrintWriter writer) {
            this.writer = writer;
        }
        
        public HtmlWriter header() {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<header>");
            writer.println("</header>");
            writer.println("<body>");
            return this;
        }
        
        public HtmlWriter footer() {
            writer.println("<body>");
            writer.println("</html>");
            return this;
        }
        
        public HtmlWriter p(String text) {
            writer.println("<p>" + "</p>");
            return this;
        }
        
        public HtmlWriter h(int level, String text) {
            writer.println("<h" + level + ">" + text + "</h" + level + ">");
            return this;
        }
                
        public HtmlWriter ul(String ... texts) {
            return ul(Arrays.asList(texts));
        }
        
        public HtmlWriter ul(List<String> texts) {
            writer.println("<ul>");
            for (String text : texts) {
                writer.println("<li>" + text + "</li>");
            }
            writer.println("</ul>");
            return this;
        }
        
        public HtmlWriter table(Map<String, Object> map) {
            writer.println("<table>");
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                writer.println("<tr><td>" + entry.getKey() + "</td>");
                writer.println("<td>");
                Object value = entry.getValue();
                if (value instanceof List) {
                    ul((List)value);
                } else if (value instanceof Map) {
                    table((Map)value);
                } else {
                    writer.println(value.toString());
                }
                writer.println("</td>");
                writer.println("</tr>");
            }
            writer.println("</table>");
            return this;
        }
    }
}
