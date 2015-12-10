package cs3500.music.model;

import java.util.*;

/**
 * An adapter from Model to MusicEditorModel
 */
public class ModelToMusicEditorModelImpl implements MusicEditorModel {

  /**
   * The model that we will delegate to.
   */
  private final Model model;

  /**
   * Constructs a ModelToMusicEditorModelImpl with the given model
   *
   * @param model the model to adapt around
   */
  public ModelToMusicEditorModelImpl(Model model) {
    this.model = model;
  }

  @Override
  public boolean hasPlayable(Playable playable) {
    try {
      this.model.getNoteAt(new PitchImpl(playable.getPitch()),
          playable.getStart(),
          playable.getInstrument());
    }
    catch (Model.IllegalAccessNoteException ex) {
      return true;
    }
    return false;
  }

  @Override
  public Set<Playable> getPlayablesAt(int beat) {
    //TODO: Make this thing
    Set<Playable> answer = new HashSet<>();
    for (int i = 0; i <= beat; i++) {
      //if (this.musicSheet.containsKey(i)) {
        List<Note> notes = model.getNotesAtTime(beat);
        for (Note n : notes) {
          if (n.getEndTime() >= beat) {
             answer.add(new PlayableNote(n));
          }
        }
      //}
    }
    return answer;
  }

  @Override
  public int getLowestPitch() {
    return this.model.getLowPitch().getValue();
  }

  @Override
  public int getHighestPitch() {
    return this.model.getHighPitch().getValue();
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
   * @return the measure of the piece.
   */
  @Override public int getMeasure() {
    return this.model.getMeasure();
  }

  @Override
  public int getLastBeat() {
    return this.model.getFinalEndBeat();
  }

  @Override
  public int getTempo() {
    return this.model.getTempo();
  }

  /**
   * Sets the tempo of this piece to the given tempo.
   *
   * @param tempo the new tempo of the piece.
   */
  @Override public void setTempo(int tempo) {
    this.model.setTempo(tempo);
  }

  /**
   * Gets a list of notes that should be played at the given time.
   *
   * @param time the time at which to get the notes that start.
   * @return the list of notes that should be played at the given time.
   */
  @Override public List<Note> getNotesAtTime(int time) {
    return this.model.getNotesAtTime(time);
  }

  /**
   * Gets a list of notes that should be turned off at the given time.
   *
   * @param time the time at which to get the notes that end.
   * @return the list of notes that should be turned off at the given time.
   */
  @Override public List<Note> getEndNotesAtTime(int time) {
    return this.model.getEndNotesAtTime(time);
  }

  /**
   * Adds a note to the music sheet at the given pitch and startTime.
   * Updates this.lowestPitch, this.highestPitch, and this.finalBeat.
   *
   * @param pitch      the pitch of the new note
   * @param startTime  the start time of the new note
   * @param endTime    The end time of the new note
   * @param instrument The instrument that plays the new note
   * @param velocity   the velocity (volume of the new note
   * @throws IllegalAddException if a note already exists at the given start time and
   *                                   pitch. Notes are allowed to be inserted over continuations
   *                                   of notes, because this can.
   *                                   happen in choirs or with different instruments.
   * @throws IllegalArgumentException  if any inputs are less than 0, or if endTime is less
   *                                   than or equal to startTime, or if velocity or instrument
   *                                   is not within [0, 127].
   */
  @Override public void addNote(Pitch pitch, int startTime, int endTime, int instrument,
      int velocity) {
    this.model.addNote(pitch, startTime, endTime, instrument, velocity);
  }

  /**
   * Gets the note at the specified starttime and pitch
   *
   * @param pitch      The pitch for the note you want to retrieve.
   * @param time       The startTime of the note you want to retrieve.
   * @param instrument the instrument of the note you want to retrieve.
   * @return The Note at the given start time and pitch and instrument.
   * @throws IllegalAccessNoteException if
   *                                          there is no note at the given pitch and time.
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
   * @throws IllegalAccessNoteException if there is no note
   *                                          at the given pitch, time, instrument
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
   * @throws IllegalAccessNoteException if there is no note
   *                                          at the given pitch and time
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
   * @throws IllegalAccessNoteException if there is no note at the
   *                                          given position.
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
   * @throws IllegalArgumentException         if the new start time is less than 0 or greater
   *                                          than or equal to the end time.
   * @throws IllegalAccessNoteException if there is no note at this position to edit.
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
   * @throws IllegalArgumentException         if the new end time is less than 0 or less
   *                                          than or equal to the start time.
   * @throws IllegalAccessNoteException if there is no note at this position to edit.
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
   */
  @Override public void addAllNotes(List<Note> allNotes, boolean atEnd) {
    this.model.addAllNotes(allNotes, atEnd);
  }

  public void addNote(int pitch, int start, int end, int instrument, int volume) {
    this.model.addNote(new PitchImpl(pitch), start, end, instrument, volume);
  }
}
