package ec.edu.uce.payments.jpa.services;

import ec.edu.uce.payments.jpa.Entities.PaymentMethod;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

public class PaymentMethodService implements CrudService<PaymentMethod> {

    @PersistenceContext
    private EntityManager entityManager;

    public PaymentMethodService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void createPaymentMethod(PaymentMethod paymentMethod) {
        try {
            entityManager.persist(paymentMethod);
        } catch (Exception e) {
            throw new ServiceJdbcException("Error creating PaymentMethod: " + paymentMethod.getId(), e);
        }
    }

    @Override
    @Transactional
    public List<PaymentMethod> getAll() {
        try {
            return entityManager.createQuery("FROM PaymentMethod", PaymentMethod.class).getResultList();
        } catch (Exception e) {
            throw new ServiceJdbcException("Error listing PaymentMethods", e);
        }
    }

    @Override
    @Transactional
    public PaymentMethod findById(Long id) {
        try {
            return entityManager.find(PaymentMethod.class, id);
        } catch (Exception e) {
            throw new ServiceJdbcException("Error finding PaymentMethod by ID: " + id, e);
        }
    }

    @Override
    @Transactional
    public void save(PaymentMethod paymentMethod) {
        try {
            if (paymentMethod.getId() != null) {
                entityManager.merge(paymentMethod);
            } else {
                entityManager.persist(paymentMethod);
            }
        } catch (Exception e) {
            throw new ServiceJdbcException("Error saving PaymentMethod: " + paymentMethod.getId(), e);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            PaymentMethod paymentMethod = entityManager.find(PaymentMethod.class, id);
            if (paymentMethod != null) {
                entityManager.remove(paymentMethod);
            } else {
                throw new IllegalArgumentException("PaymentMethod not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new ServiceJdbcException("Error deleting PaymentMethod by ID: " + id, e);
        }
    }

    @Override
    @Transactional
    public void update(PaymentMethod paymentMethod) {
        try {
            if (paymentMethod.getId() == null) {
                throw new IllegalArgumentException("ID must be provided to update PaymentMethod");
            }
            entityManager.merge(paymentMethod);
        } catch (Exception e) {
            throw new ServiceJdbcException("Error updating PaymentMethod with ID: " + paymentMethod.getId(), e);
        }
    }
}
