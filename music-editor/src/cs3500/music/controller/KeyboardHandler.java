package cs3500.music.controller;

import sun.awt.ExtendedKeyCodes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

/**
 * Listens to and handles key events.
 */
public final class KeyboardHandler implements KeyListener {

  /**
   * Represents all the events that should happen when a key is types
   * mapped to the key's int representation
   */
  private Map<Integer, Runnable> typed;

  /**
   * Represents all the events that should happen when a key is pressed
   * mapped to the key's int representation
   */
  private Map<Integer, Runnable> pressed;

  /**
   * Represents all the events that should happen when a key is released
   * mapped to the key's int representation
   */
  private Map<Integer, Runnable> released;

  /**
   * Represents testing output
   */
  private StringBuilder out;

  /**
   * Makes a new KeyBoard handler. Initializes all the fields to empty
   */
  public KeyboardHandler() {
    this.typed = new HashMap<Integer, Runnable>();
    this.pressed = new HashMap<Integer, Runnable>();
    this.released = new HashMap<Integer, Runnable>();
    this.out = new StringBuilder(1000);
  }

  /**
   * Reacts to a key-typed event from the user!
   *
   * @param e the KeyEvent that was typed
   */
  @Override public void keyTyped(KeyEvent e) {
    e.setKeyCode(ExtendedKeyCodes.getExtendedKeyCodeForChar(e.getKeyChar()));
    if (this.typed.containsKey(e.getKeyCode())) {
      this.typed.get(e.getKeyCode()).run();
    }

    String msg = "Key typed: " + displayInfo(e);
    // resize our StringBuilder to double capacity
    if (out.capacity() - out.length() < msg.length()) {
      out.ensureCapacity(out.capacity() * 2);
    }
    out.append(msg);
  }

  /**
   * Reacts to key-pressed events from the user!
   *
   * @param e the KeyEvent that was pressed
   */
  @Override public void keyPressed(KeyEvent e) {
    if (this.pressed.containsKey(e.getExtendedKeyCode())) {
      this.pressed.get(e.getExtendedKeyCode()).run();
    }

    String msg = "Key pressed: " + displayInfo(e);
    // resize our StringBuilder to double capacity
    if (out.capacity() - out.length() < msg.length()) {
      out.ensureCapacity(out.capacity() * 2);
    }
    out.append(msg);
  }

  /**
   * Reacts to key-released events from the user!
   *
   * @param e the KeyEvent that was released
   */
  @Override public void keyReleased(KeyEvent e) {
    if (this.released.containsKey(e.getExtendedKeyCode())) {
      this.released.get(e.getExtendedKeyCode()).run();
    }

    String msg = "Key released: " + displayInfo(e);
    // resize our StringBuilder to double capacity
    if (out.capacity() - out.length() < msg.length()) {
      out.ensureCapacity(out.capacity() * 2);
    }
    out.append(msg);
  }

  /**
   * Adds the given Runnable function to be run on the given key-typed event!
   *
   * @param e the key code to be associated with this Runnable
   * @param r the Runnable to be executed on the given key-typed event
   */
  public void addTypedEvent(int e, Runnable r) {
    this.typed.put(e, r);
  }

  /**
   * Adds the given Runnable function to be run on the given key-pressed event!
   *
   * @param e the key code to be associated with this Runnable
   * @param r the Runnable to be executed on the given key-pressed event
   */
  public void addPressedEvent(int e, Runnable r) {
    this.pressed.put(e, r);
  }

  /**
   * Adds the given Runnable function to be run on the given key-pressed event!
   *
   * @param e the key code to be associated with this Runnable
   * @param r the Runnable to be executed on the given key-pressed event
   */
  public void addReleasedEvent(int e, Runnable r) {
    this.released.put(e, r);
  }

  /**
   * Returns testing data.
   *
   * @param e Key event
   * @return The string with the key event
   */
  private String displayInfo(KeyEvent e) {
    return e.getKeyChar() + " key code = " + e.getKeyCode();
  }

  /**
   * Made to imitate the behavior in testing!
   */
  public class TestKeyHandler implements Runnable {

    public void run() {
      out.append("Ran\n");
    }
  }

  /**
   * Returns the output as a string for testing.
   *
   * @return the output as a string.
   */
  public String getOutput() {
    return this.out.toString();
  }
}
