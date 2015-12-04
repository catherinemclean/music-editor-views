package cs3500.music;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Tests the new functionality that was added to our model by our ViewModel
 */
public class ViewModelTest extends AbstractViewTest {

  @Test
  public void testTimeStamp1() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    assertEquals(this.mary.getTimeStamp(), 0);
    this.mary.advanceTimestamp();
    this.mary.advanceTimestamp();
    this.mary.advanceTimestamp();
    assertEquals(this.mary.getTimeStamp(), 3);
    this.mary.setTimeStamp(5);
    assertEquals(this.mary.getTimeStamp(), 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTimeStamp2() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.mary.setTimeStamp(65);
  }

  @Test
  public void testSetCurrent1() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.mary.setCurrent(450, 130);
    assertEquals(mary.getCurBeat(), 16);
    assertEquals(mary.getCurPitch(), 63);
  }

  @Test
  public void testSetCurrent2() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.mary.setCurrent(10, 175);
    assertEquals(mary.getCurBeat(), -1);
    assertEquals(mary.getCurPitch(), 61);
  }

  @Test
  public void testSetCurrent3() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.mary.setCurrent(1038, 3);
    assertEquals(mary.getCurBeat(), 39);
    assertEquals(mary.getCurPitch(), -1);
  }

  @Test
  public void testSetCurBeat1() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    assertEquals(mary.getCurBeat(), -1);
    this.mary.setCurBeat(5);
    assertEquals(mary.getCurBeat(), 5);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSetCurBeat2() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.mary.setCurBeat(80);
  }

  @Test
  public void testSetCurPitch1() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    assertEquals(mary.getCurPitch(), -1);
    this.mary.setCurPitch(60);
    assertEquals(mary.getCurPitch(), 60);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSetCurPitch2() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.mary.setCurPitch(20);
  }

  @Test
  public void testSetCurrent4() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.mary.setCurrent(530, 200);
    this.mary.setCurrent(0, 0);
    assertEquals(mary.getCurBeat(), -1);
    assertEquals(mary.getCurPitch(), -1);
  }
}
