package Fuel_Station.Fuel_Station.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name = "Customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY                                                        )
    private Long customerId;
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
    private List<Vehicle> vehicles = new ArrayList<>();

    public Customer(String customerNIC, String customerName, String customerEmail, String customerUsername, String customerPassword) {
        this.customerNIC = customerNIC;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerUsername = customerUsername;
        this.customerPassword = customerPassword;
    }
}
