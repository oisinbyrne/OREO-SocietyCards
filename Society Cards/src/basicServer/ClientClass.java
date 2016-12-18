package basicServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientClass {
    private String name;
    private int studentNumber;
    static String serverHost = "localhost";
    static int serverPort = 1234;

	public ClientClass(String name, int studentNumber){
        this.name = name;
        this.studentNumber = studentNumber;
    }

	public void sendStudentNumber(int command, String arg) {
		try {
            Socket toServer = new Socket(serverHost, serverPort);
            PrintWriter out = new PrintWriter(toServer.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(toServer.getInputStream()));
            // Write the message to the socket.
            out.println(command+"/"+arg);
            System.out.println(in.readLine());
            out.close();
            toServer.close();
        } catch (Exception se) {
            se.printStackTrace();
        }
	}
		
		public void checkStudentMembership(){
	        sendStudentNumber(4, String.valueOf(studentNumber));
		}
	}
