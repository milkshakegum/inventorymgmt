package Model;

/**
 * This InHouse subclass inherits from abstract superclass Part.
 * @author Krystal Lee
 * @version C482
 * @since Fall 2020
 */

public class InHouse extends Part {
    private int machineId;

    /**
     * Default constructor InHouse that inherits from abstract class Part. Part is the superclass/parent class.
     * @param id int inherited from superclass
     * @param name String inherited from superclass
     * @param price double inherited from superclass
     * @param stock int inherited from superclass
     * @param min int inherited from superclass
     * @param max int inherited from superclass
     * @param machineId int the machineID
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Accessor (getter) method returns the machineID.
     * @return machineID
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * Mutator (setter) method sets machineId.
     * @param id machine ID to be set
     */
    public void setMachineId(int id) {
        this.machineId = id;
    }
}