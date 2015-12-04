package cs3500.music.model;

/**
 * Represents a Pitch with a number and string representation
 */
public final class PitchImpl implements Pitch {

  /**
   * Represents the numeric representation of the pitch, which is the distance from
   * note C in octave -1
   */
  private final int pitch;
  //INVARIANT: cannot be smaller than negative 1 or larger than 127
  // Can only be negative 1 to represent a lack of Pitches or Notes in
  // a particular piece of music

  /**
   * Creates a new pitch with the given int value
   *
   * @param pitch the int value for this pitch
   * @throws IllegalArgumentException if the given pitch is smaller than -1 or
   *                                  greater than 127
   */
  public PitchImpl(int pitch) {
    if (pitch < -1 || pitch > 127) {
      throw new IllegalArgumentException("This is not a valid pitch");
    }
    this.pitch = pitch;
  }

  /**
   * Returns the numeric value of this pitch, representing the distance from C in octave -1
   *
   * @return the numeric value of this pitch
   */
  public int getValue() {
    return this.pitch;
  }

  /**
   * Returns the string version of this pitch
   *
   * @return this pitch as a string
   */
  public String toString() {
    String result = "";
    int octave = (pitch / 12) - 1;
    int p = pitch % 12;
    if (octave >= 0 && octave <= 9) {
      result += " ";
    }
    switch (p) {
      case 0:
        result += " C";
        break;
      case 1:
        result += "C#";
        break;
      case 2:
        result += " D";
        break;
      case 3:
        result += "D#";
        break;
      case 4:
        result += " E";
        break;
      case 5:
        result += " F";
        break;
      case 6:
        result += "F#";
        break;
      case 7:
        result += " G";
        break;
      case 8:
        result += "G#";
        break;
      case 9:
        result += " A";
        break;
      case 10:
        result += "A#";
        break;
      case 11:
        result += " B";
        break;
      default:
        result += "";
        break;
    }
    result += octave;
    return result;
  }

  /**
   * Overrides the equals method to set equality for pitches
   *
   * @param obj the other object to be compared
   * @return true if the given object is equal to this pitch
   */
  @Override public boolean equals(Object obj) {
    if (!(obj instanceof PitchImpl)) {
      return false;
    }
    PitchImpl p = (PitchImpl) obj;
    return p.getValue() == this.getValue();
  }

  /**
   * Overrides the hashcode method to set a unique-ish value for this pitch
   *
   * @return the hashcode for this pitch
   */
  @Override public int hashCode() {
    return this.getValue() * 31;
  }
}

