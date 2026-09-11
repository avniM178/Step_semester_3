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

class AccessChecker {

    public static String classifyAccess(
        String fieldModifier,
        String accessorContext
    ) {

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("private")) {

            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("default")) {

            if (
                accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE")
            ) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("protected")) {

            if (
                accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE")
            ) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        return "DENIED";
    }

    public static String summarizeBatch(
        String[][] attempts
    ) {

        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts) {

            String result =
                classifyAccess(
                    attempt[0],
                    attempt[1]
                );

            if (result.equals("ALLOWED")) {
                allowed++;
            } else {
                denied++;
            }
        }

        return "Allowed: " +
               allowed +
               " | Denied: " +
               denied;
    }
}

public class MovieTicketMain {

    public static void main(String[] args) {

        System.out.println(
            AccessChecker.classifyAccess(
                "private",
                "SAME_CLASS"
            )
        );

        System.out.println(
            AccessChecker.classifyAccess(
                "protected",
                "DIFFERENT_PACKAGE"
            )
        );

        String[][] attempts = {
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
            AccessChecker.summarizeBatch(attempts)
        );
    }
}