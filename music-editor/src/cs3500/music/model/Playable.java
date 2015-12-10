package cs3500.music.model;

/**
 * Represents anything that can be played by a model or a view.
 */
public interface Playable {

  /**
   * Gets the timestamp at which this Playable starts. If it's longer than one beat,
   * it returns the first beat
   *
   * @return The start time of this Playable
   */
  int getStart();

  /**
   * Gets the timestamp of this Playable immediately after it ends.
   *
   * @return The beat immediately after the playable ends
   */
  int getEnd();

  /**
   * Gets the pitch of this playable.
   *
   * @return the pitch this Playable is located at
   */
  int getPitch();

  /**
   * Gets the volume of this note
   *
   * @return the volume (velocity) of this note
   */
  int getVolume();

  /**
   * Gets the instrument that plays this note
   *
   * @return the int representation of the instrument that plays this note
   */
  int getInstrument();
}
