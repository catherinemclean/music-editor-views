package cs3500.music.model;

import java.util.List;
import java.util.Objects;

/**
 * Represents the implementation of the model to view adapter
 */
public final class ModelToViewImpl implements ModelToView {

  /**
   * The board represented as nested arrays of NoteStatus. Each space represents the status of
   * the note at that pitch and time
   */
  private NoteStatus[][] board;

  /**
   * The model that the this.board will be adapted from
   */
  private final Model model;

  /**
   * The highest Pitch in the piece
   */
  private final Pitch highest;

  /**
   * The lowest Pitch in the piece
   */
  private final Pitch lowest;

  /**
   * Makes a new model to view adapter using the given model
   *
   * @param m the model to create this adapter for
   */
  public ModelToViewImpl(Model m) {
    this.model = Objects.requireNonNull(m);
    this.highest = Objects.requireNonNull(model.getHighPitch());
    this.lowest = Objects.requireNonNull(model.getLowPitch());
    this.board = new NoteStatus[m.getFinalEndBeat()][highest.getValue() - lowest.getValue() + 1];

    this.build();
  }

  /**
   * Initializes the board so they all contain rests
   */
  private void initialize() {
    for (int i = 0; i < this.board.length; i++) {
      NoteStatus[] pitches = this.board[i];
      for (int j = 0; j < pitches.length; j++) {
        this.board[i][j] = NoteStatus.REST;
      }
    }
  }

  /**
   * Sets each entry in the board to the corresponding value in the model
   */
  private void build() {
    this.initialize();
    for (int i = 0; i <= this.model.getFinalStartBeat(); i++) {
      List<Note> ns = this.model.getNotesAtTime(i);
      for (Note n : ns) {
        int pIndex = n.getPitch().getValue() - lowest.getValue();
        int start = n.getStartTime();
        this.board[start][pIndex] = NoteStatus.PLAYED;
        for (int j = start + 1; j < n.getEndTime(); j++) {
          this.board[j][pIndex] = NoteStatus.SUSTAINED;
        }
      }
    }
  }

  /**
   * Gets the number of beats in this model
   *
   * @return the length of this model, in beats
   */
  public int getLength() {
    return this.board.length;
  }

  /**
   * Prints the console view at the given row
   *
   * @param r the beat at which to print the row
   * @return the printed string representing the model at row r
   * @throws IllegalArgumentException if r is not a row in this board
   */
  public String printRow(int r) {
    if (r < 0 || r >= this.board.length) {
      throw new IllegalArgumentException("Invalid row");
    }
    NoteStatus[] row = this.board[r];
    String result = "";
    for (int i = 0; i < row.length; i++) {
      result += this.board[r][i].toString();
    }
    return result;
  }

  /**
   * Prints all the pitches in this model
   *
   * @return a string of all the pitches in the model, each 3 characters long
   */
  public String printPitches() {
    String result = "    ";
    for (int i = model.getLowPitch().getValue(); i <= model.getHighPitch().getValue(); i++) {
      //Convert pitch to letter
      //all need three characters
      result += new PitchImpl(i).toString();
    }
    return result;
  }

  /**
   * Represents the state of a note
   */
  public enum NoteStatus {
    /**
     * Represents a note that start on this beat
     */
    PLAYED("  X "),

    /**
     * Represents a note that continues on this beat
     */
    SUSTAINED("  | "),

    /**
     * Represents a rest for this beat
     */
    REST("    ");

    /**
     * Represents the String value of the note, which will be printed in console view
     */
    private final String value;

    /**
     * Makes a note status setting the given string
     *
     * @param s the string that represents the value of the note status
     */
    NoteStatus(String s) {
      this.value = s;
    }

    /**
     * Returns the string value of this enumeration
     *
     * @return the string this.value
     */
    public String toString() {
      return this.value;
    }
  }
}

