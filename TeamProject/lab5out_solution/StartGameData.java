package lab5out_solution;

import java.io.Serializable;

public class StartGameData implements Serializable
{
	private String winLossRatio;
	public StartGameData(String winLossRatio)
	{
		setWLRatio(winLossRatio);
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
