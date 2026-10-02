import java.time.LocalDateTime;
import java.util.*;

public class Problem3Main {

    public static void main(String[] args) {

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Show show = new Show(
                "Campus Premiere",
                LocalDateTime.of(2026, 4, 10, 19, 0)
        );

        Seat a1 = new RegularSeat("A1");
        Seat a2 = new RegularSeat("A2");
        Seat f5 = new PremiumSeat("F5");
        Seat r1 = new ReclinerSeat("R1");

        List<Seat> ashaSeats =
                Arrays.asList(a1, a2, f5);

        Booking ashaBooking =
                show.book(asha, ashaSeats);

        show.book(
                ravi,
                Arrays.asList(a2)
        );

        Booking raviBooking =
                show.book(
                        ravi,
                        Arrays.asList(r1)
                );

        if (ashaBooking != null) {
            ashaBooking.cancel(
                    LocalDateTime.of(
                            2026, 4, 10, 18, 0
                    )
            );
        }

        show.book(
                neha,
                Arrays.asList(a2)
        );
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

abstract class Seat {

    private String seatNumber;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public abstract double getPrice();
}

class RegularSeat extends Seat {

    public RegularSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 150;
    }
}

class PremiumSeat extends Seat {

    public PremiumSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 250;
    }
}

class ReclinerSeat extends Seat {

    public ReclinerSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 400;
    }
}

class Show {

    private String name;
    private LocalDateTime startTime;

    private Set<String> bookedSeats;

    public Show(
            String name,
            LocalDateTime startTime) {

        this.name = name;
        this.startTime = startTime;
        bookedSeats = new HashSet<>();
    }

    public Booking book(
            Customer customer,
            List<Seat> seats) {

        if (seats.isEmpty()) {
            return null;
        }

        if (seats.size() > 6) {

            System.out.println(
                    "Maximum 6 seats allowed per booking."
            );

            return null;
        }

        for (Seat seat : seats) {

            if (bookedSeats.contains(
                    seat.getSeatNumber())) {

                System.out.println(
                        "Seat " +
                        seat.getSeatNumber() +
                        " is already booked for this show."
                );

                return null;
            }
        }

        bookedSeats.addAll(
                getSeatNumbers(seats)
        );

        Booking booking =
                new Booking(
                        customer,
                        this,
                        seats
                );

        System.out.print(
                "Booking confirmed for " +
                customer.getName() +
                ": "
        );

        for (int i = 0; i < seats.size(); i++) {

            System.out.print(
                    seats.get(i).getSeatNumber()
            );

            if (i < seats.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.println();

        System.out.printf(
                "Total: ₹%.2f%n",
                booking.calculateTotal()
        );

        return booking;
    }

    private Set<String> getSeatNumbers(
            List<Seat> seats) {

        Set<String> numbers = new HashSet<>();

        for (Seat seat : seats) {
            numbers.add(seat.getSeatNumber());
        }

        return numbers;
    }

    public void releaseSeats(List<Seat> seats) {

        for (Seat seat : seats) {
            bookedSeats.remove(
                    seat.getSeatNumber()
            );
        }
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}


class Booking {

    private Customer customer;
    private Show show;
    private List<Seat> seats;
    private boolean cancelled;

    public Booking(
            Customer customer,
            Show show,
            List<Seat> seats) {

        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>(seats);
        this.cancelled = false;
    }

    public double calculateTotal() {

        double total = 0;

        for (Seat seat : seats) {
            total += seat.getPrice();
        }

        return total;
    }

    public void cancel(
            LocalDateTime cancellationTime) {

        if (cancelled) {
            return;
        }

        if (!cancellationTime.isBefore(
                show.getStartTime())) {

            System.out.println(
                    "Cannot cancel booking after the show starts."
            );

            return;
        }

        show.releaseSeats(seats);

        cancelled = true;

        System.out.println(
                customer.getName() +
                "'s booking cancelled."
        );

        System.out.println(
                "Seats released."
        );
    }
}