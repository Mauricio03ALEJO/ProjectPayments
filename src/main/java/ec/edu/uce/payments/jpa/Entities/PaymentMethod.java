package ec.edu.uce.payments.jpa.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_methods")
public class PaymentMethod {

    @Id
    @Column(length = 50)
    private String paymentMethodId; // Ej: "PayPal", "CreditCard", "BankTransfer"

    private String paymentMethodDescription;

    @Lob
    private String paymentMethodDetails; // JSON u otra información específica del método de pago

    public PaymentMethod() {
    }

    // Getters and setters
    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentMethodDescription() {
        return paymentMethodDescription;
    }

    public void setPaymentMethodDescription(String paymentMethodDescription) {
        this.paymentMethodDescription = paymentMethodDescription;
    }

    public String getPaymentMethodDetails() {
        return paymentMethodDetails;
    }

    public void setPaymentMethodDetails(String paymentMethodDetails) {
        this.paymentMethodDetails = paymentMethodDetails;
    }
}
