package model;

import java.util.ArrayList;

import model.animal.Animal;

/**
 * Represents a Habitat in the zoo with a name, environment type, and area.
 * A habitat can contain up to 10 animals.
 */
public class Habitat {

    private String id;
    private String name;
    private EnvironmentType environment;
    private double area;
    private int maxCapacity;

    private ArrayList<Animal> myAnimals;

    /**
     * Constructs a Habitat object with the specified name, environment, and area.
     * 
     * @param id          The code of the habitat.
     * @param name        The name of the habitat.
     * @param environment The type of environment in the habitat (e.g., aquatic,
     *                    terrestrial, avian).
     * @param area        The area of the habitat in square meters.
     * @param maxCapacity The maximum capacity of the habitat.
     */
    public Habitat(String id, String name, EnvironmentType environment, double area, int maxCapacity) {
        this.id = id;
        this.name = name;
        this.environment = environment;
        this.area = area;
        this.maxCapacity = maxCapacity;

        myAnimals = new ArrayList<Animal>();

    }

    /**
     * Adds an animal to the habitat if there is available space.
     *
     * Preconditions:
     * - The `myAnimal` parameter must not be null.
     * - The 'myAnimals' list must be initialized.
     *
     * Postconditions:
     * - If the habitat is not full, the animal is added to the `myAnimals` list.
     *
     * @param myAnimal The animal to be added to the habitat.
     * @return true if the animal was successfully added, false if the habitat is
     *         full.
     */
    public boolean addAnimal(Animal myAnimal) {

        if (myAnimals.size() <= maxCapacity) {
            return myAnimals.add(myAnimal);
        }

        return false;

    }

    /**
     * Checks if the habitat contains any animals.
     *
     * @return true if the habitat has at least one animal, false otherwise.
     */
    public boolean hasAnimals() {

        return !myAnimals.isEmpty();

    }

    /**
     * Retrieves the code of the habitat.
     *
     * @return The code of the habitat.
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the name of the habitat.
     *
     * @return The name of the habitat.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the type of environment in the habitat.
     *
     * @return The type of environment in the habitat.
     */
    public EnvironmentType getEnvironment() {
        return environment;
    }

    /**
     * Retrieves the area of the habitat.
     *
     * @return The area of the habitat as a double.
     */
    public double getArea() {
        return area;
    }

    /**
     * Retrieves a list of animals in the habitat as a formatted String.
     *
     * Preconditions:
     * - The 'myAnimals' list must be initialized.
     *
     * Postconditions:
     * - A String representation of the animals in the habitat is returned.
     *
     * @return A formatted String containing the list of animals in the habitat.
     */

    public String getAnimalList() {

        String animalList = "";

        for (int i = 0; i < myAnimals.size(); i++) {

            animalList += myAnimals.get(i).getName() + "\n";
        }

        return animalList;

    }

    /**
     * Checks if the habitat contains the specified animal.
     *
     * @param myAnimal The animal to check for in the habitat.
     * @return {@code true} if the habitat contains the specified animal,
     *         {@code false} otherwise.
     */
    public boolean containsAnimal(Animal myAnimal) {

        return myAnimals.contains(myAnimal);

    }

}
