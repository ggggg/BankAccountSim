import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankAccountDriver {

    /**
     * @return the logged in account.
     */
    public static BankAccount getCurBankAccount() {
        return instance.getCurBankAccount();
    }

    /**
     * @return weather the account is logged in or not.
     */
    public static boolean isLoggedIn() {
        return getCurBankAccount() != null;
    }

    /**
     * navigates to another menu.
     *
     * @param menu the menu to navigate to
     */
    public static void navigate(Menu menu) {
        instance.setCurrentMenu(menu);
    }

    /**
     * Navigates to home page.
     */
    public static void navigateHome() {
        navigate(new Home());
    }

    /**
     * @return the registered users.
     */
    public static HashMap<String, BankAccount> getUsers() {
        return instance.getUsers();
    }

    /**
     * @return the scanner object used for input.
     */
    public static Scanner getInput() {
        return instance.getInput();
    }

    private static Core instance;

    /**
     * Start the program.
     */
    public static void main(String[] args) {
        instance = new Core();
        instance.loop();
    }

    /**
     * @param curBankAccount new bank account to set.
     */
    public static void setCurBankAccount(BankAccount curBankAccount) {
        instance.setCurBankAccount(curBankAccount);
    }

    private static class Core {
        private Menu currentMenu;
        private BankAccount curBankAccount;
        private HashMap<String, BankAccount> users;
        private boolean isRunning;
        private Scanner input;

        /**
         * @param curBankAccount new bank account to set.
         */
        public void setCurBankAccount(BankAccount curBankAccount) {
            this.curBankAccount = curBankAccount;
        }

        /**
         * @return the registered users.
         */
        public HashMap<String, BankAccount> getUsers() {
            return users;
        }

        /**
         * @return
         */
        public Menu getCurrentMenu() {
            return currentMenu;
        }

        public BankAccount getCurBankAccount() {
            return curBankAccount;
        }

        public void setCurrentMenu(Menu currentMenu) {
            this.currentMenu = currentMenu;
        }

        public Scanner getInput() {
            return input;
        }

        public Core() {

            // init all the variables
            users = new HashMap<>();
            isRunning = true;
            input = new Scanner(System.in);
        }

        public void loop() {
            // Start at home.
            currentMenu = new Home();

            // add default admin account.
            final String adminPassword = "123";
            final String adminUsername = "admin";
            BankAccount adminAccount = new BankAccount(adminPassword);
            adminAccount.setAdmin(true);
            users.put(adminUsername, adminAccount);

            System.out.println("Welcome to royal totally not RBC bank");
            System.out.println("For testing purposes, the admin username is: \"" + adminUsername + "\" and the password is: \"" + adminPassword + "\"");

            // while running get input.
            while (isRunning) {

                // In case of invalid input return to the menu with error message.
                try {
                    currentMenu.onMenuStart();

                    // Display all the menu items.
                    for (MenuItem item : getCurrentMenu().getItems()) {
                        System.out.println("Type \"" + item.getShortForm() + "\" to " + item.getName());
                    }

                    // let the user know that he can return home (does not show on home page)
                    if (!(currentMenu instanceof Home || currentMenu instanceof Login)) {
                        System.out.println("To go back home enter \"home\", to exit type \"exit\"");
                    } else {
                        System.out.println("To exit type \"exit\"");
                    }

                    // Get input.
                    String temp;
                    do {
                        temp = input.nextLine();
                    } while (temp.isEmpty());

                    // check if user wants to go to home or exit
                    if (temp.equalsIgnoreCase("home") &&
                            !(currentMenu instanceof Home || currentMenu instanceof Login)) {
                        BankAccountDriver.navigateHome();
                        continue;
                    }
                    if (temp.equalsIgnoreCase("exit")) {
                        isRunning = false;
                        continue;
                    }

                    // Call the menu to handle the input.
                    getCurrentMenu().onInput(temp);
                } catch (InputMismatchException e) {

                    // Avoid any nextInt nextLine conflicts
                    input = new Scanner(System.in);
                    System.out.println("An error has occurred, please try again.");
                }
            }
            System.out.println("Bye!");
        }
    }
}

