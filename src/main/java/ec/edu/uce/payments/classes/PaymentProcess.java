package ec.edu.uce.payments.classes;

import jakarta.ejb.Stateful;
import jakarta.enterprise.context.ApplicationScoped;

@Stateful
@ApplicationScoped
public class paymentProcessorService {

    public String processPayment(PaymentProcessor paymentProcessor) {
        if (paymentProcessor instanceof Payment) {
            Payment payment = (Payment) paymentProcessor;
            return paymentProcessor.processPayment(payment);
        } else {
            return null;
        }
    }
}
