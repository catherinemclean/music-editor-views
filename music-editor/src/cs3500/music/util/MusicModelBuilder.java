package cs3500.music.util;

import cs3500.music.model.Model;
import cs3500.music.model.ModelImpl;
import cs3500.music.model.PitchImpl;

/**
 * Builds a model for a music sheet
 */
public final class MusicModelBuilder implements CompositionBuilder<Model> {

  /**
   * Represents the model that will be built
   */
  Model m = new ModelImpl();

  /**
   * Returns the model to be built
   *
   * @return the model we will use to build the music sheet
   */
  @Override public Model build() {
    return m;
  }

  /**
   * Sets the tempo and returns this builder with the new tempo
   *
   * @param tempo The speed, in microseconds per beat
   * @return this builder
   */
  @Override public CompositionBuilder<Model> setTempo(int tempo) {
    m.setTempo(tempo);
    return this;
  }

  /**
   * Adds a note and returns this builder
   *
   * @param start      The start time of the note, in beats
   * @param end        The end time of the note, in beats
   * @param instrument The instrument number (to be interpreted by MIDI)
   * @param pitch      The pitch (in the range [0, 127], where 60 represents C4,
   *                   the middle-C on a piano)
   * @param volume     The volume (in the range [0, 127])
   * @return this builder
   */
  @Override public CompositionBuilder<Model> addNote(int start, int end, int instrument, int pitch,
      int volume) {
    m.addNote(new PitchImpl(pitch), start, end, instrument, volume);
    return this;
  }
}
