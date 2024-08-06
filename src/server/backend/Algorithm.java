/*     */ package server.backend;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;

/*     */ import org.ahmadsoft.ropes.Rope;

import server.backend.datastructs.Operation;
import server.backend.util.TimeUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Algorithm
/*     */ {
/*  17 */   ArrayList<Operation> operations = new ArrayList<>();
/*     */   public Algorithm(FileHandler files) {
/*  19 */     if (files.willLoadFile()) {
/*  20 */       String s = files.getLoaded();
/*     */       
/*  22 */       Operation initial = new Operation(TimeUtil.currentTime(), s.length(), 0, s);
/*     */       
/*  24 */       this.operations.add(initial);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int operationIndex(long time) {
/*  29 */     return Collections.binarySearch((List)this.operations, new Operation(time, 0, 0, null));
/*     */   }
/*     */   
/*     */   public Operation latestOperation() {
/*  33 */     return this.operations.get(this.operations.size() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Operation[] update(Operation o) {
/*  53 */     int index = this.operations.size();
/*     */     
/*  55 */     while (index > 0 && ((Operation)this.operations.get(index - 1)).lastSyncTime > o.lastSyncTime) {
/*  56 */       index--;
/*     */     }
/*     */     
/*  59 */     int n = this.operations.size() - index;
/*  60 */     Operation[] catchup = new Operation[n + 1];
/*  61 */     Operation toAdd = o.clone();
/*     */     
/*  63 */     for (int i = 0; i < n; i++) {
/*  64 */       Operation currOld = this.operations.get(i + index);
/*     */       
/*  66 */       if (currOld.pos <= toAdd.pos) {
/*  67 */         toAdd.pos += currOld.deltaChars;
/*  68 */         catchup[i] = currOld;
/*     */       } else {
/*  70 */         Operation transformed = currOld.clone();
/*  71 */         transformed.pos += toAdd.deltaChars;
/*  72 */         catchup[i] = transformed;
/*     */       } 
/*     */     } 
/*     */     
/*  76 */     catchup[n] = toAdd;
/*     */     
/*  78 */     toAdd.lastSyncTime = TimeUtil.currentTime();
/*  79 */     if (o.deltaChars != 0) {
/*  80 */       this.operations.add(toAdd);
/*     */     }
/*  82 */     else if (n - 1 >= 0) {
/*  83 */       toAdd.lastSyncTime = (catchup[n - 1]).lastSyncTime;
/*     */     } 
/*     */     
/*  86 */     return catchup;
/*     */   }
/*     */   
/*     */   private Rope contentRope() {
/*  90 */     Rope rope = Rope.BUILDER.build("");
/*     */     
/*  92 */     for (Operation o : this.operations) {
/*  93 */       if (o.deltaChars >= 0) {
/*  94 */         rope = rope.insert(o.pos, o.text); continue;
/*     */       } 
/*  96 */       int start = o.pos + o.deltaChars + 1;
/*  97 */       int end = o.pos + 1;
/*     */       
/*  99 */       rope = rope.delete(start, end);
/*     */     } 
/*     */ 
/*     */     
/* 103 */     return rope;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getContent() {
/* 108 */     return contentRope().toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush(FileHandler files) {
/* 120 */     Rope rope = contentRope();
/*     */     
/* 122 */     files.writeRope(rope);
/*     */   }
/*     */ }