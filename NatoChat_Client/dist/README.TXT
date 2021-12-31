========================
    DIST DESCRIPTION
========================

VERSION: 1.01

The executable file will automatically run the NatoChat_Client application using port 8888,
and will connect to the localhost computer running the NatoChat_Server service.
If you would like to change the <port-number> argument, you can go into the .bat file and
change the "8888" to any available port number (non-reserved). To change the <server-name>,
ensure that the new <server-name> points to a computer on the local subnet which is running
the NatoChat_Server application. Then, go onto windows command prompt and type in the
general syntax.


start javaw.exe -jar NatoChat_Client.jar <server-name> <port-number>

