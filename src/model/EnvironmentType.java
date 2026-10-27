package model;

/**
 * Enum representing different types of environments.
 * Each environment type has a corresponding name.
 */
public enum EnvironmentType {

    /**
     * Represents a terrestrial environment.
     */
    LAND("Terrestre"),

    /**
     * Represents an aquatic environment.
     */
    WATER("Acuatico"),

    /**
     * Represents an aviary environment.
     */
    AVIARY("Aviario"),
    /**
     * Represents a medical environment.
     */
    MEDICAL("Medico");

    /**
     * The name of the environment type.
     */
    private String typeName;

    /**
     * Constructor for the EnvironmentType enum.
     *
     * @param typeName the name of the environment type
     */
    private EnvironmentType(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Gets the name of the environment type.
     *
     * @return the name of the environment type
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Builds a numbered list with all environment types.
     *
     * @return The numbered list, one option per line.
     */
    public static String getOptionList() {
        String list = "";
        EnvironmentType[] values = EnvironmentType.values();
        for (int i = 0; i < values.length; i++) {
            list += (i + 1) + ". " + values[i].getTypeName() + "\n";
        }
        return list;
    }

    /**
     * Calculates the environment type based on the given environment option.
     *
     * Preconditions:
     * - The `environmentOption` parameter must be a valid index (1-based)
     * corresponding to an `EnvironmentType` value.
     * 
     * @param environmentOption The 1-based index of the environment type (e.g., 1
     *                          for LAND, 2 for WATER).
     * @return The `EnvironmentType` corresponding to the given `environmentOption`.
     */

    public static EnvironmentType calculateEnvironmentType(int environmentOption) {

        // LAND [0], WATER [1], AVIARY [2], MEDICAL [3]

        EnvironmentType[] values = EnvironmentType.values();

        return values[environmentOption - 1];

    }

}
