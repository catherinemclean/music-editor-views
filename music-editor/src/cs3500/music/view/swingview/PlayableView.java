package cs3500.music.view.swingview;

import java.awt.*;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Playable;
import cs3500.music.view.Constants;


public class PlayableView {
  public void paintPlayable(MusicEditorModel model, Playable playable, Graphics g) {
    // Draw the head of the playable
    g.setColor(Constants.CELL_HEAD_COLOR);
    g.fillRect(playable.getStart() * Constants.CELL_SIZE,
        (model.getHighestPitch() - playable.getPitch()) * Constants.CELL_SIZE,
        Constants.CELL_SIZE, Constants.CELL_SIZE);

    // Draw the tail of the playable
    g.setColor(Constants.CELL_TAIL_COLOR);
    g.fillRect((playable.getStart() + 1) * Constants.CELL_SIZE,
        (model.getHighestPitch() - playable.getPitch()) * Constants.CELL_SIZE,
        (playable.getEnd() - playable.getStart() - 1) * Constants.CELL_SIZE, Constants.CELL_SIZE);
  }
}