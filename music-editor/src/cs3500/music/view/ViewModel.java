package cs3500.music.view;

import java.util.Set;

import cs3500.music.model.Playable;

/**
 * Created by sean on 11/23/15.
 */
public interface ViewModel {
  /**
   * Determines if this piece contains the given playable
   * @param playable the playable to check
   * @return whether or not this piece contains the given playable
   */
  boolean hasPlayable(Playable playable);

  /**
   * Gets the playables at the given beat
   *
   * @param beat the beat to get the playables at
   * @return the playables at the given beat
   * @throws IllegalArgumentException if beat < 0 or beat > the last beat of the piece
   */
  Set<Playable> getPlayablesAt(int beat);

  /**
   * Get the lowest pitch of this piece
   *
   * @return the lowest pitch of this piece
   */
  int getLowestPitch();

  /**
   * Get the highest pitch of this piece
   *
   * @return the highest pitch of this piece
   */
  int getHighestPitch();

  /**
   * Get the last beat of this piece
   *
   * @return the last beat of this piece
   */
  int getLastBeat();

  /**
   * Gets the tempo for this piece.
   *
   * @return the tempo for this piece
   */
  int getTempo();
}
