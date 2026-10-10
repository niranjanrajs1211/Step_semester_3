
import java.util.*;

class BankAccount {
    String id;
    String type;
    double balance;

    BankAccount(String id, String type, double balance) {
        this.id = id;
        this.type = type;
        this.balance = balance;
    }

    void withdraw(double amount) {
        if (type.equals("Savings") && balance - amount < 1000) {
            System.out.println(id + " rejected: minimum balance 1000");
        } else if (type.equals("Current") && balance - amount < -5000) {
            System.out.println(id + " rejected: overdraft limit 5000");
        } else {
            balance -= amount;
            System.out.println(id + " balance " + (long) balance);
        }
    }
}

public class BankAccountWithdrawalSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, BankAccount> accounts = new LinkedHashMap<>();

        while (sc.hasNext()) {
            String operation = sc.next();

            if (operation.equals("Savings") || operation.equals("Current")) {
                String id = sc.next();
                double balance = sc.nextDouble();
                accounts.put(id, new BankAccount(id, operation, balance));
            } else if (operation.equals("WITHDRAW")) {
                String id = sc.next();
                double amount = sc.nextDouble();

                if (accounts.containsKey(id)) {
                    accounts.get(id).withdraw(amount);
                } else {
                    System.out.println("Account not found");
                }
            }
        }
        sc.close();
    }
}
