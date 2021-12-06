package model;

/**
 * Class for the servers contained in the mini rooms
 * 
 * @author Daniel Valencia - A00372845
 */
public class Server {
    // Attributes

    private double cacheMemory;
    private double ramMemory;
    private int processorsNumber;
    private int disksNumber;
    private double totalDiskCapacity;

    // Relation

    private ServerProcessorBrand processorBrand;

    // ------------------------------------------------------------------------------------------------

    // Constructor

    /**
     * Constructor of the server class
     * 
     * @param cacheMemory             the cache memory of the server
     * @param ramMemory               the RAM memory of the server
     * @param processorsNumber        the number of processors of the server
     * @param disksNumber             the number of disks of the server
     * @param totalDiskCapacity       the total capacity of the disks (in TeraBytes)
     * @param processorBrandSelection the selection of the processor brand
     *                                (<code>1</code> for Intel, <code>2</code> for
     *                                AMD)
     */
    public Server(double cacheMemory, double ramMemory, int processorsNumber, int disksNumber, double totalDiskCapacity,
            int processorBrandSelection) {
        this.cacheMemory = cacheMemory;
        this.ramMemory = ramMemory;
        this.processorsNumber = processorsNumber;
        this.disksNumber = disksNumber;
        this.totalDiskCapacity = totalDiskCapacity;
        setProcessorBrand(processorBrandSelection);
    }

    // ------------------------------------------------------------------------------------------------

    // Getters and setters

    /**
     * Returns the cache memory
     * 
     * @return the cache memory of the server
     */
    public double getCacheMemory() {
        return cacheMemory;
    }

    /**
     * Sets the cache memory of the server
     * 
     * @param cacheMemory the cache memory to set
     */
    public void setCacheMemory(double cacheMemory) {
        this.cacheMemory = cacheMemory;
    }

    /**
     * Returns the RAM memory
     * 
     * @return the RAM memory of the server
     */
    public double getRamMemory() {
        return ramMemory;
    }

    /**
     * Sets the RAM memory of the server
     * 
     * @param ramMemory the RAM memory to set
     */
    public void setRamMemory(double ramMemory) {
        this.ramMemory = ramMemory;
    }

    /**
     * Returns the number of processors
     * 
     * @return the number of processors of the server
     */
    public int getProcessorsNumber() {
        return processorsNumber;
    }

    /**
     * Sets the number of processors of the server
     * 
     * @param processorsNumber the number of processors to set
     */
    public void setProcessorsNumber(int processorsNumber) {
        this.processorsNumber = processorsNumber;
    }

    /**
     * Returns the number of disks
     * 
     * @return number of disks of the server
     */
    public int getDisksNumber() {
        return disksNumber;
    }

    /**
     * Sets the number of disks of the server
     * 
     * @param disksNumber the number of disks to set
     */
    public void setDisksNumber(int disksNumber) {
        this.disksNumber = disksNumber;
    }

    /**
     * Returns the total disk capacity
     * 
     * @return the total disk capacity of the server (in TeraBytes)
     */
    public double getTotalDiskCapacity() {
        return totalDiskCapacity;
    }

    /**
     * Sets the total disk capacity of the server
     * 
     * @param totalDiskCapacity the total disk capacity to set (in TeraBytes)
     */
    public void setTotalDiskCapacity(double totalDiskCapacity) {
        this.totalDiskCapacity = totalDiskCapacity;
    }

    /**
     * Returns the processor brand
     * 
     * @return the processor brand of the server
     */
    public ServerProcessorBrand getProcessorBrand() {
        return processorBrand;
    }

    /**
     * Sets the processor brand of the server
     * 
     * @param processorBrandSelection <code>1</code> for Intel, <code>2</code> for
     *                                AMD
     */
    public void setProcessorBrand(int processorBrandSelection) {
        switch (processorBrandSelection) {
            case 1:
                processorBrand = ServerProcessorBrand.INTEL;
                break;
            case 2:
                processorBrand = ServerProcessorBrand.AMD;
                break;
        }
    }

    // ------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Cache Memory: " + cacheMemory + " | RAM: " + ramMemory + " | Total disk capacity: " + totalDiskCapacity
                + " | Number of disks: " + disksNumber + " | Number of processors: " + processorsNumber
                + " | Processor brand: " + processorBrand;
    }
}
