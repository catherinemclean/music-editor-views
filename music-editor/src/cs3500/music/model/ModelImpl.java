package cs3500.music.model;

import java.util.*;

/**
 * Represents the model for a music sheet implementation
 */
public final class ModelImpl implements Model {

  /**
   * Maps all of the Notes that play at a specific time to that timestamp.
   * The key is the timestamp of the first beat of the cs3500.music.model.Note.
   * The value is every cs3500.music.model.Note that begins at that timestamp.
   */
  private Map<Integer, ArrayList<Note>> musicSheet;
  //INVARIANT: Each Key must be less than or equal to this.finalStartBeat

  /**
   * Represents how many beats are in a measure.
   */
  private final int measure;
  //INVARIANT: Must be greater than 0

  /**
   * Represents how many beats are in a minute for the piece.
   */
  private int tempo;
  //INVARIANT: Must be greater than 0

  /**
   * The lowest pitch that a note in the piece occurs at.
   * the smallest pitch value.
   * Negative 1 represents no pitch values.
   */
  private Pitch lowestPitch;
  //INVARIANT: Must not be smaller than negative one
  //Must be smaller than or equal to this.highestPitch

  /**
   * The highest pitch that a note in the piece occurs at.
   * the largest pitch value.
   */
  private Pitch highestPitch;
  //INVARIANT: Must not be smaller than negative
  //Must be larger than or equal to this.lowestPitch, or -1 if this.lowestPitch is -1
  // which represents that there are no notes in the piece.

  /**
   * The timestamp that the final start beat of the piece occurs at.
   */
  private int finalStartBeat;
  // INVARIANT: Must not be negative.

  /**
   * The timestamp that the final end beat or continuation of the piece occurs on.
   * One beat longer than the entire piece.
   */
  private int finalEndBeat;
  // INVARIANT: Must not be negative, must be larger than this.finalStartBeat

  /**
   * Makes a new cs3500.music.model.ModelImpl music sheet with the given measure.
   * Initializes all other fields to 0, since no notes have been added yet
   *
   * @param measure How many beats are in a measure for this piece
   * @param tempo   How many beats are in a minute for this piece
   * @throws IllegalArgumentException if given measure or tempo is not greater than 0
   */
  public ModelImpl(int measure, int tempo) {
    if (measure <= 0 || tempo <= 0) {
      throw new IllegalArgumentException("Measure and tempo must be positive");
    }
    this.measure = measure;
    this.tempo = tempo;
    this.lowestPitch = new PitchImpl(-1);
    this.highestPitch = new PitchImpl(-1);
    this.finalStartBeat = 0;
    this.finalEndBeat = 0;
    this.musicSheet = new HashMap<Integer, ArrayList<Note>>();
  }

  /**
   * Convenience constructor
   * Makes a new cs3500.music.model.ModelImpl music sheet with the default measure set to 4
   * and the default tempo set to 100000
   */
  public ModelImpl() {
    this(DEFAULT_MEASURE, DEFAULT_TEMPO);
  }

  /**
   * Gets the lowest pitch of the piece
   *
   * @return the lowest pitch
   * @throws cs3500.music.model.Model.IllegalAccessNoteException if the lowest pitch is null
   */
  public Pitch getLowestPitch() {
    return new PitchImpl(this.lowestPitch.getValue());
  }

  /**
   * Gets the measure for the piece
   *
   * @return the measure of the piece
   */
  public int getMeasure() {
    return this.measure;
  }

  /**
   * Gets the highest pitch of the piece
   *
   * @return the highest pitch
   * @throws cs3500.music.model.Model.IllegalAccessNoteException if this.highestPitch is null
   */
  public Pitch getHighestPitch() {
    return new PitchImpl(this.highestPitch.getValue());
  }

  /**
   * Gets the tempo of this piece
   *
   * @return the tempo
   */
  public int getTempo() {
    return this.tempo;
  }

  /**
   * Sets the tempo of this piece to the given tempo
   *
   * @param tempo the new tempo of the piece
   */
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  /**
   * Gets the final beat that a note starts on of the piece
   *
   * @return the final start beat
   */
  public int getFinalStartBeat() {
    return this.finalStartBeat;
  }

  /**
   * Gets the beat after the last note of the piece ends
   *
   * @return the final end beat
   */
  public int getFinalEndBeat() {
    return this.finalEndBeat;
  }

  /**
   * Gets a list of notes that should be played at the given time
   *
   * @return the list of notes that should be played at the given timestamp
   */
  public List<Note> getNotesAtTime(int time) {
    List<Note> copy = new ArrayList<>();
    if (!this.musicSheet.containsKey(time)) {
      return copy;
    }
    for (Note n : this.musicSheet.get(time)) {
      copy.add(new MusicNote(n.getPitch(), n.getStartTime(), n.getEndTime(), n.getInstrument(),
          n.getVelocity()));
    }
    return copy;
  }

  /**
   * Gets a list of notes that should be turned off at the given time.
   *
   * @param time the time at which to get the notes that end.
   * @return the list of notes that should be turned off at the given time.
   */
  public List<Note> getEndNotesAtTime(int time) {
    ArrayList<Note> result = new ArrayList<Note>();
    for (int i = 0; i <= time; i++) {
      if (this.musicSheet.containsKey(i)) {
        ArrayList<Note> notes = this.musicSheet.get(i);
        for (Note n : notes) {
          if (n.getEndTime() == time) {
            result.add(n);
          }
        }
      }
    }
    return result;
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
  public void addNote(Pitch pitch, int startTime, int endTime, int instrument, int velocity) {
    if (pitch == null || pitch.getValue() < 0 ||
        startTime < 0 || endTime < 0 || endTime <= startTime || instrument < 0 || instrument > 127
        || velocity < 0 || velocity > 127) {
      throw new IllegalArgumentException("These are not valid inputs");
    }
    if (this.lowestPitch == null || lowestPitch.getValue() == -1 ||
        pitch.getValue() < this.getLowestPitch().getValue()) {
      this.lowestPitch = new PitchImpl(pitch.getValue());
    }
    if (this.highestPitch == null || pitch.getValue() > this.getHighestPitch().getValue()) {
      this.highestPitch = new PitchImpl(pitch.getValue());
    }
    if (startTime > this.getFinalStartBeat()) {
      this.finalStartBeat = startTime;
    }
    if (endTime > this.getFinalEndBeat()) {
      this.finalEndBeat = endTime;
    }
    if (!this.musicSheet.containsKey(startTime)) {
      this.musicSheet.put(startTime, new ArrayList<Note>());
    }
    ArrayList<Note> notes = this.musicSheet.get(startTime);

    for (int i = 0; i < notes.size(); i++) {
      if (notes.get(i).getPitch().equals(pitch) && notes.get(i).getInstrument() == instrument) {
        throw new IllegalAddException();
      }
    }
    notes.add(new MusicNote(pitch, startTime, endTime, instrument, velocity));
  }

  /**
   * Gets the note at the specified starttime and pitch
   *
   * @param pitch      The pitch for the note you want to retrieve
   * @param time       The startTime of the note you want to retrieve
   * @param instrument The instrument of the note you want to retrieve
   * @return The Note at the given start time and pitch and instrument
   * @throws IllegalAccessNoteException if
   * @throws IllegalAccessNoteException if
   *                                    there is no note at the given pitch and time
   */
  public Note getNoteAt(Pitch pitch, int time, int instrument) {
    Objects.requireNonNull(pitch);
    if (!this.musicSheet.containsKey(time)) {
      throw new IllegalAccessNoteException("No notes at this time");
    }
    ArrayList<Note> notes = this.musicSheet.get(time);
    for (int i = 0; i < notes.size(); i++) {
      Note n = notes.get(i);
      if (n.getPitch().getValue() == pitch.getValue() && n.getInstrument() == instrument) {
        return new MusicNote(n.getPitch(), n.getStartTime(), n.getEndTime(), n.getInstrument(),
            n.getVelocity());
      }
    }
    throw new IllegalAccessNoteException("No notes at this pitch");
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
   * @throws cs3500.music.model.Model.IllegalAccessNoteException if there is no note
   *                                                             at the given pitch, time, instrument
   */
  public Note getNoteIn(Pitch pitch, int time, int instrument) {
    for (int i = 0; i <= time; i++) {
      if (this.musicSheet.containsKey(i)) {
        ArrayList<Note> notes = this.musicSheet.get(i);
        for (Note n : notes) {
          if (n.getEndTime() > time &&
              n.getPitch().equals(pitch) &&
              n.getInstrument() == instrument) {
            return new MusicNote(n.getPitch(), n.getStartTime(), n.getEndTime(), n.getInstrument(),
                n.getVelocity());
          }
        }
      }
    }
    throw new IllegalAccessNoteException("No notes at this pitch and time");
  }

  /**
   * Get the note that starts or continues through the given pitch and time.
   *
   * @param pitch The pitch of the note that we want to retrieve.
   * @param time  The start time or the time the note is continuing.
   * @return The Note that starts or continues at the given time played at the given pitch.
   * @throws cs3500.music.model.Model.IllegalAccessNoteException if there is no note
   *                                                             at the given pitch and time
   */
  public Note getNoteIn(Pitch pitch, int time) {
    for (int i = 0; i <= time; i++) {
      if (this.musicSheet.containsKey(i)) {
        ArrayList<Note> notes = this.musicSheet.get(i);
        for (Note n : notes) {
          if (n.getEndTime() > time && n.getPitch().equals(pitch)) {
            return new MusicNote(n.getPitch(), n.getStartTime(), n.getEndTime(), n.getInstrument(),
                n.getVelocity());
          }
        }
      }
    }
    throw new IllegalAccessNoteException("No notes at this pitch and time");
  }

  /**
   * Deletes the note at the specified time and pitch and instrument
   *
   * @param pitch      the pitch location of the note to be deleted
   * @param time       the timestamp of the first beat of the note to be deleted
   * @param instrument the instrument of the note to be deleted
   * @throws IllegalAccessNoteException if there is no note at the given position
   */
  public void deleteNote(Pitch pitch, int time, int instrument) {
    Objects.requireNonNull(pitch);
    if (!this.musicSheet.containsKey(time)) {
      throw new IllegalAccessNoteException();
    }
    ArrayList<Note> notes = this.musicSheet.get(time);
    for (int i = 0; i < notes.size(); i++) {
      if (notes.get(i).getPitch().equals(pitch) && notes.get(i).getInstrument() == instrument) {
        notes.remove(i);
        this.getNewFields();
        return;
      }
    }

    throw new IllegalAccessNoteException();
  }

  /**
   * Sets the new fields after a note is deleted
   */
  private void getNewFields() {
    int tempFinal = this.getFinalStartBeat();
    this.highestPitch = new PitchImpl(-1);
    this.lowestPitch = new PitchImpl(-1);
    this.finalStartBeat = 0;
    this.finalEndBeat = 0;
    for (int i = 0; i <= tempFinal; i++) {
      if (this.musicSheet.containsKey(i)) {
        Iterator<Note> iter = this.musicSheet.get(i).iterator();
        while (iter.hasNext()) {
          Note n = iter.next();
          if (this.lowestPitch == null || this.lowestPitch.getValue() == -1 ||
              n.getPitch().getValue() < this.getLowestPitch().getValue()) {
            this.lowestPitch = n.getPitch();
          }
          if (this.highestPitch == null || n.getPitch().getValue() > this.getHighestPitch()
              .getValue()) {
            this.highestPitch = n.getPitch();
          }
          if (n.getStartTime() > this.getFinalStartBeat()) {
            this.finalStartBeat = n.getStartTime();
          }
          if (n.getEndTime() > this.getFinalEndBeat()) {
            this.finalEndBeat = n.getEndTime();
          }
        }
      }
    }
  }

  /**
   * Edits the start time of the note at the given position
   *
   * @param pitch        the pitch of the note you want to edit
   * @param currentStart the current start time of the note you want to edit
   * @param newStart     the new start time for the note
   * @param instrument   the instrument of the note we want to edit
   * @throws IllegalArgumentException   if the new start time is less than 0 or greater
   *                                    than or equal to the end time or if there is already a
   *                                    note at this start position
   * @throws IllegalAccessNoteException if there is no note at this position to edit
   */
  public void editNoteStartTime(Pitch pitch, int currentStart, int newStart, int instrument) {
    Objects.requireNonNull(pitch);
    if (newStart < 0) {
      throw new IllegalArgumentException("This start time is not valid");
    }

    // if there's not a note at the given start, pitch, and instrument
    try {
      this.getNoteAt(pitch, currentStart, instrument);
    } catch (IllegalAccessNoteException e) {
      throw new IllegalAccessNoteException();
    }

    // if there's not already a note at the new start position
    try {
      this.getNoteAt(pitch, newStart, instrument);
    } catch (IllegalAccessNoteException e) {
      Note n = this.getNoteAt(pitch, currentStart, instrument);

      // addNote will map the new note to the correct start time
      this.addNote(pitch, newStart, n.getEndTime(), instrument, n.getVelocity());
      this.deleteNote(pitch, currentStart, instrument);
      if (this.getFinalStartBeat() < newStart) {
        this.finalStartBeat = newStart;
      }
      this.getNewFields();
      return;
    }
    throw new IllegalArgumentException("Already a note at this start position");
  }

  /**
   * Edits the start time of the note at the given position
   *
   * @param pitch        the pitch of the note you want to edit
   * @param currentStart the current start time of the note you want to edit
   * @param newEnd       the new end time for the note
   * @param instrument   represents the instrument of the note to edit
   * @throws IllegalArgumentException   if the new end time is less than 0 or less
   *                                    than or equal to the start time
   * @throws IllegalAccessNoteException if there is no note at this position to edit
   */
  public void editNoteEndTime(Pitch pitch, int currentStart, int newEnd, int instrument) {
    Objects.requireNonNull(pitch);
    Note n;
    try {
      n = this.getNoteAt(pitch, currentStart, instrument);
    } catch (IllegalAccessNoteException e) {
      throw new IllegalAccessNoteException();
    }
    this.deleteNote(pitch, currentStart, instrument);
    this.addNote(pitch, currentStart, newEnd, instrument, n.getVelocity());
    this.getNewFields();
  }

  /**
   * Adds all the notes from another piece of music to the end of this pieces
   * if atEnd is true, or consecutively if atEnd is false
   *
   * @param allNotes all of the notes in a piece
   * @param atEnd    if true, add all the notes to the end
   *                 if false, add all the notes over the current notes
   */
  public void addAllNotes(List<Note> allNotes, boolean atEnd) {
    Iterator<Note> iter = allNotes.iterator();
    int time = this.getFinalStartBeat();
    while (iter.hasNext()) {
      Note n = iter.next();
      if (atEnd) {
        this.addNote(n.getPitch(), n.getStartTime() + time, n.getEndTime() + time,
            n.getInstrument(), n.getVelocity());
      } else {
        try {
          this.addNote(n.getPitch(), n.getStartTime(), n.getEndTime(), n.getInstrument(),
              n.getVelocity());
        } catch (IllegalAddException e) {
          //do nothing, just don't add this note
        }
      }
    }
  }



  /**
   * Returns this music sheet as a String with the fields and Notes listed
   * for testing purposes exclusively
   *
   * @return the music sheet represented as a String
   */
  @Override public String toString() {
    String acc = String
        .format("Measure: %d\n Low Pitch: %d\n High Pitch: %d\n finalStartBeat: %d\n",
            this.getMeasure(), this.getLowestPitch().getValue(), this.getHighestPitch().getValue(),
            this.getFinalStartBeat());
    for (int i = 0; i <= this.getFinalStartBeat(); i++) {
      if (this.musicSheet.containsKey(i)) {
        Iterator<Note> iter = this.musicSheet.get(i).iterator();
        while (iter.hasNext()) {
          acc += iter.next().toString();
          acc += "\n";
        }
      }
    }
    return acc;
  }
}
