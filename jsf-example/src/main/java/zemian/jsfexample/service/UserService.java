/*
 *  Zemian Deng 2014
 */

package zemian.jsfexample.service;

import java.util.List;
import zemian.jsfexample.data.User;
import zemian.service.util.Utils;

/**
 *
 * @author zedeng
 */
public class UserService {
    public List<User> findAllUsers() {
        return Utils.list(
            createSampleUser("tester1", "Zemian", "Tester"),
            createSampleUser("tester2", "John", "Tester"),
            createSampleUser("tester3", "Jay", "Tester"),
            createSampleUser("tester4", "Joe", "Tester"),
            createSampleUser("tester5", "Jane", "Tester")
        );
    }

    private User createSampleUser(String username, String firstName, String lastName) {
        User user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(username + "@test.com");
        user.setAddressLine1("123 Abc St");
        user.setCity("Orlando");
        user.setState("FL");
        user.setZipCode("32829");
        return user;
    }
}
