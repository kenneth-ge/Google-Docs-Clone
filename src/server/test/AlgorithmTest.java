/*    */ package server.test;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Scanner;

import server.backend.Algorithm;
import server.backend.FileHandler;
import server.backend.datastructs.Operation;
import server.implementation.ConsoleFileHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AlgorithmTest
/*    */ {
/*    */   public static void main(String[] args) throws Exception {
/* 19 */     Algorithm algorithm = new Algorithm((FileHandler)new ConsoleFileHandler());
/*    */     
/* 21 */     Scanner sc = new Scanner(System.in);
/* 22 */     String[] str = { "", "" };
/* 23 */     long[] lastSyncTime = new long[2];
/*    */     
/* 25 */     System.out.println("Document, operation, index, text/deltachars"); while (true) {
/*    */       String text; int numChars;
/* 27 */       System.out.println("----------");
/*    */ 
/*    */       
/* 30 */       int documentIdx = sc.nextInt();
/*    */       
/* 32 */       int operation = sc.nextInt();
/* 33 */       int index = sc.nextInt();
/*    */       
/* 35 */       Operation o = null;
/*    */       
/* 37 */       switch (operation) {
/*    */         case 0:
/* 39 */           text = sc.nextLine();
/* 40 */           text = text.substring(1, text.length());
/*    */           
/* 42 */           o = new Operation(lastSyncTime[documentIdx], text.length(), index, text);
/*    */           break;
/*    */         
/*    */         case 1:
/* 46 */           numChars = sc.nextInt();
/*    */           
/* 48 */           o = new Operation(lastSyncTime[documentIdx], -numChars, index, null);
/*    */           break;
/*    */         
/*    */         default:
/* 52 */           System.out.println("Sorry couldn't do that");
/*    */           break;
/*    */       } 
/* 55 */       Operation[] ops = algorithm.update(o);
/*    */       
/* 57 */       System.out.println("Ops: " + Arrays.deepToString((Object[])ops)); byte b; int i;
/*    */       Operation[] arrayOfOperation1;
/* 59 */       for (i = (arrayOfOperation1 = ops).length, b = 0; b < i; ) { Operation update = arrayOfOperation1[b];
/* 60 */         System.out.println(update);
/*    */         
/* 62 */         if (update.deltaChars < 0) {
/* 63 */           str[documentIdx] = String.valueOf(str[documentIdx].substring(0, update.pos - update.deltaChars)) + str[documentIdx].substring(update.pos);
/*    */         } else {
/* 65 */           str[documentIdx] = String.valueOf(str[documentIdx].substring(0, update.pos)) + update.text + str[documentIdx].substring(update.pos);
/*    */         } 
/*    */         
/* 68 */         lastSyncTime[documentIdx] = update.lastSyncTime;
/*    */         b++; }
/*    */       
/* 71 */       System.out.println("Complete Document Text:");
/* 72 */       System.out.println(algorithm.getContent());
/* 73 */       System.out.println("Document 1: ");
/* 74 */       System.out.println(String.valueOf(str[0].length()) + ": " + str[0]);
/* 75 */       System.out.println("Document 2: ");
/* 76 */       System.out.println(String.valueOf(str[1].length()) + ": " + str[1]);
/*    */     } 
/*    */   }
/*    */ }
