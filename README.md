# NatoChat

<b>VERSION: 1.01</b>

<p>
This application is meant to simulate a Chatroom using the Java programming language. As such,
there is a client and server component to this project, each developed using the Model-View-Presenter
(MVP) software architecture. This was my first attempt at using the MVP architecture, so it may not 
be accurate. 
</p>
<p>
Each component (in their dist folders) have a batch file which will execute the server
and client application using a default port of 8888 on Windows NT systems.
</p> <br>
<h2>ACCESSING THE JAVA FILES</h2>
<p>
To access the .java files, to go <NatoChat_Component> -> src to find the directory.
</p> <br>

<h2>EXECUTING THE SERVER COMPONENT</h2>
<p>
The server component can be started by going to the NatoChat_Server's dist folder and executing
on the Windows NT command prompt:
<br><br>
start java.exe -jar NatoChat_Server.jar "port-number" - where the "port-number" must be higher than 1023
</p> <br>

<h2>EXECUTING THE SERVER COMPONENT</h2>
<p>
The client component can be started by going to the NatoChat_Client's dist folder and executing
on the Windows NT command prompt:
<br><br>
start javaw.exe -jar NatoChat_Client.jar "server-name" "port-number" - where the "server-name" and "port-number" target a computer with the NatoChat_Server component
</p> <br>
