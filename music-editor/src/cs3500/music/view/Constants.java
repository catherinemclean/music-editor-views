package cs3500.music.view;

import java.awt.*;

/** Some useful constants for Swing*/
public class Constants {
  private Constants() { } // prevents instantiation

  public static final int MAX_WINDOW_WIDTH
      = (int) (Toolkit.getDefaultToolkit().getScreenSize().width);
  public static final int MAX_WINDOW_HEIGHT
      = (int) (Toolkit.getDefaultToolkit().getScreenSize().height);

  public static final int CELL_SIZE = 20;

  public static final Color CELL_HEAD_COLOR  = new Color(13, 77, 127);
  public static final Color CELL_TAIL_COLOR  = new Color(111, 174, 251);
  public static final Color GRID_COLOR       = new Color(0, 0, 0);
  public static final Color BACKGROUND_COLOR = new Color(255, 255, 255);

  public static final int MAX_BEAT_VISIBLE = 38;

  public static final int DEFAULT_PLAYABLE_LENGTH     =   3;
  public static final int DEFAULT_PLAYABLE_INSTRUMENT =   1;
  public static final int DEFAULT_PLAYABLE_VOLUME     = 100;
}