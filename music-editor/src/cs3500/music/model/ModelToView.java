package cs3500.music.model;

/**
 * Represents the Model in a format to be used by the view
 * Model to view adapter
 */
public interface ModelToView {

  /**
   * Gets the number of beats in this model
   *
   * @return the length of this model, in beats
   */
  int getLength();

  /**
   * Prints the console view at the given row
   *
   * @param r the beat at which to print the row
   * @return the printed string representing the model at row r
   * @throws IllegalArgumentException if r is not a row in this board
   */
  String printRow(int r);

  /**
   * Gets the number of beats in this model
   *
   * @return the length of this model, in beats
   */
  String printPitches();
}
