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
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class View implements Runnable, AppContract.AppView {

    //Declare Class Attributes
    private Presenter presenter;
    private Scanner scnr;
    private ReentrantLock lock;

    /**
     * Default View constructor assigning generic values to class attributes.
     * This constructor should only be used for debugging purposes.
     */
    public View() {
        this.presenter = null;
        this.scnr = null;
        this.lock = new ReentrantLock();
    }

    /**
     * View constructor assigning a given Presenter to the View and
     *
     * @param inf_presenter the presenter module which the View communicates
     * with
     */
    public View(Presenter inf_presenter) {
        this.presenter = inf_presenter;
        this.scnr = new Scanner(System.in);
        this.lock = new ReentrantLock();
    }

    /**
     * Defines the behavior of the View thread. It receives input from the
     * Administrator and relays it to the Presenter for further action.
     * Normally, the user will input a command to be handled by the application.
     */
    @Override
    public void run() {
        String input = "";
        System.out.println("[STATUS]: SERVER ONLINE");
        System.out.print("[ADMIN]: ");

        while (!Thread.interrupted()) {
            if (this.scnr.hasNextLine()) {
                this.lock.lock();
                input = this.scnr.nextLine();
                this.notifyPresenter(input);
                System.out.print("[ADMIN]: ");
                this.lock.unlock();
            }
        }
        this.scnr.close();
    }

    /**
     * Prints the beginning application banner and initial status to the user.
     */
    @Override
    public void startApplication() {
        System.out.println("************************");
        System.out.println("*** NatoChat Service ***");
        System.out.println("********* START ********");
        System.out.println("************************");
        System.out.println();
        System.out.println("[STATUS]: Starting NatoChat Service...");
    }

    /**
     * Closes all related resources and prints a closing status to the user.
     */
    @Override
    public void stopApplication() {
        System.out.println("[STATUS]: Stopping NatoChat Service...");
        this.scnr.close();
    }

    /**
     * Print an incoming message to the system console for the user to see.
     *
     * @param message the incoming message to display on the system console
     */
    @Override
    public void printMessage(String message) {
        System.out.print("\n" + message + "\n[ADMIN]: ");
    }

    /**
     * Sends a message to the Presenter, expected to be a command, which incurs
     * an action defined by the application.
     *
     * @param notification the command which the user inputs to the application
     */
    @Override
    public void notifyPresenter(String notification) {
        this.presenter.handleCommand(notification);
    }
}
