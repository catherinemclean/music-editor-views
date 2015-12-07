package cs3500.music.view.midiui.mocks;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * Mocks a Midi Receiver by logging start and stop calls
 */
public class MockReceiver implements Receiver {
  private StringBuilder log;

  MockReceiver(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void send(MidiMessage message, long timeStamp) {
    ShortMessage message1 = (ShortMessage) message;

    if (message1.getCommand() == ShortMessage.NOTE_ON) {
      log.append("note ").append(timeStamp).append(" ").append(message1.getData1())
          .append(" ").append(message1.getData2()).append(" ")
          .append("\n");
    }

  }

  @Override
  public void close() {
    this.log.append("MIDI stream closed");
  }
}