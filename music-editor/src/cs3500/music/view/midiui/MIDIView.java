package cs3500.music.view.midiui;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import cs3500.music.controller.Controller;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Playable;
import cs3500.music.view.MusicEditorView;
import cs3500.music.view.midiui.mocks.MockMidiDevice;

/**
 * Represents MIDI playback for Music Editors
 */
public class MIDIView implements MusicEditorView {
  private Synthesizer synth;
  private Receiver receiver;
  private int beat;

  /**
   * Constructs a default MIDIView. The Synthesizer is the machine default.
   */
  public MIDIView() {
    Synthesizer temps = null;
    Receiver tempr = null;
    try {
      temps = MidiSystem.getSynthesizer();
      tempr = temps.getReceiver();
      temps.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.synth = temps;
    this.receiver = tempr;
    this.beat = 0;
  }

  /**
   * Constructs a specialized MIDIView, which sets the Synth and Reciever to be mock objects,
   * allowing for testing
   *
   * @param log the StringBuilder object that this view will append logs onto
   * @throws MidiUnavailableException if the synth doesn't exist
   */
  public MIDIView(StringBuilder log) throws MidiUnavailableException {
    try {
      this.synth = new MockMidiDevice(log);
      this.receiver = this.synth.getReceiver();
      this.synth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  /**
   * Plays the Given Playable using MidiMessages
   *
   * @param playable the Playable to Play sound for
   */
  public void playNote(Playable playable) throws InvalidMidiDataException {
    try {
      Instrument[] instruments = MidiSystem.getSynthesizer().getDefaultSoundbank()
          .getInstruments();
      this.synth.getChannels()[0].programChange(instruments[playable.getInstrument()].getPatch()
          .getProgram());
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }

    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0, playable.getPitch(),
        playable.getVolume());
    this.receiver.send(start, playable.getStart());
  }

  /**
   * Plays the Given Playable using MidiMessages
   *
   * @param playable the Playable to Play sound for
   */
  public void stopNote(Playable playable) throws InvalidMidiDataException {
    try {
      Instrument[] instruments = MidiSystem.getSynthesizer().getDefaultSoundbank().getInstruments();
      this.synth.getChannels()[0].programChange(instruments[playable.getInstrument()].getPatch()
          .getProgram());
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }

    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 0, playable.getPitch(),
        playable.getVolume());
    this.receiver.send(stop, playable.getEnd() - 1);
  }

  /**
   * Plays the Playables that are present in the Model
   *
   * @param model the model to render
   */
  public void render(MusicEditorModel model) {
      for (Playable p : model.getPlayablesAt(beat)) {
        try {
          if (beat == p.getStart()) {
            this.playNote(p);
          } else if (beat == p.getEnd() - 1) {
            this.stopNote(p);
          }
        } catch (InvalidMidiDataException e) {
          e.printStackTrace();
        }
      }
      beat++;
      if (beat >= model.getLastBeat()) {
        Controller.timer.cancel();
        this.receiver.close();
      }
  }
}