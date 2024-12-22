package Fuel_Station.Fuel_Station.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String EmpId;
    @Column
    private Long NIC;
    @Column
    private String Jobrole;
    @Column

    private Long Contactnumber;
    @Column
    private String Stationlicense;
    @Column
    private String Username;
    @Column
    private String Password;

    public EmployeeEntity() {

    }

    public EmployeeEntity(String empId, Long NIC, String jobrole, Long contactnumber, String stationlicense, String username, String password) {
        EmpId = empId;
        this.NIC = NIC;
        Jobrole = jobrole;
        Contactnumber = contactnumber;
        Stationlicense = stationlicense;
        Username = username;
        Password = password;
    }

    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String empId) {
        EmpId = empId;
    }

    public Long getNIC() {
        return NIC;
    }

    public void setNIC(Long NIC) {
        this.NIC = NIC;
    }

    public String getJobrole() {
        return Jobrole;
    }

    public void setJobrole(String jobrole) {
        Jobrole = jobrole;
    }

    public Long getContactnumber() {
        return Contactnumber;
    }

    public void setContactnumber(Long contactnumber) {
        Contactnumber = contactnumber;
    }

    public String getStationlicense() {
        return Stationlicense;
    }

    public void setStationlicense(String stationlicense) {
        Stationlicense = stationlicense;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "EmpId='" + EmpId + '\'' +
                ", NIC=" + NIC +
                ", Jobrole='" + Jobrole + '\'' +
                ", Contactnumber=" + Contactnumber +
                ", Stationlicense='" + Stationlicense + '\'' +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
