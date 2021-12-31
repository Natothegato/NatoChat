/**
 *  Application: NatoChat_Server
 *
 *  Represents the View module of the application,
 *  which manages UI input and output data.
 *
 *  @author NatoTheGato
 *  @version 1.01 12/30/21
 */
package View;

import Presenter.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JTextField;


public class View implements AppContract.AppView {
    
    //Declare Class Attributes for class properties
    private Presenter presenter;
    private MainFrame frame;
    private String displayName;
    
    //Delcare Class Attributes for I/O Flow Control
    private boolean nameRequested;
    private ReentrantLock lock;

    /**
     * Default View constructor assigning generic class attribute to class
     * attributes. This constructor should only be used for debugging purposes.
     */
    public View(){
        this.presenter = null;
        this.frame = new MainFrame();
        this.displayName = "[]";
        this.nameRequested = false;
        this.lock = new ReentrantLock();
    }

    /**
     * View constructor assigning a given Presenter module to the View module
     * and starting their communication.
     * 
     * @param inf_presenter the presenter module which the View communicates
     * with 
     */
    public View(Presenter inf_presenter) {
        this.presenter = inf_presenter;
        this.frame = new MainFrame();
        this.displayName = "[]";
        this.nameRequested = false;
        this.lock = new ReentrantLock();
    }
    
    /**
     * Adds an event listener to a text field component which takes text
     * input and sends it to the Presenter module.
     */
    @Override
    public void startApplication() {
        this.frame.getMainPanel().getSouth().getInput().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                
                //Find the source message of the event listener
                JTextField source = (JTextField) event.getSource();
                String input = source.getText();
                
                //Present the input to the server
                notifyPresenter(input);
                
                //If the username wasn't already requested
                if(!nameRequested){
                    presenter.assignUsername(input);
                    displayName = "[" + input + "]: ";
                    nameRequested = true;
                }
                else{
                    frame.getMainPanel().getCenter().getOutput().
                        append(displayName + input + "\n");
                }
                
                //Clear the current input field
                frame.getMainPanel().getSouth().getInput().setText("");
            }
        });
    }

    /**
     * Closes all related resources and shows a closing status to the user.
     */
    @Override
    public void stopApplication() {
        //this implementation is only neccessary for a console-based application
    }
    
    /**
     * Sends a message to the Presenter, expected to be a chat message,
     * which is relayed to a remote Chat server.
     *
     * @param message the message which the user inputs to the application
     */
    @Override
    public void printMessage(String message){
        this.frame.getMainPanel().getCenter().getOutput().append(message + 
                "\n");
    }

    /**
     * Sends a message to the Presenter, expected to be a chat message,
     * which is relayed to a remote Chat server.
     *
     * @param message the message which the user inputs to the application
     */
    @Override
    public void notifyPresenter(String message) {
        this.presenter.handleOutgoingMessage(message);
    }
}
