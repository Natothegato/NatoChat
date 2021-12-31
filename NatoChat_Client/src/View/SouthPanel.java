/**
 *  Application: NatoChat_Server
 *
 *  Represents the primary UI layout manager
 *  for the application window. It manages the
 *  position of the other layout manager in the
 *  application window.
 *
 *  @author NatoTheGato
 *  @version 1.01 12/30/21
 */
package View;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SouthPanel extends JPanel {

    //Declare Class Attribute
    private JTextField input;

    /**
     * Default SouthPanel constructor assigning generic values to class
     * attributes.
     */
    public SouthPanel() {
        super(new GridLayout(1, 1));
        this.input = new JTextField();
        this.initializePanel();
    }

    /**
     * SouthPanel constructor assigning a pre-set text field to the layout
     * manager.
     * 
     * @param inf_input JTextField representing the text field components of the
     * application window
     */
    public SouthPanel(JTextField inf_input) {
        super(new GridLayout(1, 1));
        this.input = inf_input;
        this.initializePanel();
    }

    /**
     * Sets default parameters for the south layout manager which shows the
     * south layout components of the application.
     */
    private void initializePanel() {
        this.add(this.input);
    }

    /**
     * Returns the text field component currently associated with the
     * application window.
     * 
     * @return JTextField representing the textfield components of the
     * application window 
     */
    public JTextField getInput() {
        return input;
    }
}
