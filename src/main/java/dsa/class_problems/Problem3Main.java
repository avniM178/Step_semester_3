import java.util.*;

public class Problem3Main {
    static class Student {
        String rollNumber, name;

        Student(String rollNumber, String name) {
            this.rollNumber = rollNumber;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Student)) return false;

            Student other = (Student) obj;
            return rollNumber.equals(other.rollNumber);
        }

        @Override
        public int hashCode() {
            return rollNumber.hashCode();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Student> members = new LinkedHashSet<>();
        List<String> containsResults = new ArrayList<>();

        while (sc.hasNext()) {
            String operation = sc.next();

            if (operation.equals("ADD")) {
                Student student = new Student(sc.next(), sc.next());

                if (members.add(student)) {
                    System.out.println("Added");
                } else {
                    System.out.println("duplicate rejected");
                }

            } else if (operation.equals("CONTAINS")) {
                Student student = new Student(sc.next(), sc.next());
                containsResults.add("contains: " + members.contains(student));
            }
        }

        System.out.println("member count " + members.size());

        for (String result : containsResults) {
            System.out.println(result);
        }

        sc.close();
    }
}
