package basicServer;

import java.io.IOException;
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

	public void sendStudentNumber() {
		//send student number to server
//	      try {
//	         Socket toServer = new Socket(my_serverHost, my_serverPort);
//	         PrintWriter out = new PrintWriter(toServer.getOutputStream(), true);
//	         // Write the message to the socket.
//	         out.println("GetSocieties "+studentNumber);
//	         out.close();
//	         toServer.close();
//	       } catch (Exception se) {
//	         se.printStackTrace();
//	      }
	   }	
	
	
	public void  getMemberships(){
		//get the returned list of societies the student id is associated with.
//		ObjectInputStream ois = null;
//        ois = new ObjectInputStream(toServer.getInputStream());
//        ArrayList<String> membershipList = new ArrayList<String>();
//		try {
//			membershipList = (ArrayList<String>)ois.readObject();
//			System.out.println("Memberships:\n " + membershipList);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
	}
}
