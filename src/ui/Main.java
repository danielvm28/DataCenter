package ui;

import model.DataCenter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Main class for the data center. The user interacts with this class
 * @author Daniel Valencia - A00372845
 */
public class Main {
    public static Scanner s = new Scanner(System.in);
    public static DataCenter dataCenter;

    // ------------------------------------------------------------------------------------------------

    /**
     * Asks the user for data to rent a mini room
     * 
     * <p>
     * <b>Precondition: </b> The user selected the option to rent a mini room in the
     * menu
     * <p>
     * <b>Postcondition: </b> A rented mini room
     */
    public static void rentMiniRoom() {
        // Variables for the mini room
        int rentSelection = 0;
        int dateSelection = 0;
        String miniRoomLocation = "";
        int year = 0;
        int month = 0;
        int dayOfMonth = 0;
        LocalDate rentDate = null;
        String companyNit = "";
        String companyName = "";
        int numberOfServers = 0;
        String projectNumber = "";

        // Variables for the servers
        String serverModalitySelection = "";
        double cacheMemory = 0.0;
        double ramMemory = 0.0;
        int processorsNumber = 0;
        int disksNumber = 0;
        double totalDiskCapacity = 0.0;
        int processorBrandSelection = 0;

        // Ask for the mini room data

        do {
            System.out.println("\nIndicate the purpose of the mini room:");
            System.out.println("1. Rented directly to a company");
            System.out.println("2. Rented for investigation");
            rentSelection = s.nextInt();
        } while (rentSelection != 1 && rentSelection != 2);

        s.nextLine();

        // Ensure the existence of the mini room
        do {
            System.out.println(
                    "\nIndicate the location of the mini room to rent stating the number of the row, followed by the column separated with a comma in the format 'ROW, COLUMN' (e.g. 3, 45):");
            miniRoomLocation = s.nextLine();

            if (dataCenter.locateMiniRoom(miniRoomLocation) == null) {
                System.out.println("The specified mini room does not exist, try again");
            }
        } while (dataCenter.locateMiniRoom(miniRoomLocation) == null);

        System.out.print("\nInput the number of servers that will be used: ");
        numberOfServers = s.nextInt();

        do {
            System.out.println("\nIndicate the way to manage the date:");
            System.out.println("1. Use today's date");
            System.out.println("2. Manually indicate the date");
            dateSelection = s.nextInt();
        } while (dateSelection != 1 && dateSelection != 2);

        // Automatic or manual date input
        if (dateSelection == 1) {
            rentDate = LocalDate.now();
        } else {
            System.out.print("Input the year of rent: ");
            year = s.nextInt();

            System.out.print("Input the month of rent: ");
            month = s.nextInt();

            System.out.print("Input the day of rent: ");
            dayOfMonth = s.nextInt();

            rentDate = LocalDate.of(year, month, dayOfMonth);
        }

        // Ask data according to the rent purposes
        switch (rentSelection) {
            case 1:
                System.out.print("\nInput the name of the company: ");
                s.nextLine();
                companyName = s.nextLine();

                System.out.print("Input the NIT of the company: ");
                companyNit = s.nextLine();

                System.out.println("\n" +
                        dataCenter.rentMiniRoom(miniRoomLocation, rentDate, companyNit, companyName, numberOfServers));
                break;
            case 2:
                System.out.println("\nInput the registry number of the investigation project: ");
                s.nextLine();
                projectNumber = s.nextLine();

                System.out.println(
                        "\n" + dataCenter.rentMiniRoom(miniRoomLocation, rentDate, projectNumber, numberOfServers));
                break;
        }

        System.out.println("\nPress enter to continue to the server declaration...");
        s.nextLine();

        // Ask for the server data

        do {
            System.out.print("Would you like the servers to be all the same? (y/n) ");
            serverModalitySelection = s.nextLine().toUpperCase();
        } while (!serverModalitySelection.equals("Y") && !serverModalitySelection.equals("N"));

        // Ask for information according to the selection of the user
        if (serverModalitySelection.equals("Y")) {
            System.out.print("\nInput the cache memory of the servers: ");
            cacheMemory = s.nextDouble();

            System.out.print("Input the RAM of the servers: ");
            ramMemory = s.nextDouble();

            System.out.print("Input the number of processors of the servers: ");
            processorsNumber = s.nextInt();

            System.out.print("Input the number of disks of the servers: ");
            disksNumber = s.nextInt();

            System.out.print("Input the total capacity of the disks (In TeraBytes): ");
            totalDiskCapacity = s.nextDouble();

            do {
                System.out.println("Select the brand of the processor:");
                System.out.println("1. Intel");
                System.out.println("2. AMD");
                processorBrandSelection = s.nextInt();
            } while (processorBrandSelection != 1 && processorBrandSelection != 2);

            for (int i = 0; i < numberOfServers; i++) {
                dataCenter.addServerToMiniRoom(miniRoomLocation, cacheMemory, ramMemory, processorsNumber, disksNumber,
                        totalDiskCapacity, processorBrandSelection);
            }

        } else {
            for (int i = 0; i < numberOfServers; i++) {
                System.out.print("\nInput the cache memory of the server No. " + (i + 1) + ": ");
                cacheMemory = s.nextDouble();

                System.out.print("Input the RAM of the server No. " + (i + 1) + ": ");
                ramMemory = s.nextDouble();

                System.out.print("Input the number of processors of the server No. " + (i + 1) + ": ");
                processorsNumber = s.nextInt();

                System.out.print("Input the number of disks of the server No. " + (i + 1) + ": ");
                disksNumber = s.nextInt();

                System.out.print(
                        "Input the total capacity of the disks (In TeraBytes) in the server No. " + (i + 1) + ": ");
                totalDiskCapacity = s.nextDouble();

                do {
                    System.out.println("Select the brand of the processor for the server No. " + (i + 1) + ":");
                    System.out.println("1. Intel");
                    System.out.println("2. AMD");
                    processorBrandSelection = s.nextInt();
                } while (processorBrandSelection != 1 && processorBrandSelection != 2);

                dataCenter.addServerToMiniRoom(miniRoomLocation, cacheMemory, ramMemory, processorsNumber, disksNumber,
                        totalDiskCapacity, processorBrandSelection);
            }
        }

    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Lists the mini rooms that are available
     * 
     * <p>
     * <b>Precondition: </b> The user selected the option to list available mini
     * rooms in the menu
     * <p>
     * <b>Postcondition: </b> A list is displayed
     */
    public static void listAvailableMiniRooms() {
        System.out.println("\n" + dataCenter.printAvailableMiniRooms());
        System.out.println("\nPress enter to continue...");
        s.nextLine();
        s.nextLine();
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Cancels the rent of one, or all of the mini rooms
     * 
     * <p>
     * <b>Precondition: </b> The user selected the option to cancel the rent of mini
     * rooms in the menu
     * <p>
     * <b>Postcondition: </b> Mini rooms with cancelled rent
     */
    public static void cancelMiniRoomRent() {
        int cancellationModality = 0;
        String miniRoomLocation = "";

        // Ask for the rent modality
        do {
            System.out.println("\nIndicate the cancellation modality:");
            System.out.println("1. Cancel rent of a specific mini room");
            System.out.println("2. Cancel rent of all the mini rooms");
            cancellationModality = s.nextInt();
        } while (cancellationModality != 1 && cancellationModality != 2);

        // According to the cancellation modality
        switch (cancellationModality) {
            case 1:
                // Ask for the mini room
                System.out.println(
                        "\nIndicate the location of the mini room to rent stating the number of the row, followed by the column separated with a comma in the format 'ROW, COLUMN' (e.g. 3, 45):");
                s.nextLine();
                miniRoomLocation = s.nextLine();

                // Check the existence of the mini room
                if (dataCenter.locateMiniRoom(miniRoomLocation) != null) {
                    String choice = "";
                    System.out.println("\nThe mini room has this processing capacity: \n"
                            + dataCenter.locateMiniRoom(miniRoomLocation).displayProcessingCapacity());

                    // Display the processing capacity of the mini room and then ask
                    do {
                        System.out.print("Are you sure you want to cancel the rent of this mini room? (y/n) ");
                        choice = s.nextLine().toUpperCase();
                    } while (!choice.equals("Y") && !choice.equals("N"));

                    if (choice.equals("Y")) {
                        System.out.println("\n" + dataCenter.cancelRent(miniRoomLocation));
                    }

                } else {
                    System.out.println("Error, mini room not found");
                }

                break;
            case 2:
                dataCenter.cancelRent();
                System.out.println("Rent cancelled for all mini rooms");
                break;
        }

        System.out.println("\nPress enter to continue...");
        s.nextLine();
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Shows the map of the data center showing information about the power state
     * (On/Off)
     * 
     * <p>
     * <b>Precondition: </b> The user selected the option to show the data center
     * map in the menu or is testing the power protocols
     * <p>
     * <b>Postcondition: </b> The printed data center map
     * 
     * @param test indicates if the map that is going to be shown, will be the one to
     *             test the power states or the regular one
     */
    public static void showDataCenterMap(boolean test) {
        if (test) {
            System.out.println("\n" + dataCenter.printTestMap());
        } else {
            System.out.println("\n" + dataCenter.printDataCenterMap());
        }
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Manipulates the mini rooms testing the On and Off protocols
     * 
     * <p>
     * <b>Precondition: </b> The user selected the option to manipulate the mini
     * rooms in the menu
     * <p>
     * <b>Postcondition: </b> Manipulated mini rooms according to the user's choices
     */
    public static void manipulateMiniRooms() {
        char selection = ' ';
        boolean exit = false;
        int columnOrRow = 0;

        s.nextLine();

        // Menu for the power manipulation of mini rooms
        while (!exit) {
            System.out.println("\n-----------------------------------------------------\n");
            System.out.println("Introduce a single character according to the following:");
            System.out.println("1. Simulate power On protocol (Turns all of the mini rooms On)");
            System.out.println(
                    "L. Turns off the first mini rooms of all hallways and all of the ones in the first hallway");
            System.out.println("Z. Turns off the mini rooms in a 'Z' shaped way");
            System.out.println("H. Turns off the mini rooms in odd hallways");
            System.out.println("O. Turns off the mini rooms located in the windows");
            System.out.println("M. Turns off the mini rooms in a desired column");
            System.out.println("P. Turns off the mini rooms in a desired row");
            System.out.println("0. Exit mini room manipulation menu");
            System.out.println("\n-----------------------------------------------------\n");

            selection = s.nextLine().charAt(0);
            selection = Character.toUpperCase(selection);

            // For every letter
            switch (selection) {
                case '1':
                    dataCenter.simulatePowerOnProtocol();
                    System.out.println("Mini rooms on!");
                    System.out.println("\nPress enter to continue...");
                    s.nextLine();
                    break;
                case 'L':
                case 'Z':
                case 'H':
                case 'O':
                    dataCenter.simulatePowerOffProtocol(selection);
                    showDataCenterMap(true);
                    System.out.println("\nPress enter to continue...");
                    s.nextLine();
                    break;
                case 'M':
                    System.out.print("Indicate the number of the column to turn off: ");
                    columnOrRow = s.nextInt();

                    dataCenter.simulatePowerOffProtocol(selection, columnOrRow);
                    showDataCenterMap(true);
                    System.out.println("\nPress enter to continue...");
                    s.nextLine();
                    s.nextLine();
                    break;
                case 'P':
                    System.out.print("Indicate the number of the row to turn off: ");
                    columnOrRow = s.nextInt();

                    dataCenter.simulatePowerOffProtocol(selection, columnOrRow);
                    showDataCenterMap(true);
                    System.out.println("\nPress enter to continue...");
                    s.nextLine();
                    s.nextLine();
                    break;
                case '0':
                    exit = true;
                    System.out.println("Exiting menu...");
                    break;
                default:
                    System.out.println("Error, invalid input");
                    System.out.println("Press enter to continue...");
                    s.nextLine();
                    break;
            }
        }

    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Main menu of the program
     * 
     * @param args
     */
    public static void main(String[] args) {
        double miniRoomsBaseValue = 0.0;
        boolean exit = false;
        int menuSelection = 0;

        // Welcomes the user and asks for the base value of the mini rooms to continue
        System.out.println("\n-----------------------------------------------------\n");
        System.out.println("Welcome to the data center sysytem!");
        System.out.print("Introduce the base rent value for the mini rooms: ");
        miniRoomsBaseValue = s.nextDouble();

        dataCenter = new DataCenter(miniRoomsBaseValue);

        // Prints the menu until the user wants to exit
        while (!exit) {
            System.out.println("\n-----------------------------------------------------\n");
            System.out.println("Input a number according to the desired action: ");
            System.out.println("1. Rent a mini room");
            System.out.println("2. List available mini rooms");
            System.out.println("3. Cancel rent of mini rooms");
            System.out.println("4. Show the data center map - Shows the power status (1 for ON / 0 for OFF)");
            System.out.println("5. Manipulate mini rooms (Test On/Off protocols)");
            System.out.println("0. Exit Program");
            System.out.println("\n-----------------------------------------------------\n");

            menuSelection = s.nextInt();

            switch (menuSelection) {
                case 1:
                    rentMiniRoom();
                    break;
                case 2:
                    listAvailableMiniRooms();
                    break;
                case 3:
                    cancelMiniRoomRent();
                    break;
                case 4:
                    showDataCenterMap(false);
                    System.out.println("\nPress enter to continue...");
                    s.nextLine();
                    s.nextLine();
                    break;
                case 5:
                    manipulateMiniRooms();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Error, invalid input");
                    System.out.println("Press enter to continue...");
                    s.nextLine();
                    s.nextLine();
                    break;
            }
        }

    }
}
