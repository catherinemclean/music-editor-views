package cs3500.music.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    //TODO: Make this thing
    Set<Playable> answer = new HashSet<>();
    for (int i = 0; i <= beat; i++) {
      //if (this.musicSheet.containsKey(i)) {
        List<Note> notes = model.getNotesAtTime(beat);
        for (Note n : notes) {
          if (n.getEndTime() >= beat) {
             answer.add(new PlayableNote(n));
          }
        }
      //}
    }
    return answer;
  }

  @Override
  public int getLowestPitch() {
    return this.model.getLowestPitch().getValue();
  }

  @Override
  public int getHighestPitch() {
    return this.model.getLowestPitch().getValue();
  }

  @Override
  public int getLastBeat() {
    return this.model.getFinalEndBeat();
  }

  @Override
  public int getTempo() {
    return this.model.getTempo();
  }

  public void addNote(int pitch, int start, int end, int instrument, int volume) {
    this.model.addNote(new PitchImpl(pitch), start, end, instrument, volume);
  }
}
