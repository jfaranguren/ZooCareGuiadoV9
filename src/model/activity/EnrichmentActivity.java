package model.activity;

import java.time.LocalDate;
import java.util.ArrayList;

import model.Habitat;
import model.employee.Employee;

/**
 * Represents an enrichment activity for a specific habitat.
 * This class extends the `Activity` class and includes additional attributes
 * specific to enrichment activities, such as the description, duration, and
 * products used.
 */
public class EnrichmentActivity extends Activity {

    private String description;
    private int durationInDays;
    private ArrayList<String> usedProducts;

    /**
     * Constructs an EnrichmentActivity object with the specified attributes.
     * 
     * @param id             The unique identifier of the activity. Must not be null or empty.
     * @param habitat        The habitat associated with the activity. Must not be null.
     * @param responsible    The employee responsible for the activity. Must not be null.
     * @param schedulingDate The date the activity is scheduled for. Must not be null.
     * @param description    The description of the enrichment activity. Must not be null or empty.
     * @param durationInDays The duration of the activity in days. Must be greater than 0.
     * @param usedProducts   A list of products used for the activity. Must not be null.
     */ 
    public EnrichmentActivity(String id, Habitat habitat, Employee responsible, LocalDate schedulingDate,
            String description, int durationInDays, ArrayList<String> usedProducts) {
        super(id, habitat, responsible, schedulingDate);
        this.description = description;
        this.durationInDays = durationInDays;
        this.usedProducts = usedProducts;
    }

    /**
     * Retrieves the description of the enrichment activity.
     * 
     * @return The description of the enrichment activity.
     */ 
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the enrichment activity.
     * 
     * @param description The description of the enrichment activity. Must not be null or empty.
     * 
     * Preconditions:
     * - `description` must not be null or empty.
     * 
     * Postconditions:
     * - The `description` of the enrichment activity is updated to the specified value.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the duration of the enrichment activity in days.
     * 
     * @return The duration of the enrichment activity in days.
     * 
     */
    public int getDurationInDays() {
        return durationInDays;
    }

    /**
     * Sets the duration of the enrichment activity in days.
     * 
     * @param durationInDays The duration of the enrichment activity in days. Must be greater than 0.
     * 
     * Preconditions:
     * - `durationInDays` must be greater than 0.
     * 
     * Postconditions:
     * - The `durationInDays` of the enrichment activity is updated to the specified value.
     */
    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    /**
     * Retrieves the list of products used for the enrichment activity.
     * 
     * @return A list of product names used for the enrichment activity.
     * 
     * Postconditions:
     * - The list of products used for the enrichment activity is returned.
     */
    public ArrayList<String> getUsedProducts() {
        return usedProducts;
    }

    /**
     * Sets the list of products used for the enrichment activity.
     * 
     * @param usedProducts A list of product names to set. Must not be null.
     * 
     * Preconditions:
     * - `usedProducts` must not be null.
     * 
     * Postconditions:
     * - The `usedProducts` of the enrichment activity is updated to the specified value.
     */
    public void setUsedProducts(ArrayList<String> usedProducts) {
        this.usedProducts = usedProducts;
    }

    /**
     * Returns a string representation of the enrichment activity.
     * 
     * @return A string containing the details of the enrichment activity, including
     *         the description, duration in days, and the products used.
     * 
     */
    @Override
    public String toString() {
        return super.toString()
                + "\nEnrichmentActivity [description=" + description + ", durationInDays=" + durationInDays
                + ", usedProducts=" + usedProducts + "]";
    }

}
