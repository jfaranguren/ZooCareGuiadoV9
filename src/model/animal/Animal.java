package model.animal;

/**
 * Represents an Animal with specific attributes such as name, weight, diet
 * type, life stage, and health status.
 */
public class Animal {

    private String id;
    private String name;
    private double weight;
    private DietType dietType;
    private String lifeStage;
    private String healthStatus;

    /**
     * Constructs an Animal object with the specified attributes.
     *
     * @param id           The unique identifier for the animal.
     * @param name         The name of the animal.
     * @param weight       The weight of the animal in kilograms.
     * @param dietType     The type of diet the animal follows (e.g., herbivore,
     *                     carnivore, omnivore).
     * @param lifeStage    The life stage of the animal (e.g., juvenile, adult,
     *                     senior).
     * @param healthStatus The health status of the animal (e.g., healthy, sick,
     *                     injured).
     */
    public Animal(String id, String name, double weight, DietType dietType, String lifeStage, String healthStatus) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.dietType = dietType;
        this.lifeStage = lifeStage;
        this.healthStatus = healthStatus;
    }

    /**
     * Calculates the monthly ration cost for the animal based on its diet type,
     * weight, and other factors.
     *
     * @return The monthly ration cost in the appropriate currency.
     */
    public double calculateMonthlyRationCost() {

        double rationFactor = 0.0;
        double foodUnitCost = 0.0;
        double incrementPercentage = 0.0;

        switch (dietType) {
            case CARNIVORE:
                rationFactor = 0.03;
                foodUnitCost = 25500;
                break;
            case HERBIVORE:
                rationFactor = 0.04;
                foodUnitCost = 8800;
                break;

            case OMNIVORE:
                rationFactor = 0.035;
                foodUnitCost = 15200;
                break;

            case INSECTIVORE:
                rationFactor = 0.025;
                foodUnitCost = 18300;
                break;

        }

        if (lifeStage.equals("juvenil")) {
            incrementPercentage = 0.2;
        } else if (healthStatus.equals("cuarentena") || healthStatus.equals("recuperacion")) {
            incrementPercentage = 0.15;
        } else if (healthStatus.equals("observacion")) {
            incrementPercentage = 0.1;
        }

        return (weight * rationFactor) * (1 + incrementPercentage) * foodUnitCost * 30;
    }

    /**
     * Calculates the monthly ration cost for the animal based on its weight, diet
     * type, and life stage.
     *
     * @return The monthly ration cost in the appropriate currency.
     */
    public String getName() {

        return name;

    }

    /**
     * Gets the unique identifier of the animal.
     *
     * @return The unique identifier of the animal.
     */
    public String getId() {

        return id;

    }

    /**
     * Gets the diet type of the animal.
     *
     * @return The diet type of the animal.
     */
    public DietType getDietType() {
        return dietType;
    }
}
