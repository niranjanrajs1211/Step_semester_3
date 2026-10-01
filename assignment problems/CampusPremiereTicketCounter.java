import java.util.*;

interface Seat {
    String getId();
    double getPrice();
}

class RegularSeat implements Seat {
    private String id;

    RegularSeat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return 150;
    }
}

class PremiumSeat implements Seat {
    private String id;

    PremiumSeat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return 250;
    }
}

class ReclinerSeat implements Seat {
    private String id;

    ReclinerSeat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return 400;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Show {
    private String time;
    private Set<String> bookedSeats = new HashSet<>();

    Show(String time) {
        this.time = time;
    }

    boolean isAvailable(Seat seat) {
        return !bookedSeats.contains(seat.getId());
    }

    void reserve(Seat seat) {
        bookedSeats.add(seat.getId());
    }

    void release(Seat seat) {
        bookedSeats.remove(seat.getId());
    }
}

class Booking {
    Customer customer;
    Show show;
    List<Seat> seats;
    boolean cancelled;

    Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = seats;
        this.cancelled = false;
    }

    double getTotal() {
        double total = 0;
        for (Seat seat : seats) {
            total += seat.getPrice();
        }
        return total;
    }

    void cancel() {
        if (!cancelled) {
            for (Seat seat : seats) {
                show.release(seat);
            }
            cancelled = true;
            System.out.println(customer.name + "'s booking cancelled.");
            System.out.println("Seats released.");
        }
    }
}

public class CampusPremiereTicketCounter {
    public static void main(String[] args) {
        Show show = new Show("7 PM");

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Seat a1 = new RegularSeat("A1");
        Seat a2 = new RegularSeat("A2");
        Seat f5 = new PremiumSeat("F5");
        Seat r1 = new ReclinerSeat("R1");

        List<Seat> ashaSeats = Arrays.asList(a1, a2, f5);

        boolean available = true;

        for (Seat seat : ashaSeats) {
            if (!show.isAvailable(seat)) {
                available = false;
            }
        }

        if (available && ashaSeats.size() <= 6) {
            for (Seat seat : ashaSeats) {
                show.reserve(seat);
            }

            Booking booking = new Booking(asha, show, ashaSeats);

            System.out.println("Booking confirmed for Asha: A1, A2, F5.");
            System.out.printf("Total: ₹%.2f%n", booking.getTotal());

            if (!show.isAvailable(a2)) {
                System.out.println("Seat A2 is already booked for this show.");
            }

            List<Seat> raviSeats = Arrays.asList(r1);

            for (Seat seat : raviSeats) {
                show.reserve(seat);
            }

            Booking raviBooking = new Booking(ravi, show, raviSeats);

            System.out.println("Booking confirmed for Ravi: R1.");
            System.out.printf("Total: ₹%.2f%n", raviBooking.getTotal());

            booking.cancel();
            System.out.println("Seats A1, A2, F5 released.");

            if (show.isAvailable(a2)) {
                show.reserve(a2);
                Booking nehaBooking = new Booking(
                        neha, show, Arrays.asList(a2));

                System.out.println("Booking confirmed for Neha: A2.");
                System.out.printf("Total: ₹%.2f%n", nehaBooking.getTotal());
            }
        }
    }
}
