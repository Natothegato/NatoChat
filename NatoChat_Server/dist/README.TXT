========================
    DIST DESCRIPTION
========================

VERSION: 1.01

The executable file will automatically run the NatoChat_Server application using port 8888.
If you would like to change the <port-number> argument, you can go into the .bat file and
change the "8888" to any available port number (non-reserved).

To reiterate, the application will not allow you to initialize the server on ports 0 - 1023
as those are reserved port numbers for other system services.

Below shows the general syntax required to run the application on Windows command prompt.

start java.exe -jar NatoChat_Server.jar <port-number>


