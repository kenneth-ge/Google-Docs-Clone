package experiments.club;

import java.util.LinkedList;

public class Algorithm {
	
	public static LinkedList<Operation> ll = new LinkedList<>();
	
	public static void main(String[] args) {
		
		
	}
	
	public static LinkedList<Operation> add(Operation o) {
		var iter = ll.descendingIterator();
		
		LinkedList<Operation> inOrder = new LinkedList<>();
		
		while(iter.hasNext()) {
			Operation current = iter.next();
			
			//o.timestamp represents the last synchronized time
			//ignore operations that come at the same time for now
			if(current.timestamp < o.timestamp) {
				inOrder.add(0, current);
			}
		}
		
		int newIndex = o.index;
		
		for(Operation current: inOrder) {
			if(current.index < newIndex) {
				newIndex += current.text.length();
			}
		}
		
		//updated version of operation
		o.index = newIndex;
		
		//add to both the master list of operations
		//and also send to client that sent the operation
		ll.add(o);
		inOrder.add(o);
		
		return inOrder;
	}
	
	public static class Operation {
		public long timestamp;
		public String text;
		public int index;
	}
	
}
