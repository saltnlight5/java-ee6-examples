package zemian.servlet3example.web;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import zemian.servlet3example.service.Application;
import zemian.servlet3example.service.Config;

@WebServlet("/sys-props")
public class SysPropsServlet extends HtmlWriterServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map appConfig = new TreeMap();
        Config config = Application.getInstance().getConfig();
        for (String key : config.getKeys()) {
            appConfig.put(key, config.getValue(key));
        }
        
        Map sysProps = new TreeMap(System.getProperties());
        
        HtmlWriter html = createHtmlWriter(req, resp);
        html.header()
            .h(1, "Application Config Properties")
            .table(appConfig)
            .h(1, "Java System Properties")
            .table(sysProps)
            .footer();
    }
}
