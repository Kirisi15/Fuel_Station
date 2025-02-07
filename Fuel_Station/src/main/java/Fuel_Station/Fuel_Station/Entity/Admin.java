package Fuel_Station.Fuel_Station.Entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "admin")

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    @Column
    private String adminUsername;
    @Column
    private String adminPassword;
    @Column(unique = true)
    private String adminEmail;
    @Column
    private String contactNumber;

    public Admin(String adminUsername, String adminPassword, String adminEmail, String contactNumber) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.adminEmail = adminEmail;
        this.contactNumber = contactNumber;
    }
}
