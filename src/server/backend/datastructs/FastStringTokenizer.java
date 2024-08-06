/*    */ package server.backend.datastructs;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FastStringTokenizer
/*    */ {
/*    */   String s;
/*    */   int currentIdx;
/*    */   static final int START_CODE = 48;
/*    */   static final int END_CODE = 57;
/*    */   
/*    */   public FastStringTokenizer(String s) {
/* 13 */     this.s = s;
/* 14 */     this.currentIdx = 0;
/*    */   }
/*    */   
/*    */   public int nextInt() {
/* 18 */     while (!valid(this.s.charAt(this.currentIdx))) {
/* 19 */       this.currentIdx++;
/*    */     }
/*    */     
/* 22 */     int ans = 0;
/*    */     
/* 24 */     boolean negative = false;
/*    */     char c;
/* 26 */     while (valid(c = this.s.charAt(this.currentIdx))) {
/* 27 */       if (c == '-') {
/* 28 */         negative = !negative;
/*    */       } else {
/* 30 */         int num = c - 48;
/*    */         
/* 32 */         ans *= 10;
/* 33 */         ans += num;
/*    */       } 
/*    */       
/* 36 */       this.currentIdx++;
/*    */     } 
/*    */     
/* 39 */     if (negative) {
/* 40 */       ans = -ans;
/*    */     }
/* 42 */     return ans;
/*    */   }
/*    */   
/*    */   public long nextLong() {
/* 46 */     while (!valid(this.s.charAt(this.currentIdx))) {
/* 47 */       this.currentIdx++;
/*    */     }
/*    */     
/* 50 */     long ans = 0L;
/*    */     
/* 52 */     boolean negative = false;
/*    */     char c;
/* 54 */     while (valid(c = this.s.charAt(this.currentIdx))) {
/* 55 */       if (c == '-') {
/* 56 */         negative = !negative;
/*    */       } else {
/* 58 */         int num = c - 48;
/*    */         
/* 60 */         ans *= 10L;
/* 61 */         ans += num;
/*    */       } 
/*    */       
/* 64 */       this.currentIdx++;
/*    */     } 
/*    */     
/* 67 */     return ans;
/*    */   }
/*    */   
/*    */   public String getRemaining() {
/* 71 */     return this.s.substring(this.currentIdx);
/*    */   }
/*    */   
/*    */   boolean valid(char c) {
/* 75 */     int code = c;
/* 76 */     return !(c != '-' && (48 > code || code > 57));
/*    */   }
/*    */   
/*    */   public void advance(int numChars) {
/* 80 */     this.currentIdx += numChars;
/*    */   }
/*    */ }

