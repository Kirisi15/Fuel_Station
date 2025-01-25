package Fuel_Station.Fuel_Station.enums;

public enum VehicleType {

        CAR("car"),LORRY("lorry"), BIKE("bike"),THREEWHEELER("threewheeler"), BUS("bus");

        private final String type;

        VehicleType(String string) {
            type = string;
        }

        @Override
        public String toString() {
            return type;
        }

}
