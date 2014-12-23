package zemian.servlet3example.web;

import java.util.TreeMap;
import javax.servlet.annotation.WebServlet;

@WebServlet("/sys-props")
public class SysPropsServlet extends HtmlWriterServlet {

    @Override
    protected void writeContent(HtmlWriter html) {
        TreeMap sysProps = new TreeMap(System.getProperties());
        html.header()
            .h(1, "Java System Properties")
            .table(sysProps)
            .footer();
    }
}
