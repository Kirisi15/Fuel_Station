package Fuel_Station.Fuel_Station.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
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
    private List<TransactionEntity> transactions = new ArrayList<>();



    public Employee() {

    }

    public Employee(Long employeeId, Long employeeNic, String employeeName, String employeeContactnumber, String employeeUsername, String employeePassword) {
      this.employeeId = employeeId;
        this.employeeNic = employeeNic;
        this.employeeName = employeeName;
       this.employeeContactnumber = employeeContactnumber;
        this.employeeUsername = employeeUsername;
        this.employeePassword = employeePassword;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmployeeNic() {
        return employeeNic;
    }

    public void setEmployeeNic(Long employeeNic) {
        this.employeeNic = employeeNic;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
       this.employeeName = employeeName;
    }

    public String getEmployeeContactnumber() {
        return employeeContactnumber;
    }

    public void setEmployeeContactnumber(String employeeContactnumber) {
        this.employeeContactnumber = employeeContactnumber;
    }



    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public void setEmployeeUsername(String employeeUsername) {
        this.employeeUsername = employeeUsername;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
       this. employeePassword = employeePassword;
    }

    public FuelStation getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(FuelStation fuelStation) {
        this.fuelStation = fuelStation;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "employeeId=" + employeeId + '\'' +
                ", employeeNic=" + employeeNic +
                ", employeeName='" + employeeName + '\'' +
                ", employeeContactnumber=" + employeeContactnumber +
                ", employeeUsername='" + employeeUsername + '\'' +
                ", employeePassword='" + employeePassword + '\'' +
                '}';
    }
}

