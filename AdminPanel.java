/**
 * The option menu for admins.
 */
public class AdminPanel extends Menu {
    public AdminPanel() {
        super("Admin panel", "a");
    }

    protected void initItems() {
        setItems(new MenuItem[]{new SetInterestRate(), new SetOverDrawnFee()});
    }

    @Override
    public void onMenuStart() {
        if (!BankAccountDriver.isLoggedIn()) {
            BankAccountDriver.navigate(new Login());
            return;
        }
        if (!BankAccountDriver.getCurBankAccount().isAdmin()) {
            BankAccountDriver.navigate(new Home());
            return;
        }
    }

    /**
     * lets admins change the interest rate.
     */
    private class SetInterestRate extends Action {

        public SetInterestRate() {
            super("Set interest rate", "i");
        }

        /**
         * Ask the user for the new interest rate
         */
        @Override
        public void execute() {
            System.out.println("Please enter the new interest rate:");
            BankAccount.setInterestRate(BankAccountDriver.getInput().nextFloat());
            BankAccountDriver.getInput().nextLine();
        }
    }
    /**
     * lets admins change the overdrawn fee.
     */
    private class SetOverDrawnFee extends Action {

        public SetOverDrawnFee() {
            super("Set over drawn fee", "d");
        }
        /**
         * Ask the user for the new overdrawn fee.
         */
        @Override
        public void execute() {
            System.out.println("Please enter the new overdrawn fee:");
            BankAccount.setOverDrawFee(BankAccountDriver.getInput().nextFloat());
            BankAccountDriver.getInput().nextLine();
        }
    }

}

