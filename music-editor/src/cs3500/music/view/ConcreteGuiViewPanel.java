package cs3500.music.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import cs3500.music.model.*;
import cs3500.music.controller.*;

import javax.swing.*;

/**
 * Draws the model using Swing
 */
public final class ConcreteGuiViewPanel extends JPanel {

  /**
   * Represents the model to be drawn
   */
  private final ViewModel m;

  /**
   * Represents the size of each grid square
   */
  public final static int BOX_SIZE = 25;

  /**
   * Creates a new gui panel with the given model represented
   *
   * @param m the model to be drawn
   */
  public ConcreteGuiViewPanel(ViewModel m) {
    Objects.requireNonNull(m);
    this.m = m;
  }

  /**
   * Draws the model, this.m
   *
   * @param g the graphics object that draws everything
   */
  @Override public void paint(Graphics g) {

    super.paintComponent(g);

    Pitch highest = Objects.requireNonNull(m.getHighestPitch());
    Pitch lowest = Objects.requireNonNull(m.getLowestPitch());
    int height = (highest.getValue() - lowest.getValue() + 1) * BOX_SIZE;

    // draws the beat numbers
    for (int i = 1; i <= m.getFinalEndBeat(); i++) {
      if (i % 4 == 0) {
        g.drawString(Integer.toString(i), 50 + (BOX_SIZE * i), 20);
      }
    }

    for (int i = highest.getValue(); i >= lowest.getValue(); i--) {
      Pitch p = new PitchImpl(i);
      // draws the horizontal grid
      g.drawRect(50, BOX_SIZE * (highest.getValue() - p.getValue()),
          m.getFinalEndBeat() * BOX_SIZE, BOX_SIZE);
    }

    // draws the vertical grid
    for (int i = 0; i < m.getFinalEndBeat(); i++) {
      g.drawRect(BOX_SIZE * (i + 2), BOX_SIZE, BOX_SIZE, height);
    }

    // draws all the notes
    for (int i = 0; i <= m.getFinalStartBeat(); i++) {
      List<Note> ns = m.getNotesAtTime(i);
      for (Note n : ns) {
        // draw the start of the note black
        g.setColor(Color.black);
        g.fillRect((n.getStartTime() + 2) * BOX_SIZE,
            BOX_SIZE * (highest.getValue() - n.getPitch().getValue() + 1), BOX_SIZE, BOX_SIZE);

        // draw the sustained portion of the note green
        g.setColor(Color.green);
        g.fillRect(((n.getStartTime() + 3) * BOX_SIZE),
            BOX_SIZE * (highest.getValue() - n.getPitch().getValue() + 1),
            (n.getEndTime() - n.getStartTime() - 1) * BOX_SIZE, BOX_SIZE);
      }
    }

    // draws selected box
    int beat = m.getCurBeat();
    int pitch = m.getCurPitch();
    if (beat != -1 && pitch != -1) {
      try {
        Note n = m.getNoteIn(new PitchImpl(pitch), beat);
        g.setColor(Color.cyan);
        g.fillRect((n.getStartTime() + 2) * BOX_SIZE,
            BOX_SIZE * (highest.getValue() - n.getPitch().getValue() + 1),
            (n.getEndTime() - n.getStartTime()) * BOX_SIZE, BOX_SIZE);
      } catch (Model.IllegalAccessNoteException e) {
        g.setColor(Color.cyan);
        g.fillRect((beat + 2) * BOX_SIZE, BOX_SIZE * (highest.getValue() - pitch + 1), BOX_SIZE,
            BOX_SIZE);
      }
    }
    addLine(g);

    this.revalidate();
    this.repaint();
  }

  /**
   * Adds the red line on top of the given graphic at the note currently playing
   *
   * @param g The graphics to draw onto.
   */
  public void addLine(Graphics g) {
    Pitch highest = Objects.requireNonNull(m.getHighestPitch());
    Pitch lowest = Objects.requireNonNull(m.getLowestPitch());

    int time = m.getTimeStamp();
    g.setColor(Color.red);
    if (time > 0) {
      g.drawLine((time * BOX_SIZE) + 50 - BOX_SIZE / 2, lowest.getValue() - BOX_SIZE,
          (time * BOX_SIZE) + 50 - BOX_SIZE / 2,
          (highest.getValue() - lowest.getValue() + 2) * BOX_SIZE);
    }
  }
}
