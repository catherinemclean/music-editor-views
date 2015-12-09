package cs3500.music.model;

import cs3500.music.model.*;

import java.awt.Point;
import javax.sound.midi.InvalidMidiDataException;
import java.util.List;
import java.util.Set;

import cs3500.music.view.Constants;

/**
 * Represents a ViewModel that adds additional functionality to our model.
 * This functionality includes keeping track of selected notes, and keeping
 * a timestamp.
 */
public class GuiViewModel implements ViewModel {

  /**
   * The model to add functionality to
   */
  private MusicEditorModel model;

  //private Model m;

  /**
   * The current pitch that is selected
   * <p>
   * INVARIANT: always within m.getHighestPitch() and m.getLowestPitch(), or -1
   * if no pitch is selected
   */
  private int curPitch;

  /**
   * The current beat that is selected
   * <p>
   * INVARIANT: always within 0 and m.getFinalEndBeat(), or -1 if no beat is selected
   */
  private int curBeat;

  /**
   * The current instrument of the note that was selected
   * <p>
   * INVARIANT: always within 1 and 16, or -1 if no note was selected
   */
  private int curInstrument;

  /**
   * Represents the timestamp that the piece is currently at
   * <p>
   * INVARIANT: Must be within 0 and m.getFinalEndBeat();
   */
  private int timeStamp;

  /**
   * Represents the top-left corner of the viewable screen
   */
  private Point topleft;

  /**
   * Constructs a new GuiViewModel that wraps around the given model
   *
   * @param m the model to which we are adding new functionality
   */
  public GuiViewModel(Model m) {
    this.model = new ModelToMusicEditorModelImpl(m);
    this.curPitch = -1;
    this.curBeat = -1;
    this.curInstrument = -1;
    this.timeStamp = 0;
    this.topleft = new Point(0, 0);
  }

  @Override public boolean hasPlayable(Playable playable) {
    return model.hasPlayable(playable);
  }

  @Override public Set<Playable> getPlayablesAt(int beat) {
    return model.getPlayablesAt(beat);
  }

  /**
   * Gets the lowest pitch of the piece.
   *
   * @return the lowest pitch
   */
  @Override public int getLowestPitch() {
    return model.getLowestPitch();
  }

  /**
   * Gets the highest pitch of the piece.
   *
   * @return the highest pitch
   */
  @Override public int getHighestPitch() {
    return model.getHighestPitch();
  }

  /**
   * Gets the lowest pitch of the piece.
   *
   * @return the lowest pitch
   */
  @Override public Pitch getLowPitch() {
    return this.model.getLowPitch();
  }

  /**
   * Gets the highest pitch of the piece.
   *
   * @return the highest pitch
   */
  @Override public Pitch getHighPitch() {
    return this.model.getHighPitch();
  }


  @Override public int getLastBeat() {
    return this.model.getLastBeat();
  }

  /**
   * Gets the final beat of the piece.
   *
   * @return the final beat
   */
  @Override public int getFinalStartBeat() {
    return this.model.getFinalStartBeat();
  }

  /**
   * Gets the beat after the last note of the piece ends.
   *
   * @return the final end beat
   */
  @Override public int getFinalEndBeat() {
    return this.model.getFinalEndBeat();
  }

  /**
   * Gets the measure for the piece.
   *
   * @return the measure of the piece
   */
  @Override public int getMeasure() {
    return this.model.getMeasure();
  }

  /**
   * Gets the tempo of this piece.
   *
   * @return the tempo
   */
  @Override public int getTempo() {
    return this.model.getTempo();
  }

  /**
   * Sets the tempo of this piece to the given tempo.
   *
   * @param tempo the new tempo of the piece
   */
  @Override public void setTempo(int tempo) {
    this.model.setTempo(tempo);
  }

  /**
   * Gets a list of notes that should be played at the given time.
   *
   * @return the list of notes that should be played at the given timestamp
   */
  @Override public List<Note> getNotesAtTime(int time) {
    return this.model.getNotesAtTime(time);
  }

  /**
   * Gets a list of notes that should be turned off at the given time.
   *
   * @param time the time at which to get the notes that end
   * @return the list of notes that should be turned off at the given time
   */
  @Override public List<Note> getEndNotesAtTime(int time) {
    return this.model.getEndNotesAtTime(time);
  }

  /**
   * Adds a note to the music sheet at the given pitch and startTime
   * Updates this.lowestPitch, this.highestPitch, and this.finalBeat
   *
   * @param pitch      the pitch of the new note
   * @param startTime  the start time of the new note
   * @param endTime    The end time of the new note
   * @param instrument The instrument that plays the new note
   * @param velocity   the velocity (volume of the new note
   * @throws Model.IllegalAddException      if a note already exists at the given start time and
   *                                  pitch. Notes are allowed to be inserted over continuations
   *                                  of notes, because this can
   *                                  happen in choirs or with different instruments.
   * @throws IllegalArgumentException if any inputs are less than 0, or if endTime is less
   *                                  than or equal to startTime, or if velocity or instrument
   *                                  is not within [0, 127]
   */
  @Override public void addNote(Pitch pitch, int startTime, int endTime, int instrument,
      int velocity) {
    try {
      this.model.addNote(pitch, startTime, endTime, instrument, velocity);
    } catch (Model.IllegalAddException ex) {
      //do nothing
    }
  }

  /**
   * Adds a note to the music sheet at the given pitch and startTime
   * Updates this.lowestPitch, this.highestPitch, and this.finalBeat
   *
   * @param pitch      the pitch of the new note
   * @param startTime  the start time of the new note
   * @param endTime    The end time of the new note
   * @param instrument The instrument that plays the new note
   * @param velocity   the velocity (volume of the new note
   * @throws Model.IllegalAddException      if a note already exists at the given start time and
   *                                  pitch. Notes are allowed to be inserted over continuations
   *                                  of notes, because this can
   *                                  happen in choirs or with different instruments.
   * @throws IllegalArgumentException if any inputs are less than 0, or if endTime is less
   *                                  than or equal to startTime, or if velocity or instrument
   *                                  is not within [0, 127]
   */
  @Override public void addNote(int pitch, int startTime, int endTime, int instrument,
      int velocity) {
    try {
      this.model.addNote(new PitchImpl(pitch), startTime, endTime, instrument, velocity);
    } catch (Model.IllegalAddException ex) {
      //do nothing
    }
  }

  /**
   * Gets the note at the specified starttime and pitch
   *
   * @param pitch      The pitch for the note you want to retrieve.
   * @param time       The startTime of the note you want to retrieve.
   * @param instrument the instrument of the note you want to retrieve.
   * @return The Note at the given start time and pitch and instrument.
   * @throws Model.IllegalAccessNoteException if
   *                                    there is no note at the given pitch and time.
   */
  @Override public Note getNoteAt(Pitch pitch, int time, int instrument) {
    return this.model.getNoteAt(pitch, time, instrument);
  }

  /**
   * Get the note that starts or continues through the given pitch and time with the
   * given instrument.
   *
   * @param pitch      The pitch of the note that we want to retrieve.
   * @param time       The start time or the time the note is continuing.
   * @param instrument The instrument of the note we want to retrieve.
   * @return The Note that starts or continues at the given time played at the given pitch
   * with the given instrument
   */
  @Override public Note getNoteIn(Pitch pitch, int time, int instrument) {
    return this.model.getNoteIn(pitch, time, instrument);
  }

  /**
   * Get the note that starts or continues through the given pitch and time.
   *
   * @param pitch The pitch of the note that we want to retrieve.
   * @param time  The start time or the time the note is continuing.
   * @return The Note that starts or continues at the given time played at the given pitch.
   */
  @Override public Note getNoteIn(Pitch pitch, int time) {
    return this.model.getNoteIn(pitch, time);
  }

  /**
   * Deletes the note at the specified time and pitch.
   *
   * @param pitch      the pitch location of the note to be deleted.
   * @param time       the timestamp of the first beat of the note to be deleted.
   * @param instrument the instrument of the note to be deleted.
   * @throws cs3500.music.model.Model.IllegalAccessNoteException if there is no note at the
   *                                                             given position.
   */
  @Override public void deleteNote(Pitch pitch, int time, int instrument) {
    this.model.deleteNote(pitch, time, instrument);
  }

  /**
   * Edits the start time of the note at the given position.
   *
   * @param pitch        the pitch of the note you want to edit.
   * @param currentStart the current start time of the note you want to edit.
   * @param newStart     the new start time for the note.
   * @param instrument   the instrument of the note you want to edit.
   * @throws IllegalArgumentException   if the new start time is less than 0 or greater
   *                                    than or equal to the end time.
   * @throws Model.IllegalAccessNoteException if there is no note at this position to edit.
   */
  @Override public void editNoteStartTime(Pitch pitch, int currentStart, int newStart,
      int instrument) {
    this.model.editNoteStartTime(pitch, currentStart, newStart, instrument);
  }

  /**
   * Edits the start time of the note at the given position.
   *
   * @param pitch        the pitch of the note you want to edit.
   * @param currentStart the current start time of the note you want to edit.
   * @param newEnd       the new end time for the note.
   * @param instrument   the instrument of the note you want to edit.
   * @throws IllegalArgumentException   if the new end time is less than 0 or less
   *                                    than or equal to the start time.
   * @throws Model.IllegalAccessNoteException if there is no note at this position to edit.
   */
  @Override public void editNoteEndTime(Pitch pitch, int currentStart, int newEnd,
      int instrument) {
    this.model.editNoteEndTime(pitch, currentStart, newEnd, instrument);
  }

  /**
   * Adds all the notes from another piece of music to the end of this pieces
   * if atEnd is true, or consecutively if atEnd is false.
   *
   * @param allNotes all of the notes in a piece.
   * @param atEnd    if true, add all the notes to the end.
   *                 if false, add all the notes over the current notes.
   */
  @Override public void addAllNotes(List<Note> allNotes, boolean atEnd) {
    this.model.addAllNotes(allNotes, atEnd);
  }

  /**
   * Gets the current timestamp of the piece
   *
   * @return the current timestamp
   */
  public int getTimeStamp() {
    return this.timeStamp;
  }

  /**
   * Advance the timestamp of this piece by 1;
   */
  public void advanceTimestamp() {
    if (this.timeStamp < getFinalEndBeat()) {
      this.timeStamp++;
    }
  }

  /**
   * Sets the timestamp of this piece of this piece to the given timestamp
   *
   * @param t the new timestamp
   */
  public void setTimeStamp(int t) {
    if (t > getFinalEndBeat() || t < 0) {
      throw new IllegalArgumentException("Invalid timestamp");
    }
    this.timeStamp = t;
  }

  /**
   * Resets the timestamp of this piece to 0
   */
  public void resetTimestamp() {
    this.timeStamp = 0;
  }

  /**
   * Initializes this view model
   *
   * @throws InvalidMidiDataException if the midi data is invalid
   */
  public void initialize() throws InvalidMidiDataException {
    //do nothing, no initializing needed
  }

  /**
   * Sends in the location of the mouse click to set the current Note that is being
   * selected.
   *
   * @param x The x coordinate of the mouse click.
   * @param y The y coordinate of the mouse click.
   */
  public void setCurrent(int x, int y) {
    this.curBeat = (x >= 0 && x <= (this.model.getFinalEndBeat() + 2)
        * Constants.CELL_SIZE) ? x / Constants.CELL_SIZE : -1;
    this.curPitch =
        (y >= 0 && y <= ((this.model.getHighPitch().getValue() -
            this.model.getLowPitch().getValue() + 2) * Constants.CELL_SIZE)) ?
            this.model.getHighPitch().getValue() - (y
                / Constants.CELL_SIZE) : -1;
    try {
      this.curInstrument = this.model.getNoteIn(new PitchImpl(curPitch), curBeat).getInstrument();
    } catch (Model.IllegalAccessNoteException ex) {
      this.curInstrument = -1;
    }
    System.out.println("beat: " + curBeat + "  pitch: " + curPitch);
  }

  /**
   * Gets the pitch of the note that is currently selected.
   *
   * @return The value of the pitch of the note that is currently selected.
   */
  public int getCurPitch() {
    return this.curPitch;
  }

  /**
   * Gets the beat of the note that is currently selected.
   *
   * @return The beat of the note that is currently selected.
   */
  public int getCurBeat() {
    return this.curBeat;
  }

  /**
   * Gets the instrument of the note that is currently selected.
   *
   * @return The instrument of the note that is currently selected.
   */
  public int getCurInstrument() {
    return this.curInstrument;
  }

  /**
   * Saves the pitch of the current note.
   *
   * @param pitch The pitch of the note that is currently selected.
   */
  public void setCurPitch(int pitch) {
    if (pitch < getLowPitch().getValue() && pitch > getHighPitch().getValue() &&
        pitch != -1) {
      throw new IllegalArgumentException("Pitch out of range!");
    }
    this.curPitch = pitch;
  }

  /**
   * Saves the beat of the current note.
   *
   * @param beat The beat of the note that is currently selected.
   */
  public void setCurBeat(int beat) {
    if (beat > getFinalEndBeat() || beat < -1) {
      throw new IllegalArgumentException("Beat out of bounds!");
    }
    this.curBeat = beat;
  }

  /**
   * Saves the instrument of the current note.
   *
   * @param instrument The instrument of the note that is currently selected.
   */
  public void setCurInstrument(int instrument) {
    this.curInstrument = instrument;
  }
}
