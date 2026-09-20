public class Problem1Main {

    public static void main(String[] args) {

        PremiumMember p = new PremiumMember(
            "MEM01",
            2000,
            "Coach Riya"
        );

        p.attendSession();
        p.attendSession();

        System.out.println(p.getSessionsAttended());

        String[] memberIds = {
            "MEM1",
            "GM1",
            "MEM2",
            " ",
            "MEM3"
        };

        System.out.println(
            GymMember.signUpBatch(memberIds, 1000)
        );
    }
}


class GymMember {

    private String memberId;
    private int monthlyFee;
    private int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {

        if (memberId == null ||
            memberId.trim().isEmpty() ||
            memberId.length() < 4) {

            throw new IllegalArgumentException("Invalid member ID");
        }

        if (monthlyFee <= 0) {
            throw new IllegalArgumentException("Invalid monthly fee");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void attendSession() {

        sessionsAttended++;
    }

    public int getSessionsAttended() {

        return sessionsAttended;
    }

    public static String signUpBatch(
        String[] memberIds,
        int monthlyFee
    ) {

        int signedUp = 0;
        int rejected = 0;

        for (String memberId : memberIds) {

            try {
                new GymMember(memberId, monthlyFee);
                signedUp++;

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Signed Up: "
            + signedUp
            + " | Rejected: "
            + rejected;
    }
}


class PremiumMember extends GymMember {

    private String trainerName;

    public PremiumMember(
        String memberId,
        int monthlyFee,
        String trainerName
    ) {

        super(memberId, monthlyFee);

        this.trainerName = trainerName;
    }

    public String getTrainerName() {

        return trainerName;
    }
}