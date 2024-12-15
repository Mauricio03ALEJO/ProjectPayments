package ec.edu.uce.payments.jpa.services;

import ec.edu.uce.payments.jpa.Entities.PaymentDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

public class PaymentService implements CrudService<PaymentDetail> {

    @PersistenceContext
    private EntityManager entityManager;

    public PaymentService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void createPayment(PaymentDetail payment) {
        try {
            entityManager.persist(payment);
        } catch (Exception e) {
            throw new ServiceJdbcException("Error creating PaymentDetail", e);
        }
    }

    @Override
    @Transactional
    public List<PaymentDetail> getAll() {
        try {
            return entityManager.createQuery("FROM PaymentDetail", PaymentDetail.class).getResultList();
        } catch (Exception e) {
            throw new ServiceJdbcException("Error listing PaymentDetails", e);
        }
    }

    @Override
    @Transactional
    public PaymentDetail findById(Long id) {
        try {
            return entityManager.find(PaymentDetail.class, id);
        } catch (Exception e) {
            throw new ServiceJdbcException("Error finding PaymentDetail by ID: " + id, e);
        }
    }

    @Override
    @Transactional
    public void save(PaymentDetail payment) {
        try {
            if (payment.getId() != null && payment.getId() > 0) {
                entityManager.merge(payment);
            } else {
                entityManager.persist(payment);
            }
        } catch (Exception e) {
            throw new ServiceJdbcException("Error saving PaymentDetail: " + payment.getId(), e);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            PaymentDetail payment = entityManager.find(PaymentDetail.class, id);
            if (payment != null) {
                entityManager.remove(payment);
            } else {
                throw new IllegalArgumentException("PaymentDetail not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new ServiceJdbcException("Error deleting PaymentDetail by ID: " + id, e);
        }
    }

    @Override
    @Transactional
    public void update(PaymentDetail payment) {
        try {
            if (payment.getId() == null || payment.getId() <= 0) {
                throw new IllegalArgumentException("ID must be valid to update PaymentDetail");
            }
            entityManager.merge(payment);
        } catch (Exception e) {
            throw new ServiceJdbcException("Error updating PaymentDetail with ID: " + payment.getId(), e);
        }
    }
}
