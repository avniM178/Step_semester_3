public class Problem4Main {

    public static void main(String[] args) {

        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        MembershipPlan quarterly =
                new QuarterlyPlan();

        MembershipPlan monthly =
                new MonthlyPlan();

        Membership ashaMembership =
                asha.buyMembership(quarterly);

        Membership raviMembership =
                ravi.buyMembership(monthly);

        ashaMembership.checkIn();

        ashaMembership.freeze();

        ashaMembership.checkIn();

        raviMembership.expire();

        raviMembership.freeze();
    }
}

class Member {

    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Membership buyMembership(
            MembershipPlan plan) {

        Membership membership =
                new Membership(this, plan);

        System.out.println(
                plan.getName() +
                " membership created for " +
                name +
                "."
        );

        System.out.printf(
                "Fee: ₹%.2f%n",
                plan.calculateFee()
        );

        System.out.println(
                "Status: " +
                membership.getStatus()
        );

        return membership;
    }
}

abstract class MembershipPlan {

    protected static final double BASE_RATE = 1000;

    public abstract String getName();

    public abstract double calculateFee();
}

class MonthlyPlan extends MembershipPlan {

    @Override
    public String getName() {
        return "Monthly";
    }

    @Override
    public double calculateFee() {
        return BASE_RATE;
    }
}

class QuarterlyPlan extends MembershipPlan {

    @Override
    public String getName() {
        return "Quarterly";
    }

    @Override
    public double calculateFee() {

        return BASE_RATE * 3 * 0.90;
    }
}

class AnnualPlan extends MembershipPlan {

    @Override
    public String getName() {
        return "Annual";
    }

    @Override
    public double calculateFee() {

        return BASE_RATE * 12 * 0.75;
    }
}

class Membership {

    private Member member;
    private MembershipPlan plan;
    private MembershipStatus status;

    public Membership(
            Member member,
            MembershipPlan plan) {

        this.member = member;
        this.plan = plan;
        this.status = MembershipStatus.ACTIVE;
    }

    public MembershipStatus getStatus() {
        return status;
    }

    public void checkIn() {

        if (status != MembershipStatus.ACTIVE) {

            System.out.println(
                    "Check-in denied: " +
                    member.getName() +
                    "'s membership is " +
                    status +
                    "."
            );

            return;
        }

        System.out.println(
                member.getName() +
                " checked in successfully."
        );
    }

    public void freeze() {

        if (status == MembershipStatus.EXPIRED) {

            System.out.println(
                    "Cannot freeze an Expired membership."
            );

            return;
        }

        if (status == MembershipStatus.FROZEN) {
            return;
        }

        status = MembershipStatus.FROZEN;

        System.out.println(
                member.getName() +
                "'s membership frozen."
        );

        System.out.println(
                "Status: " + status
        );
    }

    public void unfreeze() {

        if (status == MembershipStatus.EXPIRED) {

            System.out.println(
                    "Cannot unfreeze an Expired membership."
            );

            return;
        }

        if (status != MembershipStatus.FROZEN) {
            return;
        }

        status = MembershipStatus.ACTIVE;

        System.out.println(
                member.getName() +
                "'s membership unfrozen."
        );

        System.out.println(
                "Status: " + status
        );
    }

    public void expire() {

        if (status == MembershipStatus.EXPIRED) {
            return;
        }

        status = MembershipStatus.EXPIRED;

        System.out.println(
                member.getName() +
                "'s membership expired."
        );

        System.out.println(
                "Status: " + status
        );
    }
}

enum MembershipStatus {
    ACTIVE,
    FROZEN,
    EXPIRED
}