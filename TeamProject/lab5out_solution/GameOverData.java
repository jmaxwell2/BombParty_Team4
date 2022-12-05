package lab5out_solution;

import java.io.Serializable;

public class GameOverData implements Serializable
{
	private Player loser;
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Player getLoser() {
		return loser;
	}

	public void setLoser(Player loser) {
		this.loser = loser;
	}

}
