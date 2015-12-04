package cs3500.music.view;

import javax.sound.midi.*;
import java.util.List;

/**
 * Represents a mock synthesizer for testing purposes. It can create
 * a mock receiver to send input to.
 */
public final class MockMidiDevice implements Synthesizer {

  @Override public Info getDeviceInfo() {
    return null;
  }

  @Override public void open() throws MidiUnavailableException {

  }

  @Override public void close() {

  }

  @Override public boolean isOpen() {
    return false;
  }

  @Override public long getMicrosecondPosition() {
    return 0;
  }

  @Override public int getMaxReceivers() {
    return 0;
  }

  @Override public int getMaxTransmitters() {
    return 0;
  }

  /**
   * The only method that we care about here
   *
   * @return a mock receiver
   */
  public Receiver getReceiver() {
    return new MockReceiver();
  }

  @Override public List<Receiver> getReceivers() {
    return null;
  }

  @Override public Transmitter getTransmitter() throws MidiUnavailableException {
    return null;
  }

  @Override public List<Transmitter> getTransmitters() {
    return null;
  }

  @Override public int getMaxPolyphony() {
    return 0;
  }

  @Override public long getLatency() {
    return 0;
  }

  @Override public MidiChannel[] getChannels() {
    return new MidiChannel[0];
  }

  @Override public VoiceStatus[] getVoiceStatus() {
    return new VoiceStatus[0];
  }

  @Override public boolean isSoundbankSupported(Soundbank soundbank) {
    return false;
  }

  @Override public boolean loadInstrument(Instrument instrument) {
    return false;
  }

  @Override public void unloadInstrument(Instrument instrument) {

  }

  @Override public boolean remapInstrument(Instrument from, Instrument to) {
    return false;
  }

  @Override public Soundbank getDefaultSoundbank() {
    return null;
  }

  @Override public Instrument[] getAvailableInstruments() {
    return new Instrument[0];
  }

  @Override public Instrument[] getLoadedInstruments() {
    return new Instrument[0];
  }

  @Override public boolean loadAllInstruments(Soundbank soundbank) {
    return false;
  }

  @Override public void unloadAllInstruments(Soundbank soundbank) {

  }

  @Override public boolean loadInstruments(Soundbank soundbank, Patch[] patchList) {
    return false;
  }

  @Override public void unloadInstruments(Soundbank soundbank, Patch[] patchList) {

  }
}

