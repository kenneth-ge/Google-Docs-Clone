/*     */ package client.ui;
import client.logic.Networking;
import client.logic.Operation;
import client.ui.components.text.Page;
/*     */ 
/*     */ import javafx.animation.TranslateTransition;
/*     */ import javafx.application.Application;
/*     */ import javafx.application.Platform;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.event.EventHandler;
/*     */ import javafx.geometry.Insets;
/*     */ import javafx.geometry.Pos;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.Parent;
/*     */ import javafx.scene.Scene;
/*     */ import javafx.scene.control.Alert;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.control.TextField;
/*     */ import javafx.scene.layout.GridPane;
/*     */ import javafx.scene.layout.HBox;
/*     */ import javafx.scene.layout.StackPane;
/*     */ import javafx.scene.text.Font;
/*     */ import javafx.scene.text.FontWeight;
/*     */ import javafx.scene.text.Text;
/*     */ import javafx.stage.Stage;
/*     */ import javafx.util.Duration;
/*     */ 
/*     */ public class Main extends Application implements EventHandler<ActionEvent> {
/*     */   StackPane stack;
/*     */   Page editor;
/*     */   StackPane sp;
/*     */   Stage stage;
/*     */   TextField ipField;
/*     */   Networking network;
/*     */   
/*     */   public void start(Stage stage) {
/*  39 */     this.stage = stage;
/*     */     
/*  41 */     this.stack = new StackPane();
/*     */     
/*  43 */     GridPane grid = new GridPane();
/*  44 */     grid.setAlignment(Pos.CENTER);
/*  45 */     grid.setHgap(10.0D);
/*  46 */     grid.setVgap(10.0D);
/*  47 */     grid.setPadding(new Insets(25.0D, 25.0D, 25.0D, 25.0D));
/*     */     
/*  49 */     Text scenetitle = new Text("Join A Session");
/*  50 */     scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20.0D));
/*  51 */     grid.add((Node)scenetitle, 0, 0, 2, 1);
/*     */     
/*  53 */     Label userName = new Label("IP Address:");
/*  54 */     grid.add((Node)userName, 0, 1);
/*     */     
/*  56 */     this.ipField = new TextField();
/*  57 */     grid.add((Node)this.ipField, 1, 1);
/*     */     
/*  59 */     Button btn = new Button("Join");
/*  60 */     HBox hbBtn = new HBox(10.0D);
/*  61 */     hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
/*  62 */     hbBtn.getChildren().add(btn);
/*  63 */     grid.add((Node)hbBtn, 1, 4);
/*     */     
/*  65 */     btn.setOnAction(this);
/*     */     
/*  67 */     this.stack.getChildren().add(grid);
/*     */     
/*  69 */     Scene scene = new Scene((Parent)this.stack, 1280.0D, 720.0D);
/*  70 */     stage.setScene(scene);
/*  71 */     stage.setResizable(false);
/*  72 */     stage.show();
/*     */     
/*  74 */     this.editor = new Page(this);
/*     */     
/*  76 */     this.sp = new StackPane();
/*  77 */     this.sp.getChildren().add(this.editor);
/*  78 */     this.sp.setTranslateX(1280.0D);
/*     */     
/*  80 */     this.stack.getChildren().add(this.sp);
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/*  84 */     launch(
/*  85 */         new String[0]);
/*     */   }
/*     */   
/*     */   public void stop() throws Exception {
/*  89 */     super.stop();
/*     */     
/*  91 */     this.network.running = false;
/*  92 */     System.exit(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handle(ActionEvent event) {
/*  97 */     this.network = new Networking(this, this.ipField.getText());
/*     */   }
/*     */   
/*     */   public void loadTextUI() {
/* 101 */     TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5D), (Node)this.sp);
/* 102 */     tt.setFromX(1280.0D);
/* 103 */     tt.setToX(0.0D);
/*     */     
/* 105 */     tt.play();
/*     */     
/* 107 */     Platform.runLater(() -> this.stage.setResizable(true));
/*     */   }
/*     */   
/*     */   public void hostNotFound() {
/* 111 */     Alert alert = new Alert(Alert.AlertType.ERROR);
/* 112 */     alert.setTitle("Server not found");
/* 113 */     alert.setHeaderText("Couldn't connect to the server");
/* 114 */     alert.setContentText("Please make sure the server is runnng and\ndouble-check that the IP address is correct");
/*     */     
/* 116 */     alert.showAndWait();
/*     */   }
/*     */   
/*     */   public synchronized void updateText(Operation o) {
/* 120 */     this.editor.updateText(o);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendOperation(int start, int end, String text) {
/* 127 */     if (start < end) {
/* 128 */       MiniOperation miniOperation = new MiniOperation(start - end, end, "");
/*     */       
/* 130 */       this.network.in.add(miniOperation);
/*     */     } 
/*     */     
/* 133 */     MiniOperation m = new MiniOperation(text.length(), start, text);
/* 134 */     this.network.in.add(m);
/*     */   }
/*     */ }

