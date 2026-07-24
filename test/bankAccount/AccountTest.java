package bankAccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    private Account myAcount;

    public AccountTest() {
    }

    @BeforeEach
    public void setUp() {
        myAcount = new Account();
    }

    @Test
    public void deposit200InEmptyAccount_balanceIs200Test() {
        assertEquals(0, myAcount.getBalance());
        myAcount.deposit(200);
        assertEquals(200, myAcount.getBalance());
    }

    @Test
    public void depositMinus50_balanceIsZeroTest() {
        assertEquals(0, myAcount.getBalance());
        myAcount.deposit(-50);
        assertEquals(0, myAcount.getBalance());
    }

    @Test
    public void withdraw50_balanceIsZeroTest() {
        assertEquals(0, myAcount.getBalance());
        myAcount.withdraw(50);
        assertEquals(0, myAcount.getBalance());
    }

    @Test
    public void withdraw200_balanceIs300Test() {
        assertEquals(0, myAcount.getBalance());
        myAcount.deposit(500);
        myAcount.withdraw(200);
        assertEquals(300, myAcount.getBalance());
    }

    @Test
    public void deposit500_withdraw600_balanceIs500Test() {
        assertEquals(0, myAcount.getBalance());
        myAcount.deposit(500);
        myAcount.withdraw(600);
        assertEquals(500, myAcount.getBalance());
    }

    @Test
    public void deposit500_withdrawZero_balanceIs500Test() {
        assertEquals(0, myAcount.getBalance());
        myAcount.deposit(500);
        myAcount.withdraw(0);
        assertEquals(500, myAcount.getBalance());
    }

    @Test
    public void deposit500AndMinus200_withdraw100_balanceIs400Test() {
        assertEquals(0, myAcount.getBalance());
        myAcount.deposit(500);
        myAcount.deposit(-200);
        myAcount.withdraw(100);
        assertEquals(400, myAcount.getBalance());
    }

    @Test
    public void deposit1000_withdraw1000_balanceIsZeroTest() {
        assertEquals(0, myAcount.getBalance());
        myAcount.deposit(1000);
        myAcount.withdraw(1000);
        assertEquals(0, myAcount.getBalance());
    }

    @Test
    public void signupWithValidCredentials_signupSuccessTest() {
        assertFalse(myAcount.isSignedUp());
        myAcount.signUp("big_tee", "pass123");
        assertTrue(myAcount.isSignedUp());
    }

    @Test
    public void signupWithEmptyUsername_signupFailsTest() {
        assertFalse(myAcount.isSignedUp());
        myAcount.signUp("", "pass123");
        assertFalse(myAcount.isSignedUp());
    }

    @Test
    public void signupWithEmptyPassword_signupFailsTest() {
        assertFalse(myAcount.isSignedUp());
        myAcount.signUp("big_tee", "");
        assertFalse(myAcount.isSignedUp());
    }

    @Test
    public void signupTwice_secondSignupIgnoredTest() {
        assertFalse(myAcount.isSignedUp());
        myAcount.signUp("big_tee", "pass123");
        myAcount.signUp("small_tee", "newpass");
        assertEquals("big_tee", myAcount.getUsername());
    }

    @Test
    public void signUpThenSignIn_isSignedInTrueTest() {
        assertFalse(myAcount.isSignedIn());
        myAcount.signUp("big_tee", "pass123");
        myAcount.signIn("big_tee", "pass123");
        assertTrue(myAcount.isSignedIn());
    }

    @Test
    public void signIn_withWrongPassword_isSignedInFalseTest() {
        assertFalse(myAcount.isSignedIn());
        myAcount.signUp("big_tee", "pass123");
        myAcount.signIn("big_tee", "wrongPass");
        assertFalse(myAcount.isSignedIn());
    }

    @Test
    public void signInThenSignOut_isSignedInFalseTest() {
        assertFalse(myAcount.isSignedIn());
        myAcount.signUp("big_tee", "pass123");
        myAcount.signIn("big_tee", "pass123");
        myAcount.signOut();
        assertFalse(myAcount.isSignedIn());
    }

    @Test
    public void signInWithoutSignup_isSignedInFalseTest() {
        assertFalse(myAcount.isSignedIn());
        myAcount.signIn("big_tee", "pass123");
        assertFalse(myAcount.isSignedIn());
    }

    @Test
    public void withdraw_withoutSignIn_throwsIllegalArgumentExceptionTest() {
        myAcount.signUp("big_tee", "pass123");
        myAcount.deposit(500);
        assertThrows(IllegalArgumentException.class, () -> myAcount.withdraw(100, "pass123"));
    }

    @Test
    public void withdraw_withWrongPin_throwsIllegalArgumentExceptionTest() {
        myAcount.signUp("big_tee", "pass123");
        myAcount.signIn("big_tee", "pass123");
        myAcount.deposit(500);
        assertThrows(IllegalArgumentException.class, () -> myAcount.withdraw(100, "wrongPin"));
    }

    @Test
    public void withdraw_withCorrectPin_balanceUpdatedTest() {
        myAcount.signUp("big_tee", "pass123");
        myAcount.signIn("big_tee", "pass123");
        myAcount.deposit(500);
        myAcount.withdraw(200, "pass123");
        assertEquals(300, myAcount.getBalance("pass123"));
    }

    @Test
    public void withdraw_afterSignOut_throwsIllegalArgumentExceptionTest() {
        myAcount.signUp("big_tee", "pass123");
        myAcount.signIn("big_tee", "pass123");
        myAcount.deposit(500);
        myAcount.signOut();
        assertThrows(IllegalArgumentException.class, () -> myAcount.withdraw(100, "pass123"));
    }

    @Test
    public void getBalance_withoutSignIn_throwsIllegalArgumentExceptionTest() {
        myAcount.signUp("big_tee", "pass123");
        assertThrows(IllegalArgumentException.class, () -> myAcount.getBalance("pass123"));
    }

    @Test
    public void getBalance_signedIn_withCorrectPin_returnsBalanceTest() {
        myAcount.signUp("big_tee", "pass123");
        myAcount.signIn("big_tee", "pass123");
        myAcount.deposit(750);
        assertEquals(750, myAcount.getBalance("pass123"));
    }

    @Test
    public void getBalance_signedIn_withWrongPin_throwsIllegalArgumentExceptionTest() {
        myAcount.signUp("big_tee", "pass123");
        myAcount.signIn("big_tee", "pass123");
        myAcount.deposit(750);
        assertThrows(IllegalArgumentException.class, () -> myAcount.getBalance("wrongPin"));
    }

    @Test
    public void getBalance_afterSignOut_throwsIllegalArgumentExceptionTest() {
        myAcount.signUp("big_tee", "pass123");
        myAcount.signIn("big_tee", "pass123");
        myAcount.deposit(750);
        myAcount.signOut();
        assertThrows(IllegalArgumentException.class, () -> myAcount.getBalance("pass123"));
    }
}