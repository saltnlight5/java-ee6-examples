package zemian.jpaexample.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zemian.jpaexample.dao.UserDao;
import zemian.jpaexample.data.User;

@Stateless
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Inject
    private UserDao userDao;

    @PostConstruct
    public void init() {
        LOGGER.info("Service inited.");
    }

    public List<User> findMostActiveUsers() {
        LOGGER.debug("Finding all active users.");
        LOGGER.info("Using userDao={}", userDao);
        return userDao.findMostActiveUsers();
    }
}
