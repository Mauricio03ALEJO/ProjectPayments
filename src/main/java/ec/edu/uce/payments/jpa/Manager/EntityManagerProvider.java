package ec.edu.uce.payments.jpa.Manager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProvider {

    private static final String PERSISTENCE_UNIT_NAME = "paymentUnit";

    @Produces
    @ApplicationScoped
    public EntityManagerFactory createEntityManagerFactory() {
        try {
            return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create EntityManagerFactory", e);
        }
    }
}
