package lab5out;

import javax.swing.*;

import ocsf.client.AbstractClient;

public class ChatClient extends AbstractClient {

	private JLabel status;
	private JTextArea serverMsg;
	private JTextField clientID;
	private String loginStatus = "";
	
	public ChatClient() {
		//constructor
		
		// call the base class - server name and port number
		super("localhost", 8300);
	}
	
	public void setStatus(JLabel status) {
		this.status = status;
	}
	
	public void setServerMsg(JTextArea serverMsg) {
		this.serverMsg = serverMsg;
	}
	
	public void setClientID(JTextField clientID) {
		this.clientID = clientID;
	}
	
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
		System.out.println("1. login status changed");
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	
	public void connectionEstablished() {
		//status.setText("Connected");
		//status.setForeground(Color.green);
	}
	
	//@Override
	protected void handleMessageFromServer(Object arg0) {
//		if (arg0.toString().substring(0,9).equals("username:")){
//			// assign the client-id 
//			clientID.setText(arg0.toString().substring(9));
//			serverMsg.append("Server: " + arg0.toString() + "\n");
//		}
//		else {
//			serverMsg.append("Server: " + arg0.toString() + "\n");
//		}
		
		loginStatus = arg0.toString();
		System.out.println("From server: " + loginStatus);
		
//		if (arg0.toString().equals("SUCCESSFULLY LOGGED IN")) {
//			//System.out.println("SUCCESSFULLY LOGGED IN");
//			loginStatus = arg0.toString();
//		}
//		if (arg0.toString().equals("USERNAME/PASSWORD IS INCORRECT")) {
//			//System.out.println("USERNAME/PASSWORD IS INCORRECT");
//			loginStatus = arg0.toString();
//		}
//		if (arg0.toString().equals("USER ALREADY EXISTS")) {
//			//System.out.println("USER ALREADY EXISTS");
//			loginStatus = arg0.toString();
//		}
//		if (arg0.toString().equals("PASSWORDS DO NOT MATCH")) {
//			//System.out.println("PASSWORDS DO NOT MATCH");
//			loginStatus = arg0.toString();
//		}
//		if (arg0.toString().equals("SUCCESSFULLY REGISTERED")) {
//			//System.out.println("SUCCESSFULLY REGISTERED");
//			loginStatus = arg0.toString();
//		}
	}
	
	public void connectionClosed() {
		//status.setText("Not Connected");
		//status.setForeground(Color.red);
	}

}
