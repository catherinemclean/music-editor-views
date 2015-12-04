package cs3500.music.view;

import cs3500.music.model.Pitch;
import cs3500.music.model.PitchImpl;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Represents the visual display of all the pitches in the piece.
 */
public final class ConcretePitchesPanel extends JPanel {

  /**
   * Represents the model that the pitches are from.
   */
  private final ViewModel m;

  /**
   * Creates a new panel with the pitches displayed from the given model.
   *
   * @param m the model that the pitches will be drawn from.
   */
  public ConcretePitchesPanel(ViewModel m) {
    Objects.requireNonNull(m);
    this.m = m;
  }

  /**
   * Draws the pitches.
   *
   * @param g the graphics that the pitches will be drawn on.
   */
  public void paint(Graphics g) {
    Pitch highest = Objects.requireNonNull(m.getHighestPitch());
    Pitch lowest = Objects.requireNonNull(m.getLowestPitch());
    int range = highest.getValue() - lowest.getValue();

    g.setColor(Color.white);
    g.fillRect(0, 0, ConcreteGuiViewPanel.BOX_SIZE * 2,
        (range + 2) * ConcreteGuiViewPanel.BOX_SIZE);
    for (int i = highest.getValue(); i >= lowest.getValue(); i--) {
      Pitch p = new PitchImpl(i);
      // draw pitch names
      g.setColor(Color.black);
      g.drawString(p.toString(), ConcreteGuiViewPanel.BOX_SIZE / 2,
          ConcreteGuiViewPanel.BOX_SIZE * (highest.getValue() - p.getValue() + 2));
    }
  }

  /**
   * Gets the size of the pitches.
   *
   * @return the Dimension of this pitch panel.
   */
  @Override public Dimension getPreferredSize() {
    Pitch highest = Objects.requireNonNull(m.getHighestPitch());
    Pitch lowest = Objects.requireNonNull(m.getLowestPitch());
    int range = highest.getValue() - lowest.getValue();
    return new Dimension(ConcreteGuiViewPanel.BOX_SIZE * 2,
        ConcreteGuiViewPanel.BOX_SIZE * (range + 10));
  }
}
