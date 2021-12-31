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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CenterPanel extends JPanel {

    //Declare Class Attributes
    private JTextArea output;
    private JScrollPane scroller;

    /**
     * Default CenterPanel constructor assigning generic values to class
     * attributes.
     */
    public CenterPanel() {
        super(new GridLayout(1, 1));
        this.output = new JTextArea();
        this.scroller = new JScrollPane(this.output);
        this.initializePanel();
    }

    /**
     * CenterPanel constructor assigning a pre-set text area and scroll pane
     * to the layout manager.
     * 
     * @param inf_output JTextArea representing the output text area of the 
     * application window
     * @param inf_scroller JScrollPanel representing the scrolling mechanism
     * of the center application window
     */
    public CenterPanel(JTextArea inf_output, JScrollPane inf_scroller) {
        super(new GridLayout(1, 1));
        this.output = inf_output;
        this.scroller = inf_scroller;
        this.initializePanel();
    }

    /**
     * Sets default parameters for the center layout manager which shows the
     * center layout components of the application.
     */
    private void initializePanel() {
        this.output.setEditable(false);
        this.add(this.scroller);
    }

    /**
     * Returns the text area component currently associated with the
     * application window.
     * 
     * @return JTextArea representing the output text area of the 
     * application window
     */
    public JTextArea getOutput() {
        return output;
    }

    /**
     * Returns the scrolling mechanism currently associated with the
     * application window.
     * 
     * @return JScrollPanel representing the scrolling mechanism
     * of the center application window
     */
    public JScrollPane getScroller() {
        return scroller;
    }
}
