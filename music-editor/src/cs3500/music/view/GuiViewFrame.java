package cs3500.music.view;

import cs3500.music.model.*;
import cs3500.music.controller.*;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener; // Possibly of interest for handling mouse events
import java.util.Objects;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

/**
 * A graphic view represented using Swing
 */
public final class GuiViewFrame extends javax.swing.JFrame implements GuiView {

  /**
   * Represents the graphic display of the model
   */
  private final JPanel displayPanel; // You may want to refine this to a subtype of JPanel

  /**
   * Represents the graphoc display for the pitches.
   * They scroll down with the model, but are always displayed on screen.
   */
  private final JPanel displayPitches;

  /**
   * Represents the JScrollPane for the actual notes.
   */
  private JScrollPane js;

  /**
   * Represents the JScrollPane for the pitches.
   * Only scrolls vertically, also scrolls in sync with this.js.
   */
  private JScrollPane pjs;

  /**
   * Represents the model to be displayed
   */
  private final ViewModel model;

  /**
   * Creates a new gui view
   *
   * @param m the model to be graphically represented
   */
  public GuiViewFrame(ViewModel m) {
    this.model = Objects.requireNonNull(m);
    this.displayPanel = new ConcreteGuiViewPanel(this.model);
    this.displayPanel.addMouseListener(new MouseHandler(this));

    this.displayPitches = new ConcretePitchesPanel(this.model);

    this.setFocusable(true);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.displayPanel.setPreferredSize(this.getPreferredSize());


    this.displayPitches.setPreferredSize(this.getPreferredSize());
    JScrollPane jScrollPane = new JScrollPane();
    js = new JScrollPane();
    JViewport pitchesView = new JViewport();
    pitchesView.setView(displayPitches);
    pitchesView.setViewPosition(new Point(0, 0));
    js.setViewport(pitchesView);



    jScrollPane.setViewportBorder(new LineBorder(Color.RED));
    JViewport viewport = new JViewport();
    viewport.setView(displayPanel);
    viewport.setViewPosition(new Point(0, 0));
    jScrollPane.setViewport(viewport);
    jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    js.getVerticalScrollBar().setModel(jScrollPane.getVerticalScrollBar().getModel());
    js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

    this.add(jScrollPane, BorderLayout.CENTER);
    this.add(js, BorderLayout.WEST);
    this.setSize(700, 500);
    this.pjs = js;
    this.js = jScrollPane;
  }

  /**
   * Runs this visual graphical view using midi
   */
  @Override public void initialize() {
    this.setVisible(true);
  }

  /**
   * Get the preferred size of this.displayPanel
   *
   * @return the dimension of how much of the display panel should be displayed
   */
  @Override public Dimension getPreferredSize() {
    return new Dimension(model.getFinalEndBeat() * ConcreteGuiViewPanel.BOX_SIZE + 100,
        ConcreteGuiViewPanel.BOX_SIZE * (model.getHighestPitch().getValue() - model
            .getLowestPitch().getValue()) + 100);
  }

  /**
   * Adds a key listener to this gui view.
   *
   * @param k The Key Listener to be added.
   */
  public void addListener(KeyListener k) {
    this.addKeyListener(k);
  }

  /**
   * Adds a key listener to this gui view.
   *
   * @param m The Key Listener to be added.
   */
  public void addListener(MouseListener m) { this.addMouseListener(m); }

  /**
   * Removes the given key listener from this gui view.
   *
   * @param kl The Key Listener to be removed.
   */
  public void removeKeyListener(KeyListener kl) {
    super.removeKeyListener(kl);
  }

  /**
   * Removes the given key listener from this gui view.
   *
   * @param ml The Key Listener to be removed.
   */
  public void removeMouseListener(MouseListener ml) {super.removeMouseListener(ml); }

  /**
   * Sends in the location of the mouse click to set the current Note that is being
   * selected.
   *
   * @param x The x coordinate of the mouse click.
   * @param y The y coordinate of the mouse click.
   */
  public void setCurrent(int x, int y) {
    this.model.setCurrent(x, y);
    this.repaint();
  }

  /**
   * Repaints the GUI view.
   *
   * @param playing Whether or not the model is currently playing.
   */
  public void paintAgain(boolean playing) {
    this.displayPanel.setPreferredSize(this.getPreferredSize());
    JViewport jv = this.js.getViewport();
    int limit = (int) (jv.getViewPosition().getX() + jv.getExtentSize().getWidth());
    int curX = (model.getTimeStamp() + 2) * ConcreteGuiViewPanel.BOX_SIZE;
    int curY = (int) jv.getViewPosition().getY();

    if (playing) {
      if (curX > limit) {
        jv.setViewPosition(new Point(limit - (ConcreteGuiViewPanel.BOX_SIZE * 2), curY));
      }

      if (curX < (int) (jv.getViewPosition().getX())) {
        jv.setViewPosition(new Point(curX, curY));
      }
    }

    repaint();
    this.displayPanel.repaint();
  }

  /**
   * Visually shows the beginning of the gui view.
   */
  public void skipToFront() {
    JViewport jv = this.js.getViewport();
    jv.setViewPosition(new Point(0, 0));
  }

  /**
   * Visually shows the end of the gui view.
   */
  public void skipToEnd() {
    JViewport jv = this.js.getViewport();
    jv.setViewPosition(new Point(model.getFinalEndBeat() * ConcreteGuiViewPanel.BOX_SIZE, 0));
  }

  /**
   * Visually moves the gui view that is in the frame left.
   */
  public void shiftLeft() {
    JViewport jv = this.js.getViewport();
    int curX = (int) jv.getViewPosition().getX();
    int curY = (int) jv.getViewPosition().getY();
    if (curX > 50) {
      jv.setViewPosition(new Point(curX - 50, curY));
    }
  }

  /**
   * Visually moves the gui view that is in the frame right.
   */
  public void shiftRight() {
    JViewport jv = this.js.getViewport();
    int curX = (int) jv.getViewPosition().getX();
    int curY = (int) jv.getViewPosition().getY();
    if (curX < (model.getFinalEndBeat() + 2) * ConcreteGuiViewPanel.BOX_SIZE - 50) {
      jv.setViewPosition(new Point(curX + 50, curY));
    }
  }

  /**
   * Visually moves the gui view that is in the frame up.
   */
  public void shiftUp() {
    JViewport jv = this.js.getViewport();
    int curX = (int) jv.getViewPosition().getX();
    int curY = (int) jv.getViewPosition().getY();
    if (curY > 50) {
      jv.setViewPosition(new Point(curX, curY - 50));
    }
  }

  /**
   * Visually moves the gui view that is in the frame down.
   */
  public void shiftDown() {
    JViewport jv = this.js.getViewport();
    int curX = (int) jv.getViewPosition().getX();
    int curY = (int) jv.getViewPosition().getY();
    if (curY < (model.getHighestPitch().getValue() - model.getLowestPitch().getValue() + 1)
        * ConcreteGuiViewPanel.BOX_SIZE) {
      jv.setViewPosition(new Point(curX, curY + 50));
    }
  }

  /**
   * Gets the dimension of the view that is currently seen in the gui frame.
   *
   * @return The Dimension of the range that is currently in the gui frame.
   */
  public Dimension getViewableRange() {
    return this.js.getViewport().getExtentSize();
  }
}

