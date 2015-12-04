package cs3500.music;

import cs3500.music.controller.MouseHandler;
import cs3500.music.view.GuiViewFrame;
import org.junit.Test;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Tests our mouse handler, making sure everything is wired correctly.
 * Represents tests for mouse events
 */
public class MouseTest extends AbstractViewTest {

  @Test
  public void testMouse1() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    MouseHandler mh = new MouseHandler(new GuiViewFrame(mary));
    mh.mouseClicked(new MouseEvent(new Button(), 0, 0, 0, 15, 30, 0, false));
    mh.mouseClicked(new MouseEvent(new Button(), 0, 0, 0, 45, 40, 0, false));
    assertEquals(mh.getOutput(), "Clicked at x:15 y:30\nClicked at x:45 y:40\n");
  }

  @Test
  public void testMouse2() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    MouseHandler mh = new MouseHandler(new GuiViewFrame(tetris));
    mh.mousePressed(new MouseEvent(new Button(), 0, 0, 0, 15, 30, 0, false));
    mh.mousePressed(new MouseEvent(new Button(), 0, 0, 0, 45, 40, 0, false));
    assertEquals(mh.getOutput(), "Pressed at x:15 y:30\nPressed at x:45 y:40\n");
  }

  @Test
  public void testMouse3() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    MouseHandler mh = new MouseHandler(new GuiViewFrame(smb));
    mh.mouseReleased(new MouseEvent(new Button(), 0, 0, 0, 15, 30, 0, false));
    mh.mouseReleased(new MouseEvent(new Button(), 0, 0, 0, 45, 40, 0, false));
    assertEquals(mh.getOutput(), "Released at x:15 y:30\nReleased at x:45 y:40\n");
  }

  @Test
  public void testMouse4() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    MouseHandler mh = new MouseHandler(new GuiViewFrame(mary));
    mh.mouseEntered(new MouseEvent(new Button(), 0, 0, 0, 15, 30, 0, false));
    mh.mouseEntered(new MouseEvent(new Button(), 0, 0, 0, 45, 40, 0, false));
    assertEquals(mh.getOutput(), "Entered at x:15 y:30\nEntered at x:45 y:40\n");
  }

  @Test
  public void testMouse5() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    MouseHandler mh = new MouseHandler(new GuiViewFrame(mary));
    mh.mouseExited(new MouseEvent(new Button(), 0, 0, 0, 15, 30, 0, false));
    mh.mouseExited(new MouseEvent(new Button(), 0, 0, 0, 45, 40, 0, false));
    assertEquals(mh.getOutput(), "Exited at x:15 y:30\nExited at x:45 y:40\n");
  }

  @Test
  public void testMouse6() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    MouseHandler mh = new MouseHandler(new GuiViewFrame(mary));
    mh.mousePressed(new MouseEvent(new Button(), 0, 0, 0, 15, 30, 0, false));
    mh.mouseClicked(new MouseEvent(new Button(), 0, 0, 0, 45, 40, 0, false));
    mh.mouseReleased(new MouseEvent(new Button(), 0, 0, 0, 55, 16, 0, false));
    mh.mouseEntered(new MouseEvent(new Button(), 0, 0, 0, 27, 60, 0, false));
    mh.mouseExited(new MouseEvent(new Button(), 0, 0, 0, 10, 46, 0, false));
    assertEquals(mh.getOutput(), "Pressed at x:15 y:30\nClicked at x:45 y:40\nReleased at x:"
        + "55 y:16\nEntered at x:27 y:60\nExited at x:10 y:46\n");
  }
}
