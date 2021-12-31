/**
 *  Application: NatoChat_Client
 *
 *  Represents the properties and behaviors of a client
 *  connected to a chatroom in the context of the
 *  NatoChat_Client application.
 *
 *  @author NatoTheGato
 *  @version 1.01 12/30/21
 */
package Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class Client implements Runnable {

    //Declare Class Attributes for Client properties
    private Model model;
    private Socket socket;
    private String username;
    
    //Declare Class Attributes for I/O Communication
    private Scanner scnr;
    private PrintWriter writer;
    
    //Declare Class Attribute for Thread Communication
    private ReentrantLock lock;

    /**
     * Default Client constructor for assigning generic values to class 
     * attributes. This constructor should only be used for debugging purposes.
     */
    public Client() {
        this.model = null;
        this.socket = null;
        this.username = null;
        this.scnr = null;
        this.writer = null;
    }

    /**
     * Client constructor using an informed server name and port number
     * to generate a ServerSocket for server communication.
     * 
     * @param inf_model the model module which communicates with the presenter
     * @param inf_serverName the name of the remote chat server
     * @param inf_port the communication port number of the remote chat server
     */
    public Client(Model inf_model, String inf_serverName, int inf_port) {
        try {
            this.model = inf_model;
            this.socket = new Socket(inf_serverName, inf_port);
            this.scnr = new Scanner(this.socket.getInputStream());
            this.writer = new PrintWriter(this.socket.getOutputStream());
            this.username = "[]";
            this.lock = new ReentrantLock();
        } 
        catch (UnknownHostException unknownHostError) {
            this.model.notifyPresenter(
                    "[ERROR]: " + "{Cannot find target NatoChat Server with the "
                            + "given <server-name>.}");
        }
        catch (ConnectException connectError){
            this.model.notifyPresenter(
                    "[ERROR]: " + "{Target NatoChat server on the given "
                            + "<port-number> is currently unavailable.}");
        }
        catch (IOException ioError) {
            this.model.notifyPresenter(
                    "[ERROR]: {source: " + this.username + "} " +
                            "{" + ioError.toString() + "}");
        }
    }

    /**
     * Client constructor assigning informed values all Class Attributes
     * relating to server communication.
     * 
     * @param inf_model the model module which communicates with the presenter
     * @param inf_port the communication port number of the remote chat server
     * @param inf_serverName the name of the remote chat server
     * @param inf_username the name of the currently connected user
     */
    public Client(Model inf_model, int inf_port, String inf_serverName,
            String inf_username) {
        try {
            this.model = inf_model;
            this.socket = new Socket(inf_serverName, inf_port);
            this.scnr = new Scanner(this.socket.getInputStream());
            this.writer = new PrintWriter(this.socket.getOutputStream());
            this.username = inf_username;
        } 
        catch (UnknownHostException unknownHostError) {
            this.model.notifyPresenter(
                    "[ERROR]: " + "{Cannot find target NatoChat Server with the "
                            + "given <server-name>.}");
        } 
        catch (ConnectException connectError){
            this.model.notifyPresenter(
                    "[ERROR]: " + "{Target NatoChat server on the given "
                            + "<port-number> is currently unavailable.}");
        }
        catch (IOException ioError) {
            this.model.notifyPresenter(
                    "[ERROR]: {source: " + this.username + "} " +
                            "{" + ioError.toString() + "}");
        }
    }

    /**
     *  Defines the behavior of the Client thread. It reads messages from the
     *  server and relays them to the Presenter module.
     */
    @Override
    public void run() {
        String serverMessage = "";
        String formattedUsername = "";
        
        try{
            while (!Thread.interrupted()) {
                if (this.lock.tryLock()) {
                    this.lock.lock();
                    serverMessage = this.scnr.nextLine();
                    this.model.notifyPresenter(serverMessage);
                    this.lock.unlock();
                }
            }  
        }
        
        /*
            Occurs if the Reentrant lock is never intialized due to an
            UnknownHostException thrown in the constructor. The user only
            needs to know about the UnknownHostException.
        */
        catch(NullPointerException nullError){}
    }

    /**
     * Relays a message from the Client application to the server application.
     * @param message the message sent to the server application
     */
    public void sendMessageToServer(String message) {
        try{
            this.writer.println(message);
            this.writer.flush();  
        }
        catch(NullPointerException nullError){
            this.model.notifyPresenter(
                    "[ERROR]: {Failed to communicate with the NatoChat "
                            + "Server.}");
        }
    }

    /**
     * Returns the current assigned username of the client
     * @return a String representing a the Client's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Assigns a new value to the username of the client
     * @param username a String representing the Client's new username
     */
    public void setUsername(String username) {
        this.username = "[" + username + "]";
    }
}
