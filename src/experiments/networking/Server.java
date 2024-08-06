package experiments.networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import experiments.club.Algorithm;

public class Server {

	//should I have a fetch-request or a persistent connection architecture?
	public static void main(String[] args) throws Exception {
		//i'll do fetch-request for now
		
		//space/time optimizations for the algorithm come back later
		//optimizations include:
		//- rejecting new operations if there have been more than 100 operations after
		Algorithm a = new Algorithm();
		
		ServerSocket ss = new ServerSocket(5000);
		
		while(true) {
			Socket s = ss.accept();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			
		}
	}
	
}
