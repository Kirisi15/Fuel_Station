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
    private Long employee_Id;
    @Column
    private Long employee_NIC;
    @Column
    private String employee_Jobrole;
    @Column

    private Long employee_Contactnumber;

    @Column
    private String employee_Username;
    @Column
    private String employee_Password;

    @ManyToOne
    @JoinColumn(name = "stationId")
    private FuelStationEntity fuelStation;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions = new ArrayList<>();



    public EmployeeEntity() {

    }

    public EmployeeEntity(Long employee_Id, Long employee_NIC, String employee_Jobrole, Long employee_Contactnumber, String employee_Username, String employee_Password) {
        employee_Id = employee_Id;
        employee_NIC = employee_NIC;
        employee_Jobrole = employee_Jobrole;
        employee_Contactnumber = employee_Contactnumber;
        employee_Username = employee_Username;
        employee_Password = employee_Password;
    }

    public Long getEmployee_Id() {
        return employee_Id;
    }

    public void setEmployee_Id(Long employee_Id) {
        employee_Id = employee_Id;
    }

    public Long getEmployee_NIC() {
        return employee_NIC;
    }

    public void setEmployee_NIC(Long employee_NIC) {
        employee_NIC = employee_NIC;
    }

    public String getEmployee_Jobrole() {
        return employee_Jobrole;
    }

    public void setEmployee_Jobrole(String employee_Jobrole) {
        employee_Jobrole = employee_Jobrole;
    }

    public Long getEmployee_Contactnumber() {
        return employee_Contactnumber;
    }

    public void setEmployee_Contactnumber(Long employee_Contactnumber) {
        employee_Contactnumber = employee_Contactnumber;
    }



    public String getEmployee_Username() {
        return employee_Username;
    }

    public void setEmployee_Username(String employee_Username) {
        employee_Username = employee_Username;
    }

    public String getEmployee_Password() {
        return employee_Password;
    }

    public void setEmployee_Password(String employee_Password) {
        employee_Password = employee_Password;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "Employee_Id='" + employee_Id + '\'' +
                ", Employee_NIC=" + employee_NIC +
                ", Employee_Jobrole='" + employee_Jobrole + '\'' +
                ", Employee_Contactnumber=" + employee_Contactnumber +
                ", Employee_Username='" + employee_Username + '\'' +
                ", Employee_Password='" + employee_Password + '\'' +
                '}';
    }
}

