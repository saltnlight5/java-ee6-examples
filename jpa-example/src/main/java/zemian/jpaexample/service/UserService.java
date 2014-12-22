package zemian.jpaexample.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zemian.jpaexample.dao.UserDao;
import zemian.jpaexample.data.User;
import zemian.service.security.Encrypt;

@Stateless
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Inject
    private UserDao userDao;
    
    @Inject
    private Encrypt encrypt;

    @PostConstruct
    public void init() {
        LOGGER.info("Service inited.");
    }
    
    public void encryptPasswordAndSaveUser(User user, String plainPassword) {
        try {
            byte[] hashedPassword = encrypt.encrypt(plainPassword.getBytes("UTF-8"));
            user.setEncryptedPassword(new String(hashedPassword, "UTF-8"));
            userDao.save(user);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to save and encrypt user with password.", e);
        }
    }

    public List<User> findMostActiveUsers() {
        LOGGER.debug("Finding all active users.");
        LOGGER.info("Using userDao={}", userDao);
        return userDao.findMostActiveUsers();
    }
}
