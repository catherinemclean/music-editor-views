package cs3500.music.model;

import java.util.Set;

/**
 * Represents a MusicEditorModel that works with the new views
 */
public interface MusicEditorModel extends Model {

  boolean hasPlayable(Playable playable);

  Set<Playable> getPlayablesAt(int beat);

  int getHighestPitch();

  int getLowestPitch();

  int getLastBeat();

  int getTempo();

  void addNote(int pitch, int start, int end, int instrument, int volume);
}
