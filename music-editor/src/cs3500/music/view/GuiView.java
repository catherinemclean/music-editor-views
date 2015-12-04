package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface GuiView extends MusicEditorView {
  void addKeyListener(KeyListener keyboardHandler);

  void removeKeyListener(KeyListener keyboardHandler);

  void addMouseListener(MouseListener mouseHandler);

  void addMouseMotionListener(MouseMotionListener mouseHandler);

  void removeMouseListener(MouseListener mouseHandler);

  void update(int beat);
}
