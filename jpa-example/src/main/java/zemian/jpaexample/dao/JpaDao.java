package zemian.jpaexample.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JpaDao<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(JpaDao.class);
    
    @PersistenceContext
    protected EntityManager entityManager;
    
    protected Class<T> entityClass;
    
    public JpaDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
        
    public void save(T entity) {
        LOGGER.debug("Saving entity={}", entity);
        entityManager.persist(entity);
    }
    
    public T update(T entity) {
        LOGGER.debug("Updating entity={}", entity);
        return entityManager.merge(entity);
    }
    
    public T find(Object primaryKey) {
        LOGGER.debug("Finding entityClass={}, primaryKey={}", entityClass, primaryKey);
        return entityManager.find(entityClass, primaryKey);
    }
    
    public void delete(Object primaryKey) {
        LOGGER.debug("Deleting entity primaryKey={}", primaryKey);
        T ref = entityManager.getReference(entityClass, primaryKey);
        entityManager.remove(ref);
    }
}
