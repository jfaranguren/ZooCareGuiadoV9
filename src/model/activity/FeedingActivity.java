package model.activity;

import java.time.LocalDate;

import model.Habitat;
import model.animal.Animal;
import model.animal.DietType;
import model.employee.Employee;

/**
 * Represents a feeding activity for a specific animal in a habitat.
 * This class extends the `Activity` class and includes additional attributes
 * specific to feeding activities, such as the animal being fed, its diet type,
 * and the weight of the food.
 */
public class FeedingActivity extends Activity {

    private Animal animal;
    private DietType dietType;
    private double weight;

    /**
     * Constructs a FeedingActivity object with the specified attributes.
     * 
     * @param id             The unique identifier of the activity. Must not be null or empty.
     * @param habitat        The habitat associated with the activity. Must not be null.
     * @param responsible    The employee responsible for the activity. Must not be null.
     * @param schedulingDate The date the activity is scheduled for. Must not be null.
     * @param animal         The animal being fed. Must not be null.
     * @param weight         The weight of the food to be provided. Must be greater than 0.
     */
    public FeedingActivity(String id, Habitat habitat, Employee responsible, LocalDate schedulingDate, Animal animal,
            double weight) {
        super(id, habitat, responsible, schedulingDate);
        this.animal = animal;
        dietType = animal.getDietType();
        this.weight = weight;
    }

    /**
     * Retrieves the animal associated with the feeding activity.
     * 
     * @return The animal being fed.
     */ 
    public Animal getAnimal() {
        return animal;
    }

    /**
     * Sets the animal associated with the feeding activity.
     * 
     * @param animal The animal to associate with the feeding activity. Must not be null.
     * 
     * Preconditions:
     * - `animal` must not be null.
     * 
     * Postconditions:
     * - The `animal` of the feeding activity is updated to the specified value.
     * - The `dietType` is updated based on the new animal's diet type.
     */
    public void setAnimal(Animal animal) {
        this.animal = animal;
        dietType = animal.getDietType();
    }

    /**
     * Retrieves the diet type of the animal being fed.
     * 
     * @return The diet type of the animal.
     * 
     * Postconditions:
     * - The diet type of the animal is returned.
     */
    public DietType getDietType() {
        return dietType;
    }

     /**
     * Retrieves the weight of the food to be provided.
     * 
     * @return The weight of the food.
     * 
     * Postconditions:
     * - The weight of the food is returned.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the food to be provided.
     * 
     * @param weight The weight of the food. Must be greater than 0.
     * 
     * Preconditions:
     * - `weight` must be greater than 0.
     * 
     * Postconditions:
     * - The `weight` of the feeding activity is updated to the specified value.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

     /**
     * Returns a string representation of the feeding activity.
     * 
     * @return A string containing the details of the feeding activity, including
     *         the animal, diet type, and weight.
     */
    @Override
    public String toString() {
        return super.toString()
                + "\nFeedingActivity [animal=" + animal + ", dietType=" + dietType + ", weight=" + weight + "]";
    }

}
