/*    */ package server.clientfacing;
/*    */ 
/*    */ import java.net.DatagramSocket;
/*    */ import java.net.InetAddress;
/*    */ import java.net.ServerSocket;
/*    */ import java.net.Socket;
/*    */ import java.util.Scanner;

import server.backend.Algorithm;
import server.backend.BackendHandler;
import server.backend.FileHandler;
/*    */ import server.backend.util.GlobalState;
import server.implementation.DefaultFileHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Server
/*    */ {
/*    */   static Algorithm algorithm;
/*    */   static FileHandler fh;
/*    */   static Scanner sc;
/*    */   
/*    */   public static void main(String[] args) throws Exception {
/* 36 */     sc = new Scanner(System.in);
/*    */     
/* 38 */     System.out.println("Enter a file name: ");
/* 39 */     fh = (FileHandler)new DefaultFileHandler(sc.nextLine());
/* 40 */     algorithm = new Algorithm(fh);
/*    */     
/* 42 */     BackendHandler backendHandler = new BackendHandler(algorithm);
/*    */     
/* 44 */     (new Thread(Server::handleInput)).start();
/*    */     
/* 46 */     ServerSocket socket = new ServerSocket(4002);
/* 47 */     while (GlobalState.running) {
/* 48 */       Socket client = socket.accept();
/*    */       
/* 50 */       Thread t = new Thread(new ConnectionHandler(client, backendHandler));
/* 51 */       t.start();
/*    */     } 
/*    */     
/* 54 */     socket.close();
/*    */   }
/*    */   
/*    */   public static void handleInput() {
/*    */     try {
/* 59 */       Exception exception1 = null, exception2 = null; try { DatagramSocket socket = new DatagramSocket(); 
/* 60 */         try { socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
/* 61 */           String ip = socket.getLocalAddress().getHostAddress();
/*    */           
/* 63 */           System.out.println("IP address: " + ip); }
/* 64 */         finally { if (socket != null) socket.close();  }  } finally { exception2 = null; if (exception1 == null) { exception1 = exception2; } else if (exception1 != exception2) { exception1.addSuppressed(exception2); }
/*    */          }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 77 */       sc.close();
/* 78 */     } catch (Exception e) {
/* 79 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }

