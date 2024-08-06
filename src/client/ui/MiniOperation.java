/*    */ package client.ui;
/*    */ 
/*    */ public class MiniOperation
/*    */ {
/*    */   public int deltaChars;
/*    */   public int pos;
/*    */   public StringBuffer text;
/*    */   
/*    */   public MiniOperation(int deltaChars, int pos, char text) {
/* 10 */     this.deltaChars = deltaChars;
/* 11 */     this.pos = pos;
/*    */     
/* 13 */     this.text = new StringBuffer();
/* 14 */     if (text != '\000') {
/* 15 */       this.text.append(text);
/*    */     }
/*    */   }
/*    */   
/*    */   public MiniOperation(int deltaChars, int pos, String text) {
/* 20 */     this.deltaChars = deltaChars;
/* 21 */     this.pos = pos;
/*    */     
/* 23 */     this.text = new StringBuffer();
/* 24 */     if (text.length() != 0) {
/* 25 */       this.text.append(text);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean combine(MiniOperation m) {
/* 31 */     if (this.pos + this.deltaChars != m.pos || Integer.signum(m.deltaChars) != Integer.signum(this.deltaChars)) {
/* 32 */       return false;
/*    */     }
/*    */     
/* 35 */     this.deltaChars += m.deltaChars;
/* 36 */     this.text.append(m.text);
/*    */     
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 43 */     return String.valueOf(this.deltaChars) + " " + this.pos + " " + this.text.toString();
/*    */   }
/*    */ }

