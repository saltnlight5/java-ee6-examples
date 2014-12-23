package zemian.servlet3example.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;

@WebServlet("/index")
public class IndexServlet extends HtmlWriterServlet {

    @Override
    protected void writeContent(HtmlWriter html) {
        html.header()
            .h(1, "Welcome to Servlet 3 Example")
            .p("Let's explore Java Servlet 3.x Features.")
            .ul(tableOfContentList())
            .footer();
    }

    private List<String> tableOfContentList() {
        List<String> list = new ArrayList<>();
        list.add(link("Index", "/index"));
        list.add(link("View JSP Dispatcher", "/view-jsp"));
        return list;
    }

}
