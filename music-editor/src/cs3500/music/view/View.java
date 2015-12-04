package cs3500.music.view;

import javax.sound.midi.InvalidMidiDataException;

/**
 * Represents different physical ways to represent the music sheet
 */
public interface View {

  /**
   * Initializes this view
   */
  void initialize() throws InvalidMidiDataException;
}

