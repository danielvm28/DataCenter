package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class for the mini rooms of the datacenter
 * @author Daniel Valencia - A00372845
 */
public class MiniRoom {
    // Attributes
    
    private int number;
    private LocalDate rentDate;
    private boolean inWindow;
    private double rentValue;
    private boolean on;
    private boolean available;

    //Relations
    
    private Company associatedCompany;
    private ArrayList<Server> servers;

    //------------------------------------------------------------------------------------------------
    
    // Constructor

    /**
     * Constructor of the miniroom. For default, all minirooms are set to off and available
     * 
     * @param number the unique number that identifies the miniroom
     * @param inWindow boolean indicating if the miniroom is located in a window or not
     * @param rentValue the value of the rent for the miniroom 
     */
    public MiniRoom(int number, boolean inWindow, double rentValue) {
        this.number = number;
        this.inWindow = inWindow;
        this.rentValue = rentValue;
        on = false;
        available = true;
    }

    //------------------------------------------------------------------------------------------------

    // Getters and setters

    /**
     * Returns the number
     * @return the number of the mini room
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the number of the mini room
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Returns the rent date
     * @return the rent date of the mini room
     */
    public LocalDate getRentDate() {
        return rentDate;
    }

    /**
     * Sets the rent date of the mini room
     * @param rentDate the rent date to set
     */
    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    /**
     * Returns true if the mini room is adyacent to a window
     * @return if the mini room is adyacent to a window or not
     */
    public boolean isInWindow() {
        return inWindow;
    }

    /**
     * Sets a location boolean depending if the mini room is adyacent to a window or not
     * @param inWindow the window location boolean to set
     */
    public void setInWindow(boolean inWindow) {
        this.inWindow = inWindow;
    }

    /**
     * Returns the rent value
     * @return the rent value of the mini room
     */
    public double getRentValue() {
        return rentValue;
    }

    /**
     * Sets the rent value of the mini room
     * @param minimumWatchAge the rent value to set
     */
    public void setRentValue(double rentValue) {
        this.rentValue = rentValue;
    }

    /**
     * Returns the power state
     * @return the power state of the computer (On: <code>true</code>, Off: <code>false</code>)
     */
    public boolean isOn() {
        return on;
    }

    /**
     * Sets the power state of the mini room
     * @param on the power state to set (On: <code>true</code>, Off: <code>false</code>)
     */
    public void setOn(boolean on) {
        this.on = on;
    }

    /**
     * Returns the availability state
     * @return the availability state of the mini room (Available: <code>true</code>, Unavailable: <code>false</code>)
     */
    public boolean isAvailable() {
        return available;
    }
    
    /**
     * Sets the availability state of the mini room
     * @param available the availability state to set (Available: <code>true</code>, Unavailable: <code>false</code>)
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Returns the associated company
     * @return the associated company of the mini room
     */
    public Company getAssociatedCompany() {
        return associatedCompany;
    }

    /**
     * Sets the associated company of the mini room
     * @param associatedCompany the associated company to set
     */
    public void setAssociatedCompany(Company associatedCompany) {
        this.associatedCompany = associatedCompany;
    }

    /**
     * Returns the Arraylist of servers
     * @return the ArrayList of servers of the mini room
     */
    public ArrayList<Server> getServers() {
        return servers;
    }

    //------------------------------------------------------------------------------------------------

    /**
     * Adds a new server in the server ArrayList
     * @param newServer the new server that will be added
     */
    public void addServer(Server newServer) {
        servers.add(newServer);
    }

    @Override
    public String toString() {
        String windowString = inWindow ? "In window" : "Not in window";
        String availableString = available ? "Available" : "Unavailable";

        return "Availability: " + availableString + " | Location: " + windowString + " | Rent Value: " + rentValue;
    }
}
