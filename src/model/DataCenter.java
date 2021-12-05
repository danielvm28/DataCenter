package model;

import java.time.LocalDate;

public class DataCenter {
    // Constants

    public final int ROWS = 8;
    public final int COLUMNS = 50;

    // Attribute

    private double miniRoomsBaseValue;

    // Relation

    private MiniRoom[][] miniRooms;

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
     * window boolean
     * and the rent value according to the location
     * 
     * @param miniRoomsBaseValue base value of the mini rooms for the calculations
     */
    public void initializeMiniRooms(double miniRoomsBaseValue) {
        int number = 1;
        double rentValue = 0;
        boolean inWindow = false;
        MiniRoom newMiniRoom;

        // Go over the mini room matrix
        for (int i = 0; i < miniRooms.length; i++) {
            for (int j = 0; j < miniRooms[0].length; j++) {
                if (i == 0 || i == 7 || j == 0 || j == 49) { // If a mini room is found in a window
                    rentValue = miniRoomsBaseValue * 0.9;
                    inWindow = true;

                    newMiniRoom = new MiniRoom(number, inWindow, rentValue);
                    miniRooms[i][j] = newMiniRoom;
                } else if (i == 6) { // If a mini room is in the 7th corridor (Excluding the ones in the windows)
                    rentValue = miniRoomsBaseValue * 0.85;
                    inWindow = false;

                    newMiniRoom = new MiniRoom(number, inWindow, rentValue);
                    miniRooms[i][j] = newMiniRoom;
                } else if (i >= 1 && i <= 5) { // If a mini room is between the 2nd and 6th corridor (Excluding the ones
                                               // in the windows)
                    rentValue = miniRoomsBaseValue * 1.25;
                    inWindow = false;

                    newMiniRoom = new MiniRoom(number, inWindow, rentValue);
                    miniRooms[i][j] = newMiniRoom;
                }

                number++;
            }
        }
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Rents a mini room considering its location, information of the company and date of rent
     * 
     * @param miniRoomLocation location of the mini room given in the format "ROW, COLUMN"
     * @param rentDate the date of rent of the mini room
     * @param companyNit nit of the associated company that is renting the mini room
     * @param name name of the associated company that is renting the mini room
     * @param numberOfServers number of servers that will be used in the mini room
     * @return
     */
    public String rentMiniRoom(String miniRoomLocation, LocalDate rentDate, String companyNit, String name, int numberOfServers) {
        String message = "";
        MiniRoom foundMiniRoom = locateMiniRoom(miniRoomLocation);

        // Checks if the selected mini room is available for rent or not
        if (foundMiniRoom.isAvailable()){
            message = "Mini romm rented!";

            Company associatedCompany = new Company(companyNit, name);

            foundMiniRoom.setAvailable(false);
            foundMiniRoom.setRentDate(rentDate);
            foundMiniRoom.setAssociatedCompany(associatedCompany);

            // Checks if the fine needs to be added or not
            if (numberOfServers < 4) {
                double newRentValue = foundMiniRoom.getRentValue() * 1.15;
                foundMiniRoom.setRentValue(newRentValue);

                message += ", fine of 15% over the rent value applied for underutilization of the servers";
            }
        } else {
            message = "Error, the selected mini room is already rented";
        }

        return message;
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Overloaded method to rent a mini room for investigation purposes, considering its location, rent date and the number of the investigation project
     * 
     * @param miniRoomLocation location of the mini room given in the format "ROW, COLUMN"
     * @param rentDate the date of rent of the mini room
     * @param projectNumber registry number of the investigation project
     * @param numberOfServers number of servers that will be used in the mini room
     * @return
     */
    public String rentMiniRoom(String miniRoomLocation, LocalDate rentDate, String projectNumber, int numberOfServers) {
        String message = "";
        MiniRoom foundMiniRoom = locateMiniRoom(miniRoomLocation);

        // Checks if the selected mini room is available for rent or not
        if (foundMiniRoom.isAvailable()){
            message = "Mini room rented for investigation project!";

            Company associatedCompany = new Company(projectNumber);

            foundMiniRoom.setAvailable(false);
            foundMiniRoom.setRentDate(rentDate);
            foundMiniRoom.setAssociatedCompany(associatedCompany);

            // Checks if the fine needs to be added or not
            if (numberOfServers < 4) {
                double newRentValue = foundMiniRoom.getRentValue() * 1.15;
                foundMiniRoom.setRentValue(newRentValue);

                message += ", fine of 15% over the rent value applied for underutilization of the servers";
            }
        } else {
            message = "Error, the selected mini room is already rented";
        }

        return message;
    }

    // ------------------------------------------------------------------------------------------------

    public void addServerToMiniRoom(String miniRoomLocation, double cacheMemory, double ramMemory, int processorsNumber,
            int disksNumber, double totalDiskCapacity, int processorBrandSelection) {

    }

    // ------------------------------------------------------------------------------------------------

    public String printAvailableMiniRooms() {
        String message = "";

        return message;
    }

    // ------------------------------------------------------------------------------------------------

    public String cancelRent(String miniRoomLocation) {
        String message = "";

        return message;
    }

    // ------------------------------------------------------------------------------------------------

    public String cancelRent() {
        String message = "";

        return message;
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Goes over the mini rooms printing <code>1</code> if the mini room is on and
     * <code>0</code> if the mini room is off
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
     * Powers on every mini room (Without considering its availability) to test the
     * power off protocols
     */
    public void simulatePowerOnProtocol() {
        // Itterates over the mini rooms powering them on
        for (int i = 0; i < miniRooms.length; i++) {
            for (int j = 0; j < miniRooms[0].length; j++) {
                miniRooms[i][j].setOn(true);
            }
        }
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Powers off the mini rooms according to a specified way
     * 
     * @param letter indicates the specified way in which the mini rooms will be
     *               turned off
     */
    public void simulatePowerOffProtocol(char letter) {

    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Locates a mini room according to the location given separated by a comma
     * 
     * @param miniRoomLocation location of the mini room given in the format "ROW, COLUMN"
     * @return the found mini room object or <code>null</code> if the mini room was not found
     */
    public MiniRoom locateMiniRoom(String miniRoomLocation) {
        MiniRoom foundMiniRoom;
        String[] miniRoomLCoordinates = miniRoomLocation.split(", *", 0);
        int miniRoomRow = Integer.parseInt(miniRoomLCoordinates[0]) - 1;
        int miniRoomColumn = Integer.parseInt(miniRoomLCoordinates[1]) - 1;

        if ((miniRoomColumn < 0 && miniRoomColumn > 49) || (miniRoomRow < 0 && miniRoomRow > 7)) {
            foundMiniRoom = null;
        } else {
            foundMiniRoom = miniRooms[miniRoomRow][miniRoomColumn];
        }

        return foundMiniRoom;
    }
}
