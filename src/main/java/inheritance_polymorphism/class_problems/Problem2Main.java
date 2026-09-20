public class Problem2Main {

    public static void main(String[] args) {

        LibraryMember member =
                new LibraryMember("STU1", 3);

        StudentMember student =
                new StudentMember("STU2", 3, "CSE");

        HonorsStudentMember honors =
                new HonorsStudentMember(
                    "STU3",
                    3,
                    "ECE",
                    2
                );

        FacultyMember faculty =
                new FacultyMember(
                    "STU4",
                    5,
                    "Physics"
                );

        System.out.println(member.displayInfo());
        System.out.println(student.displayInfo());
        System.out.println(honors.displayInfo());
        System.out.println(faculty.displayInfo());

        System.out.println(
            LibraryMember.classifyGeneration(honors)
        );

        System.out.println(
            LibraryMember.classifyGeneration(faculty)
        );

        student.borrowBook();
        student.borrowBook();

        honors.borrowBook();

        faculty.borrowBook();
        faculty.borrowBook();
        faculty.borrowBook();

        LibraryMember[] members = {
            student,
            honors,
            faculty
        };

        System.out.println(
            LibraryMember.getTotalBooksBorrowed(members)
        );
    }
}


class LibraryMember {

    private String memberId;
    private int borrowLimit;
    private int booksBorrowed;

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

    public String displayInfo() {

        return "General Member | Books Borrowed: "
                + booksBorrowed;
    }

    public static String classifyGeneration(
            LibraryMember member) {

        if (member instanceof HonorsStudentMember) {

            return "Multilevel descendant (3 generations deep)";

        } else if (member instanceof FacultyMember) {

            return "Hierarchical sibling (independent branch)";

        } else if (member instanceof StudentMember) {

            return "Direct child (2 generations deep)";

        } else {

            return "Base LibraryMember";
        }
    }

    public static int getTotalBooksBorrowed(
            LibraryMember[] members) {

        int total = 0;

        for (LibraryMember member : members) {
            total += member.getBooksBorrowed();
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

    public String getCourse() {
        return course;
    }

    @Override
    public String displayInfo() {

        return "Student Member | Course: "
                + course
                + " | Books Borrowed: "
                + getBooksBorrowed();
    }
}


class HonorsStudentMember extends StudentMember {

    private int bonusLimit;

    public HonorsStudentMember(
            String memberId,
            int borrowLimit,
            String course,
            int bonusLimit) {

        super(memberId, borrowLimit, course);

        this.bonusLimit = bonusLimit;
    }

    @Override
    public String displayInfo() {

        return "Honors Student Member | Course: "
                + getCourse()
                + " | Bonus Limit: "
                + bonusLimit
                + " | Books Borrowed: "
                + getBooksBorrowed();
    }
}


class FacultyMember extends LibraryMember {

    private String department;

    public FacultyMember(
            String memberId,
            int borrowLimit,
            String department) {

        super(memberId, borrowLimit);

        this.department = department;
    }

    @Override
    public String displayInfo() {

        return "Faculty Member | Department: "
                + department
                + " | Books Borrowed: "
                + getBooksBorrowed();
    }
}