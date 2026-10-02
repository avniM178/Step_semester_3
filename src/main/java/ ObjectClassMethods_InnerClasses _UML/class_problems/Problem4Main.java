import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Problem4Main {

    public static void main(String[] args) {

        Customer customerA = new Customer("Customer A");
        Customer customerB = new Customer("Customer B");
        Customer customerC = new Customer("Customer C");

        Room room101 = new StandardRoom("101", 100);
        Room room201 = new DeluxeRoom("201", 180);

        Hotel hotel = new Hotel();

        LocalDate jan1 = LocalDate.of(2026, 1, 1);
        LocalDate jan5 = LocalDate.of(2026, 1, 5);

        LocalDate jan3 = LocalDate.of(2026, 1, 3);
        LocalDate jan7 = LocalDate.of(2026, 1, 7);

        System.out.println(
                hotel.isAvailable(room101, jan1, jan5)
        );

        Reservation r1 = hotel.reserve(
                customerA,
                room101,
                jan1,
                jan5
        );

        hotel.reserve(
                customerB,
                room101,
                jan3,
                jan7
        );

        if (r1 != null) {
            r1.cancel(LocalDate.of(2025, 12, 20));
        }

        Reservation r2 = hotel.reserve(
                customerC,
                room201,
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 12)
        );
    }
}


// ---------------- Customer ----------------

class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


// ---------------- Room ----------------

abstract class Room {

    private String roomNumber;
    private double pricePerDay;

    public Room(
            String roomNumber,
            double pricePerDay) {

        this.roomNumber = roomNumber;
        this.pricePerDay = pricePerDay;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public abstract double calculatePrice(long days);
}


// ---------------- Standard Room ----------------

class StandardRoom extends Room {

    public StandardRoom(
            String roomNumber,
            double pricePerDay) {

        super(roomNumber, pricePerDay);
    }

    @Override
    public double calculatePrice(long days) {

        return getPricePerDay() * days;
    }
}


// ---------------- Deluxe Room ----------------

class DeluxeRoom extends Room {

    public DeluxeRoom(
            String roomNumber,
            double pricePerDay) {

        super(roomNumber, pricePerDay);
    }

    @Override
    public double calculatePrice(long days) {

        return (getPricePerDay() * days) + 50;
    }
}


// ---------------- Suite ----------------

class Suite extends Room {

    public Suite(
            String roomNumber,
            double pricePerDay) {

        super(roomNumber, pricePerDay);
    }

    @Override
    public double calculatePrice(long days) {

        return (getPricePerDay() * days) + (100 * days);
    }
}


// ---------------- Reservation ----------------

class Reservation {

    private Customer customer;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean cancelled;

    public Reservation(
            Customer customer,
            Room room,
            LocalDate startDate,
            LocalDate endDate) {

        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cancelled = false;
    }

    public boolean overlaps(
            LocalDate newStart,
            LocalDate newEnd) {

        if (cancelled) {
            return false;
        }

        return newStart.isBefore(endDate)
                && newEnd.isAfter(startDate);
    }

    public void cancel(LocalDate cancellationDate) {

        LocalDate deadline =
                startDate.minusDays(1);

        if (cancellationDate.isAfter(deadline)) {

            System.out.println(
                    "Cancellation deadline has passed."
            );

            return;
        }

        cancelled = true;

        System.out.println(
                "Reservation for " +
                customer.getName() +
                ", Standard Room " +
                room.getRoomNumber() +
                " cancelled successfully."
        );
    }

    public boolean isCancelled() {
        return cancelled;
    }
}


// ---------------- Hotel ----------------

class Hotel {

    private List<Reservation> reservations;

    public Hotel() {
        reservations = new ArrayList<>();
    }

    public boolean isAvailable(
            Room room,
            LocalDate startDate,
            LocalDate endDate) {

        for (Reservation reservation : reservations) {

            if (reservation.overlaps(startDate, endDate)) {

                System.out.println(
                        "Room " +
                        room.getRoomNumber() +
                        " is not available from " +
                        startDate +
                        " to " +
                        endDate
                );

                return false;
            }
        }

        System.out.println(
                "Room " +
                room.getRoomNumber() +
                " is available from " +
                startDate +
                " to " +
                endDate
        );

        return true;
    }

    public Reservation reserve(
            Customer customer,
            Room room,
            LocalDate startDate,
            LocalDate endDate) {

        if (!isAvailable(room, startDate, endDate)) {
            return null;
        }

        Reservation reservation =
                new Reservation(
                        customer,
                        room,
                        startDate,
                        endDate
                );

        reservations.add(reservation);

        long days =
                ChronoUnit.DAYS.between(
                        startDate,
                        endDate
                );

        System.out.println(
                "Reservation confirmed for " +
                customer.getName() +
                ", Room " +
                room.getRoomNumber() +
                " (" +
                startDate +
                " to " +
                endDate +
                ")."
        );

        System.out.println(
                "Price: $" +
                room.calculatePrice(days)
        );

        return reservation;
    }
}