package Fuel_Station.Fuel_Station.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empId")
    private Long employeeId;
    @Column
    private Long employeeNic;
    @Column
    private String employeeName;
    @Column

    private String employeeContactnumber;

    @Column
    private String employeeUsername;
    @Column
    private String employeePassword;

    @ManyToOne
    @JoinColumn(name = "stationId")
    private FuelStation fuelStation;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public Employee(Long employeeNic, String employeeName, String employeeContactnumber, String employeeUsername, String employeePassword, FuelStation fuelStation) {
        this.employeeNic = employeeNic;
        this.employeeName = employeeName;
        this.employeeContactnumber = employeeContactnumber;
        this.employeeUsername = employeeUsername;
        this.employeePassword = employeePassword;
        this.fuelStation = fuelStation;
    }
}

