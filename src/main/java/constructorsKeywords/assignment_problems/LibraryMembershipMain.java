class MembershipCard {

    private static String libraryName;
    private static String validUntil;

    static {
        libraryName = "SRM Central Library";
        validUntil = "May 2027";

        System.out.println("Library info loaded");
    }

    private String studentName;

    public MembershipCard(String studentName) {
        this.studentName = studentName;
    }

    public void printConfirmation() {
        System.out.println(
            "Membership card issued: " + studentName
        );
    }
}

public class LibraryMembershipMain {

    public static void main(String[] args) {

        String[] names = {
            "Ananya",
            "Rohan",
            "Priya",
            "Arjun",
            "Sneha"
        };

        for (String name : names) {

            MembershipCard card =
                new MembershipCard(name);

            card.printConfirmation();
        }
    }
}