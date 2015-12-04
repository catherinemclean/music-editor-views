package cs3500.music.view;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import java.awt.event.KeyListener;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.util.Objects;

/**
 * A view composed of both the gui view and the midi view.
 * Plays the song while scrolling through the visual view.
 */

public final class CompositeView implements CompositeViewable {

  /**
   * The GuiView of the model that will be visually displayed.
   */
  private GuiView gv;

  /**
   * The MidiView of the model that will be audibly played.
   */
  private MidiView mv;

  /**
   * Creates a new CompositeView of a combination of the two given views.
   *
   * @param mv The midi view that makes up this composite view.
   * @param gv The gui view that makes up this composite view.
   */
  public CompositeView(MidiView mv, GuiView gv) {
    this.mv = Objects.requireNonNull(mv);
    this.gv = Objects.requireNonNull(gv);
  }

  /**
   * Sends in the location of the mouse click to set the current Note that is being
   * selected.
   *
   * @param x The x coordinate of the mouse click.
   * @param y The y coordinate of the mouse click.
   */
  @Override public void setCurrent(int x, int y) {
    gv.setCurrent(x, y);
  }

  /**
   * Adds a key listener to this composite view through the gui view.
   *
   * @param k The Key Listener to be added.
   */
  @Override public void addListener(KeyListener k) {
    gv.addListener(k);
  }

  /**
   * Adds a key listener to this gui view.
   *
   * @param m The Key Listener to be added.
   */
  @Override public void addListener(MouseListener m) {
    gv.addListener(m);
  }

  /**
   * Removes the given key listener from this gui view.
   *
   * @param kl The Key Listener to be removed.
   */
  @Override public void removeKeyListener(KeyListener kl) {
    gv.removeKeyListener(kl);
  }

  /**
   * Removes the given key listener from this gui view.
   *
   * @param ml The Key Listener to be removed.
   */
  @Override public void removeMouseListener(MouseListener ml) {
    gv.removeMouseListener(ml);
  }

  /**
   * Repaints the GUI view.
   *
   * @param playing Whether or not the model is currently playing.
   */
  @Override public void paintAgain(boolean playing) {
    gv.paintAgain(playing);
  }

  /**
   * Visually shows the beginning of the gui view.
   */
  @Override public void skipToFront() {
    gv.skipToFront();
  }

  /**
   * Visually shows the end of the gui view.
   */
  @Override public void skipToEnd() {
    gv.skipToEnd();
  }

  /**
   * Visually moves the gui view that is in the frame left.
   */
  @Override public void shiftLeft() {
    gv.shiftLeft();
  }

  /**
   * Visually moves the gui view that is in the frame right.
   */
  @Override public void shiftRight() {
    gv.shiftRight();
  }

  /**
   * Visually moves the gui view that is in the frame up.
   */
  @Override public void shiftUp() {
    gv.shiftUp();
  }

  /**
   * Visually moves the gui view that is in the frame down.
   */
  @Override public void shiftDown() {
    gv.shiftDown();
  }

  /**
   * Gets the dimension of the view that is currently seen in the gui frame.
   *
   * @return The Dimension of the range that is currently in the gui frame.
   */
  @Override public Dimension getViewableRange() {
    return gv.getViewableRange();
  }

  /**
   * Audibly plays all of the notes at the given time.
   *
   * @param time The time that this view is currently at.
   * @throws InvalidMidiDataException if the Midi data is invalid.
   * @throws MidiUnavailableException if Midi is unavailable.
   */
  @Override public void recordNotes(int time)
      throws InvalidMidiDataException, MidiUnavailableException {
    mv.recordNotes(time);
  }

  /**
   * Pauses this view.
   */
  @Override public void pause() {
    mv.pause();
  }

  /**
   * Returns whether or not this CompositeView is currently paused.
   *
   * @return true if this view is paused or false if it is playing.
   */
  @Override public boolean isPaused() {
    return mv.isPaused();
  }

  /**
   * Returns the midi view as output to the console for testing purposes.
   *
   * @return The midi view represented as a string.
   */
  @Override public String getOutput() {
    return mv.getOutput();
  }

  /**
   * Initializes this view by initializing its two sub views.
   */
  @Override public void initialize() throws InvalidMidiDataException {
    gv.initialize();
    mv.initialize();
  }
}
