package cs3500.music;

import org.junit.Test;
import cs3500.music.model.*;
import cs3500.music.view.*;
import cs3500.music.util.*;

import javax.sound.midi.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Tests the MidiView using the mock MidiDevice and mock Receiver
 */
public class MidiViewTest extends AbstractViewTest {

  /**
   * The MidiView object
   */
  private CompositeViewable v;

  /**
   * Creates an appropriate MidiView in testing mode with the given model
   *
   * @param m the model to use
   * @throws InvalidMidiDataException
   */
  private void create(Model m) throws InvalidMidiDataException {
    this.v = new CompositeView(new MidiViewImpl(m, true), new GuiViewFrame(new GuiViewModel(m)));
    v.initialize();
  }

  @Test public void testMidi1() throws InvalidMidiDataException {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    create(mary);
    String output = v.getOutput();
    assertEquals(output, "Channel: 0 Command: 144 Pitch: 64 Velocity: 72 time:0\n"
        + "Channel: 0 Command: 128 Pitch: 64 Velocity: 72 time:400000\n"
        + "Channel: 0 Command: 144 Pitch: 55 Velocity: 70 time:0\n"
        + "Channel: 0 Command: 128 Pitch: 55 Velocity: 70 time:1400000\n"
        + "Channel: 0 Command: 144 Pitch: 62 Velocity: 72 time:400000\n"
        + "Channel: 0 Command: 128 Pitch: 62 Velocity: 72 time:800000\n"
        + "Channel: 0 Command: 144 Pitch: 60 Velocity: 71 time:800000\n"
        + "Channel: 0 Command: 128 Pitch: 60 Velocity: 71 time:1200000\n"
        + "Channel: 0 Command: 144 Pitch: 62 Velocity: 79 time:1200000\n"
        + "Channel: 0 Command: 128 Pitch: 62 Velocity: 79 time:1600000\n"
        + "Channel: 0 Command: 144 Pitch: 55 Velocity: 79 time:1600000\n"
        + "Channel: 0 Command: 128 Pitch: 55 Velocity: 79 time:3000000\n"
        + "Channel: 0 Command: 144 Pitch: 64 Velocity: 85 time:1600000\n"
        + "Channel: 0 Command: 128 Pitch: 64 Velocity: 85 time:2000000\n"
        + "Channel: 0 Command: 144 Pitch: 64 Velocity: 78 time:2000000\n"
        + "Channel: 0 Command: 128 Pitch: 64 Velocity: 78 time:2400000\n"
        + "Channel: 0 Command: 144 Pitch: 64 Velocity: 74 time:2400000\n"
        + "Channel: 0 Command: 128 Pitch: 64 Velocity: 74 time:3000000\n"
        + "Channel: 0 Command: 144 Pitch: 55 Velocity: 77 time:3200000\n"
        + "Channel: 0 Command: 128 Pitch: 55 Velocity: 77 time:4800000\n"
        + "Channel: 0 Command: 144 Pitch: 62 Velocity: 75 time:3200000\n"
        + "Channel: 0 Command: 128 Pitch: 62 Velocity: 75 time:3600000\n"
        + "Channel: 0 Command: 144 Pitch: 62 Velocity: 77 time:3600000\n"
        + "Channel: 0 Command: 128 Pitch: 62 Velocity: 77 time:4000000\n"
        + "Channel: 0 Command: 144 Pitch: 62 Velocity: 75 time:4000000\n"
        + "Channel: 0 Command: 128 Pitch: 62 Velocity: 75 time:4800000\n"
        + "Channel: 0 Command: 144 Pitch: 55 Velocity: 79 time:4800000\n"
        + "Channel: 0 Command: 128 Pitch: 55 Velocity: 79 time:5200000\n"
        + "Channel: 0 Command: 144 Pitch: 64 Velocity: 82 time:4800000\n"
        + "Channel: 0 Command: 128 Pitch: 64 Velocity: 82 time:5200000\n"
        + "Channel: 0 Command: 144 Pitch: 67 Velocity: 84 time:5200000\n"
        + "Channel: 0 Command: 128 Pitch: 67 Velocity: 84 time:5600000\n"
        + "Channel: 0 Command: 144 Pitch: 67 Velocity: 75 time:5600000\n"
        + "Channel: 0 Command: 128 Pitch: 67 Velocity: 75 time:6400000\n"
        + "Channel: 0 Command: 144 Pitch: 55 Velocity: 78 time:6400000\n"
        + "Channel: 0 Command: 128 Pitch: 55 Velocity: 78 time:8000000\n"
        + "Channel: 0 Command: 144 Pitch: 64 Velocity: 73 time:6400000\n"
        + "Channel: 0 Command: 128 Pitch: 64 Velocity: 73 time:6800000\n"
        + "Channel: 0 Command: 144 Pitch: 62 Velocity: 69 time:6800000\n"
        + "Channel: 0 Command: 128 Pitch: 62 Velocity: 69 time:7200000\n"
        + "Channel: 0 Command: 144 Pitch: 60 Velocity: 71 time:7200000\n"
        + "Channel: 0 Command: 128 Pitch: 60 Velocity: 71 time:7600000\n"
        + "Channel: 0 Command: 144 Pitch: 62 Velocity: 80 time:7600000\n"
        + "Channel: 0 Command: 128 Pitch: 62 Velocity: 80 time:8000000\n"
        + "Channel: 0 Command: 144 Pitch: 55 Velocity: 79 time:8000000\n"
        + "Channel: 0 Command: 128 Pitch: 55 Velocity: 79 time:9600000\n"
        + "Channel: 0 Command: 144 Pitch: 64 Velocity: 84 time:8000000\n"
        + "Channel: 0 Command: 128 Pitch: 64 Velocity: 84 time:8400000\n"
        + "Channel: 0 Command: 144 Pitch: 64 Velocity: 76 time:8400000\n"
        + "Channel: 0 Command: 128 Pitch: 64 Velocity: 76 time:8800000\n"
        + "Channel: 0 Command: 144 Pitch: 64 Velocity: 74 time:8800000\n"
        + "Channel: 0 Command: 128 Pitch: 64 Velocity: 74 time:9200000\n"
        + "Channel: 0 Command: 144 Pitch: 64 Velocity: 77 time:9200000\n"
        + "Channel: 0 Command: 128 Pitch: 64 Velocity: 77 time:9600000\n"
        + "Channel: 0 Command: 144 Pitch: 55 Velocity: 78 time:9600000\n"
        + "Channel: 0 Command: 128 Pitch: 55 Velocity: 78 time:11200000\n"
        + "Channel: 0 Command: 144 Pitch: 62 Velocity: 75 time:9600000\n"
        + "Channel: 0 Command: 128 Pitch: 62 Velocity: 75 time:10000000\n"
        + "Channel: 0 Command: 144 Pitch: 62 Velocity: 74 time:10000000\n"
        + "Channel: 0 Command: 128 Pitch: 62 Velocity: 74 time:10400000\n"
        + "Channel: 0 Command: 144 Pitch: 64 Velocity: 81 time:10400000\n"
        + "Channel: 0 Command: 128 Pitch: 64 Velocity: 81 time:10800000\n"
        + "Channel: 0 Command: 144 Pitch: 62 Velocity: 70 time:10800000\n"
        + "Channel: 0 Command: 128 Pitch: 62 Velocity: 70 time:11200000\n"
        + "Channel: 0 Command: 144 Pitch: 52 Velocity: 72 time:11200000\n"
        + "Channel: 0 Command: 128 Pitch: 52 Velocity: 72 time:12800000\n"
        + "Channel: 0 Command: 144 Pitch: 60 Velocity: 73 time:11200000\n"
        + "Channel: 0 Command: 128 Pitch: 60 Velocity: 73 time:12800000\n");
  }

  @Test public void testMidi2() throws InvalidMidiDataException {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    create(test1);
    String output = v.getOutput();
    assertEquals(output, "Channel: 8 Command: 144 Pitch: 60 Velocity: 30 time:0\n"
        + "Channel: 8 Command: 128 Pitch: 60 Velocity: 30 time:400000\n"
        + "Channel: 8 Command: 144 Pitch: 54 Velocity: 54 time:0\n"
        + "Channel: 8 Command: 128 Pitch: 54 Velocity: 54 time:600000\n"
        + "Channel: 8 Command: 144 Pitch: 80 Velocity: 50 time:400000\n"
        + "Channel: 8 Command: 128 Pitch: 80 Velocity: 50 time:800000\n"
        + "Channel: 8 Command: 144 Pitch: 110 Velocity: 102 time:600000\n"
        + "Channel: 8 Command: 128 Pitch: 110 Velocity: 102 time:1200000\n"
        + "Channel: 8 Command: 144 Pitch: 101 Velocity: 120 time:800000\n"
        + "Channel: 8 Command: 128 Pitch: 101 Velocity: 120 time:1200000\n"
        + "Channel: 8 Command: 144 Pitch: 35 Velocity: 60 time:1200000\n"
        + "Channel: 8 Command: 128 Pitch: 35 Velocity: 60 time:1600000\n"
        + "Channel: 8 Command: 144 Pitch: 127 Velocity: 20 time:1200000\n"
        + "Channel: 8 Command: 128 Pitch: 127 Velocity: 20 time:1800000\n"
        + "Channel: 9 Command: 144 Pitch: 0 Velocity: 127 time:1600000\n"
        + "Channel: 9 Command: 128 Pitch: 0 Velocity: 127 time:2000000\n"
        + "Channel: 9 Command: 144 Pitch: 71 Velocity: 1 time:1800000\n"
        + "Channel: 9 Command: 128 Pitch: 71 Velocity: 1 time:2400000\n"
        + "Channel: 9 Command: 144 Pitch: 40 Velocity: 110 time:2000000\n"
        + "Channel: 9 Command: 128 Pitch: 40 Velocity: 110 time:2400000\n"
        + "Channel: 9 Command: 144 Pitch: 93 Velocity: 40 time:2400000\n"
        + "Channel: 9 Command: 128 Pitch: 93 Velocity: 40 time:2800000\n"
        + "Channel: 9 Command: 144 Pitch: 39 Velocity: 94 time:2400000\n"
        + "Channel: 9 Command: 128 Pitch: 39 Velocity: 94 time:3000000\n"
        + "Channel: 9 Command: 144 Pitch: 67 Velocity: 80 time:2800000\n"
        + "Channel: 9 Command: 128 Pitch: 67 Velocity: 80 time:3200000\n"
        + "Channel: 9 Command: 144 Pitch: 43 Velocity: 65 time:3000000\n"
        + "Channel: 9 Command: 128 Pitch: 43 Velocity: 65 time:3600000\n"
        + "Channel: 9 Command: 144 Pitch: 70 Velocity: 70 time:3200000\n"
        + "Channel: 9 Command: 128 Pitch: 70 Velocity: 70 time:3600000\n");
  }

  @Test public void testMidi3() throws InvalidMidiDataException {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    create(scale);
    assertEquals(v.isPaused(), true);
    String output = v.getOutput();
    v.pause();
    assertEquals(v.isPaused(), true);
    assertEquals(output, "Channel: 0 Command: 144 Pitch: 60 Velocity: 50 time:0\n" + "Channel: 0 Command: 128 Pitch: 60 Velocity: 50 time:200000\n"
        + "Channel: 0 Command: 144 Pitch: 62 Velocity: 58 time:200000\n" + "Channel: 0 Command: 128 Pitch: 62 Velocity: 58 time:400000\n"
        + "Channel: 0 Command: 144 Pitch: 64 Velocity: 66 time:400000\n" + "Channel: 0 Command: 128 Pitch: 64 Velocity: 66 time:600000\n"
        + "Channel: 0 Command: 144 Pitch: 65 Velocity: 74 time:600000\n" + "Channel: 0 Command: 128 Pitch: 65 Velocity: 74 time:800000\n"
        + "Channel: 0 Command: 144 Pitch: 67 Velocity: 82 time:800000\n" + "Channel: 0 Command: 128 Pitch: 67 Velocity: 82 time:1000000\n"
        + "Channel: 0 Command: 144 Pitch: 69 Velocity: 90 time:1000000\n" + "Channel: 0 Command: 128 Pitch: 69 Velocity: 90 time:1200000\n"
        + "Channel: 0 Command: 144 Pitch: 71 Velocity: 98 time:1200000\n" + "Channel: 0 Command: 128 Pitch: 71 Velocity: 98 time:1400000\n"
        + "Channel: 0 Command: 144 Pitch: 72 Velocity: 106 time:1400000\n" + "Channel: 0 Command: 128 Pitch: 72 Velocity: 106 time:1600000\n");
  }
}
