/*
 *  Zemian Deng 2014
 */

package zemian.servlet3example.web;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * A application session object that holds logged in user information.
 * 
 * @author zedeng
 */
public class LoginSession implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String LOGIN_SESSION_KEY = LoginSession.class.getSimpleName();
    private String id = UUID.randomUUID().toString();
    private Date dateCreated = new Date();
    private String username;
    
    public LoginSession(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    
    public String getId() {
        return id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public String toString() {
        return "SessionData(id=" + id + ", username=" + username + ", dateCreated=" + dateCreated + ")";
    }
}
