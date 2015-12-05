package cs3500.music.model;

import java.util.Set;

/**
 * Created by catherinemclean1 on 12/4/15.
 */
public class ModelToMusicEditorModelImpl implements MusicEditorModel {

  private final Model model;

  public ModelToMusicEditorModelImpl(Model model) {
    this.model = model;
  }

  @Override
  public boolean hasPlayable(Playable playable) {
    try {
      this.model.getNoteAt(new PitchImpl(playable.getPitch()),
          playable.getStart(),
          playable.getInstrument());
    }
    catch (Model.IllegalAccessNoteException ex) {
      return true;
    }
    return false;
  }

  @Override
  public Set<Playable> getPlayablesAt(int beat) {
    //TODO: MAke this thing
    //return this.model.getNo
    return null;
  }

  @Override
  public int getLowestPitch() {
    return this.model.getLowPitch().getValue();
  }

  @Override
  public int getHighestPitch() {
    return this.model.getLowPitch().getValue();
  }

  @Override
  public int getLastBeat() {
    return this.model.getFinalEndBeat();
  }

  @Override
  public int getTempo() {
    return this.model.getTempo();
  }
}
