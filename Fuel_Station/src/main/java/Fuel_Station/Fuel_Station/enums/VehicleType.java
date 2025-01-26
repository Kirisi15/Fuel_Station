package Fuel_Station.Fuel_Station.enums;
public enum VehicleType {

    CAR("Car", "Car"),
    LORRY("Lorry", "Lorry"),
    MOTORCYCLE("Motorcycle", "Motorcycle"),
    BIKE("Bike", "Bike"),
    THREEWHEELER("Threewheeler", "Threewheeler"),
    BUS("Bus", "Bus"),
    TRUCK("Truck", "Truck"),
    VAN("Van", "Van"),
    SUV("SUV", "SUV"),
    TRACTOR("Tractor", "Tractor"),
    PICKUP_TRUCK("Pickup Truck", "Pickup_Truck");

    private final String label;
    private final String value;

    // Constructor
    VehicleType(String label, String value) {
        this.label = label;
        this.value = value;
    }

    // Getter for label
    public String getLabel() {
        return label;
    }

    // Getter for value
    public String getValue() {
        return value;
    }

    // Static method to find VehicleType by value
    public static VehicleType fromValue(String value) {
        for (VehicleType type : VehicleType.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant with value: " + value);
    }
}
