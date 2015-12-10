package cs3500.music.model;

/**
 * An adapter from Notes to Playables.
 */
public class PlayableNote implements Playable {

  /**
   * The note to adapt
   */
  private final Note note;

  /**
   * Constructs a new PlayableNote with the given Note
   *
   * @param note the note to adapt
   */
  public PlayableNote(Note note) {
    this.note = note;
  }

  @Override public int getStart() {
    return note.getStartTime();
  }

  @Override public int getEnd() {
    return note.getEndTime();
  }

  @Override public int getPitch() {
    return note.getPitch().getValue();
  }

  @Override public int getVolume() {
    return note.getVelocity();
  }

  @Override public int getInstrument() {
    return note.getInstrument();
  }
}
