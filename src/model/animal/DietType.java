package model.animal;

/**
 * Enum representing the diet type of an animal.
 * It can be CARNIVORE, HERBIVORE, OMNIVORE, or INSECTIVORE, each with an
 * associated text description.
 */
public enum DietType {

    /**
     * Represents a carnivorous diet type with the description "Carnivora".
     */
    CARNIVORE("Carnivora"),
    /**
     * Represents a herbivorous diet type with the description "Hervibora".
     */

    HERBIVORE("Hervibora"),
    /**
     * Represents an omnivorous diet type with the description "Omnivora".
     */

    OMNIVORE("Omnivora"),
    /**
     * Represents an insectivorous diet type with the description "Insectivora".
     */

    INSECTIVORE("Insectivora");

    /**
     * The text description of the activity status.
     */
    private String text;

    /**
     * Constructor for the ActivityStatus enum.
     *
     * @param text the text description of the activity status
     */
    private DietType(String text) {
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
     * Builds a numbered list with all diet types.
     *
     * @return The numbered list, one option per line.
     */
    public static String getOptionList() {
        String list = "";
        DietType[] values = DietType.values();
        for (int i = 0; i < values.length; i++) {
            list += (i + 1) + ". " + values[i].getText() + "\n";
        }
        return list;
    }

    /**
     * Calculates the diet type based on the given diet option.
     *
     * Preconditions:
     * - The `dietOption` parameter must be a valid index (1-based)
     * corresponding to an `DietType` value.
     * 
     * @param dietOption The 1-based index of the diet type.
     * @return The `DietType` corresponding to the given `dietOption`.
     */
    public static DietType calculateDietType(int dietOption) {

        DietType[] values = DietType.values();

        if (dietOption < 1 || dietOption > values.length) {
            return null;
        }

        return values[dietOption - 1];

    }

}
