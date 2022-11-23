package lab5out_solution;

import java.io.Serializable;

public class GameOverData implements Serializable
{
	private String winLossRatio;
	
	public void setWLRatio(String winLossRatio)
	{
		this.winLossRatio = winLossRatio;
	}
	public String getWLRatio()
	{
		return winLossRatio;
	}
}
