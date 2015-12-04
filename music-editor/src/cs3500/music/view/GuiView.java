package cs3500.music.view;

import javax.sound.midi.InvalidMidiDataException;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.Dimension;

/**
 * Represents a visual gui view.
 */
public interface GuiView extends View {

  /**
   * Sends in the location of the mouse click to set the current Note that is being
   * selected.
   *
   * @param x The x coordinate of the mouse click.
   * @param y The y coordinate of the mouse click.
   */
  void setCurrent(int x, int y);

  /**
   * Adds a key listener to this gui view.
   *
   * @param k The Key Listener to be added.
   */
  void addListener(KeyListener k);

  /**
   * Adds a key listener to this gui view.
   *
   * @param m The Key Listener to be added.
   */
  void addListener(MouseListener m);

  /**
   * Removes the given key listener from this gui view.
   *
   * @param kl The Key Listener to be removed.
   */
  void removeKeyListener(KeyListener kl);

  /**
   * Removes the given key listener from this gui view.
   *
   * @param ml The Key Listener to be removed.
   */
  void removeMouseListener(MouseListener ml);

  /**
   * Repaints the GUI view.
   *
   * @param playing Whether or not the model is currently playing.
   */
  void paintAgain(boolean playing);

  /**
   * Visually shows the beginning of the gui view.
   */
  void skipToFront();

  /**
   * Visually shows the end of the gui view.
   */
  void skipToEnd();

  /**
   * Visually moves the gui view that is in the frame left.
   */
  void shiftLeft();

  /**
   * Visually moves the gui view that is in the frame right.
   */
  void shiftRight();

  /**
   * Visually moves the gui view that is in the frame up.
   */
  void shiftUp();

  /**
   * Visually moves the gui view that is in the frame down.
   */
  void shiftDown();

  /**
   * Gets the dimension of the view that is currently seen in the gui frame.
   *
   * @return The Dimension of the range that is currently in the gui frame.
   */
  Dimension getViewableRange();
}
