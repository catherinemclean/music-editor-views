package cs3500.music;


import cs3500.music.model.*;
import cs3500.music.view.*;
import cs3500.music.util.*;
import cs3500.music.controller.*;
import jdk.internal.util.xml.impl.Pair;


import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import javax.sound.midi.InvalidMidiDataException;

/**
 * Represents the music editor view
 */
public final class MusicEditor {

  /**
   * Runs the program automatically.
   *
   * @param args the command line arguments.
   * @throws IOException              if the input data is invalid
   * @throws InvalidMidiDataException if the midi data is invalid
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {

    // The file that will be played.
    String file;

    //Saves the first string to be typed as the file to be played
    try {
      file = args[0];
    } catch (IndexOutOfBoundsException ex) {
      System.out.println("You need to input a file to play.");
      return;
    }

    // Reads and parses the file and saves the info to the model
    BufferedReader f = new BufferedReader(new FileReader(file));
    Model m = MusicReader.parseFile(f, new MusicModelBuilder());

    // Initializes the controller for our composite view.
    Controller controller = new ControllerImpl(m);

    // runs the view
    controller.initialize();
  }
}
