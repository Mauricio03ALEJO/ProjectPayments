package ec.edu.uce.payments.jpa.Entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false, length = 200)
    private String productName;

    @Column(nullable = false)
    private String productDescription;

    @Column(nullable = false)
    private double productPrice;

    @Temporal(TemporalType.TIMESTAMP)
    private Date productCreationDate;

    @PrePersist
    protected void onCreate() {
        productCreationDate = new Date();
    }

    public Product() {}

    public Product(Long productId, String productName, String productDescription, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Date getProductCreationDate() {
        return productCreationDate;
    }

    public void setProductCreationDate(Date productCreationDate) {
        this.productCreationDate = productCreationDate;
    }
}
