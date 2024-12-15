package ec.edu.uce.payments.jpa.services;

import ec.edu.uce.payments.jpa.Entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

public class ProductService implements CrudService<Product> {

    @PersistenceContext
    private EntityManager entityManager;

    public ProductService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void createProduct(Product product) {
        try {
            entityManager.persist(product);
        } catch (Exception e) {
            throw new ServiceJdbcException("Error creating Product: " + product.getName(), e);
        }
    }

    @Override
    @Transactional
    public List<Product> list() {
        try {
            return entityManager.createQuery("FROM Product", Product.class).getResultList();
        } catch (Exception e) {
            throw new ServiceJdbcException("Error listing products", e);
        }
    }

    @Override
    @Transactional
    public Product findById(Long id) {
        try {
            return entityManager.find(Product.class, id);
        } catch (Exception e) {
            throw new ServiceJdbcException("Error finding Product with ID: " + id, e);
        }
    }

    @Override
    @Transactional
    public void save(Product product) {
        try {
            if (product.getId() != null && product.getId() > 0) {
                entityManager.merge(product);
            } else {
                entityManager.persist(product);
            }
        } catch (Exception e) {
            throw new ServiceJdbcException("Error saving Product: " + product.getName(), e);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            Product product = entityManager.find(Product.class, id);
            if (product != null) {
                entityManager.remove(product);
            } else {
                throw new IllegalArgumentException("Product not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new ServiceJdbcException("Error deleting Product with ID: " + id, e);
        }
    }

    @Override
    @Transactional
    public void update(Product product) {
        try {
            if (product.getId() == null || product.getId() <= 0) {
                throw new IllegalArgumentException("ID must be valid to update Product");
            }
            entityManager.merge(product);
        } catch (Exception e) {
            throw new ServiceJdbcException("Error updating Product: " + product.getName(), e);
        }
    }
}
