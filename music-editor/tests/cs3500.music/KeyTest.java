package cs3500.music;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.controller.KeyboardHandler;
import cs3500.music.model.Model;
import cs3500.music.model.ModelImpl;
import cs3500.music.view.GuiViewModel;
import cs3500.music.view.ViewModel;
import org.junit.Test;

import javax.sound.midi.InvalidMidiDataException;
import java.awt.event.KeyEvent;
import static org.junit.Assert.assertEquals;

/**
 * Tests our key handler, making sure that everything is correctly wired
 */
public class KeyTest extends AbstractViewTest {

  Model m = new ModelImpl();
  //ViewModel vm = new GuiViewModel(m);
  Controller c;

  public void initialize() throws InvalidMidiDataException {
    c = new ControllerImpl(m);
  }


  @Test
  public void testKey1() {
    KeyboardHandler kh = new KeyboardHandler();
    kh.addTypedEvent(84, kh.new TestKeyHandler());// 't'
    KeyEvent e = new KeyEvent(new Button(), 0, 0, 0, 0, 't');
    kh.keyTyped(e);
    assertEquals(kh.getOutput(), "Ran\nKey typed: t key code = 84");
  }

  @Test
  public void testKey2() {
    KeyboardHandler kh = new KeyboardHandler();
    kh.addTypedEvent(521, kh.new TestKeyHandler());
    KeyEvent e = new KeyEvent(new Button(), 0, 0, 0, 0, '+');
    kh.keyTyped(e);
    assertEquals(kh.getOutput(), "Ran\nKey typed: + key code = 521");
  }

  @Test
  public void testKey3() {
    KeyboardHandler kh = new KeyboardHandler();
    kh.addTypedEvent(65, kh.new TestKeyHandler());
    KeyEvent e = new KeyEvent(new Button(), 0, 0, 0, 0, 'a');
    kh.keyTyped(e);
    assertEquals(kh.getOutput(), "Ran\nKey typed: a key code = 65");
  }

  @Test
  public void testKey4() {
    KeyboardHandler kh = new KeyboardHandler();
    kh.addTypedEvent(69, kh.new TestKeyHandler());
    KeyEvent e = new KeyEvent(new Button(), 0, 0, 0, 0, 'e');
    kh.keyTyped(e);
    assertEquals(kh.getOutput(), "Ran\nKey typed: e key code = 69");
  }

  @Test
  public void testKey5() {
    KeyboardHandler kh = new KeyboardHandler();
    kh.addTypedEvent(45, kh.new TestKeyHandler());
    KeyEvent e = new KeyEvent(new Button(), 0, 0, 0, 0, '-');
    kh.keyTyped(e);
    assertEquals(kh.getOutput(), "Ran\nKey typed: - key code = 45");
  }

  @Test
  public void testKey6() {
    KeyboardHandler kh = new KeyboardHandler();
    kh.addTypedEvent(83, kh.new TestKeyHandler());
    KeyEvent e = new KeyEvent(new Button(), 0, 0, 0, 0, 's');
    kh.keyTyped(e);
    assertEquals(kh.getOutput(), "Ran\nKey typed: s key code = 83");
  }

  @Test
  public void testKey7() {
    KeyboardHandler kh = new KeyboardHandler();
    kh.addTypedEvent(46, kh.new TestKeyHandler());
    KeyEvent e = new KeyEvent(new Button(), 0, 0, 0, 0, '.');
    kh.keyTyped(e);
    assertEquals(kh.getOutput(), "Ran\nKey typed: . key code = 46");
  }

  @Test
  public void testKey8() {
    KeyboardHandler kh = new KeyboardHandler();
    kh.addTypedEvent(47, kh.new TestKeyHandler());
    KeyEvent e = new KeyEvent(new Button(), 0, 0, 0, 0, '/');
    kh.keyTyped(e);
    assertEquals(kh.getOutput(), "Ran\nKey typed: / key code = 47");
  }

  @Test
  public void testKey9() {
    KeyboardHandler kh = new KeyboardHandler();
    kh.addTypedEvent(77, kh.new TestKeyHandler());
    KeyEvent e = new KeyEvent(new Button(), 0, 0, 0, 0, 'm');
    kh.keyTyped(e);
    assertEquals(kh.getOutput(), "Ran\nKey typed: m key code = 77");
  }

  @Test
  public void testKey10() {
    KeyboardHandler kh = new KeyboardHandler();
    kh.addTypedEvent(78, kh.new TestKeyHandler());
    KeyEvent e = new KeyEvent(new Button(), 0, 0, 0, 0, 'n');
    kh.keyTyped(e);
    assertEquals(kh.getOutput(), "Ran\nKey typed: n key code = 78");
  }

  @Test
  public void testKey11() {
    KeyboardHandler kh = new KeyboardHandler();
    kh.addTypedEvent(71, kh.new TestKeyHandler());
    KeyEvent e = new KeyEvent(new Button(), 0, 0, 0, 0, 'g');
    kh.keyTyped(e);
    assertEquals(kh.getOutput(), "Ran\nKey typed: g key code = 71");
  }

  @Test
  public void testKey12() {
    KeyboardHandler kh = new KeyboardHandler();
    kh.addTypedEvent(72, kh.new TestKeyHandler());
    KeyEvent e = new KeyEvent(new Button(), 0, 0, 0, 0, 'h');
    kh.keyTyped(e);
    assertEquals(kh.getOutput(), "Ran\nKey typed: h key code = 72");
  }
}
