package basicServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientClass {

	public void sendStudentNumber(int studentNumber) {
		//send student number to server
	      try {
	         Socket toServer = new Socket(my_serverHost, my_serverPort);
	         PrintWriter out = new PrintWriter(toServer.getOutputStream(), true);
	         // Write the message to the socket.
	         out.println(studentNumber);
	         out.close();
	         toServer.close();
	       } catch (SecurityException se) {
	         se.printStackTrace();
	      }
	   }	
	
	
	public void  getMemberships(){
		//get the returned list of societies the student id is associated with.
		ObjectInputStream ois = null;  
        ois = new ObjectInputStream(toServer.getInputStream());
        ArrayList<String> membershipList = new ArrayList<String>();
		try {
			membershipList = (ArrayList<String>)ois.readObject();
			System.out.println("Memberships:\n " + membershipList);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
