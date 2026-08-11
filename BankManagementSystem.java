import java.util.ArrayList;
import java.util.Scanner;

public class BankManagementSystem {

    // Account class
    static class Account {
        int accountId;
        String name;
        double balance;

        Account(int accountId, String name, double balance) {
            this.accountId = accountId;
            this.name = name;
            this.balance = balance;
        }

        void displayAccount() {
            System.out.println("--------------------------------");
            System.out.println("Account ID : " + accountId);
            System.out.println("Name       : " + name);
            System.out.printf("Balance    : Rs. %.2f%n", balance);
            System.out.println("--------------------------------");
        }
    }

    // Week 1 storage: ArrayList
    static ArrayList<Account> accounts = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);

    // Manual search using iteration
    static Account findAccount(int accountId) {

        for (Account account : accounts) {

            if (account.accountId == accountId) {
                return account;
            }
        }

        return null;
    }

    // Create Account
    static void createAccount() {

        System.out.println("\n===== CREATE ACCOUNT =====");

        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();
        scanner.nextLine();

        // Manual iteration is used to check duplicate ID
        for (Account account : accounts) {

            if (account.accountId == accountId) {
                System.out.println("Account ID already exists.");
                return;
            }
        }

        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();

        if (name.trim().isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter Initial Deposit: ");
        double initialDeposit = scanner.nextDouble();

        if (initialDeposit < 0) {
            System.out.println("Initial deposit cannot be negative.");
            return;
        }

        Account newAccount =
                new Account(accountId, name, initialDeposit);

        accounts.add(newAccount);

        System.out.println("Account created successfully.");
    }

    // Deposit
    static void deposit() {

        System.out.println("\n===== DEPOSIT =====");

        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        // Manual search
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

        account.balance = account.balance + amount;

        System.out.println("Deposit successful.");
        System.out.printf("Current Balance: Rs. %.2f%n", account.balance);
    }

    // Withdraw
    static void withdraw() {

        System.out.println("\n===== WITHDRAW =====");

        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        // Manual search
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

        account.balance = account.balance - amount;

        System.out.println("Withdrawal successful.");
        System.out.printf("Current Balance: Rs. %.2f%n", account.balance);
    }

    // Balance Check
    static void balanceCheck() {

        System.out.println("\n===== BALANCE CHECK =====");

        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        // Manual search
        Account account = findAccount(accountId);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        account.displayAccount();
    }

    // Main menu
    public static void main(String[] args) {

        while (true) {

            System.out.println("\n=================================");
            System.out.println("     BANK MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Balance Check");
            System.out.println("5. Exit");
            System.out.println("=================================");

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
                    System.out.println(
                            "Thank you for using Bank Management System."
                    );

                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}