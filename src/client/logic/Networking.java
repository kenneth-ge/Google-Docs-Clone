/*     */ package client.logic;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.net.InetAddress;
/*     */ import java.net.Socket;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.concurrent.ArrayBlockingQueue;
/*     */ import java.util.concurrent.BlockingQueue;

/*     */ import org.ahmadsoft.ropes.Rope;

import client.ui.Main;
import client.ui.MiniOperation;
import client.ui.util.Utils;
/*     */ 
/*     */ public class Networking {
/*     */   public boolean running = true;
/*     */   Main main;
/*     */   String ipString;
/*     */   InetAddress ip;
/*     */   Socket s;
/*     */   public BlockingQueue<MiniOperation> in;
/*     */   BufferedWriter bw;
/*     */   BufferedReader br;
/*     */   
/*     */   public Networking(Main main, String ip) {
/*  29 */     this.main = main;
/*  30 */     this.ipString = ip;
/*     */     
/*     */     try {
/*  33 */       this.ip = InetAddress.getByName(this.ipString);
/*  34 */     } catch (UnknownHostException e) {
/*  35 */       e.printStackTrace();
/*  36 */       main.hostNotFound();
/*     */     } 
/*     */     
/*  39 */     this.in = new ArrayBlockingQueue<>(1000);
/*     */     
/*     */     try {
/*  42 */       this.s = new Socket(ip, 4002);
/*  43 */       this.s.setSoTimeout(100);
/*     */       
/*  45 */       this.br = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
/*  46 */       this.bw = new BufferedWriter(new OutputStreamWriter(this.s.getOutputStream()));
/*     */ 
/*     */       
/*  49 */       firstTimeLoad();
/*     */       
/*  51 */       main.loadTextUI();
/*     */       
/*  53 */       (new Thread(this::send)).start();
/*  54 */       (new Thread(this::receive)).start();
/*  55 */     } catch (Exception e) {
/*  56 */       e.printStackTrace();
/*  57 */       main.hostNotFound();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void send() {
/*  67 */     while (this.running) {
/*     */       try {
/*  69 */         MiniOperation m = this.in.take();
/*     */         
/*  71 */         Utils.sleep();
/*     */         
/*  73 */         while (!this.in.isEmpty() && m.combine(this.in.element())) {
/*  74 */           this.in.remove();
/*     */         }
/*     */         
/*  77 */         send(m);
/*  78 */       } catch (InterruptedException e) {
/*  79 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void receive() {
/*  85 */     while (this.running) {
/*  86 */       String line = null;
/*     */       
/*     */       try {
/*  89 */         line = readUntilNull();
/*  90 */       } catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */       
/*  94 */       if (line == null) {
/*     */         
/*  96 */         send(new MiniOperation(0, 0, ""));
/*  97 */         Utils.sleep();
/*     */         
/*     */         continue;
/*     */       } 
/* 101 */       Operation o = new Operation(line);
/*     */       
/* 103 */       receive(o);
/*     */     } 
/*     */   }
/*     */   
/*     */   void send(MiniOperation o) {
/*     */     try {
/* 109 */       this.bw.append(o.toString());
/* 110 */       this.bw.append((char) 0);
/* 111 */       this.bw.flush();
/* 112 */     } catch (IOException e) {
/* 113 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   void receive(Operation o) {
/* 118 */     this.main.updateText(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void firstTimeLoad() throws IOException {
/* 123 */     Rope rope = Rope.BUILDER.build("");
/*     */     String line;
/* 125 */     while ((line = readUntilNull()) != null && 
/* 126 */       !line.equals("end")) {
/*     */ 
/*     */ 
/*     */       
/* 130 */       Operation operation = new Operation(line);
/*     */       
/* 132 */       if (operation.deltaChars >= 0) {
/* 133 */         rope = rope.insert(operation.pos, operation.text); continue;
/*     */       } 
/* 135 */       int start = operation.pos + operation.deltaChars;
/* 136 */       int end = operation.pos;
/*     */       
/* 138 */       rope = rope.delete(start, end);
/*     */     } 
/*     */ 
/*     */     
/* 142 */     String content = rope.toString();
/* 143 */     Operation o = new Operation(0L, content.length(), 0, content);
/*     */     
/* 145 */     this.main.updateText(o);
/*     */   }
/*     */   
/*     */   public String readUntilNull() throws IOException {
/* 149 */     int i = 0;
/* 150 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 152 */     while ((i = this.br.read()) != 0) {
/* 153 */       sb.append((char)i);
/*     */     }
/*     */     
/* 156 */     return sb.toString();
/*     */   }
/*     */ }

