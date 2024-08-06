/*    */ package server.implementation;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.File;
/*    */ import java.io.FileReader;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;

/*    */ import org.ahmadsoft.ropes.Rope;

import server.backend.FileHandler;
/*    */ 
/*    */ 
/*    */ public class DefaultFileHandler
/*    */   extends FileHandler
/*    */ {
/*    */   String file;
/*    */   
/*    */   public DefaultFileHandler(String file) {
/* 19 */     this.file = file;
/*    */     
/* 21 */     File f = new File(file);
/* 22 */     if (!f.exists()) {
/*    */       try {
/* 24 */         f.createNewFile();
/* 25 */       } catch (IOException e) {
/* 26 */         e.printStackTrace();
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean willLoadFile() {
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLoaded() {
/* 39 */     StringBuffer sb = new StringBuffer();
/*    */ 
/*    */     
/*    */     try {
/* 43 */       BufferedReader br = new BufferedReader(new FileReader(this.file));
/*    */       String line;
/* 45 */       while ((line = br.readLine()) != null) {
/* 46 */         sb.append(line);
/* 47 */         sb.append('\n');
/*    */       } 
/*    */       
/* 50 */       br.close();
/*    */       
/* 52 */       return sb.toString();
/* 53 */     } catch (IOException e) {
/* 54 */       e.printStackTrace();
/*    */       
/* 56 */       return "";
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean updateExisting() {
/* 62 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getExisting() {
/* 67 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void overwrite(String toWrite) {
/*    */     try {
/* 73 */       BufferedWriter bw = new BufferedWriter(new FileWriter(this.file));
/*    */       
/* 75 */       bw.append(toWrite);
/*    */       
/* 77 */       bw.close();
/* 78 */     } catch (IOException e) {
/* 79 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void writeRope(Rope r) {
/*    */     try {
/* 86 */       BufferedWriter bw = new BufferedWriter(new FileWriter(this.file));
/*    */       
/* 88 */       r.write(bw);
/*    */       
/* 90 */       bw.close();
/* 91 */     } catch (IOException e) {
/* 92 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }

