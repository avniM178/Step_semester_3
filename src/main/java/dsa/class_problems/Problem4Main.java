import java.util.*;

public class Problem4Main {
    static class Student {
        String name;
        int[] marks;

        Student(String name, int[] marks) {
            this.name = name;
            this.marks = marks;
        }

        int total() {
            return marks[0] + marks[1] + marks[2];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+", 2);
            String name = parts[0];
            String numbers = parts[1].replaceAll("[\\[\\],]", "");
            String[] values = numbers.trim().split("\\s+");

            int[] marks = new int[3];

            for (int i = 0; i < 3; i++) {
                marks[i] = Integer.parseInt(values[i]);
            }

            students.add(new Student(name, marks));
        }

        sc.close();

        if (students.isEmpty()) return;

        int[] subjectTotals = new int[3];
        Student topper = students.get(0);

        System.out.print("Totals ");

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);

            System.out.print(student.name + " " + student.total());

            if (i < students.size() - 1) {
                System.out.print(", ");
            }

            for (int j = 0; j < 3; j++) {
                subjectTotals[j] += student.marks[j];
            }

            if (student.total() > topper.total()) {
                topper = student;
            }
        }

        System.out.printf("%naverages %.2f, %.2f, %.2f%n",
                (double) subjectTotals[0] / students.size(),
                (double) subjectTotals[1] / students.size(),
                (double) subjectTotals[2] / students.size());

        System.out.println("topper " + topper.name + " (" + topper.total() + ")");
    }
}
