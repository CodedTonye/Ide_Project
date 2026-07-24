package bankAccount;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private static final int STARTING_ACCOUNT_NUMBER = 1000;

    private final String name;
    private final List<Account> accounts;

    public Bank(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Bank name is required");
        }
        this.name = name;
        accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String registerAccount(String firstName, String lastName, String pin) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name is required");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }
        if (pin == null || pin.isEmpty()) {
            throw new IllegalArgumentException("Pin is required");
        }

        String username = firstName + lastName + accounts.size();

        Account account = new Account();
        account.signUp(username, pin);
        account.signIn(username, pin); // stays signed in permanently so the pin can gate future operations

        accounts.add(account);

        return String.valueOf(STARTING_ACCOUNT_NUMBER + accounts.size() - 1);
    }

    public void deposit(String accountNumber, int amount) {
        Account account = getAccountOrThrow(accountNumber);
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        account.deposit(amount);
    }

    public void withdraw(String accountNumber, int amount, String pin) {
        Account account = getAccountOrThrow(accountNumber);
        int balance = account.getBalance(pin); // throws if the pin is wrong or the account isn't signed in

        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        account.withdraw(amount);
    }

    public void transfer(String senderAccountNumber, String receiverAccountNumber, int amount, String pin) {
        Account senderAccount = getAccountOrThrow(senderAccountNumber);
        Account receiverAccount = getAccountOrThrow(receiverAccountNumber);
        int senderBalance = senderAccount.getBalance(pin); // validates the sender's pin

        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        if (amount > senderBalance) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        senderAccount.withdraw(amount);
        receiverAccount.deposit(amount);
    }

    public int checkBalance(String accountNumber, String pin) {
        Account account = getAccountOrThrow(accountNumber);
        return account.getBalance(pin);
    }

    public void removeAccount(String accountNumber, String pin) {
        Account account = getAccountOrThrow(accountNumber);
        account.getBalance(pin); // validates the pin before allowing removal
        accounts.set(toIndex(accountNumber), null); // keep the slot so other account numbers stay valid
    }

    public boolean isValidAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.isEmpty()) {
            return false;
        }
        for (int index = 0; index < accountNumber.length(); index++) {
            if (!Character.isDigit(accountNumber.charAt(index))) {
                return false;
            }
        }

        int number;
        try {
            number = Integer.parseInt(accountNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        return number >= STARTING_ACCOUNT_NUMBER;
    }

    public boolean accountExists(String accountNumber) {
        int index = toIndex(accountNumber);
        return index >= 0 && index < accounts.size() && accounts.get(index) != null;
    }

    public int getTotalAccountsCount() {
        int count = 0;
        for (Account account : accounts) {
            if (account != null) {
                count++;
            }
        }
        return count;
    }

    private Account getAccountOrThrow(String accountNumber) {
        if (!accountExists(accountNumber)) {
            throw new IllegalArgumentException("Account not found: " + accountNumber);
        }
        return accounts.get(toIndex(accountNumber));
    }

    private int toIndex(String accountNumber) {
        if (!isValidAccountNumber(accountNumber)) {
            return -1;
        }
        return Integer.parseInt(accountNumber) - STARTING_ACCOUNT_NUMBER;
    }
}