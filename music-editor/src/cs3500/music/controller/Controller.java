package cs3500.music.controller;

import javax.sound.midi.InvalidMidiDataException;

/**
 * Controls the interactions between the Model and the View.
 */
public interface Controller {

  /**
   * Initializes this controller.
   *
   * @throws InvalidMidiDataException if the midi data is invalid.
   */
  void initialize() throws InvalidMidiDataException;
}
