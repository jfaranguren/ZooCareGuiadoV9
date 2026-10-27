package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import model.activity.*;
import model.animal.Animal;
import model.animal.DietType;
import model.employee.Employee;
import model.employee.Role;

/**
 * Represents a Zoo with a name, city, address, budget, and a collection of
 * habitats.
 */
public class Zoo {

    // Atributes
    private String name;
    private String city;
    private String address;
    private double budget;

    // Relations
    private Habitat[] myHabitats;
    private Map zooMap;
    private ArrayList<Animal> myAnimals;
    private ArrayList<Employee> myEmployees;
    private ArrayList<Activity> myActivities;

    // Constructor
    /**
     * Constructs a Zoo object with the specified name, city, address, and budget.
     * Initializes an array to store up to 33 habitats.
     *
     * @param name    The name of the zoo.
     * @param city    The city where the zoo is located.
     * @param address The address of the zoo.
     * @param budget  The initial budget of the zoo.
     */
    public Zoo(String name, String city, String address, double budget) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.budget = budget;

        myHabitats = new Habitat[33];
        zooMap = new Map();
        createVeterinaryClinic();
        myAnimals = new ArrayList<Animal>();
        myEmployees = new ArrayList<Employee>();
        myActivities = new ArrayList<Activity>();

        testData();
    }

    /**
     * Populates the zoo with test data for demonstration purposes.
     *
     * This method adds:
     * - Two habitats with predefined attributes.
     * - One animal in the "Sabana" habitat.
     * - Two employees with different roles.
     * - A feeding activity for the animal "Simba".
     * - An enrichment activity in the "Sabana" habitat.
     * - A cleaning activity in the "Sabana" habitat.
     *
     * Preconditions:
     * - The `myHabitats`, `myAnimals`, and `myEmployees` collections must be
     * initialized.
     *
     * Postconditions:
     * - The zoo is populated with sample data for testing.
     */
    public void testData() {

        addHabitat("H01", "Sabana", 1, 200, 4);
        addHabitat("H02", "Artico", 2, 100, 5);

        addAnimalInHabitat("Sabana", "Simba", 50, 1, "juvenil", "observacion");

        addEmployee("1111", 1);
        addEmployee("2222", 2);

        addFeedingActivity("H01", "1111", LocalDate.now().plusDays(1), "Simba", 20);
        addEnrichmentActivity("H01", "1111", LocalDate.now().plusDays(7), "Inclusion de elementos de recreacion", 2,
                "Tronco-Rascador");
        addCleaningActivity("H01", "1111", LocalDate.now().plusDays(3), "Limpieza general",
                "Escoba-Bolsa de Basura-Jabon-Agua");

    }

    // Analyzers

    /**
     * Retrieves the general information of the zoo, including its name, city,
     * address, and budget.
     *
     * @return A string containing the general information of the zoo in the format:
     *         "name-city-address-budget".
     */
    public String getInformation() {
        return name + "-" + city + "-" + address + "-" + budget;
    }

    /**
     * Creates a veterinary clinic habitat and adds it to the zoo.
     *
     * Postconditions:
     * - A habitat named "Clinica Veterinaria" is added to the zoo
     */
    private void createVeterinaryClinic() {
        addHabitat("CV", "Clinica Veterinaria", 4, 400, 25);
    }

    /**
     * Adds a new habitat to the zoo. The habitat is created with the specified
     * name, environment, and area. The habitat is added to the first available
     * position in the habitats array.
     * 
     * Preconditions:
     * - The 'myHabitats' array must be initialized.
     *
     * Postconditions:
     * - If there is an available position in the `myHabitats` array, the new
     * habitat is added to the zoo.
     * 
     * @param code        The code of the habitat.
     * @param name        The name of the habitat.
     * @param environment The type of environment in the habitat (e.g., jungle,
     *                    desert).
     * @param area        The area of the habitat in square meters.
     * @param maxCapacity The maximum capacity of the habitat.
     * @return {@code true} if the habitat was successfully added; {@code false}
     *         if there is no available space in the habitats array.
     */
    public boolean addHabitat(String code, String name, int environment, double area, int maxCapacity) {

        EnvironmentType myEnvironmentType = EnvironmentType.calculateEnvironmentType(environment);

        if (myHabitats != null) {

            for (int i = 0; i < myHabitats.length; i++) {
                if (myHabitats[i] == null) {

                    Habitat myHabitat = new Habitat(code, name, myEnvironmentType, area, maxCapacity);

                    if (zooMap.addHabitat(myHabitat)) {
                        myHabitats[i] = myHabitat;
                        return true;
                    }
                }

            }
        }

        return false;

    }

    /**
     * Checks if there is at least one available habitat in the zoo.
     * 
     * Preconditions:
     * - The `myHabitats` array must be initialized.
     *
     * @return {@code true} if there is at least one habitat available;
     *         {@code false} otherwise.
     */
    public boolean hasAvailableHabitat() {

        if (myHabitats != null) {

            for (int i = 0; i < myHabitats.length; i++) {
                if (myHabitats[i] != null) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * Retrieves a list of all habitats in the zoo, including their names and
     * environments.
     * 
     * Preconditions:
     * - The `myHabitats` array must be initialized.
     * 
     * @return A string containing the list of habitats in the format:
     *         "name-environment", with each habitat on a new line.
     */
    public String getHabitatList() {

        String habitatList = "";

        if (myHabitats != null) {

            for (int i = 0; i < myHabitats.length; i++) {
                if (myHabitats[i] != null) {

                    habitatList += "\n" + myHabitats[i].getName() + "-" + myHabitats[i].getEnvironment().getTypeName();

                }
            }
        }
        return habitatList;

    }

    /**
     * Adds an animal to a specific habitat in the zoo.
     * 
     * Preconditions:
     * - The `myHabitats` array must be initialized.
     * 
     * * Postconditions:
     * - If a habitat with the specified `habitatName` exists and has available
     * space, the animal is added to the habitat.
     * 
     * 
     * @param habitatName  The name of the habitat where the animal will be added.
     * @param name         The name of the animal.
     * @param weight       The weight of the animal in kilograms.
     * @param dietType     The type of diet the animal follows (e.g., herbivore,
     *                     carnivore, omnivore).
     * @param lifeStage    The life stage of the animal (e.g., juvenile, adult,
     *                     senior).
     * @param healthStatus The health status of the animal (e.g., healthy, sick,
     *                     injured).
     * @return {@code true} if the animal was successfully added to the habitat;
     *         {@code false} if the habitat
     *         does not exist or is full.
     */
    public boolean addAnimalInHabitat(String habitatName, String name, double weight, int dietType, String lifeStage,
            String healthStatus) {

        DietType myDietType = DietType.calculateDietType(dietType);

        if (myHabitats != null && myDietType != null) {

            for (int i = 0; i < myHabitats.length; i++) {
                if (myHabitats[i] != null && myHabitats[i].getName().equals(habitatName)) {

                    Animal myAnimal = new Animal(generateIdFromDate(), name, weight,
                            myDietType, lifeStage, healthStatus);

                    if (!myHabitats[i].addAnimal(myAnimal)) {
                        return false;
                    } else {
                        myAnimals.add(myAnimal);
                        return true;
                    }

                }
            }
        }

        return false;
    }

    /**
     * Retrieves a list of all animals in all habitats in the zoo.
     *
     * Preconditions:
     * - The `myHabitats` array must be initialized.
     *
     * @return A string containing the list of all animals in all habitats,
     *         separated by newlines.
     */

    public String getAllAnimalList() {

        String allAnimalList = "";

        for (int i = 0; i < myHabitats.length; i++) {
            if (myHabitats[i] != null) {
                allAnimalList += myHabitats[i].getAnimalList() + "\n";

            }

        }
        return allAnimalList;
    }

    /**
     * Retrieves a list of all environment types available in the zoo.
     *
     * @return A string containing the list of all environment types.
     */
    public String getEnvironmentTypeList() {

        return EnvironmentType.getOptionList();

    }

    /**
     * Adds a new employee to the zoo.
     *
     * @param id   The unique identifier of the employee.
     * @param role The role of the employee, represented as an integer.
     * @return {@code true} if the employee was successfully added, {@code false}
     *         otherwise.
     */
    public boolean addEmployee(String id, int role) {

        Role myRole = Role.calculateRole(role);

        if (myRole == null) {
            return false;
        }

        Employee myEmployee = new Employee(id, myRole);

        return myEmployees.add(myEmployee);

    }

    /**
     * Retrieves the map of the zoo as a string.
     *
     * Preconditions:
     * - The `zooMap` object must be initialized (not null).
     *
     * @return A string containing the zoo map.
     */
    public String getZooMap() {

        return zooMap.getMapAsString();
    }

    // Modifiers
    /**
     * Sets the name of the zoo.
     *
     * @param name The new name of the zoo.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the city where the zoo is located.
     *
     * @param city The new city of the zoo.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the address of the zoo.
     *
     * @param address The new address of the zoo.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the budget of the zoo.
     *
     * @param budget The new budget of the zoo.
     */
    public void setBudget(double budget) {
        this.budget = budget;
    }

    /**
     * Adds a feeding activity to the zoo.
     *
     * @param habitatId      The unique identifier of the habitat where the activity
     *                       will take place.
     * @param employeeId     The unique identifier of the employee responsible for
     *                       the activity.
     * @param schedulingDate The date when the activity is scheduled.
     * @param animalName     The name of the animal involved in the
     *                       activity.
     * @param weight         The weight of the food to be provided in the activity.
     * @return A message indicating the result of the operation:
     *         - "Error de busqueda, no existe un Habitat, Empleado o Animal con el
     *         ID suministrado" if any entity is not found.
     *         - "El Animal seleccionado no se encuentra en el Habitat
     *         proporcionado" if the animal is not in the specified habitat.
     *         - "El Empleado seleccionado no está activo" if the employee is not
     *         active.
     *         - "El Empleado no esta autorizado para realizar esa actividad" if the
     *         employee is not authorized for the activity.
     *         - "Actividad registrada exitosamente" if the activity is successfully
     *         registered.
     */
    public String addFeedingActivity(String habitatId, String employeeId, LocalDate schedulingDate,
            String animalName, double weight) {

        // Busquedas
        Habitat myHabitat = searchHabitat(habitatId); // null o el objeto
        Employee myEmployee = searchEmployee(employeeId); // null o el objeto
        Animal myAnimal = searchAnimal(animalName); // null o el objeto

        // Validaciones
        if (myHabitat == null || myEmployee == null || myAnimal == null) {
            return "Error de busqueda, no existe un Habitat, Empleado o Animal con el ID suministrado";
        }
        if (!myHabitat.containsAnimal(myAnimal)) {
            return "El Animal seleccionado no se encuentra en el Habitat proporcionado";
        }
        if (!myEmployee.isActive()) {
            return "El Empleado seleccionado no está activo";
        }

        FeedingActivity myActivity = new FeedingActivity(generateIdFromDate(), myHabitat, myEmployee, schedulingDate,
                myAnimal,
                weight);

        if (!myEmployee.addActivity(myActivity)) {
            return "El Empleado no esta autorizado para realizar esa actividad";
        }
        myActivities.add(myActivity);
        return "Actividad registrada exitosamente";
    }

    /**
     * Adds a cleaning activity to the zoo.
     *
     * @param habitatId      The unique identifier of the habitat where the activity
     *                       will take place.
     * @param employeeId     The unique identifier of the employee responsible for
     *                       the activity.
     * @param schedulingDate The date when the activity is scheduled.
     * @param description    A description of the cleaning activity.
     * @param usedProducts   A string containing the products used in the cleaning
     *                       activity, separated by "-".
     * @return A message indicating the result of the operation:
     *         - "Error de busqueda, no existe un Habitat o Empleado" if the habitat
     *         or employee is not found.
     *         - "El Empleado seleccionado no está activo" if the employee is not
     *         active.
     *         - "El Empleado no esta autorizado para realizar esa actividad" if the
     *         employee is not authorized for the activity.
     *         - "Actividad registrada exitosamente" if the activity is successfully
     *         registered.
     */
    public String addCleaningActivity(String habitatId, String employeeId, LocalDate schedulingDate,
            String description, String usedProducts) {

        // Busquedas
        Habitat myHabitat = searchHabitat(habitatId); // null o el objeto
        Employee myEmployee = searchEmployee(employeeId); // null o el objeto

        // Validaciones
        if (myHabitat == null || myEmployee == null) {
            return "Error de busqueda, no existe un Habitat o Empleado";
        }

        if (!myEmployee.isActive()) {
            return "El Empleado seleccionado no está activo";
        }

        String[] myUsedproducts = usedProducts.split("-"); // Solo funciona si el String de productos usados separa los
                                                           // productos con "-"

        CleaningActivity myActivity = new CleaningActivity(generateIdFromDate(), myHabitat, myEmployee, schedulingDate,
                description,
                new ArrayList<String>(Arrays.asList(myUsedproducts)));

        if (!myEmployee.addActivity(myActivity)) {
            return "El Empleado no esta autorizado para realizar esa actividad";
        }
        myActivities.add(myActivity);
        return "Actividad registrada exitosamente";
    }

    /**
     * Adds an enrichment activity to the zoo.
     *
     * @param habitatId      The unique identifier of the habitat where the activity
     *                       will take place.
     * @param employeeId     The unique identifier of the employee responsible for
     *                       the activity.
     * @param schedulingDate The date when the activity is scheduled.
     * @param description    A description of the enrichment activity.
     * @param durationInDays The duration of the enrichment activity in days.
     * @param usedProducts   A string containing the products used in the enrichment
     *                       activity, separated by "-".
     * @return A message indicating the result of the operation:
     *         - "Error de busqueda, no existe un Habitat o Empleado" if the habitat
     *         or employee is not found.
     *         - "El Empleado seleccionado no está activo" if the employee is not
     *         active.
     *         - "El Empleado no esta autorizado para realizar esa actividad" if the
     *         employee is not authorized for the activity.
     *         - "Actividad registrada exitosamente" if the activity is successfully
     *         registered.
     */
    public String addEnrichmentActivity(String habitatId, String employeeId, LocalDate schedulingDate,
            String description, int durationInDays, String usedProducts) {

        // Busquedas
        Habitat myHabitat = searchHabitat(habitatId); // null o el objeto
        Employee myEmployee = searchEmployee(employeeId); // null o el objeto

        // Validaciones
        if (myHabitat == null || myEmployee == null) {
            return "Error de busqueda, no existe un Habitat o Empleado";
        }

        if (!myEmployee.isActive()) {
            return "El Empleado seleccionado no está activo";
        }

        String[] myUsedproducts = usedProducts.split("-"); // Solo funciona si el String de productos usados separa los
                                                           // productos con "-"

        EnrichmentActivity myActivity = new EnrichmentActivity(generateIdFromDate(), myHabitat, myEmployee,
                schedulingDate,
                description, durationInDays,
                new ArrayList<String>(Arrays.asList(myUsedproducts)));

        if (!myEmployee.addActivity(myActivity)) {
            return "El Empleado no esta autorizado para realizar esa actividad";
        }

        myActivities.add(myActivity);
        return "Actividad registrada exitosamente";
    }

    /**
     * Retrieves information about all activities registered in the zoo.
     *
     * @return A formatted String containing information about all activities.
     *         If no activities are registered, returns "No hay actividades
     *         registradas".
     */
    public String getAllActivitiesInformation() {

        String list = "";

        for (Activity activity : myActivities) {

            list += activity.toString() + "\n";

        }

        if (list.equals("")) {
            return "No hay actividades registradas";
        }

        return list;

    }

    /**
     * Retrieves information about activities of a specific type.
     *
     * @param option The type of activity to filter by:
     *               1 - FeedingActivity
     *               2 - CleaningActivity
     *               3 - EnrichmentActivity
     * @return A formatted String containing information about activities of the
     *         specified type.
     */
    public String getActivitiesInformationByType(int option) {

        String list = "";

        for (Activity activity : myActivities) {

            switch (option) {
                case 1:
                    if (activity instanceof FeedingActivity) {
                        list += activity.toString() + "\n";
                    }
                    break;
                case 2:
                    if (activity instanceof CleaningActivity) {
                        list += activity.toString() + "\n";
                    }
                    break;
                case 3:
                    if (activity instanceof EnrichmentActivity) {
                        list += activity.toString() + "\n";
                    }
                    break;

                default:
                    break;
            }

        }

        if (list.equals("")) {
            return "No hay actividades registradas del tipo seleccionado";
        }

        return list;

    }

    /**
     * Retrieves information about all feeding activities registered in the zoo.
     *
     * @return A formatted String containing information about all feeding
     *         activities.
     */
    public String getFeedingActivitiesInformation() {

        String list = "";

        for (Activity activity : myActivities) {

            if (activity instanceof FeedingActivity) {
                list += activity.toString() + "\n";
            }

        }

        return list;
    }

    /**
     * Searches for an animal in the zoo by its unique identifier.
     *
     * @param animalName The unique identifier of the animal to search for.
     * @return The Animal object if found, or {@code null} if no animal with the
     *         specified ID exists.
     */
    private Animal searchAnimal(String animalName) {

        for (Animal myAnimal : myAnimals) {
            if (myAnimal.getName().equals(animalName)) {
                return myAnimal;
            }
        }
        return null;

    }

    /**
     * Searches for an employee in the zoo by their unique identifier.
     *
     * @param employeeId The unique identifier of the employee to search for.
     * @return The Employee object if found, or {@code null} if no employee with the
     *         specified ID exists.
     */
    private Employee searchEmployee(String employeeId) {

        for (Employee myEmployee : myEmployees) {
            if (myEmployee.getId().equals(employeeId)) {
                return myEmployee;
            }
        }
        return null;
    }

    /**
     * Searches for a habitat in the zoo by its unique identifier.
     *
     * @param habitatId The unique identifier of the habitat to search for.
     * @return The Habitat object if found, or {@code null} if no habitat with the
     *         specified ID exists.
     */
    private Habitat searchHabitat(String habitatId) {
        for (Habitat myHabitat : myHabitats) {
            if (myHabitat != null && myHabitat.getId().equals(habitatId)) {
                return myHabitat;
            }
        }
        return null;
    }

    /**
     * Generates a unique identifier based on the current date and time.
     *
     * @return A unique identifier as a String, generated using the current minute,
     *         second, and nanosecond.
     */
    public String generateIdFromDate() {

        return "" + LocalDateTime.now().getMinute() + LocalDateTime.now().getSecond() + LocalDateTime.now().getNano();

    }

    /**
     * Retrieves a concatenated string of descriptions for all enrichment activities
     * that occurred in the same month and year as the specified date.
     *
     * Preconditions:
     * - The `date` parameter must not be null.
     * - The `myActivities` list must not be null and should contain valid
     * `Activity` objects.
     *
     *
     * @param date The {@link LocalDate} object representing the month and year
     *             to filter the enrichment activities by.
     * @return A string containing the descriptions of all enrichment activities
     *         that match the specified month and year. If no activities match,
     *         an empty string is returned.
     */
    public String getEnrichmentDescriptionInMonth(LocalDate date) {

        String list = "";

        for (Activity myActivity : myActivities) {

            if (myActivity instanceof EnrichmentActivity) {

                // EnrichmentActivity temporalActivity = (EnrichmentActivity) myActivity; //
                // Downcasting

                LocalDate executionDate = myActivity.getExecutionDate();

                if (executionDate.getMonth().equals(date.getMonth())
                        && executionDate.getYear() == date.getYear()) {
                    // list += temporalActivity.getDescription();

                    list += ((EnrichmentActivity) myActivity).getDescription();
                }

            }

        }

        return list;
    }

    /**
     * Updates the list of products used in a cleaning activity identified by the
     * given ID.
     *
     * Preconditions:
     * - The `newProducts` parameter must not be null or empty.
     * - The `idActivity` parameter must not be null or empty.
     * - The `myActivities` list must not be null and should contain valid
     * `Activity` objects.
     *
     * Postconditions:
     * - If a cleaning activity with the specified ID exists, its list of products
     * will be updated with the new products provided.
     *
     * @param newProducts A string containing the new products to be set, separated
     *                    by hyphens ("-").
     * @param idActivity  The ID of the cleaning activity to update.
     * @return {@code true} if the cleaning activity with the specified ID was found
     *         and updated;
     *         {@code false} otherwise.
     */
    public boolean setUsedProductsInCleaningActivity(String newProducts, String idActivity) {
        for (Activity myActivity : myActivities) {

            if (myActivity instanceof CleaningActivity && idActivity.equals(myActivity.getId())) {
                CleaningActivity semiActivity = (CleaningActivity) myActivity;

                String[] myUsedproducts = newProducts.split("-");

                semiActivity.setUsedProducts(new ArrayList<String>(Arrays.asList(myUsedproducts)));
                return true;
            }
        }
        return false;
    }

    /**
     * Counts the number of feeding activities that occurred in a specific habitat
     * during the specified month and year.
     *
     * Preconditions:
     * - The `habitatId` parameter must not be null or empty.
     * - The `date` parameter must not be null.
     * - The `myActivities` list must not be null and should contain valid
     * `Activity` objects.
     *
     *
     * @param habitatId The ID of the habitat to filter the feeding activities by.
     * @param date      The {@link LocalDate} object representing the month and year
     *                  to filter the feeding activities by.
     * @return The number of feeding activities that match the specified habitat ID
     *         and date.
     */
    public int countFeedingActivitiesInHabitatInMonth(String habitatId, LocalDate date) {
        int counter = 0;

        for (Activity myActivity : myActivities) {
            LocalDate executionDate = myActivity.getExecutionDate();

            if (myActivity.getHabitat().getId().equals(habitatId) && executionDate.getMonth().equals(date.getMonth())
                    && executionDate.getYear() == date.getYear()) {
                if (myActivity instanceof FeedingActivity) {
                    counter += 1;
                }
            }
        }
        return counter;

    }

    /**
     * Retrieves a concatenated string of details for all activities performed by a
     * specific employee
     * during the specified year.
     *
     * Preconditions:
     * - The `idEmployee` parameter must not be null or empty.
     * - The `date` parameter must not be null.
     * - The `myActivities` list must not be null and should contain valid
     * `Activity` objects.
     *
     *
     * @param idEmployee The ID of the employee whose activities are to be
     *                   retrieved.
     * @param date       The {@link LocalDate} object representing the year to
     *                   filter the activities by.
     * @return A string containing the details of all activities performed by the
     *         specified employee
     *         during the given year. If no activities match, an empty string is
     *         returned.
     */
    public String getDetailsFromEmployeeActivities(String idEmployee, LocalDate date) {

        String list = "";

        for (Activity myActivity : myActivities) {
            LocalDate executionDate = myActivity.getExecutionDate();

            if (myActivity.getResponsible().getId().equals(idEmployee) && executionDate.getYear() == date.getYear()) {

                list += myActivity.toString();
            }

        }
        return list;

    }
}
