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
package Model;

import Presenter.*;
import java.util.HashSet;

public class Model implements AppContract.AppModel {

    //Declare Class Attributes
    private Presenter presenter;
    private Client client;

    /**
     * Default Model constructor assigning generic values to class attributes.
     * This constructor should only be used for debugging purposes.
     */
    public Model() {
        this.presenter = null;
        this.client = null;
    }

    /**
     * Model constructor using a server name and port number to generate
     * the Client object.
     * 
     * @param inf_presenter the presenter module which the Model communicates
     * with
     * @param inf_serverName the name of the remote server
     * @param inf_port the communication port of the remote server
     */
    public Model(Presenter inf_presenter, String inf_serverName, int inf_port) {
        this.presenter = inf_presenter;
        this.client = new Client(this, inf_serverName, inf_port);
    }

    /**
     * Model constructor assigning informed values to all class attributes.
     * 
     * @param inf_presenter the presenter module which the Model communicates
     * with
     * @param inf_client a pre-established Client communicating with a remote
     * server
     */
    public Model(Presenter inf_presenter, Client inf_client) {
        this.presenter = inf_presenter;
        this.client = inf_client;
    }

    /**
     * Initializes the Client and starts the Client thread that will communicate
     * with the Chat server.
     */
    @Override
    public void startApplication() {
        Thread clientThread = new Thread(this.client);
        clientThread.start();
    }

    /**
     * Releases the resources used to create the Client and Client thread
     */
    @Override
    public void stopApplication() {
        this.client = null;
    }

    /**
     * Sends a message to the Presenter which is relayed to the View.
     *
     * @param notification the message to be displayed by the view module
     */
    public void notifyPresenter(String notification) {
        this.presenter.handleIncomingMessage(notification);
    }

    /**
     * Returns the Client object currently communicating with the remote Chat
     * server.
     *
     * @return the Client currently communicating with the application server
     */
    public Client getClient() {
        return client;
    }
}
