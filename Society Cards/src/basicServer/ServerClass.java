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
    // <Society ID, Name of society> table containing registered societies + their name
    static Hashtable<Integer, String> adminTable = new Hashtable<Integer, String>();
    // <Society ID, Student members> table containing who has joined a given society
    static Hashtable<Integer, ArrayList<Integer>> adminMembers = new Hashtable<Integer, ArrayList<Integer>>();
    // <Student number, ArrayList of societies student is assigned to>
    static Hashtable<Integer, ArrayList<String>> clientInformation = new Hashtable<Integer, ArrayList<String>>();
    static String serverHost = "127.0.0.1";
    static int serverPort = 1234;
    private int backlog = 5;
    private static final int REGISTER_SOCIETY = 0;
    private static final int ADD_STUDENT = 1;
    private static final int REMOVE_STUDENT = 2;
    private static final int CHECK_STUDENT_MEMBERSHIP = 3;
    private static final int GET_SOCIETIES = 4;
    private ServerSocket serverSocket;

    public static void main(String args[]) {
        //Create the server
        ServerClass server = new ServerClass();
        server.listen();
    }

    private ServerClass() {
        try {
            serverSocket = new ServerSocket(serverPort, backlog);
            System.out.println("Server is listening on port 1234");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen() {
        while (true) {
            Socket clientSocket;
            try {
                clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//                DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String input = in.readLine();
                System.out.println("Received: " + input);
                String[] command = input.split("/");
                int commandID = Integer.parseInt(command[0]);
                int studentID, societyID;
                String societyName;
                switch (commandID) {
                    case REGISTER_SOCIETY:
                        societyID = Integer.parseInt(command[1]);
                        societyName = command[2];
                        adminTable.put(societyID, societyName);
                        adminMembers.put(societyID, new ArrayList<Integer>());
                        out.println(societyName + " registered successfully");
                        break;
                    case ADD_STUDENT:
                        societyID = Integer.parseInt(command[1]);
                        studentID = Integer.parseInt(command[2]);
                        out.println(addStudentToSociety(studentID, societyID));
                        break;
                    case REMOVE_STUDENT:
                        societyID = Integer.parseInt(command[1]);
                        studentID = Integer.parseInt(command[2]);
                        out.println(removeStudentFromSociety(studentID, societyID));
                        break;
                    case CHECK_STUDENT_MEMBERSHIP:
                        societyID = Integer.parseInt(command[1]);
                        studentID = Integer.parseInt(command[2]);
                        out.println(isMember(studentID, societyID));
                        break;
                    case GET_SOCIETIES:    
                    	studentID = Integer.parseInt(command[2]);
                        out.println(getSocieties(studentID));
                        break;
                    case 5:
                        /*
                         * TODO: Output list as object or strings?
                         *
                         * //Maybe not the best practice to send objects over sockets but..... it works
                         * ObjectOutputStream outputList = new ObjectOutputStream(clientSocket.getOutputStream());
                         * String message;
                         * outputList.writeObject(Object o);
                         */
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String addStudentToSociety(int studentID, int societyID) {
        String societyName = adminTable.get(societyID);
        String output;
        System.out.println("adding");
        //Check if Society ID is a valid ID for a Society
        if (societyName != null) {
            if (clientInformation.containsKey(studentID)) {
                //Check if student is already a member
                if (adminMembers.get(societyID).contains(studentID)) {
                    return ("Failure to add  " + studentID + " to " + societyName + ": Client is already a member of Society.");
                } else {
                    adminMembers.get(societyID).add(studentID);
                    clientInformation.get(studentID).add(societyName);
                    return (studentID + "was successfully added to " + societyName);
                }
            } else {
                ArrayList<String> clientSocieties = new ArrayList<String>();
                clientSocieties.add(societyName);
                clientInformation.put(studentID, clientSocieties);
                adminMembers.get(societyID).add(studentID);
                return (studentID + " was successfully added to " + societyName);
            }
            //Check if student is registered with system
//            if (clientInformation.containsKey(studentID)) {
//                //Check if student is already a member
//                if (clientInformation.get(studentID).contains(societyName)) {
//                    System.out.println("fail");
//                    return ("Failure to add  " + studentID + " to " + societyName + ": Client is already a member of Society.");
//                } else {
//                    System.out.println("success");
//                    clientInformation.get(studentID).add(societyName);
//                    return (studentID + "was succesfully added to " + societyName);
//                }
//            } else {
//                ArrayList<String> clientSocieties = new ArrayList<String>();
//                clientSocieties.add(societyName);
//                clientInformation.put(studentID, clientSocieties);
//                return (studentID + "was succesfully added to " + societyName);
//            }
        } else return ("Failure to add  " + studentID + " to " + societyName + ": Society ID is incorrect.");
    }

    private static String removeStudentFromSociety(int studentID, int societyID) {
        String societyName = adminTable.get(societyID);
        //Check if Society ID is a valid ID for a Society
        if (societyName != null) {
            //Check if student is registered with system
            if (clientInformation.containsKey(studentID)) {
                //Check if student is a member of society
                if (clientInformation.get(studentID).remove(societyName))
                    return (studentID + "was succesfully removed from " + societyName);
                else
                    return ("Failure to remove  " + studentID + " from " + societyName + ". Client is not a member of Society.");
            } else return ("Failure to remove  " + studentID + " from " + societyName + ". Client does not exist.");
        } else return ("Failure to remove  " + studentID + " from " + societyName + ". Society ID is incorrect.");
    }

    private ArrayList<String> getAssignedSocieties(int studentID) {
        //Check if student is registered with system
        if (clientInformation.containsKey(studentID)) return clientInformation.get(studentID);
        else {
            System.out.println("Failure get assigned societies for  " + studentID + ". Client does not exist.");
            return null;
        }
    }

    private static String isMember(int studentID, int societyID) {
        String societyName = adminTable.get(societyID);
        //Check if Society ID is a valid ID for a Society
        if (societyName != null) {
            //Check if student is registered with system
            if (clientInformation.containsKey(studentID)) {
                //Check if student is a member of society
                if (clientInformation.get(studentID).contains(societyName))
                    return ("Student " + studentID + " is a member of " + societyName);
                else
                    return ("Failure to remove  " + studentID + " from " + societyName + ". Client is not a member of Society.");
            } else return ("Failure to remove  " + studentID + " from " + societyName + ". Client does not exist.");
        } else return ("Failure to remove  " + studentID + " from " + societyName + ". Society ID is incorrect.");
    }
    
    private static String getSocieties(int studentID){
    	//Check if student is registered with system
        if (clientInformation.containsKey(studentID)) {
        	ArrayList<String> societiesOfStudent = clientInformation.get(studentID);
        	StringBuilder societyList = new StringBuilder();
        	for (String s : societiesOfStudent)
        		 societyList.append(s+"\n");
        	return ("Student " + studentID + " is a member of the following societies:/n" + societyList);
        	}
        else return ("Failure to check  " + studentID + " from database. Client does not exist.");
        }
}
