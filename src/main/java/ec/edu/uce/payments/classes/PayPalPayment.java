package ec.edu.uce.payments.classes;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPaymentMethod("PayPal")
public class PayPalPayment extends Payment {

    public PayPalPayment() {
        super();
    }

    @PostConstruct
    public void initialize() {
        System.out.println("PayPalPayment initialized");
    }

    @Override
    public String processPayment(Payment payment) {
        return String.format("PayPal Payment - Detail: '%s', Amount: %.2f", payment.getPaymentDetail(), payment.getPaymentAmount());
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("PayPalPayment destroyed");
    }
}
