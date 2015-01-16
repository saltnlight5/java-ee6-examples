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
 *  
 * @author zedeng
 */
public class UserService {
    private static final Logger LOGGER = new Logger(UserService.class);
    // Username and password store
    private Properties usersProps = new Properties();
        
    /**
     * Load $HOME/java-ee-example/servlet3example-users.properties if exists, else it will
     * use the one in classpath under "/zemian/servlet3example/service/users.properties".
     * 
     * @throws RuntimeException - if both file and resource name not found. 
     */
    public void init() {
        String userHome = System.getProperty("user.home");
        File userFile = new File(userHome + "/java-ee-example/servlet3example-users.properties");
        if (userFile.exists()) {
            LOGGER.info("Loading users from file: %s", userFile);
            usersProps = Utils.readProps(userFile);
        } else {
            String resourceName = "/zemian/servlet3example/service/users.properties";
            LOGGER.info("Loading users from classpath resource: %s", resourceName);
            usersProps = Utils.readResourceProps(resourceName);
        }
    }
    
    public void destroy() {
        // Do nothing.
    }
        
    public boolean validate(String username, String password) {
        String storedPassword = usersProps.getProperty(username);
        return storedPassword != null && storedPassword.equals(password);
    }
    
    public Set<String> getUsers() {
        Set<String> result = new HashSet<>();
        for (String username : usersProps.stringPropertyNames())
            result.add(username);
        return result;
    }
}
