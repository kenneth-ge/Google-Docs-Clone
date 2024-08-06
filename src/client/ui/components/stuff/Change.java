/*    */ package client.ui.components.stuff;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Change
/*    */ {
/*    */   public int start;
/*    */   public int end;
/*    */   public String text;
/*    */   public int anchor;
/*    */   public int caret;
/*    */   public static final String BLANK = "";
/*    */   
/*    */   public Change(int anchor, int caret) {
/* 15 */     this(caret, caret, "", anchor, caret);
/*    */   }
/*    */   
/*    */   public Change(int start, int end, String text) {
/* 19 */     this(start, end, text, start + text.length(), start + text.length());
/*    */   }
/*    */   
/*    */   public Change(int start, int end, String text, int anchor, int caret) {
/* 23 */     this.start = start;
/* 24 */     this.end = end;
/* 25 */     this.text = text;
/* 26 */     this.anchor = anchor;
/* 27 */     this.caret = caret;
/*    */   }
/*    */ }

