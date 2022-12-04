package lab5out_solution;

import java.io.Serializable;

public class TryAgainData implements Serializable {

	private Integer chances;
	private String msg = "Try A New Word";

	public Integer getChances() {
		return chances;
	}

	public void setChances(int chances) {
		this.chances = chances;
	}
	
	public String getMsg() {
		return this.msg;
	}
	
	
}
