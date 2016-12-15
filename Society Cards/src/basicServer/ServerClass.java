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
		 * checkIfAdmin(clientID)
		 * true: then assign to clientInformation ArrayList of client
		 * false: report hacker to the FBI (or do nothing) 
		 */
	}
	
	public void removeStudentFromSociety(int clientID, String societyName) {
		/* TODO:
		 * checkIfAdmin(clientID)
		 * true: then remove from clientInformation ArrayList of client
		 * false: eat doritos and cry about social anxiety (or nothing or whatever)
		 */
	}
	
	public ArrayList<String> getAssignedSocieties(int clientID) {
		/* TODO:
		 * clientInformation.getID or whatever it is, return the list of societies
		 */
		return null;
	}
	
	public boolean checkIfAdmin(int clientID, String society) {
		/* TODO:
		 * adminTable.getID or whatever, check if it's there for that society, return true/false
		 */
		return false;
	}
	
	public boolean checkIfMember(int clientID, String societyName) {
		/* TODO:
		 * listOfSocieties = getAssignedSocieties(clientID);
		 * if listOfSocieties.contains(societyName) return true;
		 */
		return false;
	}

}
