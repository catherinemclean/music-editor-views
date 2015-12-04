package cs3500.music.view.consoleui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Playable;
import cs3500.music.model.Tone;
import cs3500.music.view.MusicEditorView;

import static java.util.Objects.requireNonNull;

/**
 * A console view for Music Editor Models. Heads of notes are represented by 'X's and tails are
 * represented by '|'s.
 */
public class ConsoleView implements MusicEditorView {
  private final Appendable output;

  /**
   * Constructs a new ConsoleView with the given output stream
   *
   * @param output the output stream to append the view onto
   */
  private ConsoleView(Appendable output) {
    this.output = output;
  }

  /**
   * Renders MusicEditorModels to the console, using System.out as the output stream
   */
  public static final class Builder {
    private Appendable output = System.out;

    public Builder output(Appendable output) {
      this.output = requireNonNull(output);
      return this;
    }

    public MusicEditorView build() {
      return new ConsoleView(this.output);
    }
  }

  /**
   * Draw the entire model
   *
   * @param model the model to render
   */
  @Override
  public void render(MusicEditorModel model) {
    int lowestPitch = model.getLowestPitch();
    int highestPitch = model.getHighestPitch();
    int lastBeat = model.getLastBeat();
    int maxBeatDigits = Integer.toString(lastBeat).length();

    ArrayList<ArrayList<String>> playableStrings = initGrid(model);

    // Spaces before pitch names
    append(String.format("%" + maxBeatDigits + "s ", " "));

    // Pitch names
    for (int i = lowestPitch; i <= highestPitch; i++) {
      append(String.format("%-4s", Tone.pitchNumToString(i)));
    }
    append('\n');

    for (int i = 0; i < lastBeat; i++) {
      // Beat indices
      append(String.format("%" + maxBeatDigits + "d ", i));
      Set<Playable> playables = model.getPlayablesAt(i);

      for (Playable p : playables) {
        int noteStart = p.getStart();
        int noteEnd = p.getEnd();
        int notePitch = p.getPitch();

        playableStrings.get(noteStart).set(notePitch - lowestPitch, "X   ");
        for (int j = noteStart + 1; j < noteEnd; j++) {
          if (playableStrings.get(j).get(notePitch - lowestPitch).equals("    ")) {
            playableStrings.get(j).set(notePitch - lowestPitch, "|   ");
          }
        }
      }

      for (String s : playableStrings.get(i)) {
        append(s);
      }
      append('\n');
    }

    System.out.println(output);
  }

  /**
   * Initializes the grid with empty spaces
   *
   * @param model the model to base the initialized grid off of
   * @return a grid of empty spaces, the dimensions of the model
   */
  private ArrayList<ArrayList<String>> initGrid(MusicEditorModel model) {
    int lastBeat = model.getLastBeat();
    int lowestPitch = model.getLowestPitch();
    int highestPitch = model.getHighestPitch();
    ArrayList<ArrayList<String>> playableStrings = new ArrayList<>();

    for (int i = 0; i <= lastBeat; i++) {
      playableStrings.add(new ArrayList<>());
      for (int j = lowestPitch; j <= highestPitch; j++) {
        playableStrings.get(i).add("    ");
      }
    }
    return playableStrings;
  }

  /**
   * Appends the given character sequence onto the output stream
   * @param charSequence the character sequence to append
   * @throws IOException if the output stream is invalid
   */
  private void append(CharSequence charSequence) {
    try {
      output.append(charSequence);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Appends the given character onto the output stream
   * @param c the character to append
   * @throws IOException if the output stream is invalid
   */
  private void append(char c) {
    try {
      output.append(c);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}