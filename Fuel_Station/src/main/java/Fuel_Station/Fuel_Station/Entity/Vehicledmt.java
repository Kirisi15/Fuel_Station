package Fuel_Station.Fuel_Station.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Vehicledmt {
    @Id
    private String licenseNumber;
    private String nic;

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public Vehicledmt(String licenseNumber, String nic) {
        this.licenseNumber = licenseNumber;
        this.nic=nic;
    }

    public Vehicledmt() {

    }

    @Override
    public String toString() {
        return "Vehicledmt{" +
                "licenseNumber='" + licenseNumber + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }
}
