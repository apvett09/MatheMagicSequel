# MatheMagic
This project is an online geometry solver using network sockets. It has three main programs: a server, a client handler, and a client(can have multiple client programs).
The server creates a socket in the Internet domain bound to port SERVER_PORT. The server receives requests from the client, acts on those requests, and returns the results back to the requester. The client creates a socket in the Internet domain and sends requests to the SERVER_PORT and receives responses through the socket from the server.

The client operates by sending the following commands to the server: LOGIN, SOLVE, LIST, LOGOUT, SHUTDOWN, MESSAGE

Instructions on how to run program:

•	In your IDE, build and run the MultiServer.java first followed by building and running at least one Client<#>.java programs. The ClientHandler.java program does not run. It only acts as a middle man for communication between the server and client(s).

• In Client<#>.java  the user will be prompted to send a command to the server. They will input the command and wait on a response from the MultiServer.java program.

•	Valid commands are listed above. Only once logged in will the user be able to issue the SOLVE and LIST commands. You will get a “SUCCESS” if login was successful. Only users provided in the logins.txt file will be able to login. 

•	If using the LOGIN command, the user will need to provide a valid username and password followed by the LOGIN command, all in a single line.

	Example: LOGIN root root22

•	If the user request to SOLVE they must input a “-c” or “-r” flag indicating a solve for a circle or rectangle. For a circle, the user must input a valid radius following the -c flag. For a rectangle, the user must input at least one and at most two valid value(s) for the sides of the rectangle. If only one value is inputted by the user, the program will treat the input as the value for both required sides. The user will receive calculations for the area and circumference of a circle or the area and perimeter of a rectangle.

	Example: SOLVE -c 2
	Example: SOLVE -r 2 3
	Example: SOLVE -r 5
	
•	The solutions for any valid solve command will be stored in a text file names "username_solutions.txt". "username" being the current user logged into the server.
	
•	The LIST command can be used when a user is logged in. It will retrieve all the requested solutions that the user has asked for and display them to that user currently logged in. 
	
•	The “root” user, once logged in, can use the LIST command to request the root’s solutions. The root user can also use command LIST followed by a “-all” flag, which will display the solutions of every username provided in logins.txt.
	
• Issuing the MESSAGE command will allow clients to communicate with other clients indirectly. The communication goes through the server and the server will issue the message to the corresponding clients. The root client has special administrative privileges with the “-all” command. This will allow the root to send to all active clients using the MultiServer. The MESSAGE command should be issued as follows:

    Example: MESSAGE john hi there john!
    
    The first input will be treated as the command(MESSAGE), followed by the name of the client’s username you wish to communicate
    with. Everything after the name of the client will be treated as the actual message to send and display to that user
  
•	LOGOUT command will logout the currently logged in user and terminate the Client<#>.java program
	
•	The SHUTDOWN command will close the sockets and shut down the program. If only one client is connected to the server. If more than one client is using the server no user will be able to shutdown the server until all, but one client is active. I implemented it this way so all active users can have a chance to use the MultiServer and login to issue commands.
