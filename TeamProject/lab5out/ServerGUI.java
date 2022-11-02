package lab5out;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ServerGUI extends JFrame {

	  private JLabel status;
	  
	  private String[] labels = {"Port #", "Timeout"};
	  private JTextField[] textFields = new JTextField[labels.length];
	  
	  private JLabel logLabel;
	  private JTextArea log;
	  private JScrollPane scrollPane;
	  
	  private JButton listen;
	  private JButton close;
	  private JButton stop;
	  private JButton quit;
	  
	  private Boolean listening;
	  
	  private ChatServer server;
	
	  
	  // constructor
	  public ServerGUI(String title)
	  {		    
	    listening = false;
	    
	    // Set the title of the GUI based on the command line argument 
	    this.setTitle(title);
	
	    //Set Default Closing Operation
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    
	    
	    // instantiate the chatServer
	 	server = new ChatServer();
	 	server.setPort(8300);
	 	server.setTimeout(500);
	 	
	 	
	 	// create the different panels
	    JPanel north = new JPanel();
	    JPanel center = new JPanel();
	    JPanel south = new JPanel();


	    
	    // create the status label
	    status = new JLabel("Not Connected",JLabel.CENTER);
	    server.setStatus(status);
	    status.setForeground(Color.red);
	    
	    // add the label to a north jpannel
	    // and then add north panel to BorderLayout/(Frame?)
	    north.add(status);
	    
	    // center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
	    
	    // create the three labels and cooresponding text fields
	    // add them to the center panel 
	    JLabel tempLabel;
	    JTextField tempTextField;
	    for (int j = 0; j < labels.length; j++) {
	    	tempLabel = new JLabel(labels[j], JLabel.CENTER);
	    	tempTextField = new JTextField(10);
	    	
	    	// save the textField object into the array
	    	textFields[j] = tempTextField;
	    	
	    	// add to the center panel
	    	center.add(tempLabel);
	    	center.add(tempTextField);
	    }
	    
	    // create the jtextareas and corresponding labels
	    // add them to the center panel
	    logLabel = new JLabel("Server Log Below:", JLabel.CENTER);
	    log = new JTextArea(10,33);
	    server.setLog(log);
	    scrollPane = new JScrollPane(log); 
	    center.add(logLabel);
		center.add(scrollPane);
		
	    
	    // create three buttons
	    listen = new JButton("Listen");
	    listen.addActionListener(new EventHandler(listen.getText()));
	    close = new JButton("Close");
	    close.addActionListener(new EventHandler(close.getText()));
	    stop = new JButton("Stop");
	    stop.addActionListener(new EventHandler(stop.getText()));
	    quit = new JButton("Quit");
	    quit.addActionListener(new EventHandler(quit.getText()));

	    
	    // add the buttons to a south jpannel
	    // and then add south panel to BorderLayout/(Frame?)
	    south.add(listen);
	    south.add(close);
	    south.add(stop);
	    south.add(quit);
	    
	    
	    add(north, BorderLayout.NORTH);
	    add(center, BorderLayout.CENTER);
	    add(south, BorderLayout.SOUTH);
	
	    
	    this.setSize(400, 400);
	    this.setVisible(true);
	    
	  }
	  
	  public static void main(String[] args)
	  {
	    //new ServerGUI(args[0]); //args[0] represents the title of the GUI
		  new ServerGUI("Server");
	  }
	
	
		private class EventHandler implements ActionListener{
			
			String text;
			
			public EventHandler (String t) {
				text = t;
			}
		
			public void actionPerformed(ActionEvent e) {
				if(text == "Stop") {
					if (listening == false) {
						log.append("Server not currently started \n");
					}
					else {
						server.stopListening();
					}
				}
				else if (text == "Close") {
					if (listening == false) {
						log.append("Server not currently started \n");
					}
					else {
						try {
							server.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				else if (text == "Listen") {
					listening = true;
					if (textFields[0].getText().equals("") || textFields[1].getText().equals("")) {
						// System.out.print("Port number/timeout not entered before pressing listen()\n");
						log.append("Port number/timeout not entered before pressing Listen) \n");
					}
					else {
						try {
							server.listen();
							server.serverStarted();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				else {
					// Quit is clicked
					dispose();
				}
			}
			
		}
}
