package cs3500.music.model;

import java.util.List;

/**
 * Represents the Model for a sheet of music. Contains all of the methods that a
 * sheet of music should be able to do
 */
public interface Model {

  /**
   * Represents the default measure if the user chooses not to input one.
   */
  int DEFAULT_MEASURE = 4;

  /**
   * Represents the default tempo if the user chooses not to input one.
   */
  int DEFAULT_TEMPO = 100000;

  /**
   * Gets the lowest pitch of the piece.
   *
   * @return the lowest pitch
   */
  Pitch getLowestPitch();

  /**
   * Gets the highest pitch of the piece.
   *
   * @return the highest pitch
   */
  Pitch getHighestPitch();

  /**
   * Gets the final beat of the piece.
   *
   * @return the final beat
   */
  int getFinalStartBeat();

  /**
   * Gets the beat after the last note of the piece ends.
   *
   * @return the final end beat
   */
  int getFinalEndBeat();

  /**
   * Gets the measure for the piece.
   *
   * @return the measure of the piece.
   */
  int getMeasure();

  /**
   * Gets the tempo of this piece.
   *
   * @return the tempo.
   */
  int getTempo();

  /**
   * Sets the tempo of this piece to the given tempo.
   *
   * @param tempo the new tempo of the piece.
   */
  void setTempo(int tempo);

  /**
   * Gets a list of notes that should be played at the given time.
   *
   * @param time the time at which to get the notes that start.
   * @return the list of notes that should be played at the given time.
   */
  List<Note> getNotesAtTime(int time);

  /**
   * Gets a list of notes that should be turned off at the given time.
   *
   * @param time the time at which to get the notes that end.
   * @return the list of notes that should be turned off at the given time.
   */
  List<Note> getEndNotesAtTime(int time);

  /**
   * Adds a note to the music sheet at the given pitch and startTime.
   * Updates this.lowestPitch, this.highestPitch, and this.finalBeat.
   *
   * @param pitch      the pitch of the new note
   * @param startTime  the start time of the new note
   * @param endTime    The end time of the new note
   * @param instrument The instrument that plays the new note
   * @param velocity   the velocity (volume of the new note
   * @throws IllegalAddException      if a note already exists at the given start time and
   *                                  pitch. Notes are allowed to be inserted over continuations
   *                                  of notes, because this can.
   *                                  happen in choirs or with different instruments.
   * @throws IllegalArgumentException if any inputs are less than 0, or if endTime is less
   *                                  than or equal to startTime, or if velocity or instrument
   *                                  is not within [0, 127].
   */
  void addNote(Pitch pitch, int startTime, int endTime, int instrument, int velocity);

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
  Note getNoteAt(Pitch pitch, int time, int instrument);

  /**
   * Get the note that starts or continues through the given pitch and time with the
   * given instrument.
   *
   * @param pitch The pitch of the note that we want to retrieve.
   * @param time The start time or the time the note is continuing.
   * @param instrument The instrument of the note we want to retrieve.
   * @return The Note that starts or continues at the given time played at the given pitch
   * with the given instrument
   * @throws cs3500.music.model.Model.IllegalAccessNoteException if there is no note
   * at the given pitch, time, instrument
   */
  Note getNoteIn(Pitch pitch, int time, int instrument);

  /**
   * Get the note that starts or continues through the given pitch and time.
   *
   * @param pitch The pitch of the note that we want to retrieve.
   * @param time The start time or the time the note is continuing.
   * @return The Note that starts or continues at the given time played at the given pitch.
   * @throws cs3500.music.model.Model.IllegalAccessNoteException if there is no note
   * at the given pitch and time
   */
  Note getNoteIn(Pitch pitch, int time);

  /**
   * Deletes the note at the specified time and pitch.
   *
   * @param pitch      the pitch location of the note to be deleted.
   * @param time       the timestamp of the first beat of the note to be deleted.
   * @param instrument the instrument of the note to be deleted.
   * @throws cs3500.music.model.Model.IllegalAccessNoteException if there is no note at the
   *                                                             given position.
   */
  void deleteNote(Pitch pitch, int time, int instrument);

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
  void editNoteStartTime(Pitch pitch, int currentStart, int newStart, int instrument);

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
  void editNoteEndTime(Pitch pitch, int currentStart, int newEnd, int instrument);

  /**
   * Adds all the notes from another piece of music to the end of this pieces
   * if atEnd is true, or consecutively if atEnd is false.
   *
   * @param allNotes all of the notes in a piece.
   * @param atEnd    if true, add all the notes to the end.
   *                 if false, add all the notes over the current notes.
   */
  void addAllNotes(List<Note> allNotes, boolean atEnd);


  /**
   * Returns this music sheet as a String with the fields and Notes listed
   * for testing purposes exclusively.
   *
   * @return the music sheet represented as a String
   */
  @Override String toString();

  /**
   * Indicates that the user attempted to add a note illegally.
   */
  class IllegalAddException extends IllegalStateException {

    IllegalAddException(String s) {
      super(s);
    }

    IllegalAddException() {
      super();
    }
  }


  /**
   * Indicates that the user attempted to access notes that were not there.
   */
  class IllegalAccessNoteException extends IllegalStateException {

    IllegalAccessNoteException(String s) {
      super(s);
    }

    IllegalAccessNoteException() {
      super();
    }
  }
}

