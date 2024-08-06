package experiments.algorithm.operation;

public class Operation {

	public long time;
	public int index;
	public String text;
	
	public Operation(long time, int index, String text) {
		this.time = time;
		this.index = index;
		this.text = text;
	}
	
	@Override
	public String toString() {
		return "{" + time + ", " + index + ", " + text + "}";
	}
	
}
