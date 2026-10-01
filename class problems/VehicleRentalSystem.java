abstract class Vehicle {
    String name;
    boolean available = true;

    Vehicle(String name) {
        this.name = name;
    }

    abstract double calculateCharge(int days);
}

class Sedan extends Vehicle {
    Sedan(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 50;
    }
}

class SUV extends Vehicle {
    SUV(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 80;
    }
}

class Truck extends Vehicle {
    Truck(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 100;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Rental {
    Vehicle vehicle;
    Customer customer;
    int days;

    Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
    }

    double getCharge() {
        return vehicle.calculateCharge(days);
    }
}

public class VehicleRentalSystem {
    static void rentVehicle(Vehicle vehicle, Customer customer, int days) {
        if (!vehicle.available) {
            System.out.println(vehicle.name + " is currently unavailable.");
            return;
        }

        vehicle.available = false;
        Rental rental = new Rental(vehicle, customer, days);

        System.out.println(vehicle.name + " rented successfully by " + customer.name + ".");
        System.out.println("Rental charge: $" + rental.getCharge());
    }

    static void returnVehicle(Vehicle vehicle, Customer customer) {
        vehicle.available = true;
        System.out.println(vehicle.name + " returned by " + customer.name + ".");
    }

    public static void main(String[] args) {
        Customer customer1 = new Customer("Customer 1");
        Customer customer2 = new Customer("Customer 2");
        Customer customer3 = new Customer("Customer 3");

        Vehicle sedanA = new Sedan("Sedan A");
        Vehicle suvB = new SUV("SUV B");

        rentVehicle(sedanA, customer1, 3);
        rentVehicle(sedanA, customer2, 2);
        returnVehicle(sedanA, customer1);
        rentVehicle(suvB, customer3, 5);
    }
}
