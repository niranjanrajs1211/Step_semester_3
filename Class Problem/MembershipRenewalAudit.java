class LibraryMember {
    private static int membersEnrolled;
    protected int borrowLimit;
    private int booksBorrowed;
    public final String memberNumber;

    public LibraryMember(int borrowLimit) {
        membersEnrolled++;
        memberNumber = "LIB-" + (100 + membersEnrolled);
        this.borrowLimit = borrowLimit;
    }

    void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    void borrowBook(String genre) {
        borrowBook();
    }

    int getBooksBorrowed() {
        return booksBorrowed;
    }

    static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }

        return code.charAt(0) == 'R'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    static int getMembersEnrolled() {
        return membersEnrolled;
    }
}

class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }
}

class MembershipRenewalAudit {
    static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0;
        int skipped = 0;
        int faculty = 0;
        int regular = 0;

        for (LibraryMember member : members) {
            if (member == null) {
                skipped++;
            } else {
                processed++;

                if (member instanceof FacultyMember) {
                    faculty++;
                } else {
                    regular++;
                }
            }
        }

        return processed + " processed | " +
               skipped + " null skipped | " +
               faculty + " faculty | " +
               regular + " regular";
    }
}
