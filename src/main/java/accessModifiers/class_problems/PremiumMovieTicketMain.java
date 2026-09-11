class MovieTicket {

    private String seatNumber;
    String screenId;
    protected double ticketPrice;
    public String movieTitle;

    public MovieTicket(
        String seatNumber,
        String screenId,
        double ticketPrice,
        String movieTitle
    ) {
        this.seatNumber = seatNumber;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieTitle;
    }
}

class PremiumMovieTicket extends MovieTicket {

    public PremiumMovieTicket(
        String seatNumber,
        String screenId,
        double ticketPrice,
        String movieTitle
    ) {
        super(
            seatNumber,
            screenId,
            ticketPrice,
            movieTitle
        );
    }

    public double getProtectedPrice() {
        return ticketPrice;
    }
}

class AccessChecker {

    public static String classifyAccess(
        String fieldModifier,
        String accessorContext
    ) {

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("private")) {
            return accessorContext.equals("SAME_CLASS")
                ? "ALLOWED"
                : "DENIED";
        }

        if (fieldModifier.equals("default")) {

            return (
                accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE")
            )
                ? "ALLOWED"
                : "DENIED";
        }

        if (fieldModifier.equals("protected")) {

            if (
                accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE") ||
                accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
                )
            ) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        return "DENIED";
    }
}

public class PremiumMovieTicketMain {

    public static void main(String[] args) {

        System.out.println(
            AccessChecker.classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
            )
        );

        System.out.println(
            AccessChecker.classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
            )
        );

        PremiumMovieTicket ticket =
            new PremiumMovieTicket(
                "A12",
                "SCREEN-3",
                450,
                "Avengers"
            );

        System.out.println(
            "Protected price: Rs " +
            ticket.getProtectedPrice()
        );
    }
}