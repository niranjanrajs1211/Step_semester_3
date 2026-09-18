class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    private int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    String displayInfo() {
        return "General | Books: " + booksBorrowed;
    }
}

class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    String displayInfo() {
        return "Student | Course: " + course +
               " | Books: " + getBooksBorrowed();
    }

    String getCourse() {
        return course;
    }

    private int getBooksBorrowed() {
        return 0;
    }
}

class WeeklyCirculationReport {
    static String batchPrint(LibraryMember[] members) {
        StringBuilder result = new StringBuilder();

        for (LibraryMember member : members) {
            result.append(member.displayInfo());

            if (member instanceof StudentMember) {
                StudentMember student = (StudentMember) member;
                result.append(" [Course via downcast: ")
                      .append(student.getCourse())
                      .append("]");
            }

            result.append(" | ");
        }

        return result.toString();
    }
}
