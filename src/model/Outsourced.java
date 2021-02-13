
package Model;

import java.lang.*;

/**
 * This is the Outsourced subclass to Part.
 * @author Krystal Lee
 * @version C482
 * @since Fall 2020
 */
public class Outsourced extends Part {

    private String companyName;

    /**
     * Default constructor OutSourced that inherits from abstract class Part. Part is the superclass/parent class.
     * @param partID int inherited from superclass
     * @param name String inherited from superclass
     * @param price double inherited from superclass
     * @param numInStock int inherited from superclass
     * @param min int inherited from superclass
     * @param max int inherited from superclass
     * @param companyName String companyName
     */
    public Outsourced(int partID, String name, double price, int numInStock, int min, int max, String companyName) {
        super(partID, name, price, numInStock, min, max);
        this.companyName = companyName;
    }

    /**
     * Mutator (setter) method sets new company name.
     * @param companyName the new company name to be set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Accessor (getter) method gets and return companyName.
     * @return companyName the company name
     */
    public String getCompanyName() {
        return companyName;
    }

}


