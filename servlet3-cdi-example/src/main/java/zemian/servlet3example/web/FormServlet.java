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
            .h(1, "User Data Form")
            .p(message)
            .println("<form method='post' action='form'>")
            .println("<p/>Username: <input type='text' name='username'/>")
            .println("<p/>Password: <input type='password' name='password'/>")
            .println("<p/>Choose a country: <select name='country' size='1'>")
            .println("<option default='true'>US</option>")
            .println("<option>China</option>")
            .println("<option>Korea</option>")
            .println("</select>")
            .println("<p/>Skills set: <input type='checkbox' name='skills' value='Java'/> Java")
            .println("<input type='checkbox' name='skills' value='Java EE'/>Java EE")
            .println("<input type='checkbox' name='skills' value='MySQL Database'/> MySQL Database")
            .println("<p/>Notes: <textarea name='notes' cols='50' rows='3'></textarea>")
            .println("<p/><input type='submit' value='Submit'/>")
            .println("</form>")
            .footer();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Processing form.");
        Form form = new Form();
        form.setUsername(req.getParameter("username"));
        form.setPassword(req.getParameter("password"));
        form.setNotes(req.getParameter("notes"));
        form.setCountry(req.getParameter("country"));
        String[] skills = req.getParameterValues("skills");
        skills = (skills == null) ? new String[0] : skills;
        form.setSkills(Arrays.asList(skills));
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
        private String username;
        private String password;
        private String country;
        private List<String> skills = new ArrayList<>();
        private String notes;

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

        public List<String> getSkills() {
            return skills;
        }

        public void setSkills(List<String> skills) {
            this.skills = skills;
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
            return String.format("Form(username=%s, country=%s, skills=%s, notes=%s)",
                    username, country, skills, notes);
        }
    }
}
