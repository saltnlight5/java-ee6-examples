package zemian.jpaexample.web.controller;

import java.util.UUID;
import javax.faces.bean.ManagedBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstrate Text Inputs in Form processing.
 *
 * See
 * http://docs.oracle.com/javaee/6/javaserverfaces/2.1/docs/vdldocs/facelets/index.html
 *
 * @author zedeng
 */
@ManagedBean
public class FormInputs {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormInputs.class);
    private String token = UUID.randomUUID().toString();

    private String name;
    private String password;
    private String comment;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    ////////////////////////////////////////////////////////////////////////////
    // Process form and support methods.
    ////////////////////////////////////////////////////////////////////////////
    public String processForm() {
        LOGGER.info("Processing " + this);
        LOGGER.info("Done.");
        return "form"; // Display same form again after process.
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Form(");
        sb.append(", token=").append(token);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append(", comment=").append(comment);
        sb.append(")");
        return sb.toString();
    }
    
}
