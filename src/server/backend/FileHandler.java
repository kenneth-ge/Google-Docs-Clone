package server.backend;

import org.ahmadsoft.ropes.Rope;

public abstract class FileHandler {
  public abstract boolean willLoadFile();
  
  public abstract String getLoaded();
  
  public abstract boolean updateExisting();
  
  public abstract String getExisting();
  
  public abstract void overwrite(String paramString);
  
  protected abstract void writeRope(Rope paramRope);
}

