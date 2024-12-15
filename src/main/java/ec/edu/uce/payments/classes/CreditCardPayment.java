package ec.edu.uce.payments.classes;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPaymentMethod("CreditCard")
public class CreditCardPayment extends Payment {

    public CreditCardPayment() {
        super();
    }

    @PostConstruct
    public void initialize() {
        System.out.println("CreditCardPayment initialized");
    }

    @Override
    public String processPayment(Payment payment) {
        return String.format("Credit Card Payment - Detail: '%s', Amount: %.2f", payment.getDetail(), payment.getAmount());
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("CreditCardPayment destroyed");
    }
}
