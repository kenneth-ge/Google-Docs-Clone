/*    */ package server.implementation;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;

/*    */ import org.ahmadsoft.ropes.Rope;

import server.backend.FileHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConsoleFileHandler
/*    */   extends FileHandler
/*    */ {
/*    */   public boolean willLoadFile() {
/* 14 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLoaded() {
/* 19 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean updateExisting() {
/* 24 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getExisting() {
/* 29 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void overwrite(String toWrite) {
/* 34 */     System.out.println("Algorithm attempted to overwrite file. The contents are below:");
/* 35 */     System.out.println(toWrite);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void writeRope(Rope r) {
/*    */     try {
/* 41 */       r.write(new PrintWriter(System.out));
/* 42 */     } catch (IOException e) {
/* 43 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }
