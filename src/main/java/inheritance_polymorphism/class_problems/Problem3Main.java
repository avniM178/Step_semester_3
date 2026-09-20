import java.util.Arrays;

public class Problem3Main {

    public static void main(String[] args) {

        StudentMember s =
                new StudentMember(
                    "STU5",
                    3,
                    "CSE"
                );

        s.chargeFine(100);

        System.out.println(
            s.getTotalFine()
        );

        int[] history = s.getFineHistory();

        history[0] = 999;

        System.out.println(
            Arrays.toString(
                s.getFineHistory()
            )
        );
    }
}


class LibraryMember {

    private String memberId;
    private int borrowLimit;
    private int booksBorrowed;

    private int[] fineHistory = new int[10];
    private int fineCount = 0;

    public LibraryMember(
            String memberId,
            int borrowLimit) {

        if (memberId == null ||
            memberId.trim().isEmpty() ||
            memberId.length() < 4) {

            throw new IllegalArgumentException(
                "Invalid member ID"
            );
        }

        if (borrowLimit <= 0) {
            throw new IllegalArgumentException(
                "Borrow limit must be positive"
            );
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    public void borrowBook() {

        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    protected void chargeFine(int amount) {

        if (amount > 0 &&
            fineCount < fineHistory.length) {

            fineHistory[fineCount] = amount;
            fineCount++;
        }
    }

    public int[] getFineHistory() {

        return Arrays.copyOf(
            fineHistory,
            fineCount
        );
    }

    public int getTotalFine() {

        int total = 0;

        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }

        return total;
    }
}


class StudentMember extends LibraryMember {

    private String course;

    public StudentMember(
            String memberId,
            int borrowLimit,
            String course) {

        super(memberId, borrowLimit);

        this.course = course;
    }

    @Override
    protected void chargeFine(int amount) {

        super.chargeFine(amount / 2);
    }
}