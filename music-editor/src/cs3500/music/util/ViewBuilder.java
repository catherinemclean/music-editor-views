package cs3500.music.util;

import cs3500.music.model.*;
import cs3500.music.view.View;

import javax.sound.midi.InvalidMidiDataException;

/**
 * Represents a builder a views based on a given input model and a
 * given input view type.
 */
public interface ViewBuilder {

  /**
   * Builds a new view with this builder's current specifications
   *
   * @return A new View depending on this ViewBuilder's specifications
   */
  View build();

  /**
   * Sets the model of this ViewBuilder to the given model
   *
   * @param m the model to set this builder to
   * @return this builder with the current model set to the given model
   */
  ViewBuilder setModel(Model m);

  /**
   * Sets the view of this ViewBuilder to the appropriate view, based on
   * the given string
   *
   * @param s the view to create, either "console", "visual", or "midi"
   * @return this builder with the current view set to the given view
   */
  ViewBuilder setView(String s) throws InvalidMidiDataException;

  /**
   * Sets the view of this ViewBuilder to be the test midi mode
   *
   * @return this builder
   */
  ViewBuilder setTesting() throws InvalidMidiDataException;
}
