import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 * @author Ido Ben Haim
 */

/**
 * A bank account.
 */
public class BankAccount {

    private static final String defaultName = "";

    private static final String defaultPassword = "";

    private static final double startingBalance = 0;

    private static final DecimalFormat format = new DecimalFormat("$###.##");

    private static float interestRate = 0.1f;

    private static float overDrawFee = 100;

    /*weather the account can change rates and fees*/
    private boolean admin = false;

    /*
     * name of customer.
     */
    private String name = defaultName;

    /*
     * name of customer.
     */
    private int password;

    /**
     * balance of the customer.
     */
    private double balance;

    private final LocalDate createdDate;

    /**
     * deposit money into the user's account
     *
     * @param value the amount to deposit
     */
    public void deposit(int value) {
        balance += value;
    }

    /**
     * withdraw money from the account.
     *
     * @param value the amount to withdraw
     */
    public void withdraw(int value) {
        balance -= value;
    }

    /**
     * @param password the customer's name.
     */
    public BankAccount(String password, String name) {
        this();
        this.password = hashPassword(password);
        this.name = name;
    }

    /**
     * @param password the customer's name.
     */
    public BankAccount(String password) {
        this();
        this.password = hashPassword(password);
    }

    /**
     * Create a bank account with default values.
     */
    public BankAccount() {
        createdDate = LocalDate.now();
        this.password = hashPassword(defaultPassword);
        this.balance = startingBalance;
    }

    /**
     * set the account password.
     *
     * @param password
     */
    public void setPassword(String password) {
        setPassword(hashPassword(password));
    }

    private void setPassword(int password) {
        this.password = password;
    }

    /**
     * Set the account balance.
     *
     * @param balance the new balance of the account.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return the customer's name
     */
    public String getName() {
        return name;
    }

    /**
     * Change the customer's name.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the customer's balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @return the customer's formatted balance.
     */
    public String getBalanceFormatted() {
        return format.format(balance);
    }
    /*
     * Add interest the the customer's balance.
     */
    public void tickInterest() {
        if (isOverdrawn()) {
            balance -= overDrawFee;
            return;
        }
        balance += balance * interestRate;
    }

    /**
     * Hashes the password for secure storage.
     *
     * @param password to be hashed.
     * @return the password hash.
     */
    private static int hashPassword(String password) {
        return password.hashCode();
    }

    /**
     * check if the users password is correct.
     *
     * @param password the password to be checked.
     * @return whether the password is the same as the user's or not.
     */
    public boolean checkPassword(String password) {
        return hashPassword(password) == this.password;
    }

    /**
     * @return whether the account is overdrawn.
     */
    public boolean isOverdrawn() {
        return this.balance < 0;
    }

    /**
     * @return the account info as string.
     */
    @Override
    public String toString() {
        return (!getName().equals(defaultName) ? getName() + "'s bank account, " : "")
                + "balance: " + this.getBalanceFormatted() + ", created on: " + getCreatedDate() +
                (isOverdrawn() ? " ACCOUNT OVERDRAWN!" : "");
    }

    /**
     * @return the date that the account was created on.
     */
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    /**
     * @return is the user admin.
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     *
     * @param admin is the user admin.
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * @param interestRate the global interest rate for all accounts.
     */
    public static void setInterestRate(float interestRate) {
        BankAccount.interestRate = interestRate;
    }

    /**
     * @param overDrawFee the global over draw fee for all accounts.
     */
    public static void setOverDrawFee(float overDrawFee) {
        BankAccount.overDrawFee = overDrawFee;
    }

    /**
     * @return an identical clone of the account.
     */
    @Override
    public BankAccount clone() {
        BankAccount newAccount = new BankAccount();
        newAccount.setPassword(this.password);
        newAccount.setName(this.getName());
        newAccount.setBalance(this.getBalance());
        return newAccount;
    }
}
