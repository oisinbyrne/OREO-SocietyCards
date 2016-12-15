package basicServer;

import java.util.ArrayList;
import java.util.Hashtable;

public class ServerClass {
	// <Student number, Name of society 
	Hashtable<Integer,String> adminTable = new Hashtable<Integer, String>();
	
	// <Student number, ArrayList of societies student is assigned to>
	Hashtable<Integer,ArrayList<String>> clientInformation = new Hashtable<Integer,ArrayList<String>>();
	
	// ArrayList of legal societies
	ArrayList<String> listOfSocieties = new ArrayList<String>();
	
	public void addStudentToSociety(int clientID, String societyName) {
		/* TODO:
		 * Check this is coming from an admin, then assign to clientInformation ArrayList of client 
		 */
	}
	
	public ArrayList<String> checkAssignedSocieties(int clientID) {
		/* TODO:
		 * clientInformation.getID or whatever it is
		 */
		return null;
	}
	
	public boolean checkIfAdmin(int clientID) {
		/* TODO:
		 * adminTable.getID or whatever, check if it's in, return true/false
		 */
		return false;
	}

}
