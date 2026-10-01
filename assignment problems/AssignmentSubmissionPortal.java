import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

interface AssignmentType {
    double applyPenalty(double marks, long lateDays);
}

class CodingAssignment implements AssignmentType {
    public double applyPenalty(double marks, long lateDays) {
        return marks * Math.max(0, 1 - 0.10 * lateDays);
    }
}

class WrittenAssignment implements AssignmentType {
    public double applyPenalty(double marks, long lateDays) {
        return marks * Math.max(0, 1 - 0.20 * lateDays);
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Assignment {
    String title;
    double maxMarks;
    LocalDate dueDate;
    AssignmentType type;

    Assignment(String title, double maxMarks, LocalDate dueDate, AssignmentType type) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
        this.type = type;
    }
}

class Submission {
    Student student;
    Assignment assignment;
    LocalDate submissionDate;
    String status;
    double finalMarks;

    Submission(Student student, Assignment assignment, LocalDate submissionDate) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
        this.status = "Submitted";
    }

    void grade(double awardedMarks) {
        if (!status.equals("Submitted")) {
            System.out.println("Cannot grade: submission is already graded.");
            return;
        }

        long lateDays = Math.max(0,
                ChronoUnit.DAYS.between(assignment.dueDate, submissionDate));

        finalMarks = assignment.type.applyPenalty(awardedMarks, lateDays);
        status = "Graded";

        System.out.printf("%s graded: %.0f/%.0f.%n",
                student.name, finalMarks, assignment.maxMarks);
        System.out.println("Status: Graded.");
    }

    boolean canResubmit() {
        return !status.equals("Graded");
    }
}

public class AssignmentSubmissionPortal {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding = new Assignment(
                "Linked List Lab", 50, LocalDate.of(2026, 3, 10),
                new CodingAssignment());

        Assignment written = new Assignment(
                "Design Essay", 50, LocalDate.of(2026, 3, 12),
                new WrittenAssignment());

        Submission s1 = new Submission(
                asha, coding, LocalDate.of(2026, 3, 10));

        Submission s2 = new Submission(
                ravi, written, LocalDate.of(2026, 3, 14));

        System.out.println("Asha's submission for 'Linked List Lab' received (on time).");
        System.out.println("Status: Submitted.");

        System.out.println("Ravi's submission for 'Design Essay' received (2 days late).");
        System.out.println("Status: Submitted.");

        s1.grade(45);
        s2.grade(40);

        if (!s1.canResubmit()) {
            System.out.println(
                    "Cannot resubmit: 'Linked List Lab' has already been graded.");
        }
    }
}
