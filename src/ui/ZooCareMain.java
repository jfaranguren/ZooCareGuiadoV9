package ui;

import java.util.Scanner;
import model.Zoo;

/**
 * Main class for the ZooCare application. This class provides a
 * menu-driven interface for managing a zoo.
 */
public class ZooCareMain {

    // Atributes/Relations
    private static Scanner reader = new Scanner(System.in);
    private static Zoo myZoo;

    /**
     * Main method to start the ZooCare application.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        menu();
    }

    /**
     * Displays the main menu of the ZooCare application and handles user input
     * to perform various operations such as registering zoo information, consulting
     * zoo details, and managing habitats and animals.
     *
     * Preconditions:
     * - The `reader` Scanner must be initialized.
     *
     */
    public static void menu() {

        System.out.print("\033[H\033[2J");

        int option = 0;

        do {
            System.out.println("Bienvenido a ZooCare");
            System.out.println("1. Registrar informacion general del zoologico");
            System.out.println("2. Consultar informacion general del zoologico");
            System.out.println("3. Modificar informacion general del zoologico");
            System.out.println("4. Registrar habitat");
            System.out.println("5. Registrar animal");
            System.out.println("6. Mostrar lista de animales");
            System.out.println("7. Mostrar mapa del zoologico");
            System.out.println("8. Mostrar actividades registradas");
            System.out.println("0. Salir");
            option = reader.nextInt();
            switch (option) {
                case 1:
                    registerZooInformation();
                    break;
                case 2:
                    showZooInformation();
                    break;
                case 3:
                    modifyZooInformation();
                    break;
                case 4:
                    registerHabitat();
                    break;
                case 5:
                    registerAnimal();
                    break;
                case 6:
                    showAnimalsRegistered();
                    break;
                case 7:
                    showZooMap();
                    break;
                case 8:
                    showZooActivities();
                    break;

                case 0:
                    System.out.println("Adios!");
                    break;
                default:
                    break;
            }

            if (option != 0) {
                clearConsole();
            }

        } while (option != 0);

    }

    /**
     * Clears the console screen after confirming if the user wants to return to the
     * main menu.
     * Uses ANSI escape codes to clear the screen.
     *
     * Preconditions:
     * - The `reader` Scanner must be initialized.
     *
     * @return The option selected by the user: 1 to return to the main menu, 2 to
     *         stay.
     */
    public static int clearConsole() {

        int option = 0;

        while (option != 1) {
            System.out.println("\nDesea volver al menu principal?");
            System.out.println("1. Si");
            System.out.println("2. No");
            option = reader.nextInt();

        }

        System.out.print("\033[H\033[2J");

        return option;

    }

    /**
     * Registers a new zoo by prompting the user to input its name, city, address,
     * and budget.
     * If a zoo is already registered, the method will display an error message.
     *
     * Preconditions:
     * - No zoo should be registered before calling this method.
     *
     * Postconditions:
     * - A new zoo will be created and registered with the provided details.
     *
     * Error Handling:
     * - If a zoo is already registered, an error message will be displayed.
     */
    public static void registerZooInformation() {

        if (myZoo == null) {

            reader.nextLine(); // Limpieza de buffer

            System.out.println("\nDigite el nombre del zoologico");
            String name = reader.nextLine();

            System.out.println("Digite la ciudad del zoologico");
            String city = reader.nextLine();

            System.out.println("Digite la direccion del zoologico");
            String address = reader.nextLine();

            System.out.println("Digite el presupuesto del zoologico");
            double budget = reader.nextDouble();

            myZoo = new Zoo(name, city, address, budget);

            System.out.println("Exito! Zoologico registrado exitosamente!");

        } else {
            System.out.println("Error! Ya existe un zoologico registrado");
        }

    }

    /**
     * Displays the general information about the zoo, including its name, city,
     * address, and budget. If no zoo is registered, an error message will be
     * displayed.
     *
     * Preconditions:
     * - A zoo must be registered before calling this method.
     *
     * Error Handling:
     * - If no zoo is registered, an error message will be displayed.
     */
    public static void showZooInformation() {
        if (myZoo != null) {
            System.out.println(myZoo.getInformation());
        } else {
            System.out.println("Error! Zoologico aun no registrado");
        }
    }

    /**
     * Modifies the zoo information by allowing the user to input new values for the
     * zoo's name, city, address, or budget. The method displays a menu for the user
     * to
     * select which information to modify and prompts the user to input the new
     * value.
     *
     * Preconditions:
     * - A zoo must be registered before calling this method.
     *
     * Postconditions:
     * - The selected attribute of the zoo will be updated with the new value
     * provided by the user.
     *
     * Error Handling:
     * - If no zoo is registered, the method will not perform any modifications.
     */
    public static void modifyZooInformation() {
        if (myZoo != null) {
            System.out.println("\nMenu de cambios de informacion general del Zoologico");
            System.out.println("1. Cambiar el nombre del Zoologico");
            System.out.println("2. Cambiar la ciudad del Zoologico");
            System.out.println("3. Cambiar la dirección del Zoologico");
            System.out.println("4. Cambiar el presupuesto del Zoologico");
            int option = reader.nextInt();
            switch (option) {
                case 1:
                    reader.nextLine();
                    System.out.print("\nPor favor ingresa el nuevo nombre del Zoologico: ");
                    String newName = reader.nextLine();
                    System.out.println("Dato registrado correctamente");
                    myZoo.setName(newName);
                    break;
                case 2:
                    reader.nextLine();
                    System.out.print("\nPor favor ingresa la nueva ciudad del Zoologico: ");
                    String newCity = reader.nextLine();
                    System.out.println("Dato registrado correctamente");
                    myZoo.setCity(newCity);
                    break;
                case 3:
                    reader.nextLine();
                    System.out.print("\nPor favor ingresa la nueva direccion del Zoologico: ");
                    String newAddress = reader.nextLine();
                    System.out.println("Dato registrado correctamente");
                    myZoo.setCity(newAddress);
                    break;
                case 4:
                    System.out.print("\nPor favor ingresa el nuevo presupuesto del Zoologico: ");
                    double newBudget = reader.nextDouble();
                    System.out.println("Dato registrado correctamente");
                    myZoo.setBudget(newBudget);
                    break;
                default:
                    System.out.print("\nDigite una opcion valida");
                    break;
            }
        } else {
            System.out.println("Error! Zoologico aun no registrado");
        }
    }

    /**
     * Registers a new habitat in the zoo by collecting details such as its name,
     * environment type, and area. The method prompts the user to input these
     * details and attempts to add the habitat to the zoo.
     *
     * Preconditions:
     * - A zoo must be registered before calling this method.
     *
     * Postconditions:
     * - If there is available space in the zoo, the habitat will be added.
     *
     * Error Handling:
     * - If no zoo is registered, an error message will be displayed.
     * - If the habitat cannot be added due to lack of space, an error message will
     * be displayed.
     */
    public static void registerHabitat() {

        if (myZoo != null) {

            reader.nextLine(); // Limpieza de buffer

            System.out.println("\nDigite el codigo del habitat");
            String code = reader.nextLine();

            System.out.println("\nDigite el nombre del habitat");
            String name = reader.nextLine();

            System.out.println("Digite el ambiente del habitat");
            System.out.println(myZoo.getEnvironmentTypeList());
            int environment = reader.nextInt();

            System.out.println("Digite el area del habitat");
            double area = reader.nextDouble();

            System.out.println("Digite la capacidad maxima del habitat");
            int maxCapacity = reader.nextInt();

            boolean result = myZoo.addHabitat(code, name, environment, area, maxCapacity);

            if (result) {
                System.out.println("Exito! Habitat registrado");
            } else {
                System.out.println("Error. Habitat no registrado");
            }

        } else {
            System.out.println("Error! Zoologico aun no registrado");
        }

    }

    /**
     * Registers a new animal in the zoo. The method prompts the user to input the
     * animal's details, including its name, weight, diet type, life stage, and
     * health status.
     * It also displays the list of available habitats and allows the user to select
     * the habitat where the animal will be added.
     *
     * Preconditions:
     * - A zoo must be registered before calling this method.
     * - The zoo must have at least one available habitat.
     *
     * Postconditions:
     * - If the specified habitat exists and has space, the animal will be added to
     * it.
     *
     * Error Handling:
     * - If no zoo is registered, an error message will be displayed.
     * - If there are no available habitats, the animal cannot be registered.
     */
    public static void registerAnimal() {

        if (myZoo != null) {

            if (myZoo.hasAvailableHabitat()) {

                reader.nextLine(); // Limpieza de buffer

                System.out.println("\nDigite el nombre del animal");
                String name = reader.nextLine();

                System.out.println("\nDigite el peso del animal");
                double weight = reader.nextDouble();

                reader.nextLine(); // Limpieza de buffer

                System.out.println(
                        "\nDigite el tipo de dieta del animal (carnivora (1), herbivora (2), omnivora (3), insectivora (4))");
                int dietType = reader.nextInt();
                reader.nextLine();

                System.out.println("\nDigite la etapa de vida del animal (juvenil o adulto)");
                String lifeStage = reader.nextLine();

                System.out.println(
                        "\nDigite el estado de salud del animal (saludable, cuarentena, recuperacion, en observacion)");
                String healthStatus = reader.nextLine();

                // Habitat
                System.out.println("\nA continuacion se presenta el listado de habitats registrados:");
                System.out.println(myZoo.getHabitatList());

                System.out.println("\nDigite el nombre del habitat al que se registrara el animal");
                String habitatName = reader.nextLine();

                boolean result = myZoo.addAnimalInHabitat(habitatName, name, weight, dietType, lifeStage, healthStatus);

                if (result) {
                    System.out.println("Animal registrado exitosamente");
                } else {
                    System.out.println("Error! No se pudo registrar el animal");
                }

            } else {
                System.out.println("Error! Habitats aun no registrados");
            }

        } else {
            System.out.println("Error! Zoologico aun no registrado");
        }

    }

    /**
     * Displays the list of all animals registered in the zoo.
     *
     * Preconditions:
     * - The `myZoo` object must be initialized (not null).
     */
    public static void showAnimalsRegistered() {
        if (myZoo != null) {
            System.out.println("Lista de animales registrados");
            System.out.println(myZoo.getAllAnimalList());
        } else {
            System.out.println("Error! Zoologico aun no registrado");
        }

    }

    /**
     * Displays the map of the zoo.
     *
     * Preconditions:
     * - The `myZoo` object must be initialized (not null).
     *
     */
    public static void showZooMap() {
        if (myZoo != null) {
            System.out.println("Mapa del Zoologico:");
            System.out.println(myZoo.getZooMap());
        } else {
            System.out.println("Error! Zoologico aun no registrado");
        }

    }

    /**
     * Displays all activities registered in the zoo.
     *
     * Preconditions:
     * - The `myZoo` object must be initialized (not null).
     *
     */
    private static void showZooActivities() {
        if (myZoo != null) {
            System.out.println("Actividades del Zoologico:");
            System.out.println(myZoo.getAllActivitiesInformation());
        } else {
            System.out.println("Error! Zoologico aun no registrado");
        }
    }

}