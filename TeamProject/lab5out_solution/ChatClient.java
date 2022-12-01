package lab5out_solution;

import java.io.IOException;
import java.util.*;

import ocsf.client.AbstractClient;

public class ChatClient extends AbstractClient
{
  // Private data fields for storing the GUI controllers.
  private LoginControl loginControl;
  private GamePlayController gamePlayController;
  private CreateAccountControl createAccountControl;
  private StartGameControl startGameControl;
  private GameLobbyControl gameLobbyControl;
  private Player playerData;

  // Setters for the GUI controllers.
  public void setLoginControl(LoginControl loginControl)
  {
    this.loginControl = loginControl;
  }
  public void setCreateAccountControl(CreateAccountControl createAccountControl)
  {
    this.createAccountControl = createAccountControl;
  }
  public void setGamePlayController(GamePlayController gamePlayController)
  {
    this.gamePlayController = gamePlayController;
  }
  public void setStartGameControl(StartGameControl sc) 
  {
	  this.startGameControl = sc;
  }
  public void setGameLobbyControl(GameLobbyControl glc)
  {
	  this.gameLobbyControl = glc;
  }
  public Player getPlayerData() {
	  return playerData;
  }

  // Constructor for initializing the client with default settings.
  public ChatClient()
  {
    super("localhost", 8300);
  }
  
  // Method that handles messages from the server.
  public void handleMessageFromServer(Object arg0)
  {
	  //System.out.println("msg from server of type: " + arg0.getClass());
	  
    // If we received a String, figure out what this event is.
    if (arg0 instanceof String)
    {
      // Get the text of the message.
      String message = (String)arg0;
      
      // If we successfully logged in, tell the login controller.
      if (message.equals("LoginSuccessful"))
      {
    	  //startGameControl.changeWinLossRatio(playerData);
    	  loginControl.loginSuccess();
      }
      
      // If we successfully created an account, tell the create account controller.
      else if (message.equals("CreateAccountSuccessful"))
      {
        createAccountControl.createAccountSuccess();
      }
      
      //If lobby is full, move into the GamePlay panel
      else if (message.equals("Start Game"))
      {
    	  gameLobbyControl.moveToGamePlay();
    	  
    	  Object msg = "StartTurn";

			try {
				this.sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      }
      
      //If lobby not full, move into GameLobby panel
      else if (message.equals("Move to GameLobby"))
      {
    	  startGameControl.moveToLobby();
      }
    }
    /*
    else if (arg0 instanceof StartGameData) 
    {
    	StartGameData data = (StartGameData) arg0;
    	startGameControl.changeWinLossRatio(data.getWLRatio());
    }*/
    
    // If we received an Error, figure out where to display it.
    else if (arg0 instanceof Error)
    {
      // Get the Error object.
      Error error = (Error)arg0;
      
      // Display login errors using the login controller.
      if (error.getType().equals("Login"))
      {
        loginControl.displayError(error.getMessage());
      }
      
      // Display account creation errors using the create account controller.
      else if (error.getType().equals("CreateAccount"))
      {
        createAccountControl.displayError(error.getMessage());
      }
    }
    
    // if we get a Player object from the server, the server is telling us which player
    // is associated with THIS client object
    else if (arg0 instanceof Player)
    {
    	playerData = (Player) arg0;
    }
    
    // if we get a GameTurnData object from the server... means a new turn!
    else if (arg0 instanceof GameTurnData) 
    {
    	GameTurnData gtd = (GameTurnData) arg0;
    	
    	// call a function in GamePlayController that updates panel based off of new turn data
    	gamePlayController.setNewTurnData(gtd);
    }
    
    else if (arg0 instanceof ArrayList<?>) 
    {
    	ArrayList<Player> playerList = (ArrayList<Player>) arg0;
    	gameLobbyControl.updatePlayerList(playerList);
    }
    	
  }  
}
