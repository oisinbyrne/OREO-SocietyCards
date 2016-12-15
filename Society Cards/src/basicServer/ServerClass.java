package basicServer;

import java.util.ArrayList;
import java.util.Hashtable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//TODO: Handle multiple connections
//TODO: Reduce redundancy

public class ServerClass {
	// <Society ID, Name of society>
	static Hashtable<Integer,String> adminTable = new Hashtable<Integer, String>();
	
	// <Student number, ArrayList of societies student is assigned to>
	static Hashtable<Integer,ArrayList<String>> clientInformation = new Hashtable<Integer,ArrayList<String>>();
	
	// ArrayList of legal societies
	static ArrayList<String> listOfSocieties = new ArrayList<String>();
	
	public static void main(String args[]) throws Exception {
		try {
			ServerSocket serverSocket = new ServerSocket(101);
			Socket socket = serverSocket.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			String message = in.readLine();
			
			//Split message into (Action code, arg1, arg2)
			
			int code = 0;
			int arg1 = 13437368;
			String arg2 = "FilmSoc";
			String output = null;
			
			switch(code){
			case 0:
				output = addStudentToSociety(arg1, arg2);
				break;
			case 1:
				output = removeStudentFromSociety(arg1, arg2);
				break;
			}
			
			out.println(output);
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	private static String addStudentToSociety(int clientID, String societyName) {
		//Check if Society Name is legal
		if(listOfSocieties.contains(societyName)) {
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
		else return("Failure to add  " + clientID + " to " + societyName + ": Society does not exist.");
	}
	
	private static String removeStudentFromSociety(int clientID, String societyName) {
		//Check if Society Name is legal
		if(listOfSocieties.contains(societyName)) {
			//Check if student is registered with system
			if(clientInformation.containsKey(clientID)) {
				//Check if student is a member of society
				if(clientInformation.get(clientID).remove(societyName)) return(clientID + "was succesfully removed from " + societyName);
				else return("Failure to remove  " + clientID + " from " + societyName + ". Client is not a member of Society.");
			}
			else return("Failure to remove  " + clientID + " from " + societyName + ". Client does not exist.");
		}
		else return("Failure to remove  " + clientID + " from " + societyName + ". Society does not exist.");
	}
	
	private ArrayList<String> getAssignedSocieties(int clientID) {
		//Check if student is registered with system
		if(clientInformation.containsKey(clientID)) return clientInformation.get(clientID);
		else {
			System.out.println("Failure get assigned societies for  " + clientID + ". Client does not exist.");
			return null;
		}	
	}
	
	private static String isMember(int clientID, String societyName) {
		//Check if Society Name is legal
		if(listOfSocieties.contains(societyName)) {
			//Check if student is registered with system
			if(clientInformation.containsKey(clientID)) {
				//Check if student is a member of society
				if(clientInformation.get(clientID).contains(societyName)) return("Student " + clientID + " is a member of " + societyName);
				else return("Failure to remove  " + clientID + " from " + societyName + ". Client is not a member of Society.");
			}
			else return("Failure to remove  " + clientID + " from " + societyName + ". Client does not exist.");
		}
		else return("Failure to remove  " + clientID + " from " + societyName + ". Society does not exist.");
	}
}
