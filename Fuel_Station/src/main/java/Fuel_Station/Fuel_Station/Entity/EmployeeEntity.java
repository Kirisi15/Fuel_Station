package Fuel_Station.Fuel_Station.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Employee_Id;
    @Column
    private Long Employee_NIC;
    @Column
    private String Employee_Jobrole;
    @Column

    private Long Employee_Contactnumber;
    @Column
    private String Employee_Stationlicense;
    @Column
    private String Employee_Username;
    @Column
    private String Employee_Password;

    public EmployeeEntity() {

    }

    public EmployeeEntity(String employee_Id, Long employee_NIC, String employee_Jobrole, Long employee_Contactnumber, String employee_Stationlicense, String employee_Username, String employee_Password) {
        Employee_Id = Long.valueOf(employee_Id);
        Employee_NIC = employee_NIC;
        Employee_Jobrole = employee_Jobrole;
        Employee_Contactnumber = employee_Contactnumber;
        Employee_Stationlicense = employee_Stationlicense;
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

    public String getEmployee_Stationlicense() {
        return Employee_Stationlicense;
    }

    public void setEmployee_Stationlicense(String employee_Stationlicense) {
        Employee_Stationlicense = employee_Stationlicense;
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
                ", Employee_Stationlicense='" + Employee_Stationlicense + '\'' +
                ", Employee_Username='" + Employee_Username + '\'' +
                ", Employee_Password='" + Employee_Password + '\'' +
                '}';
    }
}

