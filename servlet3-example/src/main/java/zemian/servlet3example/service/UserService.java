/*
 *  Zemian Deng 2014
 */

package zemian.servlet3example.service;

import java.io.File;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import zemian.service.logging.Logger;
import zemian.service.util.Utils;

/**
 * User DAO that uses Properties file to store users info.
 * @author zedeng
 */
public class UserService {
    private static final Logger LOGGER = new Logger(UserService.class);
    // Username and password store
    private Properties users = new Properties();
    
    public void loadFromFile(File file) {
        LOGGER.info("Loading users from file: %s", file);
        users = Utils.readProps(file);
    }
    
    public void loadFromResource(String resourceName) {
        LOGGER.info("Loading users from classpath resource: %s", resourceName);
        users = Utils.readResourceProps(resourceName);
    }
        
    public boolean validPassword(String username, String password) {
        String storedPassword = users.getProperty(username);
        return storedPassword != null && storedPassword.equals(password);
    }
    
    public Set<String> getUsers() {
        Set<String> result = new HashSet<>();
        for (String username : users.stringPropertyNames())
            result.add(username);
        return result;
    }
}
