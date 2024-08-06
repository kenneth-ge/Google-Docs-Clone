package experiments.tester;

import java.util.Scanner;

import experiments.algorithm.Algorithm;
import experiments.algorithm.operation.Operation;

public class AlgorithmTester {

	public static void main(String[] args) {
		Algorithm a = new Algorithm();
		
		Scanner sc = new Scanner(System.in);
		
		long[] lastSynced = new long[] {0, 0};
		StringBuffer[] s = new StringBuffer[] {new StringBuffer(), new StringBuffer()};
		
		System.out.println("input format: which_copy_of_the_document index text");
		System.out.println("Enter an operation");
		while(true) {
			int num = sc.nextInt();
			
			System.out.println(s[num].toString());
			
			Operation o = new Operation(lastSynced[num], sc.nextInt(), chopoffSpace(sc.nextLine()));
			
			var l = a.add(o);
			
			System.out.println(l);
			
			for(Operation o2: l) {
				s[num].insert(o2.index, o2.text);
			}
			
			System.out.println(s[num].toString());
			lastSynced[num] = l.getLast().time;
		}
	}
	
	public static String chopoffSpace(String s) {
		return s.substring(1, s.length());
	}
	
}
