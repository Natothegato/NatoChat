/**
 *  Application: NatoChat_Client
 *
 *  Defines a contract for the Model, View, and Presenter
 *  modules to follow when communicating with one another.
 *  This allows for easier unit testing with different View
 *  modules.
 *
 *  @author NatoTheGato
 *  @version 1.01 12/30/21
 */
package Presenter;

public interface AppContract {

    interface AppModel {

        /**
         * Initializes the Client and starts the Client thread that will
         * communicate with the Chat server.
         */
        public void startApplication();

        /**
         * Releases the resources used to create the Client and Client thread
         */
        public void stopApplication();

        /**
         * Sends a message to the Presenter which is relayed to the View.
         *
         * @param notification the message to be displayed by the view module
         */
        public void notifyPresenter(String notification);
    }

    interface AppView {

        /**
         * Adds an event listener to a text field component which takes text
         * input and sends it to the Presenter module.
         */
        public void startApplication();

        /**
         * Closes all related resources and shows a closing status to the user.
         */
        public void stopApplication();

        /**
         * Prints an incoming message to the text view for the user to see.
         *
         * @param message the incoming message to display on the text view
         */
        public void printMessage(String message);

        /**
         * Sends a message to the Presenter, expected to be a chat message,
         * which is relayed to a remote Chat server.
         *
         * @param message the message which the user inputs to the application
         */
        public void notifyPresenter(String message);
    }

    interface AppPresenter {

        /**
         * Starts the application by signaling the Model and View to start their
         * module implementation.
         */
        public void startApplication();

        /**
         * Stops the application by clearing resources utilized by the Model and
         * View.
         */
        public void stopApplication();

        /**
         * Takes the first input message from the View, which is intended to be
         * a Client Username, and assigns its value.
         *
         * @param username an input username by the application user
         */
        public void assignUsername(String username);

        /**
         * Takes a message from the Model, specifically a Client, and relays it
         * to the View module.
         *
         * @param message the message to be viewed on the application window
         */
        public void handleIncomingMessage(String message);

        /**
         * Takes a message from the View and relays it to the Model for
         * communication with the application server.
         *
         * @param message a command which is sent to the application server
         */
        public void handleOutgoingMessage(String message);
    }
}
