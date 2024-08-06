package client.ui.components.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.fxmisc.richtext.StyledTextArea;
import org.fxmisc.richtext.model.Codec;
import org.fxmisc.richtext.model.EditableStyledDocument;
import org.fxmisc.richtext.model.ReadOnlyStyledDocument;
import org.fxmisc.richtext.model.SegmentOps;
import org.fxmisc.richtext.model.SimpleEditableStyledDocument;
import org.fxmisc.richtext.model.StyledDocument;

import client.logic.Operation;
import client.ui.Main;
import javafx.application.Platform;
import javafx.beans.NamedArg;

public class Page extends StyledTextArea<Collection<String>, Collection<String>> {
  public static final List<String> EMPTY_LIST = Collections.emptyList();
  
  Main main;
  
  ReentrantLock lock;
  
  Page(@NamedArg("document") EditableStyledDocument<Collection<String>, String, Collection<String>> document, @NamedArg("preserveStyle") boolean preserveStyle) {
    super(Collections.emptyList(), (paragraph, styleClasses) -> {
        
        }, Collections.emptyList(), (text, styleClasses) -> {
        
        }, document, preserveStyle);
    this.lock = new ReentrantLock();
    setStyleCodecs(Codec.collectionCodec(Codec.STRING_CODEC), Codec.styledTextCodec(Codec.collectionCodec(Codec.STRING_CODEC)));
  }
  
  Page(@NamedArg("preserveStyle") boolean preserveStyle) {
    this((EditableStyledDocument<Collection<String>, String, Collection<String>>)new SimpleEditableStyledDocument(Collections.emptyList(), Collections.emptyList()), preserveStyle);
  }
  
  public Page(Main main) {
    this(true);
    this.main = main;
  }
  
  public void replace(int start, int end, StyledDocument<Collection<String>, String, Collection<String>> replacement) {
    this.main.sendOperation(start, end, replacement.getText());
    super.replace(start, end, replacement);
  }
  
  public synchronized void updateText(Operation o) {
    if (o.deltaChars == 0)
      return; 
    if (o.deltaChars <= 0) {
      replace(o.pos + o.deltaChars, o.pos, o.text, EMPTY_LIST);
    } else {
      replace(o.pos, o.pos, o.text, EMPTY_LIST);
    } 
  }
  
  public synchronized void replace(int start, int end, String text, Collection<String> styles) {
    this.lock.lock();
    Platform.runLater(() -> {
          ReadOnlyStyledDocument<Collection<String>, String, Collection<String>> doc = 
        		  ReadOnlyStyledDocument.fromSegment(text, getParagraphStyleForInsertionAt(start), new ArrayList<>(), (SegmentOps)getSegOps());
          getContent().replace(start, end, (StyledDocument)doc);
          int newCaretPos = getCaretPosition();
          int oldLength = end - start;
          int newLength = text.length();
          int delta = newLength - oldLength;
          if (end < newCaretPos)
            newCaretPos -= delta; 
        });
    this.lock.unlock();
  }
}
