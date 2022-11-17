package lab5out_solution;

import java.util.List;
import java.util.Timer;

import javax.swing.JTextField;

public class GamePlayData {

	private String threeLetters;
	private Timer timer;
	private Boolean playerTurn;
	private List<Player> playerList;
	private String playerInput;
	
	
	public String getThreeLetters() {
		return threeLetters;
	}
	public void setThreeLetters(String threeLetters) {
		this.threeLetters = threeLetters;
	}
	public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public Boolean getPlayerTurn() {
		return playerTurn;
	}
	public void setPlayerTurn(Boolean playerTurn) {
		this.playerTurn = playerTurn;
	}
	public List<Player> getPlayerList() {
		return playerList;
	}
	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}
	public String getPlayerInput() {
		return playerInput;
	}
	public void setPlayerInput(String playerInput) {
		this.playerInput = playerInput;
	}

	
}
