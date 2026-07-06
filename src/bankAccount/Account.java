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
            this.balance += amount;
        }

    }

    public void withdraw(int amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
        }

    }

    public int getBalance() {
        return this.balance;
    }

    public void signUp(String username, String password) {
        if (!this.signedUp) {
            if (!username.isEmpty() && !password.isEmpty()) {
                this.username = username;
                this.password = password;
                this.signedUp = true;
            }
        }
    }

    public boolean isSignedUp() {
        return this.signedUp;
    }

    public String getUsername() {
        return this.username;
    }

    public void signIn(String username, String password) {
        if (this.signedUp) {
            if (this.username.equals(username) && this.password.equals(password)) {
                this.signedIn = true;
            }

        }
    }

    public void signOut() {
        this.signedIn = false;
    }

    public boolean isSignedIn() {
        return this.signedIn;
    }

    public void withdraw(int amount, String pin) {
        if (this.signedIn && this.password.equals(pin)) {
            if (amount > 0 && amount <= this.balance) {
                this.balance -= amount;
            }

        } else {
            throw new IllegalArgumentException("Wrong PIN or not signed in");
        }
    }

    public int getBalance(String pin) {
        if (this.signedIn && this.password.equals(pin)) {
            return this.balance;
        } else {
            throw new IllegalArgumentException("Wrong PIN or not signed in");
        }
    }
}