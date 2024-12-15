package ec.edu.uce.payments.jpa.services;

import ec.edu.uce.payments.jpa.Entities.UserP;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

public class UserPService implements CrudService<UserP> {

    @PersistenceContext
    private EntityManager entityManager;

    public UserPService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void createUserP(UserP userP) {
        try {
            entityManager.persist(userP);
        } catch (Exception e) {
            throw new RuntimeException("Error creating user: " + userP.getName(), e);
        }
    }

    @Override
    @Transactional
    public List<UserP> list() {
        try {
            return entityManager.createQuery("FROM UserP", UserP.class).getResultList();
        } catch (Exception e) {
            throw new ServiceJdbcException("Error listing users", e);
        }
    }

    @Override
    @Transactional
    public UserP findById(Long id) {
        try {
            return entityManager.find(UserP.class, id);
        } catch (Exception e) {
            throw new ServiceJdbcException("Error finding user with ID: " + id, e);
        }
    }

    @Override
    @Transactional
    public void save(UserP user) {
        try {
            if (user.getId() != null && user.getId() > 0) {
                entityManager.merge(user);
            } else {
                entityManager.persist(user);
            }
        } catch (Exception e) {
            throw new ServiceJdbcException("Error saving user: " + user.getName(), e);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            UserP user = entityManager.find(UserP.class, id);
            if (user != null) {
                entityManager.remove(user);
            } else {
                throw new IllegalArgumentException("User not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new ServiceJdbcException("Error deleting user with ID: " + id, e);
        }
    }

    @Override
    @Transactional
    public void update(UserP user) {
        try {
            if (user.getId() == null || user.getId() <= 0) {
                throw new IllegalArgumentException("ID must be valid to update user");
            }
            entityManager.merge(user);
        } catch (Exception e) {
            throw new ServiceJdbcException("Error updating user: " + user.getName(), e);
        }
    }
}
