package model;

/**
 * Class for the company associated to the mini rooms
 * @author Daniel Valencia - A00372845
 */
public class Company {
    // Attributes

    private String nit;
    private String name;
    private String projectNumber;

    //------------------------------------------------------------------------------------------------

    // Constructors

    /**
     * Constructor for investigation projects, the company name and NIT are inferred to the ones of ICESI University
     * @param projectNumber the number of the investigation project
     */
    public Company(String projectNumber) {
        this.projectNumber = projectNumber;
        this.name = "ICESI University";
        this.nit = "890.316.745-5";
    }

    /**
     * Constructor for regular companies, the project number does not apply
     * @param nit NIT of the associated company
     * @param name name of the associated company
     */
    public Company(String nit, String name) {
        this.nit = nit;
        this.name = name;
        this.projectNumber = "N/A";
    }

    //------------------------------------------------------------------------------------------------

    // Getters and setters
    
    /**
     * Returns the NIT
     * @return NIT of the company
     */
    public String getNit() {
        return nit;
    }

    /**
     * Sets the NIT of the company
     * @param nit the NIT to set
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * Returns the name
     * @return the name of the company
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the company
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the project name
     * @return the project name of the investigation project
     */
    public String getProjectName() {
        return projectNumber;
    }

    /**
     * Sets the project number of the investigation project
     * @param projectNumber the project number to set
     */
    public void setProjectName(String projectNumber) {
        this.projectNumber = projectNumber;
    }
}
