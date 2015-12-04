package cs3500.music.model;

/**
 * Represents a cs3500.music.model.Note, which is anything at some point on the music sheet
 */
public interface Note {

  /**
   * Gets the pitch this note is at.
   *
   * @return the pitch this cs3500.music.model.Note is located at
   */
  Pitch getPitch();

  /**
   * Gets the timestamp this cs3500.music.model.Note starts at. If it's longer than one beat,
   * it returns the first beat
   *
   * @return The start time of this cs3500.music.model.Note
   */
  int getStartTime();

  /**
   * Gets the timestamp of this cs3500.music.model.Note immediately after it ends.
   *
   * @return The beat immediately after the note ends
   */
  int getEndTime();

  /**
   * Gets the instrument that plays this note
   *
   * @return the int representation of the instrument that plays this note
   */
  int getInstrument();

  /**
   * Gets the volume of this note
   *
   * @return the volume (velocity) of this note
   */
  int getVelocity();

  /**
   * Returns this cs3500.music.model.Note as a string for testing purposes
   *
   * @return the fields of this note as a string
   */
  String toString();

}
