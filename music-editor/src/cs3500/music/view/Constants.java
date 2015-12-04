package cs3500.music.view;

import java.awt.*;

public class Constants {
  private Constants() { } // prevents instantiation

  public static final int MAX_WINDOW_WIDTH
      = (int) (Toolkit.getDefaultToolkit().getScreenSize().width);
  public static final int MAX_WINDOW_HEIGHT
      = (int) (Toolkit.getDefaultToolkit().getScreenSize().height);

  public static final int CELL_SIZE    = 20;

  public static final Color CELL_HEAD_COLOR  = Color.BLACK;
  public static final Color CELL_TAIL_COLOR  = Color.BLUE;
  public static final Color GRID_COLOR  = Color.BLACK;

  public static final int MAX_BEAT_VISIBLE = 38;

  public static final int DEFAULT_PLAYABLE_LENGTH = 3;
  public static final int DEFAULT_PLAYABLE_INSTRUMENT = 1;
  public static final int DEFAULT_PLAYABLE_VOLUME = 100;
}