package ec.edu.uce.payments.jpa.Entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class UserP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 100)
    private String userName;

    @Column(nullable = false, unique = true, length = 150)
    private String userEmail;

    @Column(nullable = false, unique = true, length = 15)
    private String userPhone;

    @Temporal(TemporalType.TIMESTAMP)
    private Date userCreationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date userUpdateDate;

    @PrePersist
    protected void onCreate() {
        userCreationDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        userUpdateDate = new Date();
    }

    public UserP() {}

    public UserP(Long userId, String userName, String userEmail, String userPhone) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Date getUserUpdateDate() {
        return userUpdateDate;
    }

    public void setUserUpdateDate(Date userUpdateDate) {
        this.userUpdateDate = userUpdateDate;
    }

    public Date getUserCreationDate() {
        return userCreationDate;
    }

    public void setUserCreationDate(Date userCreationDate) {
        this.userCreationDate = userCreationDate;
    }
}
