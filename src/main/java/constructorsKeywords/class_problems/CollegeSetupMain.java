class CollegeStudent {

    private static String collegeName;
    private static String academicYear;

    static {
        collegeName = "ABC College";
        academicYear = "2026-2027";
        System.out.println("College info loaded");
    }

    private String studentName;

    public CollegeStudent(String studentName) {
        this.studentName = studentName;
    }

    public void printRecord() {
        System.out.println("Student record created: " + studentName);
    }
}

public class CollegeSetupMain {

    public static void main(String[] args) {

        String[] names = {
            "Ravi",
            "Meera",
            "Karthik",
            "Divya",
            "Anitha"
        };

        for (String name : names) {

            CollegeStudent student =
                new CollegeStudent(name);

            student.printRecord();
        }
    }
}