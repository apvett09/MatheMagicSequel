import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client3 {

    private static final int SERVER_PORT = 6342;

    public static void main(String[] args){

        DataOutputStream toServer;
        DataInputStream fromServer;
        Scanner input = new Scanner(System.in);


        // attempt to connect to the server
        try{
            Socket socket = new Socket("localhost", SERVER_PORT);

            // create input stream to receive data from server
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());

            // start sending and receiving messages to/from server
            System.out.println("Send command to server:\t");

            Thread sendMessage = new Thread(new Runnable()
            {
                @Override
                public void run() {
                    while (true) {
                        if(socket.isClosed()){
                            break;
                        }
                        else{
                            String message = input.nextLine();
                            message = message.trim();
                            try {
                                toServer.writeUTF(message);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });

            Thread readMessage = new Thread(new Runnable()
            {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String msgFromServer = fromServer.readUTF();

                            if (msgFromServer.equals("200 OK")){
                                System.out.println(msgFromServer);
                                System.out.println("Press Enter");
                                socket.close();
                                break;
                            }
                            else{
                                System.out.println(msgFromServer);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    sendMessage.stop();
                }
            });
            sendMessage.start();
            readMessage.start();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }// end try-catch
    }// end main
}