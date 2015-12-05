package cs3500.music.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cs3500.music.model.*;
import cs3500.music.view.*;

/**
 * Handles all of the Mouse events.
 */
public final class MouseHandler implements MouseListener {

  /**
   * Represents the view with which the mouse events will interact.
   */
  private cs3500.music.model.ViewModel view;

  /**
   * Represents mock output, for testing purposes.
   */
  private StringBuilder out;

  /**
   * Constructs a new mouse handler with the given view.
   *
   * @param view the view to use
   */
  public MouseHandler(cs3500.music.model.ViewModel view) {
    this.view = view;
    this.out = new StringBuilder(1000);
  }

  /**
   * Handles all mouse clicked events from the user.
   *
   * @param e the MouseEvent representing the user's click.
   */
  @Override public void mouseClicked(MouseEvent e) {
    view.setCurrent(e.getX(), e.getY());
    String msg = "Clicked at x:" + e.getX() + " y:" + e.getY() + "\n";

    // resize our StringBuilder to double capacity
    if (out.capacity() - out.length() < msg.length()) {
      out.ensureCapacity(out.capacity() * 2);
    }
    out.append(msg);
  }

  /**
   * Handles all mouse pressed events from the user.
   *
   * @param e the MouseEvent representing the user's pressed
   */
  @Override public void mousePressed(MouseEvent e) {
    String msg = "Pressed at x:" + e.getX() + " y:" + e.getY() + "\n";

    // resize our StringBuilder to double capacity
    if (out.capacity() - out.length() < msg.length()) {
      out.ensureCapacity(out.capacity() * 2);
    }
    out.append(msg);
  }

  /**
   * Handles all mouse released events from the user.
   *
   * @param e the MouseEvent representing the user's release
   */
  @Override public void mouseReleased(MouseEvent e) {
    String msg = "Released at x:" + e.getX() + " y:" + e.getY() + "\n";

    // resize our StringBuilder to double capacity
    if (out.capacity() - out.length() < msg.length()) {
      out.ensureCapacity(out.capacity() * 2);
    }
    out.append(msg);
  }

  /**
   * Handles all mouse entered events from the user.
   *
   * @param e the MouseEvent representing the user's enter
   */
  @Override public void mouseEntered(MouseEvent e) {
    String msg = "Entered at x:" + e.getX() + " y:" + e.getY() + "\n";

    // resize our StringBuilder to double capacity
    if (out.capacity() - out.length() < msg.length()) {
      out.ensureCapacity(out.capacity() * 2);
    }
    out.append(msg);
  }

  /**
   * Handles all mouse exited events from the user
   *
   * @param e the MouseEvent representing the user's exit
   */
  @Override public void mouseExited(MouseEvent e) {
    String msg = "Exited at x:" + e.getX() + " y:" + e.getY() + "\n";

    // resize our StringBuilder to double capacity
    if (out.capacity() - out.length() < msg.length()) {
      out.ensureCapacity(out.capacity() * 2);
    }
    out.append(msg);
  }

  /**
   * Return the testing output as a string.
   *
   * @return the string representation of the testing output.
   */
  public String getOutput() {
    return this.out.toString();
  }
}
