package cs3500.music.model;

/**
 * Created by catherinemclean1 on 12/4/15.
 */
public class PlayableNote implements Playable {

  private final Note note;

  public PlayableNote(Note note) {
    this.note = note;
  }


  @Override public int getStart() {
    return note.getStartTime();
  }

  @Override public int getEnd() {
    return note.getEndTime() - 1;
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
