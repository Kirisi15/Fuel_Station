package Fuel_Station.Fuel_Station.Entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY                                                        )
    private int customerId;
    @Column(name = "Customer_NIC")
    private String customerNIC;
    @Column(name = "Customer_Name")
    private String customerName;
    @Column(name = "Customer_Email")
    private  String customerEmail;
    @Column(name = "Customer_UserName")
    private String customerUsername;
    @Column(name = "Customer_Password")
    private String customerPassword;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<VehicleEntity> vehicles = new ArrayList<>();

    public CustomerEntity() {
    }



    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerNIC() {
        return customerNIC;
    }

    public void setCustomerNIC(String customerNIC) {
        this.customerNIC = customerNIC;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "customerId=" + customerId +
                ", customerNIC='" + customerNIC + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerUsername='" + customerUsername + '\'' +
                ", customerPassword='" + customerPassword + '\'' +
                '}';
    }
}
