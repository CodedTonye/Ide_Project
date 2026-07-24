package bankAccount;

public class Account {
    private int balance;
    private String username;
    private String password;
    private boolean signedUp;
    private boolean signedIn;

    public Account() {
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
        }

    }

    public void withdraw(int amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }

    }

    public int getBalance() {
        return balance;
    }

    public void signUp(String username, String password) {
        if (!signedUp) {
            if (!username.isEmpty() && !password.isEmpty()) {
                this.username = username;
                this.password = password;
                signedUp = true;
            }
        }
    }

    public boolean isSignedUp() {
        return signedUp;
    }

    public String getUsername() {
        return username;
    }

    public void signIn(String username, String password) {
        if (signedUp) {
            if (this.username.equals(username) && this.password.equals(password)) {
                signedIn = true;
            }

        }
    }

    public void signOut() {
        signedIn = false;
    }

    public boolean isSignedIn() {
        return signedIn;
    }

    public void withdraw(int amount, String pin) {
        if (signedIn && password.equals(pin)) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
            }

        } else {
            throw new IllegalArgumentException("Wrong PIN or not signed in");
        }
    }

    public int getBalance(String pin) {
        if (signedIn && password.equals(pin)) {
            return balance;
        } else {
            throw new IllegalArgumentException("Wrong PIN or not signed in");
        }
    }
}