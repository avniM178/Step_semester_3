import java.util.*;

public class Problem5Main {

    public static void main(String[] args) {

        Student asha =
                new Student("Asha", "CSE");

        Student ravi =
                new Student("Ravi", "ECE");

        asha.addChannel(
                new EmailChannel()
        );

        asha.addChannel(
                new AppChannel()
        );

        ravi.addChannel(
                new SmsChannel()
        );

        NoticeBoard board =
                new NoticeBoard();

        board.addStudent(asha);
        board.addStudent(ravi);

        Notice notice1 =
                new Notice(
                        "Lab Closed Tomorrow",
                        new HashSet<>(
                                Arrays.asList("CSE")
                        )
                );

        board.postNotice(notice1);


        Notice notice2 =
                new Notice(
                        "Fee Deadline Extended",
                        new HashSet<>(
                                Arrays.asList(
                                        "CSE",
                                        "ECE"
                                )
                        )
                );

        board.postNotice(notice2);


        Notice invalidNotice =
                new Notice(
                        "Sports Day",
                        new HashSet<>()
                );

        board.postNotice(invalidNotice);
    }
}

class Student {

    private String name;
    private String department;

    private List<NotificationChannel> channels;

    public Student(
            String name,
            String department) {

        this.name = name;
        this.department = department;

        channels = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void addChannel(
            NotificationChannel channel) {

        channels.add(channel);
    }

    public List<NotificationChannel> getChannels() {
        return channels;
    }
}

class Notice {

    private String title;
    private Set<String> targetDepartments;

    public Notice(
            String title,
            Set<String> targetDepartments) {

        this.title = title;
        this.targetDepartments =
                new HashSet<>(targetDepartments);
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getTargetDepartments() {
        return targetDepartments;
    }

    public boolean isValid() {

        return title != null &&
                !title.trim().isEmpty() &&
                !targetDepartments.isEmpty();
    }
}

interface NotificationChannel {

    void send(Student student, Notice notice);

    String getChannelName();
}

class EmailChannel
        implements NotificationChannel {

    @Override
    public void send(
            Student student,
            Notice notice) {

        System.out.println(
                "[Email → " +
                student.getName() +
                "] " +
                notice.getTitle()
        );
    }

    @Override
    public String getChannelName() {
        return "Email";
    }
}

class SmsChannel
        implements NotificationChannel {

    @Override
    public void send(
            Student student,
            Notice notice) {

        System.out.println(
                "[SMS → " +
                student.getName() +
                "] " +
                notice.getTitle()
        );
    }

    @Override
    public String getChannelName() {
        return "SMS";
    }
}

class AppChannel
        implements NotificationChannel {

    @Override
    public void send(
            Student student,
            Notice notice) {

        System.out.println(
                "[App → " +
                student.getName() +
                "] " +
                notice.getTitle()
        );
    }

    @Override
    public String getChannelName() {
        return "App";
    }
}

class NoticeBoard {

    private List<Student> students;

    public NoticeBoard() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void postNotice(Notice notice) {

        if (!notice.isValid()) {

            System.out.println(
                    "Cannot post notice: " +
                    "At least one target department is required."
            );

            return;
        }

        System.out.println(
                "Notice '" +
                notice.getTitle() +
                "' posted to " +
                String.join(
                        ", ",
                        notice.getTargetDepartments()
                ) +
                "."
        );

        for (Student student : students) {

            if (notice.getTargetDepartments()
                    .contains(student.getDepartment())) {

                for (
                        NotificationChannel channel :
                        student.getChannels()
                ) {

                    channel.send(
                            student,
                            notice
                    );
                }
            }
        }
    }
}