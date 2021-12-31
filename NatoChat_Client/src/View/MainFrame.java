/**
 *  Application: NatoChat_Server
 *
 *  Represents the application window of the
 *  user interface and holds the primary layout
 *  manager.
 *
 *  @author NatoTheGato
 *  @version 1.01 12/30/21
 */
package View;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

    //Declare Class Attribute
    private MainPanel mainPanel;

    /**
     * Default MainFrame constructor assigning generic values to class
     * attributes.
     */
    public MainFrame() {
        super("NatoChat Client");
        this.mainPanel = new MainPanel();
        this.initializeFrame();
    }

    /**
     * MainFrame constructor assigning a pre-defined MainPanel as the primary
     * layout manager.
     *
     * @param inf_mainPanel JPanel representing the primary UI layout manager
     */
    public MainFrame(MainPanel inf_mainPanel) {
        super("NatoChat Client");
        this.mainPanel = inf_mainPanel;
        this.initializeFrame();
    }

    /**
     * Sets default application window parameters which shows the application
     * window to the user.
     */
    private void initializeFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.add(this.mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    /**
     * Returns the MainPanel layout manager currently associated with the
     * application window.
     *
     * @return JPanel representing the primary UI layout manager
     */
    public MainPanel getMainPanel() {
        return mainPanel;
    }
}
