package bankAccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {
    private Bank myBank;
    private String pin;
    private String accountNumber;

    public BankTest() {
    }

    @BeforeEach
    public void setUp() {
        myBank = new Bank("Test Bank");
        pin = "1234";
        accountNumber = myBank.registerAccount("John", "Doe", pin);
    }

    @Test
    public void registerAccount_returnsValidAccountNumberTest() {
        String newAccountNumber = myBank.registerAccount("Jane", "Smith", "5678");
        assertNotNull(newAccountNumber);
        assertTrue(myBank.isValidAccountNumber(newAccountNumber));
        assertTrue(myBank.accountExists(newAccountNumber));
    }

    @Test
    public void registerTwoAccounts_accountNumbersAreDifferentTest() {
        String secondAccountNumber = myBank.registerAccount("Jane", "Smith", "5678");
        assertNotEquals(accountNumber, secondAccountNumber);
    }

    @Test
    public void registerWithEmptyFirstName_throwsIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> myBank.registerAccount("", "Doe", "1234"));
    }

    @Test
    public void registerWithEmptyLastName_throwsIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> myBank.registerAccount("John", "", "1234"));
    }

    @Test
    public void registerWithEmptyPin_throwsIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> myBank.registerAccount("John", "Doe", ""));
    }

    @Test
    public void registerWithNullFirstName_throwsIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> myBank.registerAccount(null, "Doe", "1234"));
    }

    @Test
    public void deposit100InEmptyAccount_balanceIs100Test() {
        myBank.deposit(accountNumber, 100);
        assertEquals(100, myBank.checkBalance(accountNumber, pin));
    }

    @Test
    public void deposit50AndDeposit25_balanceIs75Test() {
        myBank.deposit(accountNumber, 50);
        myBank.deposit(accountNumber, 25);
        assertEquals(75, myBank.checkBalance(accountNumber, pin));
    }

    @Test
    public void depositZero_throwsIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> myBank.deposit(accountNumber, 0));
    }

    @Test
    public void depositMinus10_throwsIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> myBank.deposit(accountNumber, -10));
    }

    @Test
    public void depositToUnknownAccount_throwsIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> myBank.deposit("9999", 10));
    }

    @Test
    public void deposit200Withdraw50WithCorrectPin_balanceIs150Test() {
        myBank.deposit(accountNumber, 200);
        myBank.withdraw(accountNumber, 50, pin);
        assertEquals(150, myBank.checkBalance(accountNumber, pin));
    }

    @Test
    public void withdrawWithWrongPin_throwsIllegalArgumentExceptionTest() {
        myBank.deposit(accountNumber, 100);
        assertThrows(IllegalArgumentException.class, () -> myBank.withdraw(accountNumber, 10, "wrongPin"));
    }

    @Test
    public void withdrawMoreThanBalance_throwsIllegalArgumentExceptionTest() {
        myBank.deposit(accountNumber, 30);
        assertThrows(IllegalArgumentException.class, () -> myBank.withdraw(accountNumber, 100, pin));
    }

    @Test
    public void withdrawZero_throwsIllegalArgumentExceptionTest() {
        myBank.deposit(accountNumber, 100);
        assertThrows(IllegalArgumentException.class, () -> myBank.withdraw(accountNumber, 0, pin));
    }

    @Test
    public void withdrawMinus5_throwsIllegalArgumentExceptionTest() {
        myBank.deposit(accountNumber, 100);
        assertThrows(IllegalArgumentException.class, () -> myBank.withdraw(accountNumber, -5, pin));
    }

    @Test
    public void deposit100Transfer40_senderIs60AndReceiverIs40Test() {
        String receiverAccountNumber = myBank.registerAccount("Jane", "Smith", "5678");
        myBank.deposit(accountNumber, 100);
        myBank.transfer(accountNumber, receiverAccountNumber, 40, pin);
        assertEquals(60, myBank.checkBalance(accountNumber, pin));
        assertEquals(40, myBank.checkBalance(receiverAccountNumber, "5678"));
    }

    @Test
    public void transferWithWrongSenderPin_throwsIllegalArgumentExceptionTest() {
        String receiverAccountNumber = myBank.registerAccount("Jane", "Smith", "5678");
        myBank.deposit(accountNumber, 100);
        assertThrows(IllegalArgumentException.class,
                () -> myBank.transfer(accountNumber, receiverAccountNumber, 10, "wrongPin"));
    }

    @Test
    public void transferToUnknownAccount_throwsIllegalArgumentExceptionTest() {
        myBank.deposit(accountNumber, 100);
        assertThrows(IllegalArgumentException.class,
                () -> myBank.transfer(accountNumber, "9999", 10, pin));
    }

    @Test
    public void transferMoreThanSenderBalance_throwsIllegalArgumentExceptionTest() {
        String receiverAccountNumber = myBank.registerAccount("Jane", "Smith", "5678");
        assertThrows(IllegalArgumentException.class,
                () -> myBank.transfer(accountNumber, receiverAccountNumber, 10, pin));
    }

    @Test
    public void deposit75_checkBalanceReturns75Test() {
        myBank.deposit(accountNumber, 75);
        assertEquals(75, myBank.checkBalance(accountNumber, pin));
    }

    @Test
    public void checkBalanceWithWrongPin_throwsIllegalArgumentExceptionTest() {
        myBank.deposit(accountNumber, 50);
        assertThrows(IllegalArgumentException.class, () -> myBank.checkBalance(accountNumber, "wrongPin"));
    }

    @Test
    public void removeAccountWithCorrectPin_accountNoLongerExistsTest() {
        myBank.removeAccount(accountNumber, pin);
        assertFalse(myBank.accountExists(accountNumber));
    }

    @Test
    public void removeAccountWithWrongPin_throwsIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> myBank.removeAccount(accountNumber, "wrongPin"));
        assertTrue(myBank.accountExists(accountNumber));
    }

    @Test
    public void removeUnknownAccount_throwsIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> myBank.removeAccount("9999", pin));
    }

    @Test
    public void isValidAccountNumber_generatedNumberIsTrueTest() {
        assertTrue(myBank.isValidAccountNumber(accountNumber));
    }

    @Test
    public void isValidAccountNumberNull_isFalseTest() {
        assertFalse(myBank.isValidAccountNumber(null));
    }

    @Test
    public void isValidAccountNumberBelowStartingRange_isFalseTest() {
        assertFalse(myBank.isValidAccountNumber("123"));
    }

    @Test
    public void isValidAccountNumberWithLetters_isFalseTest() {
        assertFalse(myBank.isValidAccountNumber("12345abc90"));
    }

    @Test
    public void accountExists_registeredAccountReturnsTrueTest() {
        assertTrue(myBank.accountExists(accountNumber));
    }

    @Test
    public void accountExists_unknownAccountReturnsFalseTest() {
        assertFalse(myBank.accountExists("9999"));
    }

    @Test
    public void registerAndRemoveAccounts_totalAccountsCountUpdatesTest() {
        assertEquals(1, myBank.getTotalAccountsCount());
        myBank.registerAccount("Jane", "Smith", "5678");
        assertEquals(2, myBank.getTotalAccountsCount());
        myBank.removeAccount(accountNumber, pin);
        assertEquals(1, myBank.getTotalAccountsCount());
    }
}