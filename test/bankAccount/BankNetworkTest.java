package bankAccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankNetworkTest {
    private BankNetwork myNetwork;
    private Bank firstBank;
    private Bank zenithBank;
    private Bank opay;
    private Bank uba;
    private String senderPin;
    private String receiverPin;
    private String senderAccountNumber;
    private String receiverAccountNumber;

    public BankNetworkTest() {
    }

    @BeforeEach
    public void setUp() {
        myNetwork = new BankNetwork();
        firstBank = myNetwork.addBank("First Bank");
        zenithBank = myNetwork.addBank("Zenith Bank");
        opay = myNetwork.addBank("Opay");
        uba = myNetwork.addBank("UBA");

        senderPin = "1111";
        receiverPin = "2222";
        senderAccountNumber = firstBank.registerAccount("Ada", "Obi", senderPin);
        receiverAccountNumber = zenithBank.registerAccount("Chidi", "Eze", receiverPin);
    }

    @Test
    public void addBank_bankExistsTest() {
        assertTrue(myNetwork.bankExists("First Bank"));
        assertTrue(myNetwork.bankExists("Zenith Bank"));
        assertTrue(myNetwork.bankExists("Opay"));
        assertTrue(myNetwork.bankExists("UBA"));
    }

    @Test
    public void addBankWithEmptyName_throwsIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> myNetwork.addBank(""));
    }

    @Test
    public void addBankTwiceWithSameName_throwsIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> myNetwork.addBank("First Bank"));
    }

    @Test
    public void getBank_returnsRegisteredBankTest() {
        Bank foundBank = myNetwork.getBank("UBA");
        assertEquals("UBA", foundBank.getName());
    }

    @Test
    public void getUnknownBank_throwsIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> myNetwork.getBank("GTBank"));
    }

    @Test
    public void bankExistsForUnknownBank_returnsFalseTest() {
        assertFalse(myNetwork.bankExists("GTBank"));
    }

    @Test
    public void deposit500TransferFromFirstBankToZenithBank_balancesUpdateTest() {
        firstBank.deposit(senderAccountNumber, 500);

        myNetwork.transfer("First Bank", senderAccountNumber,
                "Zenith Bank", receiverAccountNumber, 200, senderPin);

        assertEquals(300, firstBank.checkBalance(senderAccountNumber, senderPin));
        assertEquals(200, zenithBank.checkBalance(receiverAccountNumber, receiverPin));
    }

    @Test
    public void transferBetweenOpayAndUBA_balancesUpdateTest() {
        String opayAccountNumber = opay.registerAccount("Tolu", "Ade", "3333");
        String ubaAccountNumber = uba.registerAccount("Musa", "Bello", "4444");
        opay.deposit(opayAccountNumber, 1000);

        myNetwork.transfer("Opay", opayAccountNumber, "UBA", ubaAccountNumber, 600, "3333");

        assertEquals(400, opay.checkBalance(opayAccountNumber, "3333"));
        assertEquals(600, uba.checkBalance(ubaAccountNumber, "4444"));
    }

    @Test
    public void transferWithWrongSenderPin_throwsIllegalArgumentExceptionTest() {
        firstBank.deposit(senderAccountNumber, 500);

        assertThrows(IllegalArgumentException.class, () -> myNetwork.transfer(
                "First Bank", senderAccountNumber, "Zenith Bank", receiverAccountNumber, 200, "wrongPin"));
    }

    @Test
    public void transferMoreThanSenderBalance_throwsIllegalArgumentExceptionTest() {
        firstBank.deposit(senderAccountNumber, 100);

        assertThrows(IllegalArgumentException.class, () -> myNetwork.transfer(
                "First Bank", senderAccountNumber, "Zenith Bank", receiverAccountNumber, 500, senderPin));
    }

    @Test
    public void transferZeroAmount_throwsIllegalArgumentExceptionTest() {
        firstBank.deposit(senderAccountNumber, 500);

        assertThrows(IllegalArgumentException.class, () -> myNetwork.transfer(
                "First Bank", senderAccountNumber, "Zenith Bank", receiverAccountNumber, 0, senderPin));
    }

    @Test
    public void transferFromUnknownSenderBank_throwsIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> myNetwork.transfer(
                "GTBank", senderAccountNumber, "Zenith Bank", receiverAccountNumber, 100, senderPin));
    }

    @Test
    public void transferToUnknownReceiverBank_throwsIllegalArgumentExceptionTest() {
        firstBank.deposit(senderAccountNumber, 500);

        assertThrows(IllegalArgumentException.class, () -> myNetwork.transfer(
                "First Bank", senderAccountNumber, "GTBank", receiverAccountNumber, 100, senderPin));
    }

    @Test
    public void transferToUnknownReceiverAccount_throwsIllegalArgumentExceptionTest() {
        firstBank.deposit(senderAccountNumber, 500);

        assertThrows(IllegalArgumentException.class, () -> myNetwork.transfer(
                "First Bank", senderAccountNumber, "Zenith Bank", "9999", 100, senderPin));
    }

    @Test
    public void transferFromUnknownSenderAccount_throwsIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> myNetwork.transfer(
                "First Bank", "9999", "Zenith Bank", receiverAccountNumber, 100, senderPin));
    }

    @Test
    public void registerAccountsInDifferentBanks_accountsAreNotSharedAcrossBanksTest() {
        assertFalse(opay.accountExists(senderAccountNumber));
        assertFalse(uba.accountExists(receiverAccountNumber));
    }
}