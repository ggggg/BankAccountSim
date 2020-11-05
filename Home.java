/**
 * The home page.
 */
public class Home extends Menu {
    public Home() {
        super("Home", null);
    }

    /**
     * if account is admin, add admin options.
     */
    protected void initItems() {
        if (BankAccountDriver.isLoggedIn() && BankAccountDriver.getCurBankAccount().isAdmin()) {
            setItems(new MenuItem[]{new Logout(), new MyAccount(), new AdminPanel()});
            return;
        }
        setItems(new MenuItem[]{new Logout(), new MyAccount()});
    }

    /**
     * Code to execute when the menu is selected.
     */
    @Override
    public void onMenuStart() {
        super.onMenuStart();

        // if not logged in, go to login.
        if (!BankAccountDriver.isLoggedIn()) {
            BankAccountDriver.navigate(new Login());
            return;
        }

        // welcome massage on home.
        System.out.println("Welcome " + BankAccountDriver.getCurBankAccount().getName());
    }

    /**
     * Log out menu item.
     */
    private class Logout extends Action {

        public Logout() {
            super("Logout", "l");
        }

        /**
         * log out.
         */
        @Override
        public void execute() {
            BankAccountDriver.setCurBankAccount(null);
            BankAccountDriver.navigate(new Login());
        }
    }

}

