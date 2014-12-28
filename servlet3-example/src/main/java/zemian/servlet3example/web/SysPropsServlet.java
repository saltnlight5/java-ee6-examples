package zemian.servlet3example.web;

import java.io.IOException;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sys-props")
public class SysPropsServlet extends HtmlWriterServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HtmlWriter html = createHtmlWriter(req, resp);
        TreeMap sysProps = new TreeMap(System.getProperties());
        html.header()
            .h(1, "Java System Properties")
            .table(sysProps)
            .footer();
    }
}
