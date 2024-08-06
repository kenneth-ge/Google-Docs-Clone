/*    */ package server.backend.datastructs;
/*    */ 
/*    */ 
/*    */ public class Operation
/*    */   implements Comparable<Operation>
/*    */ {
/*    */   public long lastSyncTime;
/*    */   public final int deltaChars;
/*    */   public int pos;
/*    */   public final String text;
/*    */   
/*    */   public Operation(long lastSyncTime, int deltaChars, int pos, String text) {
/* 13 */     this.lastSyncTime = lastSyncTime;
/* 14 */     this.deltaChars = deltaChars;
/* 15 */     this.pos = pos;
/* 16 */     this.text = text;
/*    */   }
/*    */ 
/*    */   
/*    */   public Operation(long lastSyncTime, String s) {
/* 21 */     this.lastSyncTime = lastSyncTime;
/*    */     
/* 23 */     FastStringTokenizer tok = new FastStringTokenizer(s);
/*    */     
/* 25 */     this.deltaChars = tok.nextInt();
/* 26 */     this.pos = tok.nextInt();
/* 27 */     tok.advance(1);
/* 28 */     this.text = tok.getRemaining();
/*    */   }
/*    */   
/*    */   public Operation clone() {
/* 32 */     return new Operation(this.lastSyncTime, this.deltaChars, this.pos, this.text);
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Operation o) {
/* 37 */     return Long.signum(this.lastSyncTime - o.lastSyncTime);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 42 */     return String.valueOf(this.lastSyncTime) + " " + this.deltaChars + " " + this.pos + " " + this.text;
/*    */   }
/*    */ }

