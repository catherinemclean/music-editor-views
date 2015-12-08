package cs3500.music.view.swingview;

import java.awt.*;

import javax.swing.*;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Tone;
import cs3500.music.view.Constants;

/**
 * a JPanel that knows how to draw pitches
 */
public final class PitchesPanel extends JPanel {
  private final MusicEditorModel model;

  public PitchesPanel(MusicEditorModel model) {
    this.setPreferredSize(new Dimension(35,
        (model.getHighestPitch() - model.getLowestPitch() + 1) * Constants.CELL_SIZE));
    this.model = model;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, Constants.CELL_SIZE));
    g2d.setRenderingHint(
        RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

    for (int i = 0; i <= model.getHighestPitch() - model.getLowestPitch() + 1; i++) {
      String pitch = Tone.pitchNumToString(model.getHighestPitch() - i + 1);
      g2d.drawString(pitch, 0, i * Constants.CELL_SIZE);
    }

    this.repaint();
  }
}
