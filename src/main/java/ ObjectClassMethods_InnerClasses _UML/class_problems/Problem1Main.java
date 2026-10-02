
public class Problem1Main {

    public static void main(String[] args) {

        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        Vehicle sedanA = new Sedan("Sedan A", 50);
        Vehicle suvB = new SUV("SUV B", 80);

        Rental rental1 = Rental.rentVehicle(c1, sedanA, 3);

        Rental rental2 = Rental.rentVehicle(c2, sedanA, 2);

        if (rental1 != null) {
            rental1.returnVehicle();
        }

        Rental rental3 = Rental.rentVehicle(c3, suvB, 5);
    }
}



class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}



abstract class Vehicle {

    private String vehicleName;
    private double baseRate;
    private boolean available;

    public Vehicle(String vehicleName, double baseRate) {
        this.vehicleName = vehicleName;
        this.baseRate = baseRate;
        this.available = true;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public double getBaseRate() {
        return baseRate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract double calculateCharge(int days);
}



class Sedan extends Vehicle {

    public Sedan(String vehicleName, double baseRate) {
        super(vehicleName, baseRate);
    }

    @Override
    public double calculateCharge(int days) {
        return getBaseRate() * days;
    }
}


class SUV extends Vehicle {

    public SUV(String vehicleName, double baseRate) {
        super(vehicleName, baseRate);
    }

    @Override
    public double calculateCharge(int days) {
        return (getBaseRate() * days) + 20;
    }
}


class Truck extends Vehicle {

    public Truck(String vehicleName, double baseRate) {
        super(vehicleName, baseRate);
    }

    @Override
    public double calculateCharge(int days) {
        return (getBaseRate() * days) + (30 * days);
    }
}


class Rental {

    private Customer customer;
    private Vehicle vehicle;
    private int days;

    private Rental(Customer customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
    }

    public static Rental rentVehicle(
            Customer customer,
            Vehicle vehicle,
            int days) {

        if (!vehicle.isAvailable()) {
            System.out.println(
                    vehicle.getVehicleName() +
                    " is currently unavailable."
            );
            return null;
        }

        Rental rental = new Rental(customer, vehicle, days);

        vehicle.setAvailable(false);

        System.out.println(
                vehicle.getVehicleName() +
                " rented successfully by " +
                customer.getName() + "."
        );

        System.out.println(
                "Rental charge: $" +
                vehicle.calculateCharge(days)
        );

        return rental;
    }

    public void returnVehicle() {

        vehicle.setAvailable(true);

        System.out.println(
                vehicle.getVehicleName() +
                " returned by " +
                customer.getName() + "."
        );
    }
}