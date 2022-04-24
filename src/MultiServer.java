import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Vector;

public class MultiServer {

    private static final int SERVER_PORT = 6342;
    static Vector<ClientHandler> clientVector = new Vector<>();

    public static void main(String[] args){
        createCommunicationLoop();
    }// end main

    //Communication loop to connect to client and listen for commands and execute
    public static void createCommunicationLoop(){
        int clientNumber = 0;
        try{
            // Create Server Socket
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

            System.out.println("Server started at " + new Date() + "\n");

            // server loop listening for the client and responding
            while(true){
                // listen for a connection
                // using a regular client socket
                Socket socket = serverSocket.accept();

                clientNumber++;


                //now prepare to send and receive data on output streams
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

                ClientHandler currentClient = new ClientHandler("client"+clientNumber,socket, serverSocket, inputFromClient, outputToClient);
                Thread clientThread = new Thread(currentClient);
                clientVector.add(currentClient);
                clientThread.start();

            } // end server loop
        }
        catch(IOException ex){
            ex.printStackTrace();
        } //end try-catch
    }// end createCommunicationLoop

}