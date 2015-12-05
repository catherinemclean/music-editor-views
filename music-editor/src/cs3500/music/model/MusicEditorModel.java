package cs3500.music.model;

import java.util.Set;

/**
 * Created by catherinemclean1 on 12/4/15.
 */
public interface MusicEditorModel extends Model {

  boolean hasPlayable(Playable playable);

  Set<Playable> getPlayablesAt(int beat);

  int getHighestPitch();

  int getLowestPitch();

  int getLastBeat();

  int getTempo();

  int getLowestPitch();

  int getHighestPitch();

  void addNote(int pitch, int start, int end, int instrument, int volume);
}
