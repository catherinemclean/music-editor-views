package cs3500.music.view.compositeui;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.view.GuiView;
import cs3500.music.view.midiui.MIDIView;
import cs3500.music.view.swingview.SwingView;

/**
 * A View that ties the MIDI and GUI views together
 */
public class CompositeView implements GuiView {
  SwingView swingView;
  MIDIView midiView;
  MusicEditorModel model;

  public CompositeView(SwingView swingView, MIDIView midiView) {
    this.swingView = swingView;
    this.midiView = midiView;
  }

  @Override
  public void render(MusicEditorModel model) {
    this.model = model;
    this.swingView.render(model);
  }

  public void update(int beat) {
    this.swingView.update(beat);
    this.midiView.render(model);
  }

  @Override
  public void addKeyListener(KeyListener keyboardHandler) {
    this.swingView.addKeyListener(keyboardHandler);
  }

  @Override
  public void removeKeyListener(KeyListener keyboardHandler) {
    this.swingView.removeKeyListener(keyboardHandler);
  }

  @Override
  public void addMouseListener(MouseListener mouseHandler) {
    this.swingView.addMouseListener(mouseHandler);
  }

  @Override
  public void addMouseMotionListener(MouseMotionListener mouseHandler) {
    this.swingView.addMouseMotionListener(mouseHandler);
  }

  @Override
  public void removeMouseListener(MouseListener mouseHandler) {
    this.swingView.removeMouseListener(mouseHandler);
  }
}