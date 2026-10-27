package model;

/**
 * The Map class represents a 2D grid structure used to model a zoo map.
 * It contains constants for different types of cells (e.g., available spaces,
 * veterinary clinics, and non-enabled spaces)
 * and provides methods to initialize and manage the map.
 * 
 * The map is represented as a 10x10 grid, where each cell is a string
 * indicating its type.
 */
public class Map {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private final int ROWS = 10;
    private final int COLUMNS = 10;

    private final String AVAILABLE = "D ";
    private final String VETERINARY_CLINIC = "CV";
    private final String NOT_ENABLED = "  ";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private String[][] map;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    public Map() {

        map = new String[ROWS][COLUMNS];

        initializeMap();
    }

    // -----------------------------------------------------------------
    // Initialization
    // -----------------------------------------------------------------

    /**
     * Initializes the zoo map.
     *
     * The method must:
     * 1. Identify positions that are not enabled for construction.
     * 2. Mark enabled positions as AVAILABLE (D).
     * 3. Locate the Veterinary Clinic in its corresponding positions.
     */
    private void initializeMap() {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i % 3 == 0 || j % 3 == 0) {
                    map[i][j] = NOT_ENABLED;
                } else {
                    map[i][j] = AVAILABLE;
                }
            }
        }

    }

    /**
     * Places the Veterinary Clinic on the map.
     *
     * The clinic always occupies:
     * (1,1), (1,2), (2,1), (2,2)
     */
    private void initializeVeterinaryClinic() {

        // map[1][1] = VETERINARY_CLINIC;
        // map[1][2] = VETERINARY_CLINIC;
        // map[2][1] = VETERINARY_CLINIC;
        // map[2][2] = VETERINARY_CLINIC;

        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 2; j++) {
                map[i][j] = VETERINARY_CLINIC;
            }
        }

    }

    // -----------------------------------------------------------------
    // Habitat placement
    // -----------------------------------------------------------------

    /**
     * TODO
     * Determines the number of positions required based on the habitat area.
     *
     * @param area The area of the habitat.
     * @return The number of positions required, or 0 if the area is invalid.
     */
    public int calculateRequiredPositions(double area) {

        int requiredPositions = 0;

        if (area > 0 && area <= 100) {
            requiredPositions = 1;
        } else if (area > 100 && area <= 200) {
            requiredPositions = 2;
        } else if (area > 200 && area <= 300) {
            requiredPositions = 3;
        } else if (area > 300 && area <= 400) {
            requiredPositions = 4;
        }

        return requiredPositions;

    }

    /**
     * Tries to automatically locate a habitat
     * in a valid block of the zoo map.
     *
     * @param habitat habitat to place.
     * @return true if the habitat could be located.
     */
    public boolean addHabitat(Habitat habitat) {

        if (habitat == null) {

            return false;
        }

        int requiredPositions = calculateRequiredPositions(habitat.getArea());

        if (requiredPositions == 0) {

            return false;
        }

        for (int row = 1; row <= 7; row += 3) {

            for (int column = 1; column <= 7; column += 3) {

                int[][] positions = findPositions(row, column, requiredPositions);

                /*
                 * La matriz solo se modifica después de encontrar
                 * todas las posiciones requeridas.
                 */
                if (positions != null) {

                    occupyPositions(
                            habitat.getId(),
                            positions);

                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks whether a particular position in the map is available.
     *
     * @param row    The row of the position to check.
     * @param column The column of the position to check.
     * @return true if the position is available, false otherwise.
     */
    private boolean isAvailable(int row, int column) {

        return getPosition(row, column).equals(AVAILABLE);
    }

    /**
     * Places the habitat code in all the specified positions on the map.
     *
     * @param habitatCode The code of the habitat to place.
     * @param positions   A 2D array containing the coordinates of the positions to
     *                    occupy.
     */
    private void occupyPositions(
            String habitatCode,
            int[][] positions) {

        // Recorre positions[][] y asigna el valor de habitatCode en mapa

        for (int coordinate = 0; coordinate < positions.length; coordinate++) {

            int row = positions[coordinate][0];
            int column = positions[coordinate][1];

            map[row][column] = habitatCode;

        }

    }

    /**
     * Selects the search method based on the number of required positions.
     *
     * @param row               initial row of the block.
     * @param column            initial column of the block.
     * @param requiredPositions number of required positions.
     * @return found coordinates or null.
     */
    private int[][] findPositions(
            int row,
            int column,
            int requiredPositions) {

        switch (requiredPositions) {
            case 1:
                return findOnePosition(row, column);
            case 2:
                return findTwoPositions(row, column);
            case 3:
                return findThreePositions(row, column);
            case 4:
                return findFourPositions(row, column);
            default:
                return null;
        }
    }

    /**
     * TODO
     * Finds the first available position within a 2x2 block.
     *
     * Checks the following positions in order:
     * (row, column), (row, column + 1), (row + 1, column), (row + 1, column + 1).
     *
     * @param row    The initial row of the block.
     * @param column The initial column of the block.
     * @return A 2D array containing the coordinates of the first available
     *         position, or null if none are available.
     */
    private int[][] findOnePosition(int row, int column) {

        int[][] positions = null;

        if (isAvailable(row, column)) {

            positions = new int[1][2];
            positions[0][0] = row;
            positions[0][1] = column;

        } else if (isAvailable(row, column + 1)) {

            positions = new int[1][2];
            positions[0][0] = row;
            positions[0][1] = column + 1;

        } else if (isAvailable(row + 1, column)) {

            positions = new int[1][2];
            positions[0][0] = row + 1;
            positions[0][1] = column;

        } else if (isAvailable(row + 1, column + 1)) {

            positions = new int[1][2];
            positions[0][0] = row + 1;
            positions[0][1] = column + 1;

        }

        return positions;
    }

    /**
     * TODO
     * Finds two available positions within a 2x2 block that share a side.
     *
     * Valid pairs within the block:
     * - Horizontal pairs: (row, column) and (row, column + 1).
     * - Vertical pairs: (row, column) and (row + 1, column).
     * - Other valid pairs are checked similarly.
     * Diagonal positions are not considered contiguous.
     *
     * @param row    The initial row of the block.
     * @param column The initial column of the block.
     * @return A 2D array containing the coordinates of the two available positions,
     *         or null if none are found.
     */
    private int[][] findTwoPositions(int row, int column) {

        /**
         * Busca dos posiciones disponibles que compartan un lado.
         *
         * Pares válidos dentro de una manzana:
         *
         * A B __A . __. B __. .
         * . . __C . __. D __C D
         *
         * Las posiciones diagonales NO son contiguas.
         */

        int[][] positions = null;

        // Primera fila
        if (isAvailable(row, column)
                && isAvailable(row, column + 1)) {
            positions = new int[2][2];
            positions[0][0] = row;
            positions[0][1] = column;
            positions[1][0] = row;
            positions[1][1] = column + 1;

        }
        // Segunda fila
        else if (isAvailable(row + 1, column)
                && isAvailable(row + 1, column + 1)) {
            positions = new int[2][2];
            positions[0][0] = row + 1;
            positions[0][1] = column;
            positions[1][0] = row + 1;
            positions[1][1] = column + 1;

        }
        // Primera columna
        else if (isAvailable(row, column)
                && isAvailable(row + 1, column)) {
            positions = new int[2][2];
            positions[0][0] = row;
            positions[0][1] = column;
            positions[1][0] = row + 1;
            positions[1][1] = column;

        }
        // Segunda columna
        else if (isAvailable(row, column + 1)
                && isAvailable(row + 1, column + 1)) {
            positions = new int[2][2];
            positions[0][0] = row;
            positions[0][1] = column + 1;
            positions[1][0] = row + 1;
            positions[1][1] = column + 1;
        }

        return positions;
    }

    /**
     * TODO
     * Finds three available positions within a 2x2 block that share sides.
     *
     * Valid combinations within the block:
     * - Horizontal triplets: (row, column), (row, column + 1), and (row + 1,
     * column).
     * - Vertical triplets: (row, column), (row + 1, column), and (row + 1, column +
     * 1).
     * - Other valid combinations are checked similarly.
     * Diagonal positions are not considered contiguous.
     *
     * @param row    The initial row of the block.
     * @param column The initial column of the block.
     * @return A 2D array containing the coordinates of the three available
     *         positions, or null if none are found.
     */
    private int[][] findThreePositions(int row, int column) {

        int[][] positions = null;

        /**
         * Busca tres posiciones disponibles que compartan un lado.
         *
         * Ternas válidas dentro de una manzana:
         *
         * A B __A B__ A .__ . B
         * C . __. D__ C D__ C D
         *
         * Las posiciones diagonales NO son contiguas.
         */

        if (isAvailable(row, column)
                && isAvailable(row, column + 1) && isAvailable(row + 1, column)) {
            positions = new int[3][2];
            positions[0][0] = row;
            positions[0][1] = column;
            positions[1][0] = row;
            positions[1][1] = column + 1;
            positions[2][0] = row + 1;
            positions[2][1] = column;

        } else if (isAvailable(row, column)
                && isAvailable(row, column + 1) && isAvailable(row + 1, column + 1)) {
            positions = new int[3][2];
            positions[0][0] = row;
            positions[0][1] = column;
            positions[1][0] = row;
            positions[1][1] = column + 1;
            positions[2][0] = row + 1;
            positions[2][1] = column + 1;

        } else if (isAvailable(row, column)
                && isAvailable(row + 1, column) && isAvailable(row + 1, column + 1)) {
            positions = new int[3][2];
            positions[0][0] = row;
            positions[0][1] = column;
            positions[1][0] = row + 1;
            positions[1][1] = column;
            positions[2][0] = row + 1;
            positions[2][1] = column + 1;

        } else if (isAvailable(row, column + 1)
                && isAvailable(row + 1, column) && isAvailable(row + 1, column + 1)) {
            positions = new int[3][2];
            positions[0][0] = row;
            positions[0][1] = column + 1;
            positions[1][0] = row + 1;
            positions[1][1] = column;
            positions[2][0] = row + 1;
            positions[2][1] = column + 1;
        }

        return positions;
    }

    /**
     * TODO
     * Finds four available positions within a 2x2 block.
     *
     * All positions in the block must be available:
     * (row, column), (row, column + 1), (row + 1, column), and (row + 1, column +
     * 1).
     *
     * @param row    The initial row of the block.
     * @param column The initial column of the block.
     * @return A 2D array containing the coordinates of the four available
     *         positions, or null if none are found.
     */
    private int[][] findFourPositions(int row, int column) {

        int[][] positions = null;

        if (isAvailable(row, column) && isAvailable(row, column + 1) && isAvailable(row + 1, column)
                && isAvailable(row + 1, column + 1)) {

            positions = new int[4][2];

            positions[0][0] = row;
            positions[0][1] = column;

            positions[1][0] = row;
            positions[1][1] = column + 1;

            positions[2][0] = row + 1;
            positions[2][1] = column;

            positions[3][0] = row + 1;
            positions[3][1] = column + 1;

        }

        return positions;
    }

    // -----------------------------------------------------------------
    // Habitat removal
    // -----------------------------------------------------------------

    /**
     * Removes a habitat from the map and frees all its positions.
     *
     * The habitat can only be removed if it has no assigned animals.
     *
     * @param habitat The habitat to be removed.
     * @return true if the habitat was successfully removed, false otherwise.
     */
    public boolean removeHabitat(Habitat habitat) {

        boolean removed = false;

        // TODO:
        // 1. Validar que habitat no sea null.
        // 2. Verificar que el hábitat no tenga animales asignados.
        // 3. Recorrer toda la matriz.
        // 4. Buscar las posiciones cuyo contenido sea habitat.getCode().
        // 5. Cambiar esas posiciones a AVAILABLE.
        // 6. Retornar true si se encontró y retiró al menos una posición.

        return removed;
    }

    // -----------------------------------------------------------------
    // Queries
    // -----------------------------------------------------------------

    /**
     * Calculates the number of positions currently available
     * for the construction of habitats.
     *
     * @return number of available positions.
     */
    public int countAvailablePositions() {

        int count = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].equals(AVAILABLE)) {
                    count++;
                }

            }

        }

        return count;
    }

    /**
     * Returns a text representation of the complete zoo map,
     * including row and column indexes.
     *
     * @return map represented as a String.
     */
    public String getMapAsString() {

        String result = "";

        // Add column headers
        result += "\t";
        for (int col = 0; col < map[0].length; col++) {
            if (col < 10) {
                result += "|0" + col + "|\t";
            } else {
                result += "|" + col + "|\t";
            }
        }
        result += "\n";

        // Add horizontal separator
        result += "\t";
        for (int col = 0; col < map[0].length; col++) {
            result += "____\t";
        }
        result += "\n";

        // Add rows with row headers and cell values
        for (int i = 0; i < map.length; i++) {

            // Row header
            if (i < 10) {
                result += "|0" + i + "|";
            } else {
                result += "|" + i + "|";
            }

            // Cell values
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null) {
                    result += "\t|" + map[i][j] + "|";
                }
            }
            result += "\n";
        }

        return result;
    }

    /**
     * Returns the content of a specific map position.
     *
     * @param row    row index.
     * @param column column index.
     * @return content of the position.
     */
    public String getPosition(int row, int column) {

        return map[row][column];
    }
}