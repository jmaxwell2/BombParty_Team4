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
	// Data fields for this server.
	private JTextArea log;
	private JLabel status;
	private boolean running = false;
	private Database database;
	private ArrayList<Player> playerList;
	private int turnIndex = 0;
	private int count = 0;
	private Integer chances;

	// Constructor for initializing the server with default settings.
	public ChatServer() {
		super(12345);
		this.setTimeout(500);
		database = new Database();
		playerList = new ArrayList<Player>();
		chances = 3;
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
		//System.out.println("msg from client of type: " + arg0.getClass());

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
				/*
				 * newPlayer.setPlayerID(arg1.getId());
				 * 
				 * Long playerID = arg1.getId(); StartGameData startData = (StartGameData) arg0;
				 * startData.setWLRatio(newPlayer.getWinLossRatio());
				 * 
				 * // Send the win/loss ratio to the client. try { arg1.sendToClient(data); }
				 * catch (IOException e) { return; }
				 */
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

		// the input word is sent to be verified within a GamePlayData object
		else if (arg0 instanceof GameTurnData) {
			GameTurnData gtData = (GameTurnData) arg0;
			Object result;

			// verify the word
			result = database.verifyInputWord(gtData.getThreeLetters(), gtData.getPlayerInput());
			
			// if the result is FALSE, then send message back to client to display a wrong word error label
			if ((Boolean)result == false) {
				TryAgainData taData = new TryAgainData();
				
				if (this.chances > 1) {
					taData.setChances(--this.chances); // decrement the chances on the current turn
				
					try {
						arg1.sendToClient(taData);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					// current player loses a heart
					for (int i = 0; i < playerList.size(); i++) {
						// get the current player whose turn it is
						if (playerList.get(i).getTurn() == true)
							
							// check if they lost the game (lost all their hearts)
							if (playerList.get(i).getNumOfHearts() == 1)
							{
								GameOverData goData = new GameOverData();
								
								// set the lose of the game
								goData.setLoser(playerList.get(i));
								
								//Increment wins and losses of players
								if (playerList.get(0).equals(playerList.get(i))) {
									database.incrementLosses(playerList.get(0).getUsername());
									database.incrementWins(playerList.get(1).getUsername());
								} else {
									database.incrementWins(playerList.get(0).getUsername());
									database.incrementLosses(playerList.get(1).getUsername());
								}
								
								
								// send the game over data to the clients
								sendToAllClients(goData);
							}
							else	
								playerList.get(i).decrementHearts();
					}
					
					startTurn(); // player lost chances! another player's turn!
				}
			}
			// if the result is TRUE, then start new turn!
			if ((Boolean)result == true)
				startTurn();
			
		}

		// start the game!
		else if (arg0 instanceof StartGameData) {
			this.sendToAllClients(playerList);
		}
		
		// if player data is sent to server, then this is simply representing the player that
		// has decided to NOT play again
		else if (arg0 instanceof Player) {
			Player quittingPlayer = (Player) arg0;
			
			// remove this player from the playerList
			for (int i = 0; i < playerList.size(); i++)
			{
				if (playerList.get(i).getUsername().equals(quittingPlayer.getUsername()))
					playerList.remove(i);
			}
			
			// updates the game lobby player list
			this.sendToAllClients(playerList);
		}

		// the game needs to start!
		else if (arg0 instanceof String) {
			String msg = (String) arg0;

			if (msg.equals("StartTurn")) {
				startTurn();

			} else if (msg.equals("Check Players")) {
				if (playerList.size() > 1) {
					this.sendToAllClients("Start Game");
				} else {
					try {
						arg1.sendToClient("Move to GameLobby");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			else if (msg.equals("PlayAgain")) {
				//update the hearts of each player
				for (int i = 0; i < playerList.size(); i++) {
					playerList.get(i).setNumOfHearts(3);
				}
				
				startTurn();
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
	
	public void startTurn() {				
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
		turnData.setTurnString("It's " + playerList.get(turnIndex).getUsername() + "'s turn!");
		
		// initialize the chances to 3
		this.chances = 3;
		turnData.setChances(3);

		// set the list of players that are still in the game
		turnData.setPlayerList(playerList);

		// grab the random three letters from the database
		String threeLetters = database.getThreeLettersFromDatabase();
		turnData.setThreeLetters(threeLetters);

		// send the new turn's data to all the clients
		this.sendToAllClients(turnData);
	}
}
