package cs3500.music.view;

import cs3500.music.model.*;

import java.util.Objects;

/**
 * Represents the model as a console text view
 */
public final class ConsoleView implements View {

  /**
   * represents the model to be visually represented
   */
  private Model model;

  /**
   * Represents the model to view adapter for this.model
   */
  private ModelToView mtv;

  /**
   * Creates a new console view
   *
   * @param mtv represents the model to be visually displayed in the console
   */
  public ConsoleView(ModelToView mtv) {
    Objects.requireNonNull(mtv);
    this.mtv = mtv;
  }

  /**
   * Prints out the model as a string in the console view
   */
  public void initialize() {
    // print the header
    System.out.print(this.mtv.printPitches());
    System.out.print("\n");

    // print the score
    for (int i = 0; i < this.mtv.getLength(); i++) {
      System.out.print(String.format("%4d", i));
      System.out.print(this.mtv.printRow(i));
      System.out.print("\n");
    }
  }
}
