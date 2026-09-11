class LibraryMember {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    public LibraryMember() {
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {

        if (membershipId == null) {
            membershipId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {

        if (answer == null) {
            securityAnswer = null;
            return;
        }

        securityAnswer = oneWayTransform(answer);
    }

    private String oneWayTransform(String value) {

        int hash = 7;

        for (int i = 0; i < value.length(); i++) {
            hash = hash * 31 + value.charAt(i);
        }

        return Integer.toHexString(hash);
    }
}


public class LibraryMemberJavaBeanMain {

    public static void main(String[] args) {

        LibraryMember member =
                new LibraryMember();



        member.setMembershipId("LIB-8841");

        System.out.println(
                "Membership ID: "
                + member.getMembershipId()
        );


        member.setMembershipId("FAKE-0000");

        System.out.println(
                "After second ID update: "
                + member.getMembershipId()
        );


        member.setName("Priya Nair");

        System.out.println(
                "Name: "
                + member.getName()
        );



        member.setPremiumMember(true);

        System.out.println(
                "Premium Member: "
                + member.isPremiumMember()
        );


        member.setSecurityAnswer("BlueMountain");

        System.out.println(
                "Security answer stored using one-way transformation."
        );
    }
}