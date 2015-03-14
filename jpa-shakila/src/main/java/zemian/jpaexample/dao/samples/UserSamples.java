/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zemian.jpaexample.dao.samples;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.inject.Inject;
import zemian.jpaexample.dao.UserDao;
import zemian.jpaexample.data.User;
import zemian.service.logging.Logger;

/**
 *
 * @author zedeng
 */
@Stateless
public class UserSamples {
    private static final Logger LOGGER = new Logger(UserSamples.class);
    private static final String NUMS = "0123456789";
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LETTERS_NUMS = LETTERS + NUMS;
    private static final String[] FIRST_NAMES = {"John", "Chris", "Alyson", "Zemian", "Sam"};
    private static final String[] LAST_NAMES = {"Smith", "Doe", "Jane", "Johnson", "Cool"};
        
    @Inject
    private UserDao userDao;
    
    private String randLettersNums(int size) {
        int max = LETTERS_NUMS.length();
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            int index = rand.nextInt(max);
            sb.append(LETTERS_NUMS.charAt(index));
        }
        return sb.toString();
    }
    
    public List<User> createRandomNameUsers(String baseUsername, int numOfUsers) {
        List<User> result = new ArrayList<>();
        String batchId = randLettersNums(4).toLowerCase();
        LOGGER.debug("Generating users with batchId=%s", batchId);
        for (int i = 0; i < numOfUsers; i++) {
            User user = new User();
            user.setUsername(batchId + "_" + baseUsername + "_" + i);
            user.setFirstName(FIRST_NAMES[i % FIRST_NAMES.length]);
            user.setLastName(LAST_NAMES[i % LAST_NAMES.length]);
            user.setEncryptedPassword(UUID.randomUUID().toString());
            
            userDao.save(user);
            result.add(user);
        }
        return result;
    }
}
