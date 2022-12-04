package lab5out_solution;

import java.io.Serializable;

public class GameOverData implements Serializable
{
	private String winLossRatio;
	private String playerIs;
	
	public String getPlayerIs() {
		return playerIs;
	}
	public void setPlayerIs(String playerIs) {
		this.playerIs = playerIs;
	}
	public void setWLRatio(String winLossRatio)
	{
		this.winLossRatio = winLossRatio;
	}
	public String getWLRatio()
	{
		return winLossRatio;
	}
}
