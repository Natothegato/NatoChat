/**
 *  Application: NatoChat_Server
 *
 *  Represents the behaviors and properties of a chatroom
 *  in the context of the NatoChat_Server application.
 *
 *  @author NatoTheGato
 *  @version 1.01 12/30/21
 */
package Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class ChatRoom implements Runnable {

    //Declare Class Attribute relating to the ChatRoom initialization
    private Model model;
    private ServerSocket socket;
    private String roomName;

    //Declare Class Attribute related to the server's data structure
    private HashSet<Client> clients;
    private HashSet<String> clientNames;

    /**
     * Default ChatRoom constructor for assigning generic values to class
     * attributes. This constructor should only be used for debugging purposes.
     */
    public ChatRoom() {
        this.model = null;
        this.socket = null;
        this.roomName = "<room>";
        this.clients = new HashSet<>();
        this.clientNames = new HashSet<>();
    }

    /**
     * ChatRoom constructor for initializing the ChatRoom with a given port
     * number and model Module.
     *
     * @param inf_model the model module which communicates with the presenter
     * @param inf_port the <port-number> command line argument
     */
    public ChatRoom(Model inf_model, int inf_port) {
        try {
            this.model = inf_model;
            this.socket = new ServerSocket(inf_port);
            this.roomName = "ROOM #" + inf_port;
            this.clients = new HashSet<>();
            this.clientNames = new HashSet<>();
        } 
        catch (IOException ioError) {
            this.model.notifyPresenter(
                    "[ERROR]: {source: " + this.roomName + "} "
                            + "{" + ioError.toString() + "}");
        }
    }

    /**
     * Class constructor for assigning informed values to class attributes. For
     * this implementation, only a port number is supplied to initialize the
     * ServerSocket object.
     *
     * @param inf_model the model module which communicates with the presenter
     * @param inf_port the <port-number> command line argument
     * @param inf_users a set representing the currently connected clients
     * @param inf_usernames a set representing the usernames of currently
     * connected clients
     */
    public ChatRoom(Model inf_model, int inf_port,
            HashSet<Client> inf_users, HashSet<String> inf_usernames) {
        try {
            this.model = inf_model;
            this.socket = new ServerSocket(inf_port);
            this.clients = inf_users;
            this.clientNames = inf_usernames;
        } 
        catch (IOException ioError) {
            this.model.notifyPresenter(
                    "[ERROR]: {source: " + this.roomName + "} "
                            + "{" + ioError.toString() + "}");
        }
    }

    /**
     * Defines the behavior of the ChatRoom thread. It establishes a connection
     * between the connecting Client and the other Clients in the ChatRoom.
     */
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {

                //Wait for a Client connection
                Socket connection = this.socket.accept();

                //Create a Client to manage the I/O network connection
                Client client = new Client(this, connection);
                
                //Start the Client's thread of execution
                Thread user = new Thread(client);
                user.start();
            }
            
            this.stopChatRoom();
        } 
        catch (IOException ioError) {
            this.model.notifyPresenter(
                    "[ERROR]: {source: " + this.roomName + "} "
                            + "{" + ioError.toString() + "}");
        }
    }

    /**
     * Takes a Client message and sends it to every other Client in the
     * ChatRoom.
     *
     * @param source the Client who sent the message to the server
     * @param message the message the Client sent to the server
     */
    public void broadcastClientMessage(Client source, String message) {
        for (Client client : clients) {
            if (client != source) {
                client.sendMessageToClient(message);
            }
        }
    }

    /**
     * Relays a list of currently connected Client usernames to a new Client
     * connection.
     *
     * @param source the new Client connection
     * @param serverName the server's display name
     */
    public void sendUsersToClient(Client source, String serverName) {
        if (this.clients.isEmpty()) {
            source.sendMessageToClient(serverName + "This Chatroom is empty.");
        } 
        else {
            source.sendMessageToClient(serverName + "These users are in the "
                    + "Chatroom. - " + this.clientNames.toString());
        }
    }

    /**
     * Removes a Client from the Set data structures to truthfully represent who
     * is in the ChatRoom.
     *
     * @param source the Client that disconnected from the ChatRoom
     * @param username the source's username
     */
    public void removeClientFromChatRoom(Client source, String username) {
        boolean removed = this.clients.remove(source);

        if (removed) {
            this.clientNames.remove(username);
            this.model.notifyPresenter("[NOTIFICATION]: " + username + " left "
                    + this.roomName + ".");
        }
    }

    /**
     * Cleans the ChatRoom by removing all of the users and closing the socket
     * connection.
     */
    public void stopChatRoom() {
        try {
            this.clientNames.clear();
            this.clients.clear();
            this.socket.close();
            this.model.notifyPresenter("[NOTIFICATION]: " + this.roomName
                    + " closed.");
        } 
        catch (IOException ioError) {
            this.model.notifyPresenter(
                    "[ERROR]: {source: " + this.roomName + "} "
                            + "{" + ioError.toString() + "}");
        }
    }

    // ---------------- ACCESSOR METHODS ----------------
    
    /**
     * Returns the Model object utilized by the ChatRoom 
     * @return 
     */
    public Model getModel() {
        return model;
    }

    /**
     * Returns the room name of the ChatRoom
     * @return 
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Returns a Set of currently connected Clients in the ChatRoom
     * @return 
     */
    public HashSet<Client> getClients() {
        return clients;
    }

    /**
     * Returns a Set of Client names who are currently connected to the 
     * ChatRoom
     * @return HashSet of String values representing Client names
     */
    public HashSet<String> getClientNames() {
        return clientNames;
    }
}
