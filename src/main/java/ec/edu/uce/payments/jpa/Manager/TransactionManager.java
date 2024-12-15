package ec.edu.uce.payments.jpa.Manager;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.interceptor.InvocationContext;
import java.util.logging.Logger;

@Interceptor
@Transactional
public class TransactionManager {

    @Inject
    private EntityManagerProvider entityManagerProvider;

    private static final Logger logger = Logger.getLogger(TransactionManager.class.getName());

    @AroundInvoke
    public Object handleTransaction(InvocationContext context) throws Exception {
        EntityManager entityManager = entityManagerProvider.createEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            logger.info("----- Transaction started ----- Method: " + context.getMethod().getName()
                    + " in class: " + context.getMethod().getDeclaringClass());
            transaction.begin();
            Object result = context.proceed();
            transaction.commit();
            logger.info("----- Transaction finished ----- Method: " + context.getMethod().getName()
                    + " in class: " + context.getMethod().getDeclaringClass());
            return result;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
                logger.warning("Transaction rolled back due to error.");
            }
            logger.severe("Transaction failed: " + e.getMessage());
            throw new RuntimeException("Transaction failed", e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
                logger.info("EntityManager closed.");
            }
        }
    }
}
