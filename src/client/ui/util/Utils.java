/*    */ package client.ui.util;
/*    */ 
/*    */ public class Utils
/*    */ {
/*    */   public static float clamp(float min, float value, float max) {
/*  6 */     if (value < min) return min; 
/*  7 */     if (value > max) return max; 
/*  8 */     return value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int clamp(int min, int value, int max) {
/* 16 */     if (value < min) return min; 
/* 17 */     if (value > max) return max; 
/* 18 */     return value;
/*    */   }
/*    */   
/*    */   public static void sleep() {
/*    */     try {
/* 23 */       Thread.sleep(100L);
/* 24 */     } catch (Exception e) {
/* 25 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }

