package model.activity;

import java.time.LocalDate;
import java.util.ArrayList;

import model.Habitat;
import model.employee.Employee;

/**
 * Represents a cleaning activity for a specific habitat.
 * This class extends the `Activity` class and includes additional attributes
 * specific to cleaning activities, such as the cleaning method used.
 */
public class CleaningActivity extends Activity {

    private String description;
    private ArrayList<String> usedProducts;

    /**
     * Constructs a CleaningActivity object with the specified attributes.
     * 
     * @param id             The unique identifier of the activity. Must not be null
     *                       or empty.
     * @param habitat        The habitat associated with the activity. Must not be
     *                       null.
     * @param responsible    The employee responsible for the activity. Must not be
     *                       null.
     * @param schedulingDate The date the activity is scheduled for. Must not be
     *                       null.
     * @param description    The description of the cleaning activity. Must not be
     *                       null or empty.
     * @param usedProducts   A list of products used for the cleaning activity. Must
     *                       not be null.
     * 
     */
    public CleaningActivity(String id, Habitat habitat, Employee responsible, LocalDate schedulingDate,
            String description, ArrayList<String> usedProducts) {
        super(id, habitat, responsible, schedulingDate);
        this.description = description;
        this.usedProducts = usedProducts;
    }

    /**
     * Retrieves the description of the cleaning activity.
     * 
     * @return The description of the cleaning activity.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the cleaning activity.
     * 
     * @param description The description of the cleaning activity. Must not be null
     *                    or empty.
     * 
     *                    Preconditions:
     *                    - `description` must not be null or empty.
     * 
     *                    Postconditions:
     *                    - The `description` of the cleaning activity is updated to
     *                    the specified value.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the list of products used for the cleaning activity.
     * 
     * @return A list of product names used for the cleaning activity.
     * 
     */
    public ArrayList<String> getUsedProducts() {
        return usedProducts;
    }

    /**
     * Sets the list of products used for the cleaning activity.
     * 
     * @param usedProducts A list of product names to set. Must not be null.
     * 
     *                     Preconditions:
     *                     - `usedProducts` must not be null.
     * 
     *                     Postconditions:
     *                     - The `usedProducts` of the cleaning activity is updated
     *                     to the specified value.
     */
    public void setUsedProducts(ArrayList<String> usedProducts) {
        this.usedProducts = usedProducts;
    }

    /**
     * Returns a string representation of the cleaning activity.
     * 
     * @return A string containing the details of the cleaning activity, including
     *         the cleaning method.
     * 
     */
    @Override
    public String toString() {
        return super.toString()
                + "\nCleaningActivity [description=" + description + ", usedProducts=" + usedProducts + "]";
    }

}
