/**
 * The option menu for an account.
 */
public class MyAccount extends Menu {
    public MyAccount() {
        super("My account", "m");
    }

    protected void initItems() {
        setItems(new MenuItem[]{new Deposit(), new AccountInfo(), new WithDraw(), new ChangeName(), new ChangePassword(), new InterestWait()});
    }

    /**
     * Check if logged in.
     */
    @Override
    public void onMenuStart() {
        if (!BankAccountDriver.isLoggedIn()) {
            BankAccountDriver.navigate(new Login());
            return;
        }
    }

    /**
     * show the account info.
     */
    private static class AccountInfo extends Action {

        public AccountInfo() {
            super("Account info", "i");
        }

        /**
         * print the account's info.
         */
        @Override
        public void execute() {
            System.out.println(BankAccountDriver.getCurBankAccount());
        }
    }

    /**
     * Deposit into account.
     */
    private static class Deposit extends Action {

        public Deposit() {
            super("Deposit", "d");
        }

        /**
         * Get the user input to deposit into account.
         */
        @Override
        public void execute() {
            System.out.println("How much would you like to deposit:");
            BankAccountDriver.getCurBankAccount().deposit(BankAccountDriver.getInput().nextInt());
            BankAccountDriver.getInput().nextLine();
        }
    }

    /**
     * Withdraw from account.
     */
    private static class WithDraw extends Action {

        public WithDraw() {
            super("With draw", "w");
        }
        /**
         * Get the user input to Withdraw from account.
         */
        @Override
        public void execute() {
            System.out.println("How much would you like to withdraw:");
            BankAccountDriver.getCurBankAccount().withdraw(BankAccountDriver.getInput().nextInt());
            BankAccountDriver.getInput().nextLine();
        }
    }

    /**
     * Change the account password.
     */
    private static class ChangePassword extends Action {

        public ChangePassword() {
            super("Change password", "cp");
        }

        /**
         * Get the user input to change the account password.
         */
        @Override
        public void execute() {
            System.out.println("Please enter new password:");
            BankAccountDriver.getCurBankAccount().setPassword(BankAccountDriver.getInput().nextLine());
            System.out.println("Success!");
        }
    }

    /**
     * Simulate a month passing, adding interest and charging fees.
     */
    private static class InterestWait extends Action {

        public InterestWait() {
            super("Wait a month for interest", "wait");
        }

        /**
         * Simulate a month passing.
         */
        @Override
        public void execute() {
            BankAccountDriver.getCurBankAccount().tickInterest();
            System.out.println("Success! Your current balance: " + BankAccountDriver.getCurBankAccount().getBalanceFormatted());
        }
    }
    /**
     * Change the account name.
     */
    private static class ChangeName extends Action {

        public ChangeName() {
            super("Change account name", "cn");
        }

        /**
         * Get the user input to change the account name.
         */
        @Override
        public void execute() {
            System.out.println("Please enter your new name:");
            BankAccountDriver.getCurBankAccount().setName(BankAccountDriver.getInput().nextLine());
            System.out.println("Success! Your new name is now: " + BankAccountDriver.getCurBankAccount().getName());
        }
    }

}

