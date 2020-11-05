/**
 * Menu item which executes code when selected.
 */
public abstract class Action extends MenuItem {

    /**
     * set display options.
     * @param name the name displayed in menu.
     * @param shortForm what one has to type to select.
     */
    public Action(String name, String shortForm) {
        super(name, shortForm);
    }

    /**
     * What happens when code is executed.
     */
    public abstract void execute();
}