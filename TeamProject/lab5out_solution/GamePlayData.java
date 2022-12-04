package lab5out_solution;

import java.io.Serializable;
import java.util.*;
import java.util.Timer;

import javax.swing.JTextField;

public class GamePlayData implements Serializable {

	private String threeLetters;
	private String playerInput;
	private Integer chances;
	
	
	public Integer getChances() {
		return chances;
	}
	public void setChances(Integer chances) {
		this.chances = chances;
	}
	
	public String getThreeLetters() {
		return threeLetters;
	}
	public void setThreeLetters(String threeLetters) {
		this.threeLetters = threeLetters;
	}

	public String getPlayerInput() {
		return playerInput;
	}
	public void setPlayerInput(String playerInput) {
		this.playerInput = playerInput;
	}

	
}
