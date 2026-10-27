package model;

/**
 * Enum representing the status of a system.
 * It can either be ACTIVE or RETIRED, each with an associated text description.
 */
public enum SystemStatus {

    /**
     * Represents an active system status with the description "Activo".
     */
    ACTIVE("Activo"),

    /**
     * Represents a retired system status with the description "Retirado".
     */
    RETIRED("Retirado");

    /**
     * The text description of the system status.
     */
    private String text;

    /**
     * Constructor for the SystemStatus enum.
     *
     * @param text the text description of the system status
     */
    private SystemStatus(String text) {
        this.text = text;
    }

    /**
     * Gets the text description of the system status.
     *
     * @return the text description of the system status
     */
    public String getText() {
        return text;
    }

    /**
     * Builds a numbered list with all system status.
     *
     * @return The numbered list, one option per line.
     */
    public static String getOptionList() {
        String list = "";
        SystemStatus[] values = SystemStatus.values();
        for (int i = 0; i < values.length; i++) {
            list += (i + 1) + ". " + values[i].getText() + "\n";
        }
        return list;
    }

    /**
     * Calculates the system status based on the given option.
     *
     * Preconditions:
     * - The `option` parameter must be a valid index (1-based)
     * corresponding to an `SystemStatus` value.
     * 
     * @param option The 1-based index of the system status.
     * @return The `SystemStatus` corresponding to the given `option`.
     */
    public static SystemStatus calculateRole(int option) {

        SystemStatus[] values = SystemStatus.values();

        if (option < 1 || option > values.length) {
            return null;
        }

        return values[option - 1];

    }

}
