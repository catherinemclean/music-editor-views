package cs3500.music.view;

import java.util.Set;

import cs3500.music.model.Playable;

/** An interface for read-only MusicEditorModels to let views use */
public interface ViewModel {
  /** Determines if this piece contains the given playable */
  boolean hasPlayable(Playable playable);

  /**
   * Gets the playables at the given beat
   * @throws IllegalArgumentException if beat < 0 or beat > the last beat of the piece
   */
  Set<Playable> getPlayablesAt(int beat);

  /** Get the lowest pitch of this piece, as an integer from 0-127 */
  int getLowestPitch();

  /** Get the highest pitch of this piece, as an integer from 0-127 */
  int getHighestPitch();

  /** Get the last beat of this piece */
  int getLastBeat();

  /** Gets the tempo for this piece. */
  int getTempo();
}
