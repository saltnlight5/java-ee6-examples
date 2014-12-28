package zemian.servlet3example.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import zemian.service.logging.Logger;

@WebServlet("/form")
public class FormServlet extends HtmlWriterServlet {
    private static final Logger LOGGER = new Logger(FormServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HtmlWriter html = createHtmlWriter(req, resp);    
        String message = getMessage(req);
        
        html.header()
            .h(1, "Process Form")
            .p(message)
            .println("<form method='post' action='form'>")
            .println("<p/>Username: <input type='text' name='username'/>")
            .println("<p/>Password: <input type='password' name='password'/>")
            .println("<p/>Check any languages you like: <input type='checkbox' name='languages' value='Java'/> Java")
            .println("<input type='checkbox' name='languages' value='Python'/> Python")
            .println("<input type='checkbox' name='languages' value='Perl'/> Perl")
            .println("<p/>Notes: <textarea name='notes' cols='50' rows='3'></textarea>")
            .println("<p/>Choose a country: <select name='country' size='1'>")
            .println("<option default='true'>US</option>")
            .println("<option>China</option>")
            .println("<option>Korea</option>")
            .println("</select>")
            .println("<p/><input type='submit' value='Submit'/>")
            .println("</form>")
            .println(html.link("Back to Home", "/index"))
            .footer();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Processing password form.");
        Form form = new Form();
        form.setUsername(req.getParameter("username"));
        form.setPassword(req.getParameter("password"));
        form.setNotes(req.getParameter("notes"));
        form.setCountry(req.getParameter("country"));
        String[] langAry = req.getParameterValues("languages");
        langAry = (langAry == null) ? new String[0] : langAry;
        form.setLanguages(Arrays.asList(langAry));
        req.setAttribute("message", "Processed: " + form);
        doGet(req, resp);
    }
    
    private String getMessage(HttpServletRequest req) {
        String message = (String)req.getAttribute("message");
        if (message ==  null) {
            message = "";
        }
        return message;
    }
    
    public static class Form {
        String username;
        String password;
        List<String> languages = new ArrayList<>();
        String notes;
        String country;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public List<String> getLanguages() {
            return languages;
        }

        public void setLanguages(List<String> languages) {
            this.languages = languages;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        @Override
        public String toString() {
            return String.format("Form(username=%s, languages=%s, country=%s, notes=%s)",
                    username, languages, country, notes);
        }
    }
}
