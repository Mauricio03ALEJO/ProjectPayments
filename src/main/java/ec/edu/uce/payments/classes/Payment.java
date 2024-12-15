package ec.edu.uce.payments.classes;

public abstract class Payment implements PaymentProcessor {
    protected String paymentDetail;
    protected double paymentAmount;

    public Payment(String paymentDetail, double paymentAmount) {
        this.paymentDetail = paymentDetail;
        this.paymentAmount = paymentAmount;
    }

    public Payment() {
    }

    public String getPaymentDetail() {
        return paymentDetail;
    }

    public void setPaymentDetail(String paymentDetail) {
        this.paymentDetail = paymentDetail;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Override
    public String toString() {
        return String.format("PaymentDetail='%s', PaymentAmount=%.2f", paymentDetail, paymentAmount);
    }
}
