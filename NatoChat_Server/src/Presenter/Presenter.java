/**
 *  Application: NatoChat_Server
 *
 *  Represents the Presenter module of the application,
 *  which manages the communication between the Model
 *  and View.
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
     * attributes.
     */
    public Presenter() {
        this.model = new Model(this);
        this.view = new View(this);
        this.startApplication(8888);
    }

    /**
     * Presenter constructor using an informed <port-number> to start the Chat
     * service and initializing the Model and View modules.
     *
     * @param inf_port the <port-number> command line argument
     */
    public Presenter(int inf_port) {
        this.model = new Model(this);
        this.view = new View(this);
        this.startApplication(inf_port);
    }

    /**
     * Starts the application by signaling the Model and View to start their
     * module implementation.
     *
     * @param port the <port-number> command line argument
     */
    @Override
    public void startApplication(int port) {
        this.view.startApplication();
        this.model.startApplication(port);
        Thread viewThread = new Thread(view);
        viewThread.start();
    }

    /**
     * Stops the application by clearing resources utilized by the Model and
     * View.
     */
    @Override
    public void stopApplication() {
        this.model.stopApplication();
        this.view.stopApplication();
    }

    /**
     * Takes a message from the Model (specifically a Client or ChatRoom) and
     * relays it to the View module.
     *
     * @param message the message to be viewed on the system console
     */
    @Override
    public void handleIncomingMessage(String message) {
        this.view.printMessage(message);
    }

    /**
     * Takes a message from the View and starts an action based on the message
     * contents.
     *
     * @param command a command which changes the server configuration
     */
    @Override
    public void handleCommand(String command) {
        //Handle Command Feature
    }
}
