/*    */ package client.logic;
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
/*    */   public Operation(String s) {
/* 21 */     FastStringTokenizer tok = new FastStringTokenizer(s);
/*    */     
/* 23 */     this.lastSyncTime = tok.nextLong();
/* 24 */     this.deltaChars = tok.nextInt();
/* 25 */     this.pos = tok.nextInt();
/* 26 */     tok.advance(1);
/* 27 */     this.text = tok.getRemaining();
/*    */   }
/*    */   
/*    */   public Operation clone() {
/* 31 */     return new Operation(this.lastSyncTime, this.deltaChars, this.pos, this.text);
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Operation o) {
/* 36 */     return Long.signum(this.lastSyncTime - o.lastSyncTime);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 41 */     return String.valueOf(this.lastSyncTime) + " " + this.deltaChars + " " + this.pos + " " + this.text;
/*    */   }
/*    */ }

