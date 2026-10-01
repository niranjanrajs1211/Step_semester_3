import java.util.*;

abstract class Room {
    int number;
    boolean reserved = false;

    Room(int number) {
        this.number = number;
    }

    abstract double calculatePrice(int days);
}

class StandardRoom extends Room {
    StandardRoom(int number) {
        super(number);
    }

    double calculatePrice(int days) {
        return days * 100;
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(int number) {
        super(number);
    }

    double calculatePrice(int days) {
        return days * 150;
    }
}

class Suite extends Room {
    Suite(int number) {
        super(number);
    }

    double calculatePrice(int days) {
        return days * 250;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Reservation {
    Customer customer;
    Room room;
    String startDate;
    String endDate;
    int days;
    boolean active = true;

    Reservation(Customer customer, Room room, String startDate, String endDate, int days) {
        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
    }

    double getPrice() {
        return room.calculatePrice(days);
    }

    void cancel() {
        if (active) {
            active = false;
            room.reserved = false;
            System.out.println("Reservation for " + customer.name + ", Room " + room.number + " cancelled successfully.");
        }
    }
}

public class HotelBookingSystem {
    static Reservation reserve(Customer customer, Room room, String start, String end, int days) {
        if (room.reserved) {
            System.out.println("Room " + room.number + " is not available from " + start + " to " + end + ".");
            return null;
        }

        room.reserved = true;
        Reservation reservation = new Reservation(customer, room, start, end, days);

        System.out.println("Reservation confirmed for " + customer.name + ", Room " + room.number + " (" + start + "-" + end + ").");
        System.out.println("Price: $" + reservation.getPrice());

        return reservation;
    }

    public static void main(String[] args) {
        Customer customerA = new Customer("Customer A");
        Customer customerC = new Customer("Customer C");

        Room room101 = new StandardRoom(101);
        Room room201 = new DeluxeRoom(201);

        System.out.println("Standard Room 101 is available from Jan 1 to Jan 5.");

        Reservation reservation = reserve(customerA, room101, "Jan 1", "Jan 5", 4);

        reserve(new Customer("Customer B"), room101, "Jan 3", "Jan 7", 4);

        if (reservation != null)
            reservation.cancel();

        reserve(customerC, room201, "Feb 10", "Feb 12", 2);
    }
}
