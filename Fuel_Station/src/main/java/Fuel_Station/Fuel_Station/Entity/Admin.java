package Fuel_Station.Fuel_Station.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin")

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adminId;
    @Column(nullable = false)
    private String adminUsername;
    @Column(nullable = false)
    private String adminPassword;
    @Column(unique = true, nullable = false)
    private String email;
    @Column
    private String contactNumber;

    public Admin(String adminUsername, String adminPassword, String adminEmail, String contactNumber) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.email = adminEmail;
        this.contactNumber = contactNumber;
    }
}
