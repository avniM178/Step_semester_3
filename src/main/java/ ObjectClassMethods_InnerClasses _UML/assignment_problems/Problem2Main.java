import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Problem2Main {

    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding =
                new CodingAssignment(
                        "Linked List Lab",
                        50,
                        LocalDate.of(2026, 3, 10)
                );

        Assignment written =
                new WrittenAssignment(
                        "Design Essay",
                        50,
                        LocalDate.of(2026, 3, 12)
                );

        Submission ashaSubmission =
                asha.submit(coding,
                        LocalDate.of(2026, 3, 10));

        Submission raviSubmission =
                ravi.submit(written,
                        LocalDate.of(2026, 3, 14));

        ashaSubmission.grade(45);

        raviSubmission.grade(40);

        ashaSubmission.resubmit(
                LocalDate.of(2026, 3, 11)
        );
    }
}



class Student {

    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Submission submit(
            Assignment assignment,
            LocalDate submissionDate) {

        return assignment.createSubmission(
                this,
                submissionDate
        );
    }
}



abstract class Assignment {

    private String title;
    private int maxMarks;
    private LocalDate dueDate;

    public Assignment(
            String title,
            int maxMarks,
            LocalDate dueDate) {

        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public abstract double applyLatePenalty(
            double awardedMarks,
            long lateDays
    );

    public Submission createSubmission(
            Student student,
            LocalDate submissionDate) {

        long lateDays = 0;

        if (submissionDate.isAfter(dueDate)) {
            lateDays =
                    ChronoUnit.DAYS.between(
                            dueDate,
                            submissionDate
                    );
        }

        Submission submission =
                new Submission(
                        student,
                        this,
                        submissionDate,
                        lateDays
                );

        System.out.println(
                student.getName() +
                "'s submission for '" +
                title +
                "' received " +
                (lateDays == 0
                        ? "(on time)"
                        : "(" + lateDays + " days late)")
                + "."
        );

        System.out.println(
                "Status: " +
                submission.getStatus()
        );

        return submission;
    }
}


class CodingAssignment extends Assignment {

    public CodingAssignment(
            String title,
            int maxMarks,
            LocalDate dueDate) {

        super(title, maxMarks, dueDate);
    }

    @Override
    public double applyLatePenalty(
            double awardedMarks,
            long lateDays) {

        double penalty =
                lateDays * 0.10;

        return awardedMarks * (1 - penalty);
    }
}


class WrittenAssignment extends Assignment {

    public WrittenAssignment(
            String title,
            int maxMarks,
            LocalDate dueDate) {

        super(title, maxMarks, dueDate);
    }

    @Override
    public double applyLatePenalty(
            double awardedMarks,
            long lateDays) {

        double penalty =
                lateDays * 0.20;

        return awardedMarks * (1 - penalty);
    }
}


class Submission {

    private Student student;
    private Assignment assignment;
    private LocalDate submissionDate;
    private long lateDays;

    private SubmissionStatus status;
    private double finalMarks;

    public Submission(
            Student student,
            Assignment assignment,
            LocalDate submissionDate,
            long lateDays) {

        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
        this.lateDays = lateDays;
        this.status = SubmissionStatus.SUBMITTED;
    }

    public SubmissionStatus getStatus() {
        return status;
    }

    public void grade(double awardedMarks) {

        if (status != SubmissionStatus.SUBMITTED) {

            System.out.println(
                    "Cannot grade this submission."
            );

            return;
        }

        if (awardedMarks < 0 ||
                awardedMarks > assignment.getMaxMarks()) {

            System.out.println(
                    "Invalid marks."
            );

            return;
        }

        finalMarks =
                assignment.applyLatePenalty(
                        awardedMarks,
                        lateDays
                );

        status = SubmissionStatus.GRADED;

        System.out.printf(
                "%s graded: %.0f/%d",
                student.getName(),
                finalMarks,
                assignment.getMaxMarks()
        );

        if (lateDays > 0) {

            double penalty =
                    (1 -
                    finalMarks / awardedMarks) * 100;

            System.out.printf(
                    " after %.0f%% late penalty",
                    penalty
            );
        }

        System.out.println(
                ". Status: " + status
        );
    }

    public void resubmit(LocalDate newDate) {

        if (status == SubmissionStatus.GRADED) {

            System.out.println(
                    "Cannot resubmit: '" +
                    assignment.getTitle() +
                    "' has already been graded."
            );

            return;
        }

        submissionDate = newDate;

        System.out.println(
                "Submission updated."
        );
    }
}


enum SubmissionStatus {
    SUBMITTED,
    GRADED
}