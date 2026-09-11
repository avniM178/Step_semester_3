class LibraryMember {

    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(
            String membershipPin,
            String branchCode,
            double finesOwed,
            String displayName) {

        this.membershipPin = membershipPin;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}


class AccessChecker {

    public static String classifyAccess(
            String fieldModifier,
            String accessorContext) {

        if (fieldModifier.equals("private")) {

            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("default")) {

            if (accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")
                    || accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {

                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }


    public static String firstDeniedAttempt(String[][] attempts) {

        for (int i = 0; i < attempts.length; i++) {

            String modifier = attempts[i][0];
            String context = attempts[i][1];

            String result = classifyAccess(modifier, context);

            if (result.equals("DENIED")) {

                return modifier
                        + " via "
                        + context
                        + " (attempt #"
                        + (i + 1)
                        + ")";
            }
        }

        return "None Denied";
    }
}


public class ReferenceDeskSubclassReachMain {

    public static void main(String[] args) {

        System.out.println("Access Checks");
        System.out.println("-------------------------");

        System.out.println(
                "protected + SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE: "
                + AccessChecker.classifyAccess(
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
                )
        );

        System.out.println(
                "protected + SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE: "
                + AccessChecker.classifyAccess(
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
                )
        );

        System.out.println(
                "private + SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE: "
                + AccessChecker.classifyAccess(
                        "private",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
                )
        );

        System.out.println(
                "default + SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE: "
                + AccessChecker.classifyAccess(
                        "default",
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
                )
        );

        System.out.println();


        String[][] attempts = {
                {"public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
                {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
                {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };

        System.out.println("First Denied Attempt");
        System.out.println("-------------------------");

        System.out.println(
                AccessChecker.firstDeniedAttempt(attempts)
        );


        System.out.println();


        String[][] allowedAttempts = {
                {"public", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},
                {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };

        System.out.println("Second Test");
        System.out.println("-------------------------");

        System.out.println(
                AccessChecker.firstDeniedAttempt(allowedAttempts)
        );
    }
}