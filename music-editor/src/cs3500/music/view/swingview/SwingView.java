package cs3500.music.view.swingview;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.util.MouseHandler;
import cs3500.music.view.Constants;
import cs3500.music.view.GuiView;

/**
 * A view for MusicEditorModels that renders all the playables in the model on a grid, according
 * to each playable's respective pitch and beat, with pitches as the Y-Axis and beats as the
 * X-Axis.
 */
public class SwingView extends JFrame implements GuiView {
  private JPanel root = new JPanel(new BorderLayout(2, 0));
  private JScrollPane scrollPane;
  private JFrame frame = new JFrame("Jav-insky");
  private MusicEditorModel model;
  static int currBeat = 0;

  /**
   * Constructs a SwingView, sets the default close operation to exit, and the maximum size
   * to be the size of the screen
   */
  public SwingView() {
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setMaximumSize(new Dimension(Constants.MAX_WINDOW_WIDTH, Constants.MAX_WINDOW_HEIGHT));
  }

  @Override
  public void render(MusicEditorModel model) {
    this.model = model;
    createAndShowGUI();
  }

  @Override
  public void update(int beat) {
    SwingView.currBeat = beat;
    if (SwingView.currBeat >= Constants.MAX_BEAT_VISIBLE) {
      scrollPane.getHorizontalScrollBar().setValue(beat * Constants.CELL_SIZE);
    }
  }

  @Override
  public void scrollLeft() {
    int hScrollVal = this.scrollPane.getHorizontalScrollBar().getValue();
    this.scrollPane.getHorizontalScrollBar().setValue(hScrollVal - Constants.CELL_SIZE);
  }

  @Override
  public void scrollRight() {
    int hScrollVal = this.scrollPane.getHorizontalScrollBar().getValue();
    this.scrollPane.getHorizontalScrollBar().setValue(hScrollVal + Constants.CELL_SIZE);
  }

  @Override
  public void scrollUp() {
    int hScrollVal = this.scrollPane.getVerticalScrollBar().getValue();
    this.scrollPane.getVerticalScrollBar().setValue(hScrollVal - Constants.CELL_SIZE);
  }

  @Override
  public void scrollDown() {
    int hScrollVal = this.scrollPane.getVerticalScrollBar().getValue();
    this.scrollPane.getVerticalScrollBar().setValue(hScrollVal + Constants.CELL_SIZE);
  }

  /** Draws all components of the GUI, including all the playables, the pitches, and the beats */
  private void createAndShowGUI() {
    GridOfPlayablesPanel playablesPanel = new GridOfPlayablesPanel(model);
    PitchesPanel pitches = new PitchesPanel(model);
    Box timesBox = createBeatsBox();

    playablesPanel.setBackground(Constants.BACKGROUND_COLOR);
    pitches.setBackground(Constants.BACKGROUND_COLOR);
    timesBox.setBackground(Constants.BACKGROUND_COLOR);

    this.root.add(playablesPanel);
    this.scrollPane = new JScrollPane(root);

    this.scrollPane.setColumnHeaderView(timesBox);
    this.scrollPane.setRowHeaderView(pitches);

    this.scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    this.scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

    frame.add(this.scrollPane);

    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  /** Creates a Horizontal Box containing beats spaced 16 beats apart*/
  private Box createBeatsBox() {
    Box times = Box.createHorizontalBox();
    for (int i = 0; i <= model.getLastBeat(); i++) {
      if (i % 16 == 0) {
        Box labelBox = Box.createHorizontalBox();
        labelBox.setMinimumSize(new Dimension(Constants.CELL_SIZE * 4, Constants.CELL_SIZE));
        labelBox.setMaximumSize(new Dimension(Constants.CELL_SIZE * 4, Constants.CELL_SIZE));
        labelBox.setPreferredSize(new Dimension(Constants.CELL_SIZE * 4, Constants.CELL_SIZE));
        JLabel label = new JLabel(Integer.toString(i));
        labelBox.add(label);
        times.add(labelBox);
        i+=3;
      } else {
        times.add(Box.createHorizontalStrut(Constants.CELL_SIZE));
      }
    }
    return times;
  }

  @Override
  public void addKeyListener(KeyListener keyboardHandler) {
    this.frame.addKeyListener(keyboardHandler);
  }

  @Override
  public void removeKeyListener(KeyListener keyboardHandler) {
    this.frame.removeKeyListener(keyboardHandler);
  }

  @Override
  public void addMouseListener(MouseListener mouseHandler) {
    this.root.addMouseListener(mouseHandler);
  }

  @Override
  public void removeMouseListener(MouseListener mouseHandler) {
    this.root.removeMouseListener(mouseHandler);
  }
}