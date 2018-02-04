/**
 * This class represents the Rod with disks on top. It involves
 * @author EFE ACER
 * @version 1.0
 */

import java.util.Stack;

public class Rod {
    //Constants
    private static final int  DISK_CAPACITY = 6;
    
    //Instance variables
    private Stack<Disk> disksOnTop;
    
    /**
     * The constructor for the Rod class, it basically constructs a stack to
     * model the disks it contains on top. There is no disks on top of the rod
     * when a rod is constructed with this constructor.
     */
    public Rod() {
        disksOnTop = new Stack<Disk>();
    }
    
    /**
     * The constructor for the Rod class, it basically constructs a stack to
     * model the disks it contains on top. It receives the number of disks it
     * contains as a parameter.
     * @param numberOfDisks The number of disks that the rod will contain.
     */
    public Rod(int numberOfDisks) {
        disksOnTop = new Stack<Disk>();
        for (int i = 0; i < numberOfDisks; i++) {
            disksOnTop.push(new Disk(DISK_CAPACITY - i));
        }
    }
    
    /**
     * Returns the stack consisting of the disks on top of the rod.
     * @return The stack consisting of the disks on top of the rod.
     */
    public Stack<Disk> getDisksOnTop() {
        return disksOnTop;
    }
}