/**
 *  Application: NatoChat_Client
 *
 *  Starts the main thread of execution by handling
 *  command line arguments and creating the presenter.
 *
 * @author NatoTheGato
 * @version 1.01 12/30/21
 */

import Presenter.Presenter;

public class Application {

    /**
     * Starts the main thread of execution using input command line arguments
     * @param args String[] representing the command line arguments
     */
    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                System.err.println("Usage: java -jar NatoChat_Server.jar "
                        + "<server-name> <port-number>");
                System.exit(1);
            }

            String serverName = args[0];
            int port = Integer.parseInt(args[1]);
            
            /*
                Validates the port number to ensure it is not a Reserved Port
                Number.
            */
            if(port < 1024){
                System.err.println("[ERROR]: The informed <port-number> cannot "
                        + "be below 1024\nas that is either a "
                        + "negative number or a Reserved Port Number.");
                System.exit(1);
            }
           
            Presenter presenter = new Presenter(serverName, port);
        } 
        catch (NumberFormatException formatError) {
            System.err.println("<port-number> must be an integer.");
            System.exit(1);
        }
    }
}
