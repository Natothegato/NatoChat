/**
 *  Application: NatoChat_Client
 *
 *  Represents the Model module of the application,
 *  which manages the initialization and management
 *  of the Client.
 *
 *  @author NatoTheGato
 *  @version 1.01 12/30/21
 */
package Presenter;

import Model.Model;
import View.View;

public class Presenter implements AppContract.AppPresenter {

    //Declare Class Attributes
    private Model model;
    private View view;

    /**
     * Default Presenter constructor assigning generic values to class
     * attributes. This constructor should only be used for debugging purposes.
     */
    public Presenter() {
        this.view = new View(this);
        this.model = new Model(this, "localhost", 8888);
        this.startApplication();
    }

    /**
     * Presenter constructor using a given remote server name and communication
     * port to start the application.
     * @param inf_serverName the hostname of the remote chat server
     * @param inf_port the communication port of the remote chat server
     */
    public Presenter(String inf_serverName, int inf_port) {
        this.view = new View(this);
        this.model = new Model(this, inf_serverName, inf_port);
        this.startApplication();
    }
    
    /**
     * Starts the application by signaling the Model and View to start their
     * module implementation.
     */
    @Override
    public void startApplication() {
        this.view.startApplication();
        this.model.startApplication();
    }

    /**
     * Stops the application by clearing resources utilized by the 
     * Model and View.
     */
    @Override
    public void stopApplication() {
        this.view.stopApplication();
        this.model.stopApplication();
    }
    
    /**
     * Takes the first input message from the View, which is intended to be
     * a Client Username, and assigns its value.
     *
     * @param username an input username by the application user
     */
    @Override
    public void assignUsername(String username) {
        this.model.getClient().setUsername(username);
        this.handleOutgoingMessage(username);
    }
    
    /**
     * Takes a message from the Model, specifically a Client, and relays it
     * to the View module.
     *
     * @param message the message to be viewed on the application window
     */
    @Override
    public void handleIncomingMessage(String message) {
        this.view.printMessage(message);
    }

    /**
     * Takes a message from the View and relays it to the Model for
     * communication with the application server.
     *
     * @param message a message which is sent to the application server
     */
    @Override
    public void handleOutgoingMessage(String message) {
        this.model.getClient().sendMessageToServer(message);
    }
}
