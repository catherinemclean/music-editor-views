package cs3500.music.model;

/**
 * Represents a Pitch for a Note
 */
public interface Pitch {

  /**
   * Returns the numeric value of this pitch, representing the distance from C in octave -1
   *
   * @return the numeric value of this pitch
   */
  int getValue();

  /**
   * Returns the string version of this pitch
   *
   * @return this pitch as a string
   */
  String toString();
}
