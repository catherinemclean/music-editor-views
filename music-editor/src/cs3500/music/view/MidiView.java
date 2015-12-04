package cs3500.music.view;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

/**
 * Represents a midi view.
 */
public interface MidiView extends View {

  /**
   * Audibly plays all of the notes at the given time.
   *
   * @param time The time that this view is currently at.
   * @throws InvalidMidiDataException if the Midi data is invalid.
   * @throws MidiUnavailableException if Midi is unavailable.
   */
  void recordNotes(int time) throws InvalidMidiDataException, MidiUnavailableException;

  /**
   * Pauses this view.
   */
  void pause();

  /**
   * Returns whether or not this view is currently paused.
   *
   * @return true if this view is paused or false if it is playing.
   */
  boolean isPaused();

  /**
   * Returns the midi view as output to the console for testing purposes.
   *
   * @return The midi view represented as a string.
   */
  String getOutput();
}
