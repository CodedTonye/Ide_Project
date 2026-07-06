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
        this.myAcount = new Account();
    }

    @Test
    public void deposit200InEmptyAccount_balanceIs200Test() {
        assertEquals(0, this.myAcount.getBalance());
        this.myAcount.deposit(200);
        assertEquals(200, this.myAcount.getBalance());
    }

    @Test
    public void depositMinus50_balanceIsZeroTest() {
        assertEquals(0, this.myAcount.getBalance());
        this.myAcount.deposit(-50);
        assertEquals(0, this.myAcount.getBalance());
    }

    @Test
    public void withdraw50_balanceIsZeroTest() {
        assertEquals(0, this.myAcount.getBalance());
        this.myAcount.withdraw(50);
        assertEquals(0, this.myAcount.getBalance());
    }

    @Test
    public void withdraw200_balanceIs300Test() {
        assertEquals(0, this.myAcount.getBalance());
        this.myAcount.deposit(500);
        this.myAcount.withdraw(200);
        assertEquals(300, this.myAcount.getBalance());
    }

    @Test
    public void deposit500_withdraw600_balanceIs500Test() {
        assertEquals(0, this.myAcount.getBalance());
        this.myAcount.deposit(500);
        this.myAcount.withdraw(600);
        assertEquals(500, this.myAcount.getBalance());
    }

    @Test
    public void deposit500_withdrawZero_balanceIs500Test() {
        assertEquals(0, this.myAcount.getBalance());
        this.myAcount.deposit(500);
        this.myAcount.withdraw(0);
        assertEquals(500, this.myAcount.getBalance());
    }

    @Test
    public void deposit500AndMinus200_withdraw100_balanceIs400Test() {
        assertEquals(0, this.myAcount.getBalance());
        this.myAcount.deposit(500);
        this.myAcount.deposit(-200);
        this.myAcount.withdraw(100);
        assertEquals(400, this.myAcount.getBalance());
    }

    @Test
    public void deposit1000_withdraw1000_balanceIsZeroTest() {
        assertEquals(0, this.myAcount.getBalance());
        this.myAcount.deposit(1000);
        this.myAcount.withdraw(1000);
        assertEquals(0, this.myAcount.getBalance());
    }

    @Test
    public void signupWithValidCredentials_signupSuccessTest() {
        assertFalse(this.myAcount.isSignedUp());
        this.myAcount.signUp("big_tee", "pass123");
        assertTrue(this.myAcount.isSignedUp());
    }

    @Test
    public void signupWithEmptyUsername_signupFailsTest() {
        assertFalse(this.myAcount.isSignedUp());
        this.myAcount.signUp("", "pass123");
        assertFalse(this.myAcount.isSignedUp());
    }

    @Test
    public void signupWithEmptyPassword_signupFailsTest() {
        assertFalse(this.myAcount.isSignedUp());
        this.myAcount.signUp("big_tee", "");
        assertFalse(this.myAcount.isSignedUp());
    }

    @Test
    public void signupTwice_secondSignupIgnoredTest() {
        assertFalse(this.myAcount.isSignedUp());
        this.myAcount.signUp("big_tee", "pass123");
        this.myAcount.signUp("small_tee", "newpass");
        assertEquals("big_tee", this.myAcount.getUsername());
    }

    @Test
    public void signUpThenSignIn_isSignedInTrueTest() {
        assertFalse(this.myAcount.isSignedIn());
        this.myAcount.signUp("big_tee", "pass123");
        this.myAcount.signIn("big_tee", "pass123");
        assertTrue(this.myAcount.isSignedIn());
    }

    @Test
    public void signIn_withWrongPassword_isSignedInFalseTest() {
        assertFalse(this.myAcount.isSignedIn());
        this.myAcount.signUp("big_tee", "pass123");
        this.myAcount.signIn("big_tee", "wrongPass");
        assertFalse(this.myAcount.isSignedIn());
    }

    @Test
    public void signInThenSignOut_isSignedInFalseTest() {
        assertFalse(this.myAcount.isSignedIn());
        this.myAcount.signUp("big_tee", "pass123");
        this.myAcount.signIn("big_tee", "pass123");
        this.myAcount.signOut();
        assertFalse(this.myAcount.isSignedIn());
    }

    @Test
    public void signInWithoutSignup_isSignedInFalseTest() {
        assertFalse(this.myAcount.isSignedIn());
        this.myAcount.signIn("big_tee", "pass123");
        assertFalse(this.myAcount.isSignedIn());
    }

    @Test
    public void withdraw_withoutSignIn_throwsIllegalArgumentExceptionTest() {
        this.myAcount.signUp("big_tee", "pass123");
        this.myAcount.deposit(500);
        assertThrows(IllegalArgumentException.class, () -> this.myAcount.withdraw(100, "pass123"));
    }

    @Test
    public void withdraw_withWrongPin_throwsIllegalArgumentExceptionTest() {
        this.myAcount.signUp("big_tee", "pass123");
        this.myAcount.signIn("big_tee", "pass123");
        this.myAcount.deposit(500);
        assertThrows(IllegalArgumentException.class, () -> this.myAcount.withdraw(100, "wrongPin"));
    }

    @Test
    public void withdraw_withCorrectPin_balanceUpdatedTest() {
        this.myAcount.signUp("big_tee", "pass123");
        this.myAcount.signIn("big_tee", "pass123");
        this.myAcount.deposit(500);
        this.myAcount.withdraw(200, "pass123");
        assertEquals(300, this.myAcount.getBalance("pass123"));
    }

    @Test
    public void withdraw_afterSignOut_throwsIllegalArgumentExceptionTest() {
        this.myAcount.signUp("big_tee", "pass123");
        this.myAcount.signIn("big_tee", "pass123");
        this.myAcount.deposit(500);
        this.myAcount.signOut();
        assertThrows(IllegalArgumentException.class, () -> this.myAcount.withdraw(100, "pass123"));
    }

    @Test
    public void getBalance_withoutSignIn_throwsIllegalArgumentExceptionTest() {
        this.myAcount.signUp("big_tee", "pass123");
        assertThrows(IllegalArgumentException.class, () -> this.myAcount.getBalance("pass123"));
    }

    @Test
    public void getBalance_signedIn_withCorrectPin_returnsBalanceTest() {
        this.myAcount.signUp("big_tee", "pass123");
        this.myAcount.signIn("big_tee", "pass123");
        this.myAcount.deposit(750);
        assertEquals(750, this.myAcount.getBalance("pass123"));
    }

    @Test
    public void getBalance_signedIn_withWrongPin_throwsIllegalArgumentExceptionTest() {
        this.myAcount.signUp("big_tee", "pass123");
        this.myAcount.signIn("big_tee", "pass123");
        this.myAcount.deposit(750);
        assertThrows(IllegalArgumentException.class, () -> this.myAcount.getBalance("wrongPin"));
    }

    @Test
    public void getBalance_afterSignOut_throwsIllegalArgumentExceptionTest() {
        this.myAcount.signUp("big_tee", "pass123");
        this.myAcount.signIn("big_tee", "pass123");
        this.myAcount.deposit(750);
        this.myAcount.signOut();
        assertThrows(IllegalArgumentException.class, () -> this.myAcount.getBalance("pass123"));
    }
}
