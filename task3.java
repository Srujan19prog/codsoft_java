import java.util.Scanner;

public class task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double balance = 5000.0;
        int choice = 0;

        do {
            System.out.println("\n--- ATM MENU ---");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Enter a number.");
                sc.next();
                continue;
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter withdrawal amount: ");

                    if (sc.hasNextDouble()) {
                        double amount = sc.nextDouble();

                        if (amount <= 0) {
                            System.out.println("Invalid amount.");
                        } else if (amount > balance) {
                            System.out.println("Insufficient balance.");
                        } else {
                            balance -= amount;
                            System.out.println("Withdrawal successful.");
                            System.out.println("Please collect your cash.");
                        }
                    } else {
                        System.out.println("Invalid amount.");
                        sc.next();
                    }
                    break;

                case 2:
                    System.out.print("Enter deposit amount: ");

                    if (sc.hasNextDouble()) {
                        double amount = sc.nextDouble();

                        if (amount <= 0) {
                            System.out.println("Invalid amount.");
                        } else {
                            balance += amount;
                            System.out.println("Deposit successful.");
                        }
                    } else {
                        System.out.println("Invalid amount.");
                        sc.next();
                    }
                    break;

                case 3:
                    System.out.printf(
                        "Available balance: Rs. %.2f%n", balance
                    );
                    break;

                case 4:
                    System.out.println(
                        "Thank you for using the ATM."
                    );
                    break;

                default:
                    System.out.println(
                        "Invalid choice. Please try again."
                    );
            }

        } while (choice != 4);

        sc.close();
    }
}