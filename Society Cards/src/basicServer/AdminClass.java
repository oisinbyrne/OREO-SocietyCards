package basicServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AdminClass {

    static int societyCount = 0;
    private int societyID;
    private String societyName;

    public AdminClass(String societyName){
        societyCount++;
        this.societyName = societyName;
        societyID = societyCount;
        registerSociety();
    }

    private void sendMessage(int command, String arg){
        try {
            Socket toServer = new Socket(ServerClass.serverHost, 1234);
            PrintWriter out = new PrintWriter(toServer.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(toServer.getInputStream()));
            // Write the message to the socket.
            String message = command+"/"+societyID+"/"+arg;
//            System.out.println("Sent message: " + message);
            out.println(command+"/"+societyID+"/"+arg);
            System.out.println(in.readLine());
            out.close();
            toServer.close();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    private void registerSociety() {
        sendMessage(0, societyName);
    }

    public void addStudent(int studentID) {
        sendMessage(1, String.valueOf(studentID));
    }

    public void removeStudent(int studentID) {
        sendMessage(2, societyName);
    }

    public void checkMembership(int studentID){
        sendMessage(3, String.valueOf(studentID));
    }
}
