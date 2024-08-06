package experiments.algorithm;

import java.util.LinkedList;

import experiments.algorithm.operation.Operation;

public class Algorithm {
	
	public LinkedList<Operation> ll = new LinkedList<>();
	
	public Algorithm() {
		
	}
	
	public LinkedList<Operation> add(Operation o) {
		var iter = ll.descendingIterator();
		LinkedList<Operation> inOrder = new LinkedList<>();
		
		while(iter.hasNext()) {
			Operation current = iter.next();
			
			//o.timestamp represents the last synchronized time
			//ignore operations that come at the same time for now
			if(current.time > o.time) {
				inOrder.add(0, current);
			}else{
				break;
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
		o.time = System.currentTimeMillis();
		
		//add to both the master list of operations
		//and also send to client that sent the operation
		ll.add(o);
		
		System.out.println(ll);
		inOrder.add(o);
		
		return inOrder;
	}
	
}
