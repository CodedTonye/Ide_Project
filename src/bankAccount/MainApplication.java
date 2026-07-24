package bankAccount;

import java.util.Scanner;

public class MainApplication {
    private static Scanner scanner = new Scanner(System.in);
    private static BankNetwork network = new BankNetwork();

    static void main(String[] args) {
        network.addBank("First Bank");
        network.addBank("Zenith Bank");
        network.addBank("Opay");
        network.addBank("UBA");

        System.out.println("Welcome to the Bank Application!");
        System.out.println("Available banks: First Bank, Zenith Bank, Opay, UBA");

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt();

            switch (choice) {
                case 1:
                    createBank();
                    break;
                case 2:
                    openBankMenu();
                    break;
                case 3:
                    interbankTransfer();
                    break;
                case 4:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }

        scanner.close();
    }

    // ===== MAIN MENU =====

    private static void printMainMenu() {
        System.out.println();
        System.out.println("===== MAIN MENU =====");
        System.out.println("1. Create a new bank");
        System.out.println("2. Enter a bank");
        System.out.println("3. Transfer between banks");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    private static void createBank() {
        System.out.print("Enter new bank name: ");
        String bankName = scanner.nextLine();

        try {
            network.addBank(bankName);
            System.out.println("Bank \"" + bankName + "\" created!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void interbankTransfer() {
        System.out.print("Sender bank name: ");
        String senderBankName = scanner.nextLine();
        System.out.print("Sender account number: ");
        String senderAccountNumber = scanner.nextLine();
        System.out.print("Receiver bank name: ");
        String receiverBankName = scanner.nextLine();
        System.out.print("Receiver account number: ");
        String receiverAccountNumber = scanner.nextLine();
        System.out.print("Amount to transfer: ");
        int amount = readInt();
        System.out.print("Sender pin: ");
        String pin = scanner.nextLine();

        try {
            network.transfer(senderBankName, senderAccountNumber,
                    receiverBankName, receiverAccountNumber, amount, pin);
            System.out.println("Interbank transfer successful!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ===== BANK MENU (after entering a specific bank) =====

    private static void openBankMenu() {
        System.out.print("Enter bank name: ");
        String bankName = scanner.nextLine();

        Bank bank;
        try {
            bank = network.getBank(bankName);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        boolean inBankMenu = true;
        while (inBankMenu) {
            printBankMenu(bank.getName());
            int choice = readInt();

            switch (choice) {
                case 1:
                    registerAccount(bank);
                    break;
                case 2:
                    deposit(bank);
                    break;
                case 3:
                    withdraw(bank);
                    break;
                case 4:
                    transferWithinBank(bank);
                    break;
                case 5:
                    checkBalance(bank);
                    break;
                case 6:
                    removeAccount(bank);
                    break;
                case 7:
                    inBankMenu = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private static void printBankMenu(String bankName) {
        System.out.println();
        System.out.println("===== " + bankName + " =====");
        System.out.println("1. Register new account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer (same bank)");
        System.out.println("5. Check balance");
        System.out.println("6. Remove account");
        System.out.println("7. Back to main menu");
        System.out.print("Choose an option: ");
    }

    private static void registerAccount(Bank bank) {
        System.out.print("First name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Choose a pin: ");
        String pin = scanner.nextLine();

        try {
            String accountNumber = bank.registerAccount(firstName, lastName, pin);
            System.out.println("Account created! Account number: " + accountNumber);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deposit(Bank bank) {
        System.out.print("Account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Amount to deposit: ");
        int amount = readInt();

        try {
            bank.deposit(accountNumber, amount);
            System.out.println("Deposit successful!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void withdraw(Bank bank) {
        System.out.print("Account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Amount to withdraw: ");
        int amount = readInt();
        System.out.print("Pin: ");
        String pin = scanner.nextLine();

        try {
            bank.withdraw(accountNumber, amount, pin);
            System.out.println("Withdrawal successful!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void transferWithinBank(Bank bank) {
        System.out.print("Sender account number: ");
        String senderAccountNumber = scanner.nextLine();
        System.out.print("Receiver account number: ");
        String receiverAccountNumber = scanner.nextLine();
        System.out.print("Amount to transfer: ");
        int amount = readInt();
        System.out.print("Sender pin: ");
        String pin = scanner.nextLine();

        try {
            bank.transfer(senderAccountNumber, receiverAccountNumber, amount, pin);
            System.out.println("Transfer successful!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void checkBalance(Bank bank) {
        System.out.print("Account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Pin: ");
        String pin = scanner.nextLine();

        try {
            int balance = bank.checkBalance(accountNumber, pin);
            System.out.println("Balance: " + balance);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void removeAccount(Bank bank) {
        System.out.print("Account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Pin: ");
        String pin = scanner.nextLine();

        try {
            bank.removeAccount(accountNumber, pin);
            System.out.println("Account removed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static int readInt() {
        String line = scanner.nextLine();
        try {
            return Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
