/*
 *  Zemian Deng 2014
 */

package zemian.servlet3example.web;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author zedeng
 */
public class SessionData {
    public static final String SESSION_DATA_KEY = "zemian.servlet3example.web.SessionData";
    private String id = UUID.randomUUID().toString();
    private Date dateCreated = new Date();

    public String getId() {
        return id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public String toString() {
        return "SessionData(id=" + id + ", dateCreated=" + dateCreated + ")";
    }
}
