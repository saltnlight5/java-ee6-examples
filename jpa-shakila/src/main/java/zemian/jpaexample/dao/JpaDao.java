package zemian.jpaexample.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import zemian.service.logging.Logger;

public class JpaDao<T> {
    private static final Logger LOGGER = new Logger(JpaDao.class);
    
    @PersistenceContext
    protected EntityManager entityManager;

    protected Class<T> entityClass;

    public JpaDao() {
    }

    public JpaDao(Class<T> entityClass) {
        setEntityClass(entityClass);
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void save(T entity) {
        LOGGER.debug("Saving entity=%s", entity);
        entityManager.persist(entity);
    }

    public T update(T entity) {
        LOGGER.debug("Updating entity=%s", entity);
        return entityManager.merge(entity);
    }

    public T find(Object primaryKey) {
        LOGGER.debug("Finding entityClass=%s, primaryKey=%s", entityClass, primaryKey);
        return entityManager.find(entityClass, primaryKey);
    }

    public void delete(Object primaryKey) {
        LOGGER.debug("Deleting entity primaryKey=%s", primaryKey);
        T ref = entityManager.getReference(entityClass, primaryKey);
        entityManager.remove(ref);
    }
}
