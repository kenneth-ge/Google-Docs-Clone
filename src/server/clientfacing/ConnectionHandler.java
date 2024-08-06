/*     */ package server.clientfacing;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.net.Socket;

import server.backend.BackendHandler;
import server.backend.datastructs.Operation;
import server.backend.util.GlobalState;
/*     */ 
/*     */ public class ConnectionHandler
/*     */   implements Runnable {
/*     */   Socket s;
/*     */   BackendHandler a;
/*     */   BufferedWriter bw;
/*     */   BufferedReader br;
/*     */   long lastSyncTime;
/*     */   
/*     */   public ConnectionHandler(Socket s, BackendHandler a) {
/*  22 */     this.s = s;
/*  23 */     this.a = a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     try {
/*  34 */       this.bw = new BufferedWriter(new OutputStreamWriter(this.s.getOutputStream()));
/*  35 */       this.br = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
/*     */       
/*  37 */       for (Operation o : this.a.getAllOperations()) {
/*  38 */         this.bw.append(o.toString());
/*  39 */         this.bw.append((char) 0);
/*     */         
/*  41 */         this.lastSyncTime = o.lastSyncTime;
/*     */       } 
/*  43 */       this.bw.append("end\000");
/*  44 */       flush();
/*     */       
/*  46 */       while (GlobalState.running) {
/*  47 */         String action = readUntilNull();
/*     */         
/*  49 */         Operation operation = new Operation(this.lastSyncTime, action);
/*     */         
/*  51 */         Operation[] newOps = this.a.update(operation);
/*     */         
/*  53 */         for (int i = 0; i < newOps.length - 1; i++) {
/*  54 */           Operation o = newOps[i];
/*     */           
/*  56 */           sendOperation(o);
/*     */           
/*  58 */           this.lastSyncTime = o.lastSyncTime;
/*     */         } 
/*     */         
/*  61 */         this.lastSyncTime = (newOps[newOps.length - 1]).lastSyncTime;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  67 */         flush();
/*     */       } 
/*     */       
/*  70 */       this.bw.close();
/*  71 */       this.br.close();
/*  72 */       this.s.close();
/*  73 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String readUntilNull() {
/*     */     try {
/*  80 */       int i = 0;
/*  81 */       StringBuffer sb = new StringBuffer();
/*     */       
/*  83 */       while ((i = this.br.read()) != 0) {
/*  84 */         sb.append((char)i);
/*     */       }
/*     */       
/*  87 */       return sb.toString();
/*  88 */     } catch (IOException e) {
/*  89 */       e.printStackTrace();
/*  90 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void sendOperation(Operation o) {
/*     */     try {
/*  96 */       this.bw.append(o.toString());
/*  97 */       this.bw.append((char) 0);
/*  98 */     } catch (IOException e) {
/*  99 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void flush() {
/*     */     try {
/* 105 */       this.bw.flush();
/* 106 */     } catch (IOException e) {
/* 107 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }
