package model.activity;

/**
 * Enum representing the status of an activity.
 * It can be ASSIGNED, FINISHED, or CANCELED, each with an associated text
 * description.
 */
public enum ActivityStatus {

    /**
     * Represents an assigned activity status with the description "Asignada".
     */
    ASSIGNED("Asignada"),

    /**
     * Represents a finished activity status with the description "Finalizada".
     */
    FINISHED("Finalizada"),

    /**
     * Represents a canceled activity status with the description "Cancelada".
     */
    CANCELED("Cancelada");

    /**
     * The text description of the activity status.
     */
    private String text;

    /**
     * Constructor for the ActivityStatus enum.
     *
     * @param text the text description of the activity status
     */
    private ActivityStatus(String text) {
        this.text = text;
    }

    /**
     * Gets the text description of the activity status.
     *
     * @return the text description of the activity status
     */
    public String getText() {
        return text;
    }

    /**
     * Builds a numbered list with all activity status.
     *
     * @return The numbered list, one option per line.
     */
    public static String getOptionList() {
        String list = "";
        ActivityStatus[] values = ActivityStatus.values();
        for (int i = 0; i < values.length; i++) {
            list += (i + 1) + ". " + values[i].getText() + "\n";
        }
        return list;
    }

    /**
     * Calculates the activity status based on the given activity option.
     *
     * Preconditions:
     * - The `activityOption` parameter must be a valid index (1-based)
     * corresponding to an `ActivityStatus` value.
     * 
     * @param activityOption The 1-based index of the activity status.
     * @return The `ActivityStatus` corresponding to the given `activityOption`.
     */
    public ActivityStatus calculateActivityStatus(int activityOption) {

        ActivityStatus[] values = ActivityStatus.values();

        if (activityOption < 1 || activityOption > values.length) {
            return null;
        }

        return values[activityOption - 1];

    }

}
