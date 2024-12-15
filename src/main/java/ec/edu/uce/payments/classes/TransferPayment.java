package ec.edu.uce.payments.classes;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPaymentMethod("Transfer")
public class TransferPayment extends Payment {

    public TransferPayment() {
        super();
    }

    @PostConstruct
    public void initialize() {
        System.out.println("TransferPayment initialized");
    }

    @Override
    public String processPayment(Payment payment) {
        return String.format("Transfer Payment - Detail: '%s', Amount: %.2f", payment.getPaymentDetail(), payment.getPaymentAmount());
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("TransferPayment destroyed");
    }
}
