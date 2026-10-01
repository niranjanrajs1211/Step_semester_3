abstract class Employee {
    String name;

    Employee(String name) {
        this.name = name;
    }

    abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days <= 30;
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days <= 15;
    }
}

class Contractor extends Employee {
    Contractor(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days <= 7;
    }
}

class LeaveRequest {
    Employee employee;
    String startDate;
    String endDate;
    String status = "Pending";

    LeaveRequest(Employee employee, String startDate, String endDate, int days) {
        if (!employee.canTakeLeave(days)) {
            throw new IllegalArgumentException("Leave not allowed for " + employee.name);
        }
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    void approve() {
        if (status.equals("Pending"))
            status = "Approved";
        else
            System.out.println("Cannot change leave request status from " + status + " to Approved.");
    }

    void reject() {
        if (status.equals("Pending"))
            status = "Rejected";
        else
            System.out.println("Cannot change leave request status from " + status + " to Rejected.");
    }

    void changeToPending() {
        if (!status.equals("Pending"))
            System.out.println("Cannot change leave request status from " + status + " to Pending.");
    }

    void display() {
        System.out.println("Leave request submitted for " + employee.name + " (" + startDate + "-" + endDate + ").");
        System.out.println("Status: " + status);
    }
}

public class EmployeeLeaveRequestWorkflow {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest request1 = new LeaveRequest(john, "Jan 1", "Jan 5", 5);
        request1.display();
        request1.approve();
        System.out.println("John's leave request (Jan 1-Jan 5) approved.");
        System.out.println("Status: " + request1.status);
        request1.changeToPending();

        LeaveRequest request2 = new LeaveRequest(jane, "Feb 10", "Feb 11", 2);
        request2.display();
        request2.reject();
        System.out.println("Jane's leave request (Feb 10-Feb 11) rejected.");
        System.out.println("Status: " + request2.status);
    }
}
