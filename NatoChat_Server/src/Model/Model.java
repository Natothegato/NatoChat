/**
 *  Application: NatoChat_Server
 *
 *  Represents the Model module of the application,
 *  which manages the initialization and management
 *  of ChatRooms.
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
    private HashSet<ChatRoom> rooms;

    /**
     * Default Model constructor for assigning generic values to class
     * attributes. This constructor should only be used for debugging purposes.
     */
    public Model() {
        this.presenter = null;
        this.rooms = new HashSet<>();
    }

    /**
     * Model constructor for assigning a given Presenter module to the Model.
     *
     * @param inf_presenter the presenter module which the Model communicates
     * with
     */
    public Model(Presenter inf_presenter) {
        this.presenter = inf_presenter;
        this.rooms = new HashSet<>();
    }

    /**
     * Model constructor for assigning a given presenter and a Set of ChatRooms
     * to the Model.
     *
     * @param inf_presenter the presenter module which the Model communicates
     * with
     * @param inf_rooms a preset Set of ChatRooms to initialize
     */
    public Model(Presenter inf_presenter, HashSet<ChatRoom> inf_rooms) {
        this.presenter = inf_presenter;
        this.rooms = inf_rooms;
    }

    /**
     * Initializes all ChatRooms, Client connections, and their data structures
     * once called upon by the Presenter
     *
     * @param port the <port-number> command line argument parsed as an integer
     */
    @Override
    public void startApplication(int port) {
        ChatRoom firstRoom = new ChatRoom(this, port);
        Thread roomSession = new Thread(firstRoom);
        this.rooms.add(firstRoom);
        roomSession.start();
    }

    /**
     * Releases the resources used to create each ChatRoom and Client 
     * connection
     */
    @Override
    public void stopApplication() {
        for (ChatRoom room : this.rooms) {
            room.stopChatRoom();
        }
        this.rooms.clear();
    }

    /**
     * Sends a message to the Presenter which is relayed to the View.
     *
     * @param notification the message to be displayed by the view module
     */
    @Override
    public void notifyPresenter(String notification) {
        this.presenter.handleIncomingMessage(notification);
    }
}
