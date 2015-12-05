package cs3500.music.view.swingview;

import java.awt.*;

import javax.swing.*;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Playable;
import cs3500.music.view.Constants;

/**
 * A dummy view that simply draws a string
 */
public final class GridOfPlayablesPanel extends JPanel {
  private final MusicEditorModel model;
  private PlayableView playableView = new PlayableView();

  public GridOfPlayablesPanel(MusicEditorModel model) {
    this.model = model;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    paintPlayables(g);
    paintGrid(g);
    drawLine(g, SwingView.currBeat);
    this.repaint();
  }

  private void paintPlayables(Graphics g) {
    super.paintComponent(g);

    // Draw all the playables
    for (int i = 0; i < model.getLastBeat(); i++) {
      for (Playable p : model.getPlayablesAt(i)) {
        if (p.getStart() == i) {
          playableView.paintPlayable(this.model, p, g);
        }
      }
    }
  }

  private void paintGrid(Graphics g) {
    g.setColor(Constants.GRID_COLOR);
    // Horizontal Lines
    for (int i = 0; i <= model.getHighestPitch() - model.getLowestPitch() + 1; i++) {
      int y = i * Constants.CELL_SIZE;
      if ((model.getHighestPitch() - i + 1) % 12 == 0) {
        ((Graphics2D) g).setStroke(new BasicStroke(3));
        g.drawLine(0, y, model.getLastBeat() * Constants.CELL_SIZE, y);
      } else {
        ((Graphics2D) g).setStroke(new BasicStroke(2));
        g.drawLine(0, y, model.getLastBeat() * Constants.CELL_SIZE, y);
      }
    }

    // Vertical Lines
    for (int i = 0; i <= model.getLastBeat() + 1; i++) {
      if (i % 4 == 0 || i == model.getLastBeat()) {
        g.drawLine(i * Constants.CELL_SIZE, 0, i * Constants.CELL_SIZE, (model.getHighestPitch() -
            model.getLowestPitch() + 1) * Constants.CELL_SIZE);
      }
    }
  }

  private void drawLine(Graphics g, int beat) {
    g.setColor(Color.RED);
    g.drawLine(beat * Constants.CELL_SIZE, 0, beat * Constants.CELL_SIZE,
        model.getHighestPitch() * Constants.CELL_SIZE);
  }
}
