package experiments.algorithm.datastructures;

public class LinkedList<T> {

	public Node<T> start, end;
	
	public LinkedList() {
		start = null;
		end = null;
	}
	
	//aka append
	public void add(T t) {
		Node<T> n = new Node<>();
		n.element = t;
		n.prev = start;
		n.next = null;
		
		if(start == null) {
			start = n;
		}else{
			start.next = n;
		}
		
		end = n;
	}
	
	public Node<T> create(T t){
		Node<T> n = new Node<>();
		n.element = t;
		
		return n;
	}
	
	public static class Node<T> {
		public T element;
		public Node<T> prev, next;
	}
	
}
