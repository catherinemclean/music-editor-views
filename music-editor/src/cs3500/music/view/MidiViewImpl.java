package cs3500.music.view;

import javax.sound.midi.*;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Objects;
import java.io.*;

import cs3500.music.model.Model;
import cs3500.music.model.Note;

/**
 * Represents the MIDI playback view
 */
public final class MidiViewImpl implements MidiView {

  /**
   * Synthesizer object that creates sounds
   */
  private Synthesizer synth;

  /**
   * Receiver object that receives playback messages
   */
  private Receiver receiver;

  /**
   * Allows for the playback of multiple notes in multiple channels
   */
  private Sequencer seq;

  /**
   * The model that this view is playing
   */
  private final Model m;

  /**
   * Whether or not playback is paused
   */
  private boolean paused;

  /**
   * Whether or not to create a mock MidiDevice
   */
  private boolean mock;

  /**
   * The output produced by a mock receiver
   */
  private String output;

  /**
   * Creates a new Midi view with playback
   *
   * @param m
   */
  public MidiViewImpl(Model m) throws InvalidMidiDataException {
    this(m, false);
  }

  /**
   * Creates a new Midi view with either playback or testing
   *
   * @param m    the model that this view will play back
   * @param mock whether or not to create a mock MidiDevice
   */
  public MidiViewImpl(Model m, boolean mock) throws InvalidMidiDataException {
    this.m = Objects.requireNonNull(m);
    Synthesizer s = null;
    Sequencer sq = null;
    Receiver r = null;
    this.paused = true;
    this.mock = mock;
    this.output = "";
    if (!mock) {
      try {
        s = MidiSystem.getSynthesizer();
        sq = MidiSystem.getSequencer();
        r = s.getReceiver();
        s.open();
        sq.open();
      } catch (MidiUnavailableException e) {
        e.printStackTrace();
      }
      synth = s;
      receiver = r;
      seq = sq;
    } else {
      try {
        s = new MockMidiDevice();
        r = s.getReceiver();
      } catch (MidiUnavailableException e) {
        e.printStackTrace();
      }
      synth = s;
      receiver = r;
      seq = null;
    }
  }


  /**
   * Relevant classes and methods from the javax.sound.midi library:
   * <ul>
   *  <li>{@link MidiSystem#getSynthesizer()}</li>
   *  <li>{@link Synthesizer}
   *    <ul>
   *      <li>{@link Synthesizer#open()}</li>
   *      <li>{@link Synthesizer#getReceiver()}</li>
   *      <li>{@link Synthesizer#getChannels()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link Receiver}
   *    <ul>
   *      <li>{@link Receiver#send(MidiMessage, long)}</li>
   *      <li>{@link Receiver#close()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link MidiMessage}</li>
   *  <li>{@link ShortMessage}</li>
   *  <li>{@link MidiChannel}
   *    <ul>
   *      <li>{@link MidiChannel#getProgram()}</li>
   *      <li>{@link MidiChannel#programChange(int)}</li>
   *    </ul>
   *  </li>
   * </ul>
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   *   https://en.wikipedia.org/wiki/General_MIDI
   *   </a>
   */

  /**
   * Plays the midi view
   *
   * @throws InvalidMidiDataException if the given data is not valid in Midi
   */
  public void initialize() throws InvalidMidiDataException {
    // sets each channel to its respective instrument
    if (!this.mock) {
      MidiChannel[] chan = synth.getChannels();
      for (int i = 1; i < 16; i++) {
        MidiChannel mc = chan[i];
        mc.programChange(i);
      }
    }

    if (this.mock) {
      int t = m.getTempo();
      for (int i = 0; i <= m.getFinalStartBeat(); i++) {
        List<Note> ns = m.getNotesAtTime(i);
        for (Note n : ns) {
          MidiMessage start =
              new ShortMessage(ShortMessage.NOTE_ON, n.getInstrument() - 1, n.getPitch().getValue(),
                  n.getVelocity());
          MidiMessage stop =
              new ShortMessage(ShortMessage.NOTE_OFF, n.getInstrument() - 1, n.getPitch().getValue(),
                  n.getVelocity());
          receiver.send(start, t * n.getStartTime());
          receiver.send(stop, t * (n.getStartTime() + (n.getEndTime() - n.getStartTime())));
        }

        // Create a stream to hold the output
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        System.setOut(ps);
        System.out.print(((MockReceiver) receiver).output());
        // Put things back
        System.out.flush();
        System.setOut(old);

        this.output = out.toString();
      }
      receiver.close();
    }
  }

  /**
   * Returns the midi view as output to the console for testing purposes.
   *
   * @return The midi view represented as a string.
   */
  public String getOutput() {
    return this.output;
  }

  /**
   * Audibly plays all of the notes at the given time.
   *
   * @param time The time that this view is currently at.
   * @throws InvalidMidiDataException if the Midi data is invalid.
   * @throws MidiUnavailableException if Midi is unavailable.
   */
  public void recordNotes(int time) throws InvalidMidiDataException, MidiUnavailableException {
    receiver = synth.getReceiver();
    MidiChannel[] chan = synth.getChannels();
    int t = m.getTempo();
    List<Note> offs = m.getEndNotesAtTime(time);
    for (Note n : offs) {
      chan[n.getInstrument() - 1].noteOff(n.getPitch().getValue(), n.getVelocity());
    }

    List<Note> ons = m.getNotesAtTime(time);
    for (Note n : ons) {
      chan[n.getInstrument() - 1].noteOn(n.getPitch().getValue(), n.getVelocity());
    }

    receiver.close();
  }

  /**
   * Pauses this view.
   */
  @Override public void pause() {
    MidiChannel[] chan = synth.getChannels();
    for (MidiChannel mc : chan) {
      mc.allNotesOff();
    }
    paused = true;
  }

  /**
   * Returns whether or not this view is currently paused.
   *
   * @return true if this view is paused or false if it is playing.
   */
  public boolean isPaused() {
    return this.paused;
  }
}

