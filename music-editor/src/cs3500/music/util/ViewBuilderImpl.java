package cs3500.music.util;

import cs3500.music.model.*;
import cs3500.music.view.*;
import cs3500.music.view.compositeui.CompositeView;
import cs3500.music.view.consoleui.ConsoleView;
import cs3500.music.view.midiui.MIDIView;
import cs3500.music.view.swingview.SwingView;

import javax.sound.midi.InvalidMidiDataException;

/**
 * Represents a builder of views. View can be built with a model, and
 * a specific type of view (visual, console, or midi)
 */

public final class ViewBuilderImpl implements ViewBuilder {

  /**
   * The view of this builder
   */
  private MusicEditorView v;

  /**
   * The model of this builder
   */
  private MusicEditorModel m;

  /**
   * Builds a new view with this builder's current specifications
   *
   * @return A new View depending on this ViewBuilder's specifications
   */
  @Override public MusicEditorView build() {
    return v;
  }

  /**
   * Sets the model of this ViewBuilder to the given model
   *
   * @param m the model to set this builder to
   * @return this builder with the current model set to the given model
   */
  @Override public ViewBuilder setModel(MusicEditorModel m) {
    this.m = m;
    return this;
  }

  /**
   * Sets the view of this ViewBuilder to the appropriate view, based on
   * the given string
   *
   * @param s the view to create, either "console", "visual", or "midi"
   * @return this builder with the current view set to the given view
   */
  @Override public ViewBuilder setView(String s) throws InvalidMidiDataException {
    switch (s) {
      case "console":
        ConsoleView.Builder b = new ConsoleView.Builder();
        this.v = b.build();
        //this.v = new ConsoleView(new ModelToViewImpl(m));
        return this;
      case "visual":
        this.v = new SwingView();
        return this;
      case "midi":
        this.v = new MIDIView();
        return this;
      case "composite":
        this.v = new CompositeView(new SwingView(), new MIDIView());
        return this;
      default:
        throw new IllegalArgumentException("Invalid view");
    }
  }

  /**
   * Sets the view of this ViewBuilder to be a midi test view
   *
   * @return this builder
   */
  /*public ViewBuilder setTesting() throws InvalidMidiDataException {
    this.v = new MidiViewImpl(m, true);
    return this;
  }*/
}
