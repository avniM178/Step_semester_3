class BookingReceipt {

    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        this.seatNumbers = seatNumbers.clone();
    }

    public String getBookingId() {
        return bookingId;
    }

    public String[] getSeatNumbers() {
        return seatNumbers.clone();
    }

    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        if (index < 0 || index >= seatNumbers.length) {
            return this;
        }

        String[] updatedSeats = seatNumbers.clone();
        updatedSeats[index] = newSeat;

        return new BookingReceipt(bookingId, updatedSeats);
    }

    public static String processNightlySettlement(
            BookingReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        for (BookingReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof GroupBookingReceipt) {
                groupCount++;
            } else {
                individualCount++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + groupCount + " group | "
                + individualCount + " individual";
    }
}


class GroupBookingReceipt extends BookingReceipt {

    private final int groupSize;

    public GroupBookingReceipt(
            String bookingId,
            String[] seatNumbers,
            int groupSize) {

        super(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}


public class BookingReceiptMain {

    public static void main(String[] args) {

        // Test 1: Defensive copy
        String[] seats = {"A1", "A2"};

        BookingReceipt receipt =
                new BookingReceipt("CH-1001", seats);

        String[] returnedSeats = receipt.getSeatNumbers();

        returnedSeats[0] = "X1";

        System.out.println(
                "Original first seat: "
                + receipt.getSeatNumbers()[0]
        );


        // Test 2: With-style update
        BookingReceipt updatedReceipt =
                receipt.withUpdatedSeat(1, "A3");

        System.out.println(
                "Original seats: "
                + String.join(", ", receipt.getSeatNumbers())
        );

        System.out.println(
                "Updated seats: "
                + String.join(", ", updatedReceipt.getSeatNumbers())
        );


        // Test 3: Group booking
        GroupBookingReceipt groupReceipt =
                new GroupBookingReceipt(
                        "CH-2002",
                        new String[]{"B1", "B2"},
                        2
                );

        System.out.println(
                "Group size: "
                + groupReceipt.getGroupSize()
        );


        // Test 4: Nightly settlement
        BookingReceipt[] batch = {
                groupReceipt,
                null,
                new BookingReceipt(
                        "CH-3003",
                        new String[]{"C1"}
                )
        };

        System.out.println(
                BookingReceipt.processNightlySettlement(batch)
        );
    }
}