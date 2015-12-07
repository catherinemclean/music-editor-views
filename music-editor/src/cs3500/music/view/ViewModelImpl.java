package cs3500.music.view;

import java.util.Set;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Playable;

/** A concrete implementation of a ViewModel. Allows for read-only access to the
 * MusicEditorModel that it is constructed with. */
public class ViewModelImpl implements ViewModel {
  private final MusicEditorModel model;

  /** Creates a concrete ViewModel instance with the given model */
  public ViewModelImpl(MusicEditorModel model) {
    this.model = model;
  }

  @Override
  public boolean hasPlayable(Playable playable) {
    return this.model.hasPlayable(playable);
  }

  @Override
  public Set<Playable> getPlayablesAt(int beat) {
    return this.model.getPlayablesAt(beat);
  }

  @Override
  public int getLowestPitch() {
    return this.model.getLowestPitch();
  }

  @Override
  public int getHighestPitch() {
    return this.model.getLowestPitch();
  }

  @Override
  public int getLastBeat() {
    return this.model.getLastBeat();
  }

  @Override
  public int getTempo() {
    return this.model.getTempo();
  }
}
