/*
 *  Zemian Deng 2014
 */

package zemian.servlet3example.web;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import zemian.service.util.Utils;

/**
 *
 * @author zedeng
 */
public class HtmlWriter {
    private HttpServletRequest req;
    private PrintWriter writer;

    public HtmlWriter(HttpServletRequest req, PrintWriter writer) {
        this.req = req;
        this.writer = writer;
    }
    
    public HttpServletRequest getReq() {
        return req;
    }

    public HtmlWriter out(String text) {
        writer.println(text);
        return this;
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
        writer.println("<p>" + text + "</p>");
        return this;
    }

    public HtmlWriter h(int level, String text) {
        writer.println("<h" + level + ">" + text + "</h" + level + ">");
        return this;
    }

    public HtmlWriter ul(String... texts) {
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

    public HtmlWriter table(Map<?, ?> map) {
        writer.println("<table>");
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            String key = Utils.toString(entry.getKey());
            Object value = entry.getValue();            
            writer.println("<tr><td>" + key + "</td>");
            writer.println("<td>");
            if (value instanceof List) {
                ul((List) value);
            } else if (value instanceof Map) {
                table((Map) value);
            } else {
                writer.println(Utils.toString(value));
            }
            writer.println("</td>");
            writer.println("</tr>");
        }
        writer.println("</table>");
        return this;
    }
    
    public String link(String label, String url) {
        String contextPath = req.getContextPath();
        return "<a href=\"" + contextPath + url + "\">" + label + "</a>";
    }
}
