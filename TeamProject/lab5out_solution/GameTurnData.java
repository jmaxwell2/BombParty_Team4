package lab5out_solution;

import java.util.ArrayList;
import java.util.Timer;

public class GameTurnData {

	private ArrayList<Player> playerList;
	private Player theirTurn;
	private String threeLetters;
	private Timer timer;
	
	
	public ArrayList<Player> getPlayerList() {
		return playerList;
	}
	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}
	public Player getTheirTurn() {
		return theirTurn;
	}
	public void setTheirTurn(Player theirTurn) {
		this.theirTurn = theirTurn;
	}
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
}
