import java.util.*;

interface NotificationChannel {
    void send(Student student, Notice notice);
}

class EmailChannel implements NotificationChannel {
    public void send(Student student, Notice notice) {
        System.out.println("[Email → " + student.name + "] " + notice.title);
    }
}

class SmsChannel implements NotificationChannel {
    public void send(Student student, Notice notice) {
        System.out.println("[SMS → " + student.name + "] " + notice.title);
    }
}

class AppChannel implements NotificationChannel {
    public void send(Student student, Notice notice) {
        System.out.println("[App → " + student.name + "] " + notice.title);
    }
}

class Student {
    String name;
    String department;
    List<NotificationChannel> channels;

    Student(String name, String department,
            List<NotificationChannel> channels) {
        this.name = name;
        this.department = department;
        this.channels = channels;
    }
}

class Notice {
    String title;
    Set<String> departments;

    Notice(String title, Set<String> departments) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Notice title is required.");
        }

        if (departments == null || departments.isEmpty()) {
            throw new IllegalArgumentException(
                    "At least one target department is required.");
        }

        this.title = title;
        this.departments = departments;
    }
}

class NoticeBoard {
    private List<Student> students;

    NoticeBoard(List<Student> students) {
        this.students = students;
    }

    void postNotice(Notice notice) {
        System.out.println(
                "Notice '" + notice.title + "' posted to " +
                String.join(", ", notice.departments) + ".");

        for (Student student : students) {
            if (notice.departments.contains(student.department)) {
                for (NotificationChannel channel : student.channels) {
                    channel.send(student, notice);
                }
            }
        }
    }
}

public class CampusNoticeBroadcaster {
    public static void main(String[] args) {
        Student asha = new Student(
                "Asha",
                "CSE",
                Arrays.asList(
                        new EmailChannel(),
                        new AppChannel()));

        Student ravi = new Student(
                "Ravi",
                "ECE",
                Arrays.asList(
                        new SmsChannel()));

        NoticeBoard board = new NoticeBoard(
                Arrays.asList(asha, ravi));

        Notice notice1 = new Notice(
                "Lab Closed Tomorrow",
                new HashSet<>(Arrays.asList("CSE")));

        Notice notice2 = new Notice(
                "Fee Deadline Extended",
                new HashSet<>(Arrays.asList("CSE", "ECE")));

        board.postNotice(notice1);
        board.postNotice(notice2);

        try {
            new Notice(
                    "Sports Day",
                    new HashSet<>());
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Cannot post notice: At least one target department is required.");
        }
    }
}
