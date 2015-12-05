package cs3500.music.model;

import java.util.Set;

/**
 * Created by catherinemclean1 on 12/4/15.
 */
public interface MusicEditorModel {

  boolean hasPlayable(Playable playable);

  Set<Playable> getPlayablesAt(int beat);

  int getLastBeat();

  int getTempo();

  void addNote(int pitch, int start, int end, int instrument, int volume);
}
