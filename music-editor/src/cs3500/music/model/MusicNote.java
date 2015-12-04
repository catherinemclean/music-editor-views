package cs3500.music.model;

/**
 * Represents a cs3500.music.model.Note that is played and has a continuation
 */
public final class MusicNote implements Note {

  /**
   * The combination of the letter note and octave that the note is played at
   * Represented as an int that is the number of steps it is away from C at octave -1
   * C at octave -1 is the lowest note that can be played, and is represented as 0. Every
   * step (which can be from C to C#, C# to D, D to D#, D# to E, E to F, F to F#, F# to G
   * G to G#, G# to A, A to A#, A# to B, B to C, with A being in the next highest octave
   */
  private final Pitch pitch;
  //INVARIANT: must be positive

  /**
   * The timestamp for the first beat of this note
   */
  private final int startTime;
  //INVARIANT: Must be greater than zero
  //must be smaller than this.endTime

  /**
   * The timestamp for the beat immediately after this note ends
   */
  private final int endTime;
  //INVARIANT: must be a positive number
  //must be at least one larger than this.startTime


  /**
   * The instrument that plays this note
   * Represented as an int from Midi
   */
  private final int instrument;
  // INVARIANT: Must be between 0 to 127 (inclusive)

  private final int velocity;
  // INVARIANT: must be between 0 and 127 (inclusive)

  /**
   * Creates a new cs3500.music.model.MusicNote
   *
   * @param pitch      the letter note and octave this note is played at
   * @param startTime  the timestamp of the first beat of this note
   * @param endTime    the timestamp immediately after this note ends
   * @param instrument the instrument of this note
   * @param velocity   the velocity (volume) of this note
   * @throws IllegalArgumentException if the pitch, startTime, or endTime is less than zero
   *                                  or if the startTime is not smaller than the endTime, or
   *                                  if instrument is not within [0,127],
   *                                  or if velocity is not within [0, 127].
   */
  public MusicNote(Pitch pitch, int startTime, int endTime, int instrument, int velocity) {
    if (pitch == null || pitch.getValue() < 0 || pitch.getValue() > 128 ||
        startTime < 0 || endTime < 0 || endTime <= startTime || instrument < 0 || instrument > 127
        || velocity < 0 || velocity > 127) {
      throw new IllegalArgumentException("These are invalid inputs");
    }
    this.pitch = pitch;
    this.startTime = startTime;
    this.endTime = endTime;
    this.instrument = instrument;
    this.velocity = velocity;
  }

  /**
   * The pitch this note is at.
   *
   * @return the pitch this cs3500.music.model.Note is located at
   */
  public Pitch getPitch() {
    return new PitchImpl(pitch.getValue());
  }

  /**
   * Gets the timestamp this cs3500.music.model.Note starts at. If it's longer than one beat,
   * it returns the first beat
   *
   * @return The start time of this cs3500.music.model.Note
   */
  public int getStartTime() {
    return startTime;
  }

  /**
   * Gets the timestamp of this cs3500.music.model.Note immediately after it ends.
   *
   * @return the beat immediately after this note ends
   */
  public int getEndTime() {
    return endTime;
  }

  /**
   * Gets the instrument that plays this note
   *
   * @return the int representation of the instrument that plays this note
   */
  public int getInstrument() {
    return this.instrument;
  }

  /**
   *
   */
  public int getVelocity() {
    return this.velocity;
  }

  /**
   * Returns this cs3500.music.model.Note as a string for testing purposes
   *
   * @return the fields of this note as a string
   */
  public String toString() {
    return String.format("Pitch: %d, StartTime: %d, EndTime: %d", this.getPitch().getValue(),
        this.getStartTime(), this.getEndTime());
  }

  /**
   * Overrides the equals method
   *
   * @param other another object that could be a cs3500.music.model.Note
   * @return whether or not this object is equal to other
   */
  @Override public boolean equals(Object other) {
    if (other instanceof MusicNote) {
      MusicNote n = (MusicNote) other;
      return this.pitch.getValue() == n.getPitch().getValue() &&
          this.startTime == n.getStartTime() &&
          this.endTime == n.getEndTime() &&
          this.instrument == n.getInstrument() &&
          this.velocity == n.getVelocity();
    }
    return false;
  }

  /**
   * Overrides the hashCode method
   *
   * @return a hashcode for this music note
   */
  @Override public int hashCode() {
    return (this.pitch.getValue() * 199) +
        (this.startTime * 419) +
        (this.endTime * 683) +
        (this.instrument * 101) +
        (this.velocity * 103);

  }
}

