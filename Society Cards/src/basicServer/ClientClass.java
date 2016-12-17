package basicServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientClass {
    private String name;
    private int studentNumber;

	public ClientClass(String name, int studentNumber){
        this.name = name;
        this.studentNumber = studentNumber;
    }

	public void sendStudentNumber(int command, String arg) {
		try {
            Socket toServer = new Socket(ServerClass.serverHost, 1234);
            PrintWriter out = new PrintWriter(toServer.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(toServer.getInputStream()));
            // Write the message to the socket.
            String message = command+"/"+arg;
//            System.out.println("Sent message: " + message);
            out.println(command+"/"+arg);
            System.out.println(in.readLine());
            out.close();
            toServer.close();
        } catch (Exception se) {
            se.printStackTrace();
        }
	}
		
		public void checkStudentMembership(int studentID){
	        sendStudentNumber(4, String.valueOf(studentID));
		}
	}
