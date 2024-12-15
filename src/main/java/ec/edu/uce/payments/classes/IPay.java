package ec.edu.uce.payments.classes;

public interface PaymentProcessor {
    String processPayment(Payment payment);
}
