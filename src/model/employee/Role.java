package model.employee;

public enum Role {

    /**
     * Caretaker role.
     */
    CARETAKER("Cuidador"),

    /**
     * Veterinarian role.
     */
    VETERINARIAN("Veterinario");

    /**
     * The text of the value, shown to the user.
     */
    private String text;

    /**
     * Constructor for the Role enum.
     *
     * @param text the name shown to the user
     */
    private Role(String text) {
        this.text = text;
    }

    /**
     * Gets the name shown to the user.
     *
     * @return the name of the value
     */
    public String getText() {
        return text;
    }

    /**
     * Builds a numbered list with all role types.
     *
     * @return The numbered list, one option per line.
     */
    public static String getOptionList() {
        String list = "";
        Role[] values = Role.values();
        for (int i = 0; i < values.length; i++) {
            list += (i + 1) + ". " + values[i].getText() + "\n";
        }
        return list;
    }

    /**
     * Calculates the role type based on the given option.
     *
     * Preconditions:
     * - The `option` parameter must be a valid index (1-based)
     * corresponding to an `Role` value.
     * 
     * @param option The 1-based index of the role type.
     * @return The `Role` corresponding to the given `option`.
     */
    public static Role calculateRole(int option) {

        Role[] values = Role.values();

        if (option < 1 || option > values.length) {
            return null;
        }

        return values[option - 1];

    }

}
