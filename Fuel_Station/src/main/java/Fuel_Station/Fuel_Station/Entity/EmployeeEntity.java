package Fuel_Station.Fuel_Station.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empId")
    private Long employeeId;
    @Column
    private Long employeeNic;
    @Column
    private String employeeJobrole;
    @Column

    private Long employeeContactnumber;

    @Column
    private String employeeUsername;
    @Column
    private String employeePassword;

    @ManyToOne
    @JoinColumn(name = "stationId")
    private FuelStationEntity fuelStation;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions = new ArrayList<>();



    public EmployeeEntity() {

    }

    public EmployeeEntity(Long employeeId, Long employeeNic, String employeeJobrole, Long employeeContactnumber, String employeeUsername, String employeePassword) {
      this.employeeId = employeeId;
        this.employeeNic = employeeNic;
        this.employeeJobrole = employeeJobrole;
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

    public String getEmployeeJobrole() {
        return employeeJobrole;
    }

    public void setEmployeeJobrole(String employeeJobrole) {
       this. employeeJobrole = employeeJobrole;
    }

    public Long getEmployeeContactnumber() {
        return employeeContactnumber;
    }

    public void setEmployeeContactnumber(Long employeeContactnumber) {
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

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "employeeId=" + employeeId + '\'' +
                ", employeeNic=" + employeeNic +
                ", employeeJobrole='" + employeeJobrole + '\'' +
                ", employeeContactnumber=" + employeeContactnumber +
                ", employeeUsername='" + employeeUsername + '\'' +
                ", employeePassword='" + employeePassword + '\'' +
                '}';
    }
}

