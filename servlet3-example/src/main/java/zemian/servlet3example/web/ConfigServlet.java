package zemian.servlet3example.web;

import zemian.servlet3example.service.Application;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/config")
public class ConfigServlet extends HtmlWriterServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HtmlWriter html = createHtmlWriter(req, resp);
        Properties props = getConfigProperties();
        TreeMap sysProps = new TreeMap(props);
        html.header()
            .h(1, "Application Config Properties")
            .table(sysProps)
            .footer();
    }

    private Properties getConfigProperties() throws IOException {
        Application app = Application.getInstance();
        Properties props = new Properties();
        try(Reader reader = new FileReader(app.getConfigFile())) {
            props.load(reader);
        }
        return props;
    }
}
