package zemian.servlet3example.web;

import static zemian.service.util.Utils.list;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import zemian.servlet3example.service.Application;

@WebServlet("/thread-info")
public class ThreadInfoServlet  extends HtmlWriterServlet {    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {      
        ServletContext sc = req.getServletContext();
        List<List<?>> rows = list(
            list("NAME", "VALUE"),
            list("Request Instance", req),
            list("Session Instance", req.getSession(false)),
            list("ServletContext Instance", sc),
            list("ThreadInfoServlet Instance", this),
            list("ServletContext Path", sc.getContextPath()),
            list("Singleton Application Instance", Application.getInstance()),
            list("Current Thread", Thread.currentThread()),
            list("Current Thread ContextClassLoader", Thread.currentThread().getContextClassLoader()),
            list("ContextClassLoader Tree", "<pre>" + getClassLoaderTreeInfo("") + "</pre>")
        );
        
        HtmlWriter html = createHtmlWriter(req, resp); 
        html.header()
            .h(1, "Thread Execution Information")
            .table(rows)
            .footer();
    }

    public static String getClassLoaderTreeInfo(String indent) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        StringBuilder result = new StringBuilder();
        getClassLoaderTreeInfo(classLoader, result, indent);
        return result.toString();
    }
    
    public static void getClassLoaderTreeInfo(ClassLoader classLoader, StringBuilder sb, String indent) {
        sb.append(indent);
        sb.append(String.format("ClassLoader: %s", classLoader));
        sb.append("\n");
        ClassLoader parent = classLoader.getParent();
        if (parent != null) {
            getClassLoaderTreeInfo(parent, sb, indent + "  ");
        }
    }
}
