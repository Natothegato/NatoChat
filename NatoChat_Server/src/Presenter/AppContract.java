/**
 *  Application: NatoChat_Server
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

    /**
     * Defines the actions of the Model module in context of the Chat server
     */
    interface AppModel {

        /**
         * Initializes all ChatRooms, Client connections, and their data
         * structures once called upon by the Presenter
         *
         * @param port the <port-number> command line argument parsed as an
         * integer
         */
        public void startApplication(int port);

        /**
         * Releases the resources used to create each ChatRoom and Client
         * connection
         */
        public void stopApplication();

        /**
         * Sends a message to the Presenter which is relayed to the View.
         *
         * @param notification the message to be displayed by the view module
         */
        public void notifyPresenter(String notification);
    }

    /**
     * Defines the actions of the View module in context of the Chat server
     */
    interface AppView {

        /**
         * Prints the beginning application banner and initial status to the
         * user.
         */
        public void startApplication();

        /**
         * Closes all related resources and prints a closing status to the user.
         */
        public void stopApplication();

        /**
         * Print an incoming message to the system console for the user to see.
         *
         * @param message the incoming message to display on the system console
         */
        public void printMessage(String message);

        /**
         * Sends a message to the Presenter, expected to be a command, which
         * incurs an action defined by the application.
         *
         * @param notification the command which the user inputs to the
         * application
         */
        public void notifyPresenter(String notification);
    }

    /**
     * Defines the actions of the Presenter module in context of the Chat server
     */
    interface AppPresenter {

        /**
         * Starts the application by signaling the Model and View to start their
         * module implementation.
         *
         * @param port the <port-number> command line argument
         */
        public void startApplication(int port);

        /**
         * Stops the application by clearing resources utilized by the Model and
         * View.
         */
        public void stopApplication();

        /**
         * Takes a message from the Model (specifically a Client or ChatRoom)
         * and relays it to the View module.
         *
         * @param message the message to be viewed on the system console
         */
        public void handleIncomingMessage(String message);

        /**
         * Takes a message from the View and starts an action based on the
         * message contents.
         *
         * @param command a command which changes the server configuration
         */
        public void handleCommand(String command);
    }
}
