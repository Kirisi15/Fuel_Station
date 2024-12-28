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
    private Long Employee_Id;
    @Column
    private Long Employee_NIC;
    @Column
    private String Employee_Jobrole;
    @Column

    private Long Employee_Contactnumber;

    @Column
    private String Employee_Username;
    @Column
    private String Employee_Password;

    @ManyToOne
    @JoinColumn(name = "stationId", nullable = false)
    private FuelStationEntity fuelStation;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions = new ArrayList<>();



    public EmployeeEntity() {

    }

    public EmployeeEntity(String employee_Id, Long employee_NIC, String employee_Jobrole, Long employee_Contactnumber, String employee_Username, String employee_Password) {
        Employee_Id = Long.valueOf(employee_Id);
        Employee_NIC = employee_NIC;
        Employee_Jobrole = employee_Jobrole;
        Employee_Contactnumber = employee_Contactnumber;
        Employee_Username = employee_Username;
        Employee_Password = employee_Password;
    }

    public Long getEmployee_Id() {
        return Employee_Id;
    }

    public void setEmployee_Id(String employee_Id) {
        Employee_Id = Long.valueOf(employee_Id);
    }

    public Long getEmployee_NIC() {
        return Employee_NIC;
    }

    public void setEmployee_NIC(Long employee_NIC) {
        Employee_NIC = employee_NIC;
    }

    public String getEmployee_Jobrole() {
        return Employee_Jobrole;
    }

    public void setEmployee_Jobrole(String employee_Jobrole) {
        Employee_Jobrole = employee_Jobrole;
    }

    public Long getEmployee_Contactnumber() {
        return Employee_Contactnumber;
    }

    public void setEmployee_Contactnumber(Long employee_Contactnumber) {
        Employee_Contactnumber = employee_Contactnumber;
    }



    public String getEmployee_Username() {
        return Employee_Username;
    }

    public void setEmployee_Username(String employee_Username) {
        Employee_Username = employee_Username;
    }

    public String getEmployee_Password() {
        return Employee_Password;
    }

    public void setEmployee_Password(String employee_Password) {
        Employee_Password = employee_Password;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "Employee_Id='" + Employee_Id + '\'' +
                ", Employee_NIC=" + Employee_NIC +
                ", Employee_Jobrole='" + Employee_Jobrole + '\'' +
                ", Employee_Contactnumber=" + Employee_Contactnumber +
                ", Employee_Username='" + Employee_Username + '\'' +
                ", Employee_Password='" + Employee_Password + '\'' +
                '}';
    }
}

