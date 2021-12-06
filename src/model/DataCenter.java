package model;

import java.time.LocalDate;

/**
 * Controller class of the Data Center
 * 
 * @author Daniel Valencia - A00372845
 */
public class DataCenter {
    // Constants

    public final int ROWS = 8;
    public final int COLUMNS = 50;

    // Attribute

    private double miniRoomsBaseValue;

    // Relation

    private MiniRoom[][] miniRooms;
    private MiniRoom[][] testMiniRooms;

    // ------------------------------------------------------------------------------------------------

    // Constructor

    /**
     * Constructor of the data center
     * 
     * @param miniRoomsBaseValue base value for all the mini rooms
     */
    public DataCenter(double miniRoomsBaseValue) {
        this.miniRoomsBaseValue = miniRoomsBaseValue;
        miniRooms = new MiniRoom[ROWS][COLUMNS];
        testMiniRooms = new MiniRoom[ROWS][COLUMNS];
        initializeMiniRooms(miniRoomsBaseValue);
    }

    // ------------------------------------------------------------------------------------------------

    // Getters and setters

    /**
     * Returns the base value
     * 
     * @return base value of the mini rooms
     */
    public double getMiniRoomsBaseValue() {
        return miniRoomsBaseValue;
    }

    /**
     * Sets the base value of the mini rooms
     * 
     * @param miniRoomsBaseValue the base value to set
     */
    public void setMiniRoomsBaseValue(double miniRoomsBaseValue) {
        this.miniRoomsBaseValue = miniRoomsBaseValue;
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Goes over the mini room matrix to initialize the minirooms, assigning the
     * window boolean and the rent value according to the location
     * 
     * <p>
     * <b>Precondition: </b> The user starts the program
     * <p>
     * <b>Postcondition: </b> Mini rooms with initial values
     * 
     * @param miniRoomsBaseValue base value of the mini rooms for the calculations
     */
    public void initializeMiniRooms(double miniRoomsBaseValue) {
        int number = 1;
        double initialRentValue = 0;
        boolean inWindow = false;
        MiniRoom newMiniRoom;

        // Go over the mini room matrix
        for (int i = 0; i < miniRooms.length; i++) {
            for (int j = 0; j < miniRooms[0].length; j++) {
                if (i == 0 || i == 7 || j == 0 || j == 49) { // If a mini room is found in a window
                    initialRentValue = miniRoomsBaseValue * 0.9;
                    inWindow = true;

                    newMiniRoom = new MiniRoom(number, inWindow, initialRentValue);
                    miniRooms[i][j] = newMiniRoom;
                } else if (i == 6) { // If a mini room is in the 7th corridor (Excluding the ones in the windows)
                    initialRentValue = miniRoomsBaseValue * 0.85;
                    inWindow = false;

                    newMiniRoom = new MiniRoom(number, inWindow, initialRentValue);
                    miniRooms[i][j] = newMiniRoom;
                } else if (i >= 1 && i <= 5) { // If a mini room is between the 2nd and 6th corridor (Excluding the ones
                                               // in the windows)
                    initialRentValue = miniRoomsBaseValue * 1.25;
                    inWindow = false;

                    newMiniRoom = new MiniRoom(number, inWindow, initialRentValue);
                    miniRooms[i][j] = newMiniRoom;
                }

                // Fill the test matrix with mini rooms
                testMiniRooms[i][j] = new MiniRoom(0, false, 0);

                number++;
            }
        }
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Rents a mini room considering its location, information of the company and
     * date of rent
     * 
     * <p>
     * <b>Precondition: </b> The user selected the option to rent a mini room in the
     * menu
     * <p>
     * <b>Postcondition: </b> A message indicating the rent of the mini room or not
     * 
     * @param miniRoomLocation location of the mini room given in the format "ROW,
     *                         COLUMN"
     * @param rentDate         the date of rent of the mini room
     * @param companyNit       nit of the associated company that is renting the
     *                         mini room
     * @param name             name of the associated company that is renting the
     *                         mini room
     * @param numberOfServers  number of servers that will be used in the mini room
     * @return message indicating the result of the operation
     */
    public String rentMiniRoom(String miniRoomLocation, LocalDate rentDate, String companyNit, String name,
            int numberOfServers) {
        String message = "";
        MiniRoom foundMiniRoom = locateMiniRoom(miniRoomLocation);
        double rentValue = 0.0;

        // Checks if the selected mini room is available for rent or not
        if (foundMiniRoom.isAvailable()) {
            message = "Mini room rented!";

            Company associatedCompany = new Company(companyNit, name);

            foundMiniRoom.setAvailable(false);
            foundMiniRoom.setOn(true);
            foundMiniRoom.setRentDate(rentDate);
            foundMiniRoom.setAssociatedCompany(associatedCompany);

            // Checks if the fine needs to be added or not
            if (numberOfServers < 4) {
                rentValue = foundMiniRoom.getInitialRentValue() * 1.15;
                message += ", fine of 15% over the rent value applied for underutilization of the servers";
            } else {
                rentValue = foundMiniRoom.getInitialRentValue();
            }

            foundMiniRoom.setRentValue(rentValue);
        } else {
            message = "Error, the selected mini room is already rented";
        }

        return message;
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Overloaded method to rent a mini room for investigation purposes, considering
     * its location, rent date and the number of the investigation project
     * 
     * <p>
     * <b>Precondition: </b> The user selected the option to rent a mini room menu
     * by the means of an investigation project
     * <p>
     * <b>Postcondition: </b> A message indicating the rent of the mini room or not
     * 
     * @param miniRoomLocation location of the mini room given in the format "ROW,
     *                         COLUMN"
     * @param rentDate         the date of rent of the mini room
     * @param projectNumber    registry number of the investigation project
     * @param numberOfServers  number of servers that will be used in the mini room
     * @return message indicating the result of the operation
     */
    public String rentMiniRoom(String miniRoomLocation, LocalDate rentDate, String projectNumber, int numberOfServers) {
        String message = "";
        MiniRoom foundMiniRoom = locateMiniRoom(miniRoomLocation);
        double rentValue = 0.0;

        // Checks if the selected mini room is available for rent or not
        if (foundMiniRoom.isAvailable()) {
            message = "Mini room rented for investigation project!";

            Company associatedCompany = new Company(projectNumber);

            foundMiniRoom.setAvailable(false);
            foundMiniRoom.setOn(true);
            foundMiniRoom.setRentDate(rentDate);
            foundMiniRoom.setAssociatedCompany(associatedCompany);

            // Checks if the fine needs to be added or not
            if (numberOfServers < 4) {
                rentValue = foundMiniRoom.getInitialRentValue() * 1.15;
                message += ", fine of 15% over the rent value applied for underutilization of the servers";
            } else {
                rentValue = foundMiniRoom.getInitialRentValue();
            }

            foundMiniRoom.setRentValue(rentValue);
        } else {
            message = "Error, the selected mini room is already rented";
        }

        return message;
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Adds a new server into the mini room considering all of its information
     * 
     * <p>
     * <b>Precondition: </b> The user selected the option to rent a mini room in the
     * menu
     * <p>
     * <b>Postcondition: </b> The addition of servers in the mini room
     * 
     * @param miniRoomLocation        location of the mini room given in the format
     *                                "ROW,
     *                                COLUMN"
     * @param cacheMemory             the cache memory of the server
     * @param ramMemory               the RAM memory of the server
     * @param processorsNumber        the number of processors of the server
     * @param disksNumber             the number of disks of the server
     * @param totalDiskCapacity       the total capacity of the disks (in TeraBytes)
     * @param processorBrandSelection the selection of the processor brand
     *                                (<code>1</code> for Intel, <code>2</code> for
     *                                AMD)
     */
    public void addServerToMiniRoom(String miniRoomLocation, double cacheMemory, double ramMemory, int processorsNumber,
            int disksNumber, double totalDiskCapacity, int processorBrandSelection) {
        MiniRoom foundMiniRoom = locateMiniRoom(miniRoomLocation);
        Server newServer = new Server(cacheMemory, ramMemory, processorsNumber, disksNumber, totalDiskCapacity,
                processorBrandSelection);

        foundMiniRoom.addServer(newServer);
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Prints the list of available mini rooms in the matrix
     * 
     * <p>
     * <b>Precondition: </b> The user selected the option to list the available mini
     * rooms in the menu
     * <p>
     * <b>Postcondition: </b> A list of the available mini rooms
     * 
     * @return the list of available mini rooms
     */
    public String printAvailableMiniRooms() {
        String list = "";

        // Go over the matrix and identify the available mini rooms to print
        for (int i = 0; i < miniRooms.length; i++) {
            for (int j = 0; j < miniRooms[0].length; j++) {
                if (miniRooms[i][j].isAvailable()) {
                    list += "\n" + "Row: " + (i+1) + "| Column: " + (j+1) + "| " + miniRooms[i][j].toString();
                }
            }
        }

        // Checks if any available mini room has been found
        return list.equals("") ? "No available mini rooms" : list;
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Cancels the rent of a specific mini room (Sets the status to available,
     * erases the rent date, associated company and servers, resets the rent value
     * to its initial value and turns the mini room off)
     * 
     * <p>
     * <b>Precondition: </b> The user selected the option to cancel the rent of an
     * specific mini room in the menu
     * <p>
     * <b>Postcondition: </b> The cancelation of the rent of the mini room
     * 
     * @param miniRoomLocation location of the mini room given in the format "ROW,
     *                         COLUMN"
     * @return message indicating the result of the operation
     */
    public String cancelRent(String miniRoomLocation) {
        String message = "";
        MiniRoom foundMiniRoom = locateMiniRoom(miniRoomLocation);

        // Checks the availablility of the mini room
        if (!foundMiniRoom.isAvailable()) {
            foundMiniRoom.setAvailable(true);
            foundMiniRoom.setRentDate(null);
            foundMiniRoom.setAssociatedCompany(null);
            foundMiniRoom.eliminateServers();
            foundMiniRoom.setRentValue(foundMiniRoom.getInitialRentValue());
            foundMiniRoom.setOn(false);

            message = "Rent cancelled!";
        } else {
            message = "Error, the mini room has not been rented";
        }

        return message;
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Overloaded method to cancel the rent of every mini room (Sets the status to
     * available, erases the rent date, associated company and servers, resets the
     * rent value to its initial value and turns the mini rooms off)
     * 
     * <p>
     * <b>Precondition: </b> The user selected the option to cancel the rent of all
     * the mini rooms in the menu
     * <p>
     * <b>Postcondition: </b> The cancelation of the rent of all the mini rooms
     */
    public void cancelRent() {
        // Goes over the mini rooms cancelling rents
        for (int i = 0; i < miniRooms.length; i++) {
            for (int j = 0; j < miniRooms[0].length; j++) {
                miniRooms[i][j].setAvailable(true);
                miniRooms[i][j].setRentDate(null);
                miniRooms[i][j].setAssociatedCompany(null);
                miniRooms[i][j].eliminateServers();
                miniRooms[i][j].setRentValue(miniRooms[i][j].getInitialRentValue());
                miniRooms[i][j].setOn(false);
            }
        }
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Goes over the mini rooms printing <code>1</code> if the mini room is on and
     * <code>0</code> if the mini room is off
     * 
     * <p>
     * <b>Precondition: </b> The user selected the option to print the data center
     * map in the menu
     * <p>
     * <b>Postcondition: </b> A map of the mini room matrix
     * 
     * @return the data center map indicating the power state (on/off) of the mini
     *         rooms
     */
    public String printDataCenterMap() {
        String map = "";

        // Itterates over the mini rooms checking the power state and adding the results
        // to the map String
        for (int i = 0; i < miniRooms.length; i++) {
            for (int j = 0; j < miniRooms[0].length; j++) {
                map += miniRooms[i][j].isOn() == true ? "[" + 1 + "]" : "[" + 0 + "]";
            }

            map += "\n";
        }

        return map;
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Goes over the mini rooms printing <code>1</code> if the mini room is on and
     * <code>0</code> if the mini room is off. Test variant to test the power states
     * 
     * <p>
     * <b>Precondition: </b> The user was testing the power protocols
     * <p>
     * <b>Postcondition: </b> A map of the mini room test matrix
     * 
     * @return the test data center map indicating the power state (on/off) of the mini
     *         rooms
     */
    public String printTestMap() {
        String map = "";

        // Itterates over the mini rooms checking the power state and adding the results
        // to the map String
        for (int i = 0; i < testMiniRooms.length; i++) {
            for (int j = 0; j < testMiniRooms[0].length; j++) {
                map += testMiniRooms[i][j].isOn() == true ? "[" + 1 + "]" : "[" + 0 + "]";
            }

            map += "\n";
        }

        return map;
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Powers on every mini room in the test matrix to test the power off protocols
     * 
     * <p>
     * <b>Precondition: </b> The user decided to manipulate the mini rooms in the
     * menu, and selected the option to simulate the power on protocol
     * <p>
     * <b>Postcondition: </b> All of the mini rooms are powered on disregarding the
     * state
     */
    public void simulatePowerOnProtocol() {
        // Itterates over the mini rooms powering them on
        for (int i = 0; i < testMiniRooms.length; i++) {
            for (int j = 0; j < testMiniRooms[0].length; j++) {
                testMiniRooms[i][j].setOn(true);
            }
        }
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Powers off the mini rooms according to a specified way
     * 
     * <p>
     * <b>Precondition: </b> The user decided to manipulate the mini rooms in the
     * menu, and selected the option to simulate the power off protocol
     * <p>
     * <b>Postcondition: </b> The mini rooms are powered off in a specified way
     * 
     * @param letter indicates the specified way in which the mini rooms will be
     *               turned off
     */
    public void simulatePowerOffProtocol(char letter) {
        // Turn off the mini rooms according to the given letter
        switch (letter) {
            case 'L': // All of the mini rooms in the first corridor and the first mini rooms of all
                      // the other corridors
                for (int i = 0; i < testMiniRooms.length; i++) {
                    for (int j = 0; j < testMiniRooms[0].length; j++) {
                        if (i == 0) {
                            testMiniRooms[i][j].setOn(false);
                        } else if (j == 0) {
                            testMiniRooms[i][j].setOn(false);
                        }
                    }
                }
                break;
            case 'Z': // Z shaped pattern
                int col = 7;
                int col2 = 14;
                int col3 = 21;
                int col4 = 28;
                int col5 = 35;
                int col6 = 42;
                int col7 = 49;
                for (int i = 0; i < testMiniRooms.length; i++) {
                    for (int j = 0; j < testMiniRooms[0].length; j++) {
                        if (i == 0) {
                            testMiniRooms[i][j].setOn(false);
                        } else if (i == 7) {
                            testMiniRooms[i][j].setOn(false);
                        } else if (j == col || j == col2 || j == col3 || j == col4 || j == col5 || j == col6 || j == col7) {
                            testMiniRooms[i][j].setOn(false);
                        }
                    }
                    col--;
                    col2--;
                    col3--;
                    col4--;
                    col5--;
                    col6--;
                    col7--;
                }
                break;
            case 'H': // All of the odd corridors
                for (int i = 0; i < testMiniRooms.length; i++) {
                    for (int j = 0; j < testMiniRooms[0].length; j++) {
                        if ((i + 1) % 2 != 0) {
                            testMiniRooms[i][j].setOn(false);
                        }
                    }
                }
                break;
            case 'O': // For the mini rooms located in the windows
                for (int i = 0; i < testMiniRooms.length; i++) {
                    for (int j = 0; j < testMiniRooms[0].length; j++) {
                        if (i == 0 || i == 7 || j == 0 || j == 49) {
                            testMiniRooms[i][j].setOn(false);
                        }
                    }
                }
                break;
        }
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Powers off the mini rooms in columns or rows according to a number
     * 
     * <p>
     * <b>Precondition: </b> The user decided to manipulate the mini rooms in the
     * menu, and selected the option to simulate the power off protocol
     * <p>
     * <b>Postcondition: </b> The mini rooms are powered off in a specified way
     * 
     * @param letter      indicates the specified way in which the mini rooms will
     *                    be turned off
     * @param columnOrRow indicates the number of the column or row that will be
     *                    turned off
     */
    public void simulatePowerOffProtocol(char letter, int columnOrRow) {
        switch (letter) {
            case 'M': // For the mini rooms in the specified column
                for (int i = 0; i < testMiniRooms.length; i++) {
                    for (int j = 0; j < testMiniRooms.length; j++) {
                        if ((columnOrRow - 1) == j) {
                            testMiniRooms[i][j].setOn(false);
                        }
                    }
                }
                break;
            case 'P': // For the mini rooms in the specified row
                for (int i = 0; i < testMiniRooms.length; i++) {
                    for (int j = 0; j < testMiniRooms[0].length; j++) {
                        if ((columnOrRow - 1) == i) {
                            testMiniRooms[i][j].setOn(false);
                        }
                    }
                }
                break;
        }
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Locates a mini room according to the location given separated by a comma
     * 
     * <p>
     * <b>Precondition: </b> It is needed to interact with a mini room
     * <p>
     * <b>Postcondition: </b> The mini room object is located
     * 
     * @param miniRoomLocation location of the mini room given in the format "ROW,
     *                         COLUMN"
     * @return the found mini room object or <code>null</code> if the mini room was
     *         not found
     */
    public MiniRoom locateMiniRoom(String miniRoomLocation) {
        MiniRoom foundMiniRoom;
        String[] miniRoomLCoordinates = miniRoomLocation.split(", *", 0);
        int miniRoomRow = Integer.parseInt(miniRoomLCoordinates[0]) - 1;
        int miniRoomColumn = Integer.parseInt(miniRoomLCoordinates[1]) - 1;

        if ((miniRoomColumn < 0 || miniRoomColumn > 49) || (miniRoomRow < 0 || miniRoomRow > 7)) {
            foundMiniRoom = null;
        } else {
            foundMiniRoom = miniRooms[miniRoomRow][miniRoomColumn];
        }

        return foundMiniRoom;
    }
}
