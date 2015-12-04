package cs3500.music.view.swingview;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Tone;
import cs3500.music.util.MouseHandler;
import cs3500.music.view.Constants;
import cs3500.music.view.GuiView;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class SwingView extends JFrame implements GuiView {
  private JScrollPane scrollPane;
  private JPanel root = new JPanel(new BorderLayout());
  private JFrame frame = new JFrame("Jav-insky");
  private MusicEditorModel model;
  static int currBeat = 0;

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

  private void createAndShowGUI() {
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setMaximumSize(new Dimension(Constants.MAX_WINDOW_WIDTH, Constants.MAX_WINDOW_HEIGHT));

    GridOfPlayablesPanel playablesPanel = new GridOfPlayablesPanel(model);
    Box pitchesPanel = drawPitches();
    Box timesBox = drawTimes();

    this.root.add(pitchesPanel, BorderLayout.WEST);
    this.root.add(timesBox, BorderLayout.NORTH);
    this.root.add(playablesPanel, BorderLayout.CENTER);

    this.scrollPane = new JScrollPane(root);
    this.scrollPane.getVerticalScrollBar().setUnitIncrement(4);
    this.scrollPane.getHorizontalScrollBar().setUnitIncrement(4);

    frame.add(this.scrollPane);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  private Box drawPitches() {
    Box pitches = Box.createVerticalBox();
    for (int i = model.getHighestPitch(); i >= model.getLowestPitch(); i--) {
      JLabel label = new JLabel(Tone.pitchNumToString(i));
      pitches.add(Box.createVerticalStrut(Constants.CELL_SIZE / 5));
      pitches.add(label);
    }
    return pitches;
  }

  private Box drawTimes() {
    Box times = Box.createHorizontalBox();
    times.add(Box.createHorizontalStrut(27));
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

  public void addMouseMotionListener(MouseMotionListener mouseHandler) {
    this.root.addMouseMotionListener(mouseHandler);
  }

  public void removeMouseListener(MouseHandler mouseHandler) {
    this.root.removeMouseListener(mouseHandler);
  }

  public KeyListener getKeyListener() {
    return this.getKeyListeners()[0];
  }

  public MouseListener getMouseListener() {
    return this.getMouseListeners()[0];
  }
}