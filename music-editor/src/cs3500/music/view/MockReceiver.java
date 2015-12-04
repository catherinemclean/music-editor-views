package cs3500.music.view;

import javax.sound.midi.*;
import java.util.List;

/**
 * Represents a mock receiver that handles MIDI input and logs
 * messages into a StringBuilder rather than playing notes through a
 * synthesizer.
 */
public final class MockReceiver implements Receiver {

  /**
   * Builds up messages that are received by this MockReceiver
   */
  private StringBuilder out = new StringBuilder(1000);

  /**
   * Sends messages to the string builder
   *
   * @param message   the message to send
   * @param timeStamp the time of the message
   */
  @Override public void send(MidiMessage message, long timeStamp) {
    ShortMessage cur = (ShortMessage) message;
    String msg = "Channel: " + cur.getChannel() + " Command: " + cur.getCommand() +
        " Pitch: " + cur.getData1() + " Velocity: " + cur.getData2() +
        " time:" + Long.toString(timeStamp) + "\n";

    // resize our StringBuilder to double capacity
    if (out.capacity() - out.length() < msg.length()) {
      out.ensureCapacity(out.capacity() * 2);
    }
    out.append(msg);
  }

  /**
   * Closes this receiver
   */
  @Override public void close() {

  }

  /**
   * Returns the output after receiving all messages
   *
   * @return the output after receiving all messages
   */
  public String output() {
    return this.out.toString();
  }
}
