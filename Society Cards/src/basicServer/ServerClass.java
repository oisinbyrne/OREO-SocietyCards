package basicServer;

import java.util.ArrayList;
import java.util.Hashtable;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//TODO: Handle multiple connections
//TODO: Reduce redundancy
//TODO: Allow user to pass in port number as argument
//TODO: Use listOfSocieties?
//TODO: Request society ID and name for extra security measure?
//TODO: Change isMember so that user can check without societyID

public class ServerClass {
	// <Society ID, Name of society>
	static Hashtable<Integer,String> adminTable = new Hashtable<Integer, String>();
	// <Student number, ArrayList of societies student is assigned to>
	static Hashtable<Integer,ArrayList<String>> clientInformation = new Hashtable<Integer,ArrayList<String>>();	
	// ArrayList of legal societies
	static ArrayList<String> listOfSocieties = new ArrayList<String>();
	
	private int backlog = 5;
	private ServerSocket serverSocket;
	
	public static void main(String args[]) {
		//Create the server
		ServerClass server = new ServerClass();
		server.listen();
	}
	
	private ServerClass() {
		try {
			serverSocket = new ServerSocket(1234, backlog);
			System.out.println("Server is listening on port 1234");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void listen() {
		while(true) {
			Socket clientSocket;
			try {
				clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				DataInputStream in = new DataInputStream(clientSocket.getInputStream());
				
				int command = in.readInt();
				int clientID = in.readInt();
				int societyID;
				societyID = in.readInt();
				
				switch(command){
				case 1:
					out.println(addStudentToSociety(clientID, societyID));
					break;
				case 2:
					out.println(removeStudentFromSociety(clientID, societyID));
					break;
				case 3:
					/*
					 * TODO: Output list as object or strings?
					 * 
					 * //Maybe not the best practice to send objects over sockets but..... it works
					 * ObjectOutputStream outputList = new ObjectOutputStream(clientSocket.getOutputStream()); 
					 * String message;
					 * outputList.writeObject(Object o);
					 */	
					break;
				case 4:
					out.println(isMember(clientID, societyID));
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static String addStudentToSociety(int clientID, int societyID) {
		String societyName = adminTable.get(societyID);
		//Check if Society ID is a valid ID for a Society
		if(societyName != null) {
			//Check if student is registered with system
			if(clientInformation.containsKey(clientID)) {
				//Check if student is already a member
				if(clientInformation.get(clientID).contains(societyName)){
					return("Failure to add  " + clientID + " to " + societyName + ": Client is already a member of Society.");
				}
				else {
					clientInformation.get(clientID).add(societyName);
					return(clientID + "was succesfully added to " + societyName);
				}
			}
			else {
				ArrayList<String> clientSocieties = new ArrayList<String>();
				clientSocieties.add(societyName);
				clientInformation.put(clientID, clientSocieties);
				return(clientID + "was succesfully added to " + societyName);
			}
		}
		else return("Failure to add  " + clientID + " to " + societyName + ": Society ID is incorrect.");
	}
	
	private static String removeStudentFromSociety(int clientID, int societyID) {
		String societyName = adminTable.get(societyID);
		//Check if Society ID is a valid ID for a Society
		if(societyName != null) {
			//Check if student is registered with system
			if(clientInformation.containsKey(clientID)) {
				//Check if student is a member of society
				if(clientInformation.get(clientID).remove(societyName)) return(clientID + "was succesfully removed from " + societyName);
				else return("Failure to remove  " + clientID + " from " + societyName + ". Client is not a member of Society.");
			}
			else return("Failure to remove  " + clientID + " from " + societyName + ". Client does not exist.");
		}
		else return("Failure to remove  " + clientID + " from " + societyName + ". Society ID is incorrect.");
	}
	
	private ArrayList<String> getAssignedSocieties(int clientID) {
		//Check if student is registered with system
		if(clientInformation.containsKey(clientID)) return clientInformation.get(clientID);
		else {
			System.out.println("Failure get assigned societies for  " + clientID + ". Client does not exist.");
			return null;
		}	
	}
	
	private static String isMember(int clientID, int societyID) {
		String societyName = adminTable.get(societyID);
		//Check if Society ID is a valid ID for a Society
		if(societyName != null) {
			//Check if student is registered with system
			if(clientInformation.containsKey(clientID)) {
				//Check if student is a member of society
				if(clientInformation.get(clientID).contains(societyName)) return("Student " + clientID + " is a member of " + societyName);
				else return("Failure to remove  " + clientID + " from " + societyName + ". Client is not a member of Society.");
			}
			else return("Failure to remove  " + clientID + " from " + societyName + ". Client does not exist.");
		}
		else return("Failure to remove  " + clientID + " from " + societyName + ". Society ID is incorrect.");
	}
}
