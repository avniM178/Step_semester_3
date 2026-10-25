import java.util.*;

public class Problem2Main {
    interface Chargeable {
        void bookChargingBay();
    }

    static class Vehicle {
        String passNumber, owner, type;

        Vehicle(String passNumber, String owner, String type) {
            this.passNumber = passNumber;
            this.owner = owner;
            this.type = type;
        }

        int passFee() {
            return type.equals("Bike") || type.equals("E-Bike")
                    || type.equals("EBike") ? 300 : 1000;
        }
    }

    static class ElectricVehicle extends Vehicle implements Chargeable {
        ElectricVehicle(String passNumber, String owner, String type) {
            super(passNumber, owner, type);
        }

        public void bookChargingBay() {
            System.out.println(passNumber + " charging bay allotted");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new LinkedHashMap<>();

        while (sc.hasNext()) {
            String operation = sc.next();

            if (operation.equals("PASS")) {
                String type = sc.next();
                String pass = sc.next();
                String owner = sc.next();

                Vehicle vehicle;

                if (type.equals("E-Bike") || type.equals("E-Car")
                        || type.equals("EBike") || type.equals("ECar")) {
                    vehicle = new ElectricVehicle(pass, owner, type);
                } else {
                    vehicle = new Vehicle(pass, owner, type);
                }

                vehicles.put(pass, vehicle);
                System.out.println(pass + " (" + type + ") pass fee "
                        + vehicle.passFee());

            } else if (operation.equals("CHARGE")) {
                String pass = sc.next();
                Vehicle vehicle = vehicles.get(pass);

                if (vehicle == null) {
                    System.out.println("Vehicle not found");
                } else if (vehicle instanceof Chargeable) {
                    ((Chargeable) vehicle).bookChargingBay();
                } else {
                    System.out.println(pass + " rejected: charging unsupported");
                }
            }
        }

        sc.close();
    }
}
