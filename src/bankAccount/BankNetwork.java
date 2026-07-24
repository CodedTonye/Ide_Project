package bankAccount;


import java.util.ArrayList;
import java.util.List;

public class BankNetwork {
    private final List<Bank> banks;

    public BankNetwork() {
        banks = new ArrayList<>();
    }

    public Bank addBank(String bankName) {
        if (bankName == null || bankName.isEmpty()) {
            throw new IllegalArgumentException("Bank name is required");
        }
        if (bankExists(bankName)) {
            throw new IllegalArgumentException("Bank already exists: " + bankName);
        }

        Bank bank = new Bank(bankName);
        banks.add(bank);
        return bank;
    }

    public Bank getBank(String bankName) {
        return getBankOrThrow(bankName);
    }

    public boolean bankExists(String bankName) {
        return findBankByName(bankName) != null;
    }

    public void transfer(String senderBankName, String senderAccountNumber,
                         String receiverBankName, String receiverAccountNumber,
                         int amount, String senderPin) {
        Bank senderBank = getBankOrThrow(senderBankName);
        Bank receiverBank = getBankOrThrow(receiverBankName);

        if (!receiverBank.accountExists(receiverAccountNumber)) {
            throw new IllegalArgumentException("Receiver account not found: " + receiverAccountNumber);
        }

        senderBank.withdraw(senderAccountNumber, amount, senderPin);
        receiverBank.deposit(receiverAccountNumber, amount);
    }

    private Bank findBankByName(String bankName) {
        if (bankName == null) {
            return null;
        }
        for (Bank bank : banks) {
            if (bank.getName().equals(bankName)) {
                return bank;
            }
        }
        return null;
    }

    private Bank getBankOrThrow(String bankName) {
        Bank bank = findBankByName(bankName);
        if (bank == null) {
            throw new IllegalArgumentException("Bank not found: " + bankName);
        }
        return bank;
    }
}