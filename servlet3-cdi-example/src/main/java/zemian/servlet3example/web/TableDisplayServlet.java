package zemian.servlet3example.web;

import static zemian.service.util.Utils.list;
import static zemian.service.util.Utils.map;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/tables")
public class TableDisplayServlet  extends HtmlWriterServlet {    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<List<?>> dataList = list(
            list("Foo", "Low", 99.00),
            list("Bar", "High", 100.00),
            list("Joe", "Medium", 99.5)
        );
        List<List<?>> dataListWithHeaders = list();
        dataListWithHeaders.add(list("NAME", "DESCRIPTION", "VALUES"));
        dataListWithHeaders.addAll(dataList);
        
        Map<String, Object> dataMap = map(
                "Foo", "123", 
                "Bar", "123"
        );
        List<List<?>> emptListWithHeader = list(
            list("NAME", "DESCRIPTION", "VALUE")
        );
        List<List<?>> emptList = list(
            list("NAME", "DESCRIPTION", "VALUE")
        );
        
        HtmlWriter html = createHtmlWriter(req, resp); 
        html.header()
            .h(1, "Table With Data List")
            .table(dataListWithHeaders)
            .h(1, "Table With Data List Without RowIndex and Header")
            .table(dataList, "myDataTable", false, false)
            .h(1, "Table With Data Map")
            .table(dataMap)
            .h(1, "Empty Table With Header")
            .table(emptListWithHeader)
            .h(1, "Empty Table")
            .table(emptList)
            .footer();
    }
}
