package Fuel_Station.Fuel_Station.enums;

public enum VehicleType {

        CAR("car"),LORRY("lorry"), MOTORCYCLE("Motorcycle"),BIKE("bike"),THREEWHEELER("threewheeler"), BUS("bus"), TRUCK("Truck"),
    VAN("Van"), SUV("SUV"),TRACTOR("Tractor"),PICKUP_TRUCK("Pickup_Truck");

        private final String type;

        VehicleType(String string) {
            type = string;
        }

        @Override
        public String toString() {
            return type;
        }

}
