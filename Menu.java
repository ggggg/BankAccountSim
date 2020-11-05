/**
 * A menu which has sub items that one can select from.
 */
public abstract class Menu extends MenuItem {
    private MenuItem[] items;

    /**
     * set display options.
     * @param name the name displayed in menu.
     * @param shortForm what one has to type to select.
     */
    public Menu(String name, String shortForm) {
        super(name, shortForm);
        initItems();
    }

    /**
     * Set the menu items.
     * @param items the menu items.
     */
    protected void setItems(MenuItem[] items) {
        this.items = items;
    }

    /**
     * @return all the menu items
     */
    public MenuItem[] getItems() {
        return items;
    }

    /**
     * Fill in the items array.
     */
    protected abstract void initItems();

    /**
     * Code to execute when the menu is selected.
     */
    public void onMenuStart(){}

    /**
     * Executes or navigates to selected item.
     * @param input the input from the user.
     */
    public void onInput(String input) {

        // find the item matching the input.
        for (MenuItem item : items) {
            if (item.getShortForm().equals(input)) {

                // check the type of the item
                if (item instanceof Action) {

                    // execute the action.
                    ((Action) item).execute();
                    return;
                }
                if (item instanceof Menu) {

                    // open the menu.
                    BankAccountDriver.navigate((Menu) item);
                    return;
                }
            }
        }

        // invalid option.
        System.out.println("Please enter a valid option from the list.");
    }
}