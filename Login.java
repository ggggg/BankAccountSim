/**
 * The login page.
 */
public class Login extends Menu {
    public Login() {
        super("Login", null);
    }

    @Override
    protected void initItems() {
        setItems(new MenuItem[]{new LoginAction(), new SignUp()});
    }

    /**
     * Check if user is already logged in.
     */
    @Override
    public void onMenuStart() {
        super.onMenuStart();
        if (BankAccountDriver.isLoggedIn()) {
            BankAccountDriver.navigateHome();
            return;
        }
    }

    /**
     * Login menu item.
     */
    private class LoginAction extends Action {

        public LoginAction() {
            super("login", "l");
        }

        /**
         * Login into an account.
         */
        @Override
        public void execute() {

            // ask for username.
            System.out.println("Username:");
            String username = BankAccountDriver.getInput().nextLine();

            // Check if user is registered.
            if (!BankAccountDriver.getUsers().containsKey(username)) {
                System.out.println("User not found.");
                return;
            }

            // Ask for password.
            System.out.println("Password:");
            String password = BankAccountDriver.getInput().nextLine();

            // check password.
            if (!BankAccountDriver.getUsers().get(username).checkPassword(password)) {
                System.out.println("Wrong password.");
                return;
            }
            BankAccountDriver.setCurBankAccount(BankAccountDriver.getUsers().get(username));
            BankAccountDriver.navigateHome();
        }
    }

    /**
     * Signup menu item.
     */
    private class SignUp extends Action {

        public SignUp() {
            super("Sign up", "s");
        }

        /**
         * Create an account.
         */
        @Override
        public void execute() {

            // while username is taken, ask for a new username.
            String username;
            System.out.println("Username:");
            username = BankAccountDriver.getInput().nextLine();
            while (BankAccountDriver.getUsers().containsKey(username)) {
                System.out.println(
                        "A user with the username: " + username + " already exists, please enter another.");
                username = BankAccountDriver.getInput().nextLine();
            }

            // Ask user for password.
            System.out.println("Password:");
            String password = BankAccountDriver.getInput().nextLine();

            // Option to add a name to the account.
            System.out.println("Would you like too add your name? type yes or no");
            BankAccount newAccount;
            if (BankAccountDriver.getInput().nextLine().equalsIgnoreCase("yes")) {
                System.out.println("Please enter your name:");
                newAccount = new BankAccount(password, BankAccountDriver.getInput().nextLine());
            } else {
                newAccount = new BankAccount(password);
            }
            BankAccountDriver.getUsers().put(username, newAccount);
        }
    }
}
