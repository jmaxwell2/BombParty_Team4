package lab5out_solution;

import java.io.Serializable;
import java.util.*;
import java.util.Timer;

import javax.swing.JTextField;

public class GamePlayData implements Serializable {

	private String threeLetters;
//	private Timer timer;
//	private Boolean playerTurn;
//	private ArrayList<Player> playerList;
	private String playerInput;
	
	
	public String getThreeLetters() {
		return threeLetters;
	}
	public void setThreeLetters(String threeLetters) {
		this.threeLetters = threeLetters;
	}
//	public Timer getTimer() {
//		return timer;
//	}
//	public void setTimer(Timer timer) {
//		this.timer = timer;
//	}
//	public Boolean getPlayerTurn() {
//		return playerTurn;
//	}
//	public void setPlayerTurn(Boolean playerTurn) {
//		this.playerTurn = playerTurn;
//	}
//	public List<Player> getPlayerList() {
//		return playerList;
//	}
//	public void setPlayerList(ArrayList<Player> playerList) {
//		this.playerList = playerList;
//	}
	public String getPlayerInput() {
		return playerInput;
	}
	public void setPlayerInput(String playerInput) {
		this.playerInput = playerInput;
	}

	
}
