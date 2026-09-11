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

    public String getMembershipPin() {
        return membershipPin;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public double getFinesOwed() {
        return finesOwed;
    }

    public String getDisplayName() {
        return displayName;
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
                    || accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }


    public static String summarizeByModifier(
            String[][] attempts) {

        String[] modifiers = {
                "private",
                "default",
                "protected",
                "public"
        };

        int[] allowed = new int[4];
        int[] denied = new int[4];

        for (String[] attempt : attempts) {

            String modifier = attempt[0];
            String context = attempt[1];

            String result = classifyAccess(
                    modifier,
                    context
            );

            int index = getModifierIndex(modifier);

            if (index == -1) {
                continue;
            }

            if (result.equals("ALLOWED")) {
                allowed[index]++;
            } else {
                denied[index]++;
            }
        }

        return "private: "
                + allowed[0] + " allowed / "
                + denied[0] + " denied | "
                + "default: "
                + allowed[1] + " allowed / "
                + denied[1] + " denied | "
                + "protected: "
                + allowed[2] + " allowed / "
                + denied[2] + " denied | "
                + "public: "
                + allowed[3] + " allowed / "
                + denied[3] + " denied";
    }


    private static int getModifierIndex(String modifier) {

        if (modifier.equals("private")) {
            return 0;
        }

        if (modifier.equals("default")) {
            return 1;
        }

        if (modifier.equals("protected")) {
            return 2;
        }

        if (modifier.equals("public")) {
            return 3;
        }

        return -1;
    }
}


public class MembershipFieldReachCheckerMain {

    public static void main(String[] args) {

        LibraryMember member = new LibraryMember(
                "PIN-8841",
                "BR-102",
                125.50,
                "Priya Nair"
        );

        System.out.println("Library Member");
        System.out.println("-------------------------");
        System.out.println("Membership PIN: "
                + member.getMembershipPin());
        System.out.println("Branch Code: "
                + member.getBranchCode());
        System.out.println("Fines Owed: "
                + member.getFinesOwed());
        System.out.println("Display Name: "
                + member.getDisplayName());

        System.out.println();


        // Individual access checks

        System.out.println("Access Checks");
        System.out.println("-------------------------");

        System.out.println(
                "private + SAME_CLASS: "
                + AccessChecker.classifyAccess(
                        "private",
                        "SAME_CLASS"
                )
        );

        System.out.println(
                "private + SAME_PACKAGE: "
                + AccessChecker.classifyAccess(
                        "private",
                        "SAME_PACKAGE"
                )
        );

        System.out.println(
                "default + SAME_PACKAGE: "
                + AccessChecker.classifyAccess(
                        "default",
                        "SAME_PACKAGE"
                )
        );

        System.out.println(
                "default + DIFFERENT_PACKAGE: "
                + AccessChecker.classifyAccess(
                        "default",
                        "DIFFERENT_PACKAGE"
                )
        );

        System.out.println(
                "protected + SAME_CLASS: "
                + AccessChecker.classifyAccess(
                        "protected",
                        "SAME_CLASS"
                )
        );

        System.out.println(
                "protected + DIFFERENT_PACKAGE: "
                + AccessChecker.classifyAccess(
                        "protected",
                        "DIFFERENT_PACKAGE"
                )
        );

        System.out.println(
                "public + DIFFERENT_PACKAGE: "
                + AccessChecker.classifyAccess(
                        "public",
                        "DIFFERENT_PACKAGE"
                )
        );

        System.out.println();




        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println("Summary By Modifier");
        System.out.println("-------------------------");

        System.out.println(
                AccessChecker.summarizeByModifier(attempts)
        );
    }
}