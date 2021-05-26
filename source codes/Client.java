import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Main class for the client side
 * @author omar-
 */
public class Client {
    /**
     * Main method for the client side
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        Scanner input = new Scanner(System.in);
        
        try{ // handling the exceptions:
            Socket socket = new Socket("127.0.0.1", 9090); 
            DataOutputStream dout=new DataOutputStream(socket.getOutputStream()); // for sending message to server.
            DataInputStream in = new DataInputStream(socket.getInputStream()); // for receiving message from server

            String commingGoingMsg = "";
            while(true)
            {
                commingGoingMsg =in.readUTF();  // for receiving message from server.
                System.out.println(commingGoingMsg);  // printing the received message from server.
                if(commingGoingMsg.contains("off successfully#")
                        || commingGoingMsg.startsWith("     27;")) // means if logout successfully...
                {
                    break;
                }
                
                /*
                commingGoingMsg: taking input from user and delete the...
                extra space in the beginnig and in the end of the file 
                */
                commingGoingMsg = input.nextLine().trim(); 
                dout.writeUTF(commingGoingMsg);   // for sending message to server.
                dout.flush();  // make sure that message is sent
            }
            socket.close(); // when finish
        } catch(ConnectException e){  // handling ConnectException:
            System.out.println("    Cannot connect to server, please try again later...");
        } catch(Exception e){   // handling Unknown exception:
            System.out.println("    (Something happend in client side. Please try again...)");
        }
    } 
}
