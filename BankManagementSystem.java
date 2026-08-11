import java.util.ArrayList;
import java.util.Scanner;

public class BankManagementSystem {

    static class Account {
        int accountId;
        String name;
        double balance;

        Account(int accountId, String name, double balance) {
            this.accountId = accountId;
            this.name = name;
            this.balance = balance;
        }

        void display() {
            System.out.println("Account ID : " + accountId);
            System.out.println("Name       : " + name);
            System.out.println("Balance    : Rs." + balance);
        }
    }

    static ArrayList<Account> accounts = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    // Manual search using loop
    static Account findAccount(int accountId) {
        for (Account account : accounts) {
            if (account.accountId == accountId) {
                return account;
            }
        }
        return null;
    }

    static void createAccount() {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();
        scanner.nextLine();

        // Check duplicate account ID
        if (findAccount(accountId) != null) {
            System.out.println("Account ID already exists.");
            return;
        }

        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Initial Deposit: ");
        double balance = scanner.nextDouble();

        if (balance < 0) {
            System.out.println("Initial deposit cannot be negative.");
            return;
        }

        Account account = new Account(accountId, name, balance);
        accounts.add(account);

        System.out.println("Account created successfully.");
    }

    static void deposit() {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        Account account = findAccount(accountId);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter Deposit Amount: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }

        account.balance += amount;

        System.out.println("Deposit successful.");
        System.out.println("Updated Balance: Rs." + account.balance);
    }

    static void withdraw() {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        Account account = findAccount(accountId);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter Withdrawal Amount: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
            return;
        }

        if (amount > account.balance) {
            System.out.println("Insufficient balance.");
            return;
        }

        account.balance -= amount;

        System.out.println("Withdrawal successful.");
        System.out.println("Updated Balance: Rs." + account.balance);
    }

    static void balanceCheck() {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        Account account = findAccount(accountId);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        account.display();
    }

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Balance Check");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    deposit();
                    break;

                case 3:
                    withdraw();
                    break;

                case 4:
                    balanceCheck();
                    break;

                case 5:
                    System.out.println("Thank you for using the Bank Management System.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}