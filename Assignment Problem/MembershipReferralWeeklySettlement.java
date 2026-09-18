class GymMember {
    private static int membersEnrolled;
    public final String membershipNumber;

    protected int monthlyFee;
    private int feesPaid;
    private String paymentMode;

    public GymMember(int monthlyFee) {
        membersEnrolled++;
        membershipNumber = "GYM-" + (2000 + membersEnrolled);
        this.monthlyFee = monthlyFee;
    }

    void payFee(int amount) {
        feesPaid += amount;
    }

    void payFee(int amount, String mode) {
        paymentMode = mode;
        payFee(amount);
    }

    int getFeesPaid() {
        return feesPaid;
    }

    static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }

        return code.charAt(0) == 'G'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    static int getMembersEnrolled() {
        return membersEnrolled;
    }
}

class GroupClassMember extends GymMember {
    private String className;

    public GroupClassMember(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }
}

class MembershipReferralWeeklySettlement {
    static String processWeeklyCheckIn(GymMember[] members) {
        int processed = 0;
        int skipped = 0;
        int group = 0;
        int individual = 0;

        for (GymMember member : members) {
            if (member == null) {
                skipped++;
            } else {
                processed++;

                if (member instanceof GroupClassMember) {
                    group++;
                } else {
                    individual++;
                }
            }
        }

        return processed + " processed | " +
               skipped + " null skipped | " +
               group + " group | " +
               individual + " individual";
    }
}
