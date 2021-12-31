/**
 *  Application: NatoChat_Server
 *
 *  Starts the main thread of execution by handling
 *  command line arguments and creating the presenter.
 *
 *  @author NatoTheGato
 *  @version 1.01 12/30/21
 */


import Presenter.Presenter;

public class Application {

    /**
     * Starts the main thread of execution using input command line arguments
     * @param args String[] representing the command line arguments
     */
    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                System.err.println("Usage: java -jar NatoChat_Server.jar "
                        + "<port-number>");
                System.exit(1);
            }
            
            int port = Integer.parseInt(args[0]);
            
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
            
            Presenter presenter = new Presenter(port);
        } 
        catch (NumberFormatException formatError) {

            //If there is an issue with parsing the command line argument 
            System.err.println("<port-number> must be an integer.");
            System.exit(1);
        }
    }
}
