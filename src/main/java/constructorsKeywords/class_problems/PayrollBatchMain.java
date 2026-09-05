class Employee {

    private String employeeId;
    private double salary;

    public Employee(String employeeId, double salary) {
        this.employeeId = employeeId;
        this.salary = salary;
    }

    public void raiseSalary(double salary) {
        this.salary = this.salary + salary;
    }

    public void printSalary() {
        System.out.println(employeeId + " | Final Salary: Rs " + salary);
    }
}

public class PayrollBatchMain {

    public static void main(String[] args) {

        Employee[] employees = {
            new Employee("E-101", 40000),
            new Employee("E-102", 55000),
            new Employee("E-103", 62000),
            new Employee("E-104", 48000)
        };

        double bonus = 5000;

        for (Employee employee : employees) {
            employee.raiseSalary(bonus);
            employee.printSalary();
        }
    }
}