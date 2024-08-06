/*    */ package server.backend;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.function.Function;

import server.backend.datastructs.Operation;

/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BackendHandler
/*    */ {
/*    */   Algorithm a;
/*    */   
/*    */   public BackendHandler(Algorithm a) {
/* 18 */     this.a = a;
/*    */   }
/*    */   
/*    */   public synchronized List<Operation> getAllOperations() {
/* 22 */     return this.a.operations;
/*    */   }
/*    */   
/*    */   public synchronized Operation latestOperation() {
/* 26 */     return this.a.latestOperation();
/*    */   }
/*    */   
/*    */   public synchronized void forAllAfter(long lastSyncTime, Function<Operation, Void> f) {
/* 30 */     int startIdx = this.a.operationIndex(lastSyncTime) + 1;
/*    */     
/* 32 */     for (int i = startIdx; i < this.a.operations.size(); i++) {
/* 33 */       f.apply(this.a.operations.get(i));
/*    */     }
/*    */   }
/*    */   
/*    */   public synchronized int numOperations() {
/* 38 */     return this.a.operations.size();
/*    */   }
/*    */   
/*    */   public synchronized Operation[] update(Operation operation) {
/* 42 */     return this.a.update(operation);
/*    */   }
/*    */ }

