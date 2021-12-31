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

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

    //Declare Class Attributes
    private CenterPanel center;
    private SouthPanel south;

    /**
     * Default MainPanel constructor assigning generic values to class
     * attributes.
     */
    public MainPanel() {
        super(new BorderLayout());
        this.center = new CenterPanel();
        this.south = new SouthPanel();
        this.initializePanel();
    }

    /**
     * MainPanel constructor assigning a preset CenterPanel and SouthPanel
     * UI layout managers to the MainPanel layout manager.
     * 
     * @param inf_center CenterPanel representing the center application window
     * layout manager
     * @param inf_south SouthPanel representing the bottom application window 
     * layout manager
     */
    public MainPanel(CenterPanel inf_center, SouthPanel inf_south) {
        super(new BorderLayout());
        this.center = inf_center;
        this.south = inf_south;
        this.initializePanel();
    }

    /**
     * Sets default parameters for the main layout manager which shows the
     * center and south layout managers to the user.
     */
    private void initializePanel() {
        this.add(this.center, BorderLayout.CENTER);
        this.add(this.south, BorderLayout.SOUTH);
    }

    /**
     * Returns the CenterPanel layout manager currently associated with the
     * application window.
     * 
     * @return CenterPanel representing the center application window
     * layout manager
     */
    public CenterPanel getCenter() {
        return center;
    }

    /**
     * Returns the SouthPanel layout manager currently associated with the
     * application window.
     * 
     * @return SouthPanel representing the bottom application window 
     * layout manager
     */
    public SouthPanel getSouth() {
        return south;
    }
}
