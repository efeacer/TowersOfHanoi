/**
 * This class represents the Rods consisting of three seperate Rod with disks on top. It involves
 * methods to move the disks from one rod to another in certain patterns.
 * @author EFE ACER
 * @version 1.0
 */

import java.util.Stack;
import java.util.LinkedList;

public class Rods {
    //Constants
    private static final int NUMBER_OF_RODS = 3;
    
    //Instance Variables
    private Rod[] rodArray;
    private LinkedList<Integer> movesToSolve;
    
    /**
     * The constructor of the Rods class. Takes the disk number and the initial rod, the rod 
     * where the disks will be at the beginning, as parameters.
     * @param numberOfDisks The number of disks on the platform.
     * @param initialRod The rod where the disks will be at the beginning.
     */
    public Rods(int numberOfDisks, int initialRod) {
        Rod initial = new Rod(numberOfDisks);
        rodArray = new Rod[NUMBER_OF_RODS];
        for (int i = 0; i < NUMBER_OF_RODS; i++) {
            if (i == initialRod) {
                rodArray[i] = initial;
            } else { 
                rodArray[i] = new Rod();
            }
        }
        rodArray[initialRod] = initial;
        movesToSolve = new LinkedList<Integer>(); 
    }
    
    /**
     * A method that is similar to the constructor, it initializes the platform but it 
     * does not change other data structures.
     * @param numberOfDisks The number of disks on the platform.
     * @param initialRod The rod where the disks will be at the beginning.
     */
    public void initializeRods(int numberOfDisks, int initialRod) {
        Rod initial = new Rod(numberOfDisks);
        rodArray = new Rod[NUMBER_OF_RODS];
        for (int i = 0; i < NUMBER_OF_RODS; i++) {
            if (i == initialRod) {
                rodArray[i] = initial;
            } else { 
                rodArray[i] = new Rod();
            }
        }
        rodArray[initialRod] = initial;
    }
    
    /**
     * Returns the array of three seperate rods.
     * @return The array of three seperate rods.
     */
    public Rod[] getRodArray() {
        return rodArray;
    }
    
    /**
     * Moves the disk on top of one rod to the top of another rod.
     * @param from The rod that the disk is taken.
     * @param destination The rod that the disk is given.
     */
    public void moveDisk(int from, int destination) {
        Stack<Disk> fromStack = rodArray[from].getDisksOnTop();
        if (!fromStack.isEmpty()) {
            rodArray[destination].getDisksOnTop().push(fromStack.pop());
        } else {
            System.out.println("Rod is empty, no disks to move...");
        }          
    }
    
    /**
     * Moves the disks in a certain recursive pattern such that the Towers of Hanoi problem
     * is properly solved.
     * @param disksOnTop The number of disks on the starting rod.
     * @param from The rod, where the disks are at the beginning.
     * @param spare The rod that is neither from nor destination.
     * @param destination The rod, where the disks will be at the end.
     */
    public void solveTowersOfHanoi(int disksOnTop, int from, int spare, int destination) {
        if (disksOnTop >= 1) {
            solveTowersOfHanoi(disksOnTop - 1, from, destination, spare);
            moveDisk(from, destination);
            movesToSolve.addLast(from);
            movesToSolve.addLast(destination);
            solveTowersOfHanoi(disksOnTop - 1, spare, from, destination);
        } 
    }
    
    /**
     * Returns a data structure containing the certain moves to solve the Towers of Hanoi
     * problem.
     * @return A list containing the certain moves to solve the Towers of Hanoi problem.
     */
    public LinkedList<Integer> getMovesToSolve() {
        return movesToSolve;
    }
}