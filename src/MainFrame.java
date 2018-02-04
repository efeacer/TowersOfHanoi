/**
 * The main framwe establishing the GUI.
 * @author EFE ACER
 * @version 1.0
 */

import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class MainFrame extends JFrame {    
    //Constants
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;
    private static final int RODS_WIDTH = 550;
    private static final int RODS_HEIGHT = 400;
    private static final int INITIAL_NUMBER_OF_DISKS = 4;
    private static final int INITIAL_ROD = 0;
    private static final String TITLE = "Towers of Hanoi by EFE ACER";
    private static final String DISK_NUMBER_LABEL = "Select the number of disks:";
    private static final int DELAY = 1000;
    
    //Instance Variables
    private JPanel panel;
    private JButton nextButton;
    private JButton animateButton;
    private JLabel diskNumberLabel;
    private JComboBox<Integer> diskNumberSelection;
    private JComponent mainComponent;
    private Rods rods;
    private Timer timer;
    private LinkedList<Integer> movesToSolve;
     
    /**
     * Constructor of the main frame.
     */
    public MainFrame() {
        rods = new Rods(INITIAL_NUMBER_OF_DISKS, INITIAL_ROD);
        createComponents();
        setTitle(TITLE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    
    /**
     * Creates the smaller GUI elements and adds them to the frame.
     */
    private void createComponents() {
        timer = new Timer(DELAY, new AnimationListener());
        
        nextButton = new JButton("Next");
        animateButton = new JButton("Animate");
        nextButton.addActionListener(new NextButtonListener());
        animateButton.addActionListener(new AnimateButtonListener());
        
        panel = new JPanel();
        
        mainComponent = new MainComponent(rods);
        mainComponent.setPreferredSize(new Dimension(RODS_WIDTH, RODS_HEIGHT));
        
        panel.add(mainComponent);
        
        diskNumberLabel = new JLabel(DISK_NUMBER_LABEL);
        
        diskNumberSelection = new JComboBox<Integer>(new Integer[] {1, 2, 3, 4, 5, 6});
        diskNumberSelection.setSelectedItem(4);
        diskNumberSelection.addActionListener(new DiskNumberChoiceListener());
        
        rods.solveTowersOfHanoi(rods.getRodArray()[INITIAL_ROD].getDisksOnTop().size(), INITIAL_ROD, 1, 2);
        rods.initializeRods((int) diskNumberSelection.getSelectedItem(), INITIAL_ROD);
        
        panel.add(diskNumberLabel);
        panel.add(diskNumberSelection);
        panel.add(nextButton);
        panel.add(animateButton); 
        add(panel);
    }
    
    /**
     * Inner class for the ActionListener used by the next button.
     */
    private class NextButtonListener implements ActionListener {
        /**
         * Performs a move to solve the Towers of Hanoi Problem.
         * @param event The action event.
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            timer.stop();
            movesToSolve = rods.getMovesToSolve();
            if (!movesToSolve.isEmpty()) {
                rods.moveDisk(movesToSolve.removeFirst(), movesToSolve.removeFirst());
                ((MainComponent) mainComponent).update();
            }
        }
    }
    
    /**
     * Inner class for the ActionListener used by the animate button.
     */
    private class AnimateButtonListener implements ActionListener {
        /**
         * Triggers the animation process.
         * @param event The action event.
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            timer.start();
        }
    }
    
    /**
     * Inner class for the ActionListener used by the timer.
     */
    private class AnimationListener implements ActionListener {
        /**
         * Animates the disk in a way to solve the Towers of Hanoi problem.
         * @param event The action event.
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            movesToSolve = rods.getMovesToSolve();
            if (!movesToSolve.isEmpty()) {
                rods.moveDisk(movesToSolve.removeFirst(), movesToSolve.removeFirst());
                ((MainComponent) mainComponent).update();
            } else {
                timer.stop();
            }
        }
    } 
    
    /**
     * Inner class for the ActionListener used by the combo box.
     */
    private class DiskNumberChoiceListener implements ActionListener {
        /**
         * Changes the disk number according to the selected number on the combo box.
         * @param event The action event.
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            timer.stop();
            timer = new Timer(DELAY, new AnimationListener());
            rods = new Rods((int) diskNumberSelection.getSelectedItem(), INITIAL_ROD);
            ((MainComponent) mainComponent).updateRods(rods);
            rods.solveTowersOfHanoi(rods.getRodArray()[INITIAL_ROD].getDisksOnTop().size(), INITIAL_ROD, 1, 2);
            rods.initializeRods((int) diskNumberSelection.getSelectedItem(), INITIAL_ROD);
        }
    } 
}
