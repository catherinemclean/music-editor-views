package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * A view interface which allows for scrolling left and right, as well as adding and removing
 * key/mouse handlers
 */
public interface GuiView extends MusicEditorView {
  /** Add a key listener to the view*/
  void addKeyListener(KeyListener keyboardHandler);

  /** Remove a key listener to the view*/
  void removeKeyListener(KeyListener keyboardHandler);

  /** Add a mouse listener to the view*/
  void addMouseListener(MouseListener mouseHandler);

  /** Remove a mouse listener to the view*/
  void removeMouseListener(MouseListener mouseHandler);

  /** Update the view according to the given beat*/
  void update(int beat);

  /**
   * Scroll the GUI one beat to the left (doesn't do anything if it's already all the way to the
   * left)
   */
  void scrollLeft();

  /**
   * Scroll the GUI one beat to the right (doesn't do anything if it's already all the way to the
   * right)
   */
  void scrollRight();

  /**
   * Scroll the GUI one half-pitch up (doesn't do anything if it's already all the way up)
   */
  void scrollUp();

  /**
   * Scroll the GUI one half-pitch up (doesn't do anything if it's already all the way up)
   */
  void scrollDown();
}
