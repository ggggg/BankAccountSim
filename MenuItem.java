/**
 * An item displayed in menus.
 */
public abstract class MenuItem {
    private final String name;
    private final String shortForm;

    /**
     * @param name the display name of the item.
     * @param shortForm what to input to open said item.
     */
    public MenuItem(String name, String shortForm) {
        this.shortForm = shortForm;
        this.name = name;
    }

    /**
     * @return the display name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * @return the short form of the item.
     */
    public String getShortForm() {
        return shortForm;
    }
}