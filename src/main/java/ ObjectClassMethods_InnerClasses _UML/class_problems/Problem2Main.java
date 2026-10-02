import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Problem2Main {

    public static void main(String[] args) {

        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        Reviewer alice = new Reviewer("Alice");
        Reviewer bob = new Reviewer("Bob");

        LeaveRequest johnRequest = john.submitLeave(
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 1, 5)
        );

        alice.approve(johnRequest);

        LeaveRequest janeRequest = jane.submitLeave(
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 11)
        );

        bob.reject(janeRequest);

        johnRequest.changeStatus(LeaveStatus.PENDING);
    }
}


// ---------------- Leave Status ----------------

enum LeaveStatus {
    PENDING,
    APPROVED,
    REJECTED
}


// ---------------- Employee ----------------

abstract class Employee {

    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean isLeaveAllowed(long days);

    public LeaveRequest submitLeave(
            LocalDate startDate,
            LocalDate endDate) {

        long days = ChronoUnit.DAYS.between(startDate, endDate) + 1;

        if (!isLeaveAllowed(days)) {
            System.out.println(
                    "Leave request rejected by policy for " +
                    name
            );
            return null;
        }

        LeaveRequest request =
                new LeaveRequest(this, startDate, endDate);

        System.out.println(
                "Leave request submitted for " +
                name +
                " (" + startDate + " to " + endDate + ")."
        );

        System.out.println("Status: " + request.getStatus());

        return request;
    }
}


// ---------------- Full Time Employee ----------------

class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean isLeaveAllowed(long days) {
        return days <= 30;
    }
}


// ---------------- Part Time Employee ----------------

class PartTimeEmployee extends Employee {

    public PartTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean isLeaveAllowed(long days) {
        return days <= 15;
    }
}


// ---------------- Contractor ----------------

class Contractor extends Employee {

    public Contractor(String name) {
        super(name);
    }

    @Override
    public boolean isLeaveAllowed(long days) {
        return days <= 10;
    }
}


// ---------------- Leave Request ----------------

class LeaveRequest {

    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveStatus status;

    public LeaveRequest(
            Employee employee,
            LocalDate startDate,
            LocalDate endDate) {

        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = LeaveStatus.PENDING;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void changeStatus(LeaveStatus newStatus) {

        if (status != LeaveStatus.PENDING) {
            System.out.println(
                    "Cannot change leave request status from " +
                    status +
                    " to " +
                    newStatus + "."
            );
            return;
        }

        status = newStatus;

        System.out.println(
                employee.getName() +
                "'s leave request (" +
                startDate +
                " to " +
                endDate +
                ") " +
                newStatus.toString().toLowerCase() +
                "."
        );

        System.out.println("Status: " + status);
    }
}


// ---------------- Reviewer ----------------

class Reviewer {

    private String name;

    public Reviewer(String name) {
        this.name = name;
    }

    public void approve(LeaveRequest request) {

        request.changeStatus(LeaveStatus.APPROVED);
    }

    public void reject(LeaveRequest request) {

        request.changeStatus(LeaveStatus.REJECTED);
    }
}