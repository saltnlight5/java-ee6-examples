package zemian.jpaexample.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zemian.jpaexample.data.User;

public class UserDao extends JpaDao<User> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

    public UserDao() {
        super(User.class);
    }
    
    @SuppressWarnings("unchecked")
    public List<User> findMostActiveUsers() {
        LOGGER.debug("Querying database for most active users.");
        // Return all users for now.
        List<User> resultList = getEntityManager().createQuery("SELECT e FROM User e").getResultList();
        return resultList;
    }
}
