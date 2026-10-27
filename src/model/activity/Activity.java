package model.activity;

import java.time.LocalDate;

import model.Habitat;
import model.employee.Employee;

/**
 * Represents an abstract activity that is associated with a habitat and managed
 * by an employee.
 * 
 * This class provides the basic structure for activities, including their ID,
 * habitat, responsible employee, status, cost, and important dates such as
 * registration, scheduling, and execution.
 * 
 */

public abstract class Activity {

    private String id;
    private Habitat habitat;
    private Employee responsible;
    private ActivityStatus status;
    private double cost;
    private LocalDate registrationDate;
    private LocalDate schedulingDate;
    private LocalDate executionDate;

    /**
     * Constructs an Activity object with the specified attributes.
     * 
     * @param id             The unique identifier of the activity. Must not be null or empty.
     * @param habitat        The habitat associated with the activity. Must not be null.
     * @param responsible    The employee responsible for the activity. Must not be null.
     * @param schedulingDate The date the activity is scheduled for. Must not be null.
     */
    public Activity(String id, Habitat habitat, Employee responsible, LocalDate schedulingDate) {
        this.id = id;
        this.habitat = habitat;
        this.responsible = responsible;

        status = ActivityStatus.ASSIGNED;
        cost = 0.0;

        registrationDate = LocalDate.now();
        this.schedulingDate = schedulingDate;
        executionDate = null;

    }

    /**
     * Retrieves the unique identifier of the activity.
     * 
     * @return The ID of the activity.
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the habitat associated with the activity.
     * 
     * @return The habitat of the activity.
     */
    public Habitat getHabitat() {
        return habitat;
    }

    /**
     * Retrieves the employee associated with the activity.
     * 
     * @return The responsible employee of the activity.
     */
    public Employee getResponsible() {
        return responsible;
    }

    /**
     * Retrieves the status associated with the activity.
     * 
     * @return The status of the activity.
     */
    public ActivityStatus getStatus() {
        return status;
    }

    /**
     * Retrieves the cost associated with the activity.
     * 
     * @return The cost of the activity.
     */
    public double getCost() {
        return cost;
    }

    /**
     * Retrieves the registration date associated with the activity.
     * 
     * @return The registration date of the activity.
     */
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Retrieves the scheduling date associated with the activity.
     * 
     * @return The scheduling date of the activity.
     */
    public LocalDate getSchedulingDate() {
        return schedulingDate;
    }

    /**
     * Retrieves the execution date associated with the activity.
     * 
     * @return The execution date of the activity.
     */
    public LocalDate getExecutionDate() {
        return executionDate;
    }

    /**
     * Sets the habitat associated with the activity.
     * 
     * @param habitat The habitat to associate with the activity. Must not be null.
     * 
     * Preconditions:
     *  - `habitat` must not be null.
     * 
     * Postconditions:
     * - The `habitat` of the activity is updated to the specified value.
     */
    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

    /**
     * Sets the employee responsible for the activity.
     * 
     * @param responsible The employee responsible for the activity. Must not be
     *                    null.
     * 
     * Preconditions:
     * - `responsible` must not be null.
     * 
     * Postconditions:
     * - The `responsible` employee of the activity is updated to the specified value.
     */
    public void setResponsible(Employee responsible) {
        this.responsible = responsible;
    }

    /**
     * Sets the status of the activity.
     * 
     * @param status The new status of the activity. Must not be null.
     * 
     * Preconditions:
     * - `status` must not be null.
     * 
     * Postconditions:
     *  - The `status` of the activity is updated to the specified value.
     */
    public void setStatus(ActivityStatus status) {
        this.status = status;
    }

    /**
     * Sets the cost of the activity.
     * 
     * @param cost The cost of the activity. Must be a non-negative value.
     * 
     * Preconditions:
     * - `cost` must be greater than or equal to 0.
     * 
     * Postconditions:
     * - The `cost` of the activity is updated to the specified value.
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Sets the scheduling date of the activity.
     * 
     * @param schedulingDate The date the activity is scheduled for. Must not be
     *                       null.
     * 
     * Preconditions:
     * - `schedulingDate` must not be null.
     * 
     * Postconditions:
     * - The `schedulingDate` of the activity is updated to the specified value.
     */
    public void setSchedulingDate(LocalDate schedulingDate) {
        this.schedulingDate = schedulingDate;
    }

    /**
     * Sets the execution date of the activity.
     * 
     * @param executionDate The date the activity is executed. Can be null if the
     *                      activity is not yet executed.
     * 
     * Postconditions:
     * - The `executionDate` of the activity is updated to the specified value.
     */
    public void setExecutionDate(LocalDate executionDate) {
        this.executionDate = executionDate;
    }

    /**
     * Returns a string representation of the activity.
     * 
     * @return A string containing the details of the activity, including its ID,
     *         habitat, responsible employee, status, cost, registration date, 
     *         scheduling date, and execution date.
     */
    @Override
    public String toString() {
        return "Activity [id=" + id + ", habitat=" + habitat + ", responsible=" + responsible + ", status=" + status
                + ", cost=" + cost + ", registrationDate=" + registrationDate + ", schedulingDate=" + schedulingDate
                + ", executionDate=" + executionDate + "]";
    }

}
