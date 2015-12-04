package cs3500.music.view;


import com.sun.javaws.exceptions.InvalidArgumentException;
import com.sun.tools.corba.se.idl.InvalidArgument;
import cs3500.music.model.*;

import java.awt.Point;
import javax.sound.midi.InvalidMidiDataException;
import java.util.List;

/**
 * Represents a ViewModel that adds additional functionality to our model.
 * This functionality includes keeping track of selected notes, and keeping
 * a timestamp.
 */
public class GuiViewModel implements ViewModel {

  /**
   * The model to add functionality to
   */
  private Model m;

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
   * Constructs a new GuiViewModel that wraps around the given model
   *
   * @param m the model to which we are adding new functionality
   */
  public GuiViewModel(Model m) {
    this.m = m;
    this.curPitch = -1;
    this.curBeat = -1;
    this.curInstrument = -1;
    this.timeStamp = 0;
  }

  /**
   * Gets the lowest pitch of the piece.
   *
   * @return the lowest pitch
   */
  @Override public Pitch getLowestPitch() {
    return m.getLowestPitch();
  }

  /**
   * Gets the highest pitch of the piece.
   *
   * @return the highest pitch
   */
  @Override public Pitch getHighestPitch() {
    return m.getHighestPitch();
  }

  /**
   * Gets the final beat of the piece.
   *
   * @return the final beat
   */
  @Override public int getFinalStartBeat() {
    return m.getFinalStartBeat();
  }

  /**
   * Gets the beat after the last note of the piece ends.
   *
   * @return the final end beat
   */
  @Override public int getFinalEndBeat() {
    return m.getFinalEndBeat();
  }

  /**
   * Gets the measure for the piece.
   *
   * @return the measure of the piece
   */
  @Override public int getMeasure() {
    return m.getMeasure();
  }

  /**
   * Gets the tempo of this piece.
   *
   * @return the tempo
   */
  @Override public int getTempo() {
    return m.getTempo();
  }

  /**
   * Sets the tempo of this piece to the given tempo.
   *
   * @param tempo the new tempo of the piece
   */
  @Override public void setTempo(int tempo) {
    m.setTempo(tempo);
  }

  /**
   * Gets a list of notes that should be played at the given time.
   *
   * @return the list of notes that should be played at the given timestamp
   */
  @Override public List<Note> getNotesAtTime(int time) {
    return m.getNotesAtTime(time);
  }

  /**
   * Gets a list of notes that should be turned off at the given time.
   *
   * @param time the time at which to get the notes that end
   * @return the list of notes that should be turned off at the given time
   */
  @Override public List<Note> getEndNotesAtTime(int time) {
    return m.getEndNotesAtTime(time);
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
   * @throws IllegalAddException      if a note already exists at the given start time and
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
      m.addNote(pitch, startTime, endTime, instrument, velocity);
    } catch (IllegalAddException ex) {
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
   * @throws IllegalAccessNoteException if
   *                                    there is no note at the given pitch and time.
   */
  @Override public Note getNoteAt(Pitch pitch, int time, int instrument) {
    return m.getNoteAt(pitch, time, instrument);
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
    return m.getNoteIn(pitch, time, instrument);
  }

  /**
   * Get the note that starts or continues through the given pitch and time.
   *
   * @param pitch The pitch of the note that we want to retrieve.
   * @param time  The start time or the time the note is continuing.
   * @return The Note that starts or continues at the given time played at the given pitch.
   */
  @Override public Note getNoteIn(Pitch pitch, int time) {
    return m.getNoteIn(pitch, time);
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
    m.deleteNote(pitch, time, instrument);
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
   * @throws IllegalAccessNoteException if there is no note at this position to edit.
   */
  @Override public void editNoteStartTime(Pitch pitch, int currentStart, int newStart,
      int instrument) {
    m.editNoteStartTime(pitch, currentStart, newStart, instrument);
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
   * @throws IllegalAccessNoteException if there is no note at this position to edit.
   */
  @Override public void editNoteEndTime(Pitch pitch, int currentStart, int newEnd,
      int instrument) {
    m.editNoteEndTime(pitch, currentStart, newEnd, instrument);
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
    m.addAllNotes(allNotes, atEnd);
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
  @Override public void initialize() throws InvalidMidiDataException {
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
    this.curBeat = (x >= 50 && x <= (m.getFinalEndBeat() + 2) * ConcreteGuiViewPanel.BOX_SIZE) ?
        (x - 50) / ConcreteGuiViewPanel.BOX_SIZE :
        -1;
    this.curPitch =
        (y >= 25 && y <= ((m.getHighestPitch().getValue() - m.getLowestPitch().getValue() + 2)
            * ConcreteGuiViewPanel.BOX_SIZE)) ?
            m.getHighestPitch().getValue() - ((y - ConcreteGuiViewPanel.BOX_SIZE)
                / ConcreteGuiViewPanel.BOX_SIZE) :
            -1;
    try {
      this.curInstrument = m.getNoteIn(new PitchImpl(curPitch), curBeat).getInstrument();
    } catch (IllegalAccessNoteException ex) {
      this.curInstrument = -1;
    }
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
   * @throw IllegalArgumentException is the pitch is not in the board.
   */
  public void setCurPitch(int pitch) {
    if ((pitch < getLowestPitch().getValue() && pitch != -1) ||
        (pitch > getHighestPitch().getValue() && pitch != -1)) {
      throw new IllegalArgumentException("Pitch out of range!");
    }
    this.curPitch = pitch;
  }

  /**
   * Saves the beat of the current note.
   *
   * @param beat The beat of the note that is currently selected.
   * @throw IllegalArgumentException is the beat is not in the board.
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
