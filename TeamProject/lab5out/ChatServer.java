package lab5out;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Hashtable;

public class ChatServer extends AbstractServer
{
  private JTextArea log;
  private JLabel status;
  private DatabaseFile database;
  private Hashtable data;
  
  
  public ChatServer()
  {
    super(12345);
  }
  
  public ChatServer(int port)
  {
    super(port);
  }
  
  public void setLog(JTextArea log)
  {
    this.log = log;
  }
  
  public JTextArea getLog()
  {
    return log;
  }
  
  public void setStatus(JLabel status)
  {
    this.status = status;
  }
  
  public JLabel getStatus()
  {
    return status;
  }
  
  
  
  @Override
  protected void handleMessageFromClient(Object arg0, ConnectionToClient arg1)
  {
    // TODO Auto-generated method stub
    //System.out.println("Message from Client" + arg0.toString() + arg1.toString());
    //log.append("Message from Client" + arg0.toString() + arg1.toString() + "\n");
    
    if (arg0 instanceof LoginData)
    {
       LoginData loginData = (LoginData)arg0;
       System.out.println("Username/Password :" + loginData.getUsername() + " " + loginData.getPassword());
       
       // verify that the username is unique
       data = database.getData();
       
       if (!data.containsKey(loginData.getUsername())) {
    	   // user does not exist!
    	   try {
				arg1.sendToClient("USERNAME/PASSWORD IS INCORRECT");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
       else {
    	   // check if the username and password match up with the files
    	   if (!data.get(loginData.getUsername()).equals(loginData.getPassword())) {
	    	   try {
					arg1.sendToClient("USERNAME/PASSWORD IS INCORRECT");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	   }
    	   else {    		   
    		   try {
				arg1.sendToClient("SUCCESSFULLY LOGGED IN");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	   }
    	   
       }

    }
    
    //MESSAGE FROM CREATE ACCOUNT
	if (arg0 instanceof CreateAccountData)
	{
	   CreateAccountData caData = (CreateAccountData)arg0;
	   System.out.println("Username/Password1/Password2: " + caData.getUsername() + " " + caData.getPassword1() + " " + caData.getPassword2());
	
	   // verify that the username is unique
       data = database.getData();
       
       if (data.containsKey(caData.getUsername())) {
    	   // username is already in use!    	   
    	   try {
				arg1.sendToClient("USER ALREADY EXISTS");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
       else if (!caData.getPassword1().equals(caData.getPassword2())) {
    	   // passwords must be the same!    	   
    	   try {
				arg1.sendToClient("PASSWORDS DO NOT MATCH");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	   
       }
       else {
    	   // send the client to the contacts page    	   
    	   try {
				arg1.sendToClient("SUCCESSFULLY REGISTERED");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

       }
	}
  }
  
  protected void listeningException(Throwable exception) 
  {
    //Display info about the exception
    System.out.println("Listening Exception:" + exception);
    exception.printStackTrace();
    System.out.println(exception.getMessage());
    
    if (this.isListening())
    {
      log.append("Server not Listening\n");
      status.setText("Not Connected");
      status.setForeground(Color.RED);
    }
    
  }
  
  protected void serverStarted() 
  {
    System.out.println("Server Started");
    log.append("Server Started\n");
    
    //sets the JLabel status to Listening and is colored green
 	status.setText("Listening");
 	status.setForeground(Color.green);
    
    // create the database
    database = new DatabaseFile();
  }
  
  protected void serverStopped() 
  {
    System.out.println("Server Stopped");
    log.append("Server Stopped Accepting New Clients - Press Listen to Start Accepting New Clients\n");
    
    // sets the JLabel status to Stopped and is colored red
 	status.setText("Stopped");
 	status.setForeground(Color.red);
  }
  
  protected void serverClosed() 
  {
    System.out.println("Server and all current clients are closed - Press Listen to Restart");
    log.append("Server and all current clients are closed - Press Listen to Restart\n");
    
    // sets the JLabel status to Closed and is colored red
 	status.setText("Closed");
 	status.setForeground(Color.red);  
  }

  
  protected void clientConnected(ConnectionToClient client) 
  {
    System.out.println("Client Connected");

//    Object msg;
//	msg = "username:" + "client" + client.getId();
//	
//	try {
//		client.sendToClient(msg);
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	
	log.append("Client " + client.getId() + " Connected\n");
  }
}
