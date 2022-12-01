package lab5out_solution;

import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.Timer;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class ChatServer extends AbstractServer {
	// Data fields for this chat server.
	private JTextArea log;
	private JLabel status;
	private boolean running = false;
	private Database database;
	private ArrayList<Player> playerList;
	private int turnIndex = 0;
	private int count = 0;

	// Constructor for initializing the server with default settings.
	public ChatServer() {
		super(12345);
		this.setTimeout(500);
		database = new Database();
		playerList = new ArrayList<Player>();
	}

	void setDatabase(Database database) {
		this.database = database;
	}

	// Getter that returns whether the server is currently running.
	public boolean isRunning() {
		return running;
	}

	// Setters for the data fields corresponding to the GUI elements.
	public void setLog(JTextArea log) {
		this.log = log;
	}

	public void setStatus(JLabel status) {
		this.status = status;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	// When the server starts, update the GUI.
	public void serverStarted() {
		running = true;
		status.setText("Listening");
		status.setForeground(Color.GREEN);
		log.append("Server started\n");
	}

	// When the server stops listening, update the GUI.
	public void serverStopped() {
		status.setText("Stopped");
		status.setForeground(Color.RED);
		log.append("Server stopped accepting new clients - press Listen to start accepting new clients\n");
	}

	// When the server closes completely, update the GUI.
	public void serverClosed() {
		running = false;
		status.setText("Close");
		status.setForeground(Color.RED);
		log.append("Server and all current clients are closed - press Listen to restart\n");
	}

	// When a client connects or disconnects, display a message in the log.
	public void clientConnected(ConnectionToClient client) {
		log.append("Client " + client.getId() + " connected\n");
	}

	// When a message is received from a client, handle it.
	public void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
		
		// If we received LoginData, verify the account information.
		if (arg0 instanceof LoginData) {
			// Check the username and password with the database.
			LoginData data = (LoginData) arg0;
			Object result;
			if (database.verifyAccount(data.getUsername(), data.getPassword())) {
				result = "LoginSuccessful";
				log.append("Client " + arg1.getId() + " successfully logged in as " + data.getUsername() + "\n");
				
				// fetch player info and add it to the playerList
				Player newPlayer = database.getPlayerDatabaseData(data.getUsername());
				/*newPlayer.setPlayerID(arg1.getId());
				
				Long playerID = arg1.getId();
				StartGameData startData = (StartGameData) arg0;
				startData.setWLRatio(newPlayer.getWinLossRatio());
								
				// Send the win/loss ratio to the client.
				try {
					arg1.sendToClient(data);
				} catch (IOException e) {
					return;
				}*/
				// add the new player to the playerList
				playerList.add(newPlayer);
								
				// update the turnIdex variable because whenever the game starts,
				// it needs to be the size of the playerList
				turnIndex = playerList.size() - 1;
				
				// send the player data to the client
				try {
					arg1.sendToClient(newPlayer);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				result = new Error("The username and password are incorrect.", "Login");
				log.append("Client " + arg1.getId() + " failed to log in\n");
			}

			// Send the result to the client.
			try {
				arg1.sendToClient(result);
			} catch (IOException e) {
				return;
			}
		}

		// If we received CreateAccountData, create a new account.
		else if (arg0 instanceof CreateAccountData) {
			// Try to create the account.
			CreateAccountData data = (CreateAccountData) arg0;
			Object result;
			if (database.createNewAccount(data.getUsername(), data.getPassword())) {
				result = "CreateAccountSuccessful";
				log.append("Client " + arg1.getId() + " created a new account called " + data.getUsername() + "\n");
			} else {
				result = new Error("The username is already in use.", "CreateAccount");
				log.append("Client " + arg1.getId() + " failed to create a new account\n");
			}

			// Send the result to the client.
			try {
				arg1.sendToClient(result);
			} catch (IOException e) {
				return;
			}
		}
		
		else if (arg0 instanceof StartGameData) {
			this.sendToAllClients(playerList);
		}
		
		// the game needs to start!
		else if (arg0 instanceof String)
		{
			String msg = (String) arg0;
			
			if (msg.equals("StartTurn"))
			{
				System.out.println("StartTurn invoked in server");
				GameTurnData turnData = new GameTurnData();
				
				// update the previous turn's player false
				playerList.get(turnIndex).setTurn(false);
				
				// update the next player's turn
				count++;
				turnIndex = (count % playerList.size());
				playerList.get(turnIndex).setTurn(true);
				
				// set the turn on the turnData
				turnData.setTheirTurn(playerList.get(turnIndex));
				
				// set the turn text label
				turnData.setTurnString("It's " + playerList.get(turnIndex) + "'s turn!");
				//System.out.println("It's " + playerList.get(turnIndex).toString() + "'s turn!");
				
				// set the list of players that are still in the game
				turnData.setPlayerList(playerList);
				
				// grab the random three letters from the database
				String threeLetters = database.getThreeLettersFromDatabase();
				turnData.setThreeLetters(threeLetters);
				//System.out.println(threeLetters);
				
				// update the countdown object
//				Timer t = new Timer();
//				t.schedule(null, 15000);
//				turnData.setTimer(t);
								
				// send the new turn's data to all the clients
				this.sendToAllClients(turnData);
				
			}
		}
	}

	// Method that handles listening exceptions by displaying exception information.
	public void listeningException(Throwable exception) {
		running = false;
		status.setText("Exception occurred while listening");
		status.setForeground(Color.RED);
		log.append("Listening exception: " + exception.getMessage() + "\n");
		log.append("Press Listen to restart server\n");
	}
}
