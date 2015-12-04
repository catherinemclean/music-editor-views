package cs3500.music;

import cs3500.music.model.*;
import cs3500.music.view.GuiViewModel;
import cs3500.music.view.ViewModel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Represents tests for {@link cs3500.music.model}
 */
public class ModelTest {


  Note n1 = new MusicNote(new PitchImpl(2), 3, 4, 0, 0); //one beat long
  Note n2 = new MusicNote(new PitchImpl(4), 5, 9, 89, 127);
  Note n3 = new MusicNote(new PitchImpl(4), 11, 13, 127, 50);

  Model m1 = new ModelImpl(4, 120);
  Model m2 = new ModelImpl(3, 100);
  ViewModel m3 = new GuiViewModel(this.m1);

  @Test public void testNoteEndTime() {
    assertEquals(n1.getEndTime(), 4);
    assertEquals(n2.getEndTime(), 9);
    assertEquals(n3.getEndTime(), 13);
  }

  @Test public void testNoteStartTime() {
    assertEquals(n1.getStartTime(), 3);
    assertEquals(n2.getStartTime(), 5);
    assertEquals(n3.getStartTime(), 11);
  }

  @Test public void testNoteGetPitch() {
    assertEquals(n1.getPitch(), new PitchImpl(2));
    assertEquals(n2.getPitch(), new PitchImpl(4));
    assertEquals(n3.getPitch(), new PitchImpl(4));
  }

  @Test public void testNoteGetVelocity() {
    assertEquals(n1.getVelocity(), 0);
    assertEquals(n2.getVelocity(), 127);
    assertEquals(n3.getVelocity(), 50);
  }

  @Test public void testNoteGetInstrument() {
    assertEquals(n1.getInstrument(), 0);
    assertEquals(n2.getInstrument(), 89);
    assertEquals(n3.getInstrument(), 127);
  }

  @Test public void testNoteEquals() {
    assertEquals(new MusicNote(new PitchImpl(6), 6, 10, 0, 6),
        new MusicNote(new PitchImpl(6), 6, 10, 0, 6));
    assertEquals(new MusicNote(new PitchImpl(5), 3, 4, 0, 0),
        new MusicNote(new PitchImpl(5), 3, 4, 0, 0));
    assertNotEquals(new MusicNote(new PitchImpl(3), 3, 6, 5, 127),
        new MusicNote(new PitchImpl(3), 3, 4, 5, 127));
    assertNotEquals(new MusicNote(new PitchImpl(3), 3, 6, 4, 78),
        new MusicNote(new PitchImpl(3), 3, 4, 4, 78));
    assertNotEquals(new MusicNote(new PitchImpl(3), 3, 6, 3, 90),
        new MusicNote(new PitchImpl(5), 3, 6, 3, 9));
    assertNotEquals(new MusicNote(new PitchImpl(3), 3, 6, 2, 7),
        new MusicNote(new PitchImpl(3), 2, 6, 2, 7));
    assertNotEquals(new MusicNote(new PitchImpl(3), 3, 6, 2, 9),
        new MusicNote(new PitchImpl(3), 2, 6, 4, 9));
  }


  @Test public void testModelGetters() {
    assertEquals(m1.getFinalStartBeat(), 0);
    assertEquals(m1.getHighestPitch(), new PitchImpl(-1));
    assertEquals(m1.getLowestPitch(), new PitchImpl(-1));
    assertEquals(m1.getMeasure(), 4);
    assertEquals(m1.getTempo(), 120);
  }

  @Test public void testModelGetters1() {
    assertEquals(m3.getFinalStartBeat(), 0);
    assertEquals(m3.getTimeStamp(), 0);
    assertEquals(m3.getHighestPitch(), new PitchImpl(-1));
    assertEquals(m3.getLowestPitch(), new PitchImpl(-1));
    assertEquals(m3.getMeasure(), 4);
    assertEquals(m3.getTempo(), 120);
  }

  @Test public void testModel2Getters() {
    assertEquals(m2.getFinalStartBeat(), 0);
    assertEquals(m2.getHighestPitch(), new PitchImpl(-1));
    assertEquals(m2.getLowestPitch(), new PitchImpl(-1));
    assertEquals(m2.getMeasure(), 3);
    assertEquals(m2.getTempo(), 100);
  }

  @Test public void testToString() {
    assertEquals(n1.toString(), "Pitch: 2, StartTime: 3, EndTime: 4");
    assertEquals(n2.toString(), "Pitch: 4, StartTime: 5, EndTime: 9");
    assertEquals(n3.toString(), "Pitch: 4, StartTime: 11, EndTime: 13");
  }

  @Test public void testToStringModel() {
    assertEquals(m1.toString(), "Measure: 4\n" + " Low Pitch: -1\n" + " High Pitch: -1\n" +
            " finalStartBeat: 0\n");
    assertEquals(m2.toString(),
        "Measure: 3\n" + " Low Pitch: -1\n" + " High Pitch: -1\n"
            + " finalStartBeat: 0\n");
  }

  @Test public void testAddNote() {
    m1.addNote(new PitchImpl(2), 3, 4, 0, 5);
    assertEquals(m1.toString(), "Measure: 4\n" + " Low Pitch: 2\n" + " High Pitch: 2\n" +
            " finalStartBeat: 3\n" + "Pitch: 2, StartTime: 3, EndTime: 4\n");
    m1.addNote(new PitchImpl(4), 5, 9, 0, 4);
    assertEquals(m1.toString(),
        "Measure: 4\n" + " Low Pitch: 2\n" + " High Pitch: 4\n" +
            " finalStartBeat: 5\n" + "Pitch: 2, StartTime: 3, EndTime: 4\n" +
            "Pitch: 4, StartTime: 5, EndTime: 9\n");
    m1.addNote(new PitchImpl(1), 1, 2, 0, 5);
    assertEquals(m1.toString(),
        "Measure: 4\n" + " Low Pitch: 1\n" + " High Pitch: 4\n"
            + " finalStartBeat: 5\n" +
            "Pitch: 1, StartTime: 1, EndTime: 2\n" +
            "Pitch: 2, StartTime: 3, EndTime: 4\n" +
            "Pitch: 4, StartTime: 5, EndTime: 9\n");
  }

  @Test public void testAddNote2() {
    m2.addNote(new PitchImpl(2), 3, 4, 0, 6);
    assertEquals(m2.toString(), "Measure: 3\n" + " Low Pitch: 2\n" + " High Pitch: 2\n" +
            " finalStartBeat: 3\n" + "Pitch: 2, StartTime: 3, EndTime: 4\n");
    m2.addNote(new PitchImpl(4), 5, 9, 0, 7);
    assertEquals(m2.toString(),
        "Measure: 3\n" + " Low Pitch: 2\n" + " High Pitch: 4\n" +
            " finalStartBeat: 5\n" + "Pitch: 2, StartTime: 3, EndTime: 4\n" +
            "Pitch: 4, StartTime: 5, EndTime: 9\n");
    m2.addNote(new PitchImpl(1), 1, 2, 0, 5);
    assertEquals(m2.toString(),
        "Measure: 3\n" + " Low Pitch: 1\n" + " High Pitch: 4\n"
            + " finalStartBeat: 5\n" + "Pitch: 1, StartTime: 1, EndTime: 2\n" +
            "Pitch: 2, StartTime: 3, EndTime: 4\n" +
            "Pitch: 4, StartTime: 5, EndTime: 9\n");
  }

  @Test public void testAddNote3() {
    m2.addNote(new PitchImpl(2), 3, 4, 0, 6);
    assertEquals(m2.getFinalStartBeat(), 3);
    assertEquals(m2.getLowestPitch(), new PitchImpl(2));
    assertEquals(m2.getHighestPitch(), new PitchImpl(2));
    assertEquals(m2.toString(), "Measure: 3\n" + " Low Pitch: 2\n" +
        " High Pitch: 2\n" + " finalStartBeat: 3\n" + "Pitch: 2, StartTime: 3, EndTime: 4\n");
    m2.addNote(new PitchImpl(2), 4, 9, 0, 6);
    assertEquals(m2.getFinalStartBeat(), 4);
    assertEquals(m2.getLowestPitch(), new PitchImpl(2));
    assertEquals(m2.getHighestPitch(), new PitchImpl(2));
    assertEquals(m2.toString(), "Measure: 3\n" + " Low Pitch: 2\n" +
        " High Pitch: 2\n" + " finalStartBeat: 4\n" + "Pitch: 2, StartTime: 3, EndTime: 4\n" +
        "Pitch: 2, StartTime: 4, EndTime: 9\n");
    m2.addNote(new PitchImpl(2), 7, 11, 0, 8);
    assertEquals(m2.getFinalStartBeat(), 7);
    assertEquals(m2.getLowestPitch(), new PitchImpl(2));
    assertEquals(m2.getHighestPitch(), new PitchImpl(2));
    assertEquals(m2.toString(),
        "Measure: 3\n" + " Low Pitch: 2\n" + " High Pitch: 2\n"
            + " finalStartBeat: 7\n" + "Pitch: 2, StartTime: 3, EndTime: 4\n" +
            "Pitch: 2, StartTime: 4, EndTime: 9\n" +
            "Pitch: 2, StartTime: 7, EndTime: 11\n");
  }

  @Test public void testAddNote4() {
    m1.addNote(new PitchImpl(2), 3, 4, 0, 6);
    assertEquals(m1.getFinalStartBeat(), 3);
    assertEquals(m1.getLowestPitch(), new PitchImpl(2));
    assertEquals(m1.getHighestPitch(), new PitchImpl(2));
    assertEquals(m1.toString(),
        "Measure: 4\n" + " Low Pitch: 2\n" + " High Pitch: 2\n" +
            " finalStartBeat: 3\n" + "Pitch: 2, StartTime: 3, EndTime: 4\n");
    m1.addNote(new PitchImpl(2), 4, 9, 0, 6);
    assertEquals(m1.getFinalStartBeat(), 4);
    assertEquals(m1.getLowestPitch(), new PitchImpl(2));
    assertEquals(m1.getHighestPitch(), new PitchImpl(2));
    assertEquals(m1.toString(), "Measure: 4\n" + " Low Pitch: 2\n" + " High Pitch: 2\n" +
            " finalStartBeat: 4\n" + "Pitch: 2, StartTime: 3, EndTime: 4\n" +
            "Pitch: 2, StartTime: 4, EndTime: 9\n");
    m1.addNote(new PitchImpl(2), 7, 11, 0, 8);
    assertEquals(m1.getFinalStartBeat(), 7);
    assertEquals(m1.getLowestPitch(), new PitchImpl(2));
    assertEquals(m1.getHighestPitch(), new PitchImpl(2));
    assertEquals(m1.toString(),
        "Measure: 4\n" + " Low Pitch: 2\n" + " High Pitch: 2\n"
            + " finalStartBeat: 7\n" + "Pitch: 2, StartTime: 3, EndTime: 4\n" +
            "Pitch: 2, StartTime: 4, EndTime: 9\n" +
            "Pitch: 2, StartTime: 7, EndTime: 11\n");
  }

  @Test public void testAddNote5() {
    m2.addNote(new PitchImpl(2), 3, 4, 0, 4);
    assertEquals(m2.toString(), "Measure: 3\n" + " Low Pitch: 2\n" + " High Pitch: 2\n" +
        " finalStartBeat: 3\n" + "Pitch: 2, StartTime: 3, EndTime: 4\n");
    m2.addNote(new PitchImpl(3), 3, 4, 0, 10);
    assertEquals(m2.toString(),
        "Measure: 3\n" + " Low Pitch: 2\n" + " High Pitch: 3\n" +
            " finalStartBeat: 3\n" + "Pitch: 2, StartTime: 3, EndTime: 4\n" +
            "Pitch: 3, StartTime: 3, EndTime: 4\n");
    m2.addNote(new PitchImpl(5), 3, 10, 0, 109);
    assertEquals(m2.toString(),
        "Measure: 3\n" + " Low Pitch: 2\n" + " High Pitch: 5\n"
            + " finalStartBeat: 3\n" +
            "Pitch: 2, StartTime: 3, EndTime: 4\n" +
            "Pitch: 3, StartTime: 3, EndTime: 4\n" +
            "Pitch: 5, StartTime: 3, EndTime: 10\n");
  }

  @Test public void testAddNote6() {
    m1.addNote(new PitchImpl(2), 3, 4, 0, 127);
    assertEquals(m1.toString(),
        "Measure: 4\n" + " Low Pitch: 2\n" + " High Pitch: 2\n" +
            " finalStartBeat: 3\n" + "Pitch: 2, StartTime: 3, EndTime: 4\n");
    m1.addNote(new PitchImpl(3), 3, 4, 0, 126);
    assertEquals(m1.toString(),
        "Measure: 4\n" + " Low Pitch: 2\n" + " High Pitch: 3\n" +
            " finalStartBeat: 3\n" + "Pitch: 2, StartTime: 3, EndTime: 4\n" +
            "Pitch: 3, StartTime: 3, EndTime: 4\n");
    m1.addNote(new PitchImpl(5), 3, 10, 0, 125);
    assertEquals(m1.toString(),
        "Measure: 4\n" + " Low Pitch: 2\n" + " High Pitch: 5\n"
            + " finalStartBeat: 3\n" +
            "Pitch: 2, StartTime: 3, EndTime: 4\n" +
            "Pitch: 3, StartTime: 3, EndTime: 4\n" +
            "Pitch: 5, StartTime: 3, EndTime: 10\n");
  }

  @Test public void testAddNote7() {
    m1.addNote(new PitchImpl(5), 5, 10, 0, 124);
    assertEquals(m1.toString(), "Measure: 4\n" + " Low Pitch: 5\n" + " High Pitch: 5\n" +
            " finalStartBeat: 5\n" + "Pitch: 5, StartTime: 5, EndTime: 10\n");
    m1.addNote(new PitchImpl(5), 7, 17, 0, 123);
    assertEquals(m1.toString(),
        "Measure: 4\n" + " Low Pitch: 5\n" + " High Pitch: 5\n" +
            " finalStartBeat: 7\n" + "Pitch: 5, StartTime: 5, EndTime: 10\n" +
            "Pitch: 5, StartTime: 7, EndTime: 17\n");
    m1.addNote(new PitchImpl(5), 10, 23, 0, 0);
    assertEquals(m1.toString(),
        "Measure: 4\n" + " Low Pitch: 5\n" + " High Pitch: 5\n"
            + " finalStartBeat: 10\n" +
            "Pitch: 5, StartTime: 5, EndTime: 10\n" +
            "Pitch: 5, StartTime: 7, EndTime: 17\n" +
            "Pitch: 5, StartTime: 10, EndTime: 23\n");
  }

  @Test public void testAddNote8() {
    m2.addNote(new PitchImpl(5), 5, 10, 0, 1);
    assertEquals(m2.toString(), "Measure: 3\n" + " Low Pitch: 5\n" + " High Pitch: 5\n" +
            " finalStartBeat: 5\n" + "Pitch: 5, StartTime: 5, EndTime: 10\n");
    m2.addNote(new PitchImpl(5), 7, 17, 0, 2);
    assertEquals(m2.toString(),
        "Measure: 3\n" + " Low Pitch: 5\n" + " High Pitch: 5\n" +
            " finalStartBeat: 7\n" + "Pitch: 5, StartTime: 5, EndTime: 10\n" +
            "Pitch: 5, StartTime: 7, EndTime: 17\n");
    m2.addNote(new PitchImpl(5), 10, 23, 0, 45);
    assertEquals(m2.toString(),
        "Measure: 3\n" + " Low Pitch: 5\n" + " High Pitch: 5\n"
            + " finalStartBeat: 10\n" +
            "Pitch: 5, StartTime: 5, EndTime: 10\n" +
            "Pitch: 5, StartTime: 7, EndTime: 17\n" +
            "Pitch: 5, StartTime: 10, EndTime: 23\n");
  }

  @Test public void testEditStartAndEnd() {
    m1.addNote(new PitchImpl(3), 4, 5, 0, 78);
    m1.addNote(new PitchImpl(9), 2, 10, 0, 90);
    m1.editNoteEndTime(new PitchImpl(3), 4, 9, 0);
    assertEquals(m1.toString(), "Measure: 4\n" +
        " Low Pitch: 3\n" + " High Pitch: 9\n" +
            " finalStartBeat: 4\n" + "Pitch: 9, StartTime: 2, EndTime: 10\n" +
            "Pitch: 3, StartTime: 4, EndTime: 9\n");
    m1.editNoteStartTime(new PitchImpl(9), 2, 7, 0);
    assertEquals(m1.toString(),
        "Measure: 4\n" + " Low Pitch: 3\n" + " High Pitch: 9\n" +
            " finalStartBeat: 7\n" + "Pitch: 3, StartTime: 4, EndTime: 9\n"
            + "Pitch: 9, StartTime: 7, EndTime: 10\n");
  }

  @Test public void testEditStartAndEnd2() {
    m2.addNote(new PitchImpl(3), 4, 5, 0, 122);
    m2.addNote(new PitchImpl(9), 2, 10, 0, 30);
    m2.editNoteEndTime(new PitchImpl(3), 4, 9, 0);
    assertEquals(m2.toString(), "Measure: 3\n" +
        " Low Pitch: 3\n" + " High Pitch: 9\n" +
            " finalStartBeat: 4\n" + "Pitch: 9, StartTime: 2, EndTime: 10\n" +
            "Pitch: 3, StartTime: 4, EndTime: 9\n");
    m2.editNoteStartTime(new PitchImpl(9), 2, 7, 0);
    assertEquals(m2.toString(),
        "Measure: 3\n" + " Low Pitch: 3\n" + " High Pitch: 9\n" +
            " finalStartBeat: 7\n" + "Pitch: 3, StartTime: 4, EndTime: 9\n"
            + "Pitch: 9, StartTime: 7, EndTime: 10\n");
  }

  @Test public void testEditStartAndEnd3() {
    m2.addNote(new PitchImpl(3), 4, 5, 0, 2);
    m2.addNote(new PitchImpl(9), 2, 10, 0, 90);
    m2.addNote(new PitchImpl(9), 8, 14, 0, 35);
    m2.editNoteEndTime(new PitchImpl(3), 4, 9, 0);
    assertEquals(m2.toString(), "Measure: 3\n" +
        " Low Pitch: 3\n" + " High Pitch: 9\n" +
        " finalStartBeat: 8\n" + "Pitch: 9, StartTime: 2, EndTime: 10\n" +
        "Pitch: 3, StartTime: 4, EndTime: 9\n" +
        "Pitch: 9, StartTime: 8, EndTime: 14\n");
    m2.editNoteStartTime(new PitchImpl(9), 2, 7, 0);
    m2.editNoteStartTime(new PitchImpl(9), 8, 10, 0);
    assertEquals(m2.toString(),
        "Measure: 3\n" + " Low Pitch: 3\n" + " High Pitch: 9\n" +
            " finalStartBeat: 10\n" + "Pitch: 3, StartTime: 4, EndTime: 9\n"
            + "Pitch: 9, StartTime: 7, EndTime: 10\n" +
            "Pitch: 9, StartTime: 10, EndTime: 14\n");
  }

  @Test public void testEditStartAndEnd4() {
    m1.addNote(new PitchImpl(3), 4, 5, 1, 34);
    m1.addNote(new PitchImpl(9), 2, 10, 1, 54);
    m1.addNote(new PitchImpl(9), 8, 14, 1, 89);
    m1.editNoteEndTime(new PitchImpl(3), 4, 9, 1);
    assertEquals(m1.toString(), "Measure: 4\n" +
        " Low Pitch: 3\n" + " High Pitch: 9\n" +
            " finalStartBeat: 8\n" + "Pitch: 9, StartTime: 2, EndTime: 10\n" +
            "Pitch: 3, StartTime: 4, EndTime: 9\n" +
            "Pitch: 9, StartTime: 8, EndTime: 14\n");
    m1.editNoteStartTime(new PitchImpl(9), 2, 7, 1);
    m1.editNoteStartTime(new PitchImpl(9), 8, 10, 1);
    assertEquals(m1.toString(),
        "Measure: 4\n" + " Low Pitch: 3\n" + " High Pitch: 9\n" +
            " finalStartBeat: 10\n" + "Pitch: 3, StartTime: 4, EndTime: 9\n"
            + "Pitch: 9, StartTime: 7, EndTime: 10\n" +
            "Pitch: 9, StartTime: 10, EndTime: 14\n");
  }

  @Test public void testGetNote() {
    m1.addNote(new PitchImpl(1), 4, 8, 2, 5);
    m1.addNote(new PitchImpl(3), 8, 9, 2, 90);
    assertEquals(m1.getNoteAt(new PitchImpl(1), 4, 2), new MusicNote(new PitchImpl(1), 4, 8, 2, 5));
    assertEquals(m1.getNoteAt(new PitchImpl(3), 8, 2),
        new MusicNote(new PitchImpl(3), 8, 9, 2, 90));
  }

  @Test public void testGetNote2() {
    m2.addNote(new PitchImpl(1), 4, 8, 2, 90);
    m2.addNote(new PitchImpl(3), 8, 9, 2, 90);
    assertEquals(m2.getNoteAt(new PitchImpl(1), 4, 2),
        new MusicNote(new PitchImpl(1), 4, 8, 2, 90));
    assertEquals(m2.getNoteAt(new PitchImpl(3), 8, 2),
        new MusicNote(new PitchImpl(3), 8, 9, 2, 90));
  }

  @Test public void testGetNote3() {
    m2.addNote(new PitchImpl(5), 6, 10, 3, 89);
    m2.addNote(new PitchImpl(2), 3, 8, 3, 2);
    assertEquals(m2.getNoteAt(new PitchImpl(5), 6, 3),
        new MusicNote(new PitchImpl(5), 6, 10, 3, 89));
    assertEquals(m2.getNoteAt(new PitchImpl(2), 3, 3),
        new MusicNote(new PitchImpl(2), 3, 8, 3, 2));
    m2.editNoteStartTime(new PitchImpl(5), 6, 9, 3);
    assertEquals(m2.getNoteAt(new PitchImpl(5), 9, 3),
        new MusicNote(new PitchImpl(5), 9, 10, 3, 89));
    assertEquals(m2.getNoteAt(new PitchImpl(2), 3, 3), new MusicNote(new PitchImpl(2), 3, 8, 3, 2));
    m2.editNoteEndTime(new PitchImpl(2), 3, 5, 3);
    assertEquals(m2.getNoteAt(new PitchImpl(5), 9, 3),
        new MusicNote(new PitchImpl(5), 9, 10, 3, 89));
    assertEquals(m2.getNoteAt(new PitchImpl(2), 3, 3),
        new MusicNote(new PitchImpl(2), 3, 5, 3, 2));
  }

  @Test public void testGetNote4() {
    m1.addNote(new PitchImpl(5), 6, 10, 0, 0);
    m1.addNote(new PitchImpl(2), 3, 8, 0, 0);
    assertEquals(m1.getNoteAt(new PitchImpl(5), 6, 0),
        new MusicNote(new PitchImpl(5), 6, 10, 0, 0));
    assertEquals(m1.getNoteAt(new PitchImpl(2), 3, 0),
        new MusicNote(new PitchImpl(2), 3, 8, 0, 0));
    m1.editNoteStartTime(new PitchImpl(5), 6, 9, 0);
    assertEquals(m1.getNoteAt(new PitchImpl(5), 9, 0),
        new MusicNote(new PitchImpl(5), 9, 10, 0, 0));
    assertEquals(m1.getNoteAt(new PitchImpl(2), 3, 0), new MusicNote(new PitchImpl(2), 3, 8, 0, 0));
    m1.editNoteEndTime(new PitchImpl(2), 3, 5, 0);
    assertEquals(m1.getNoteAt(new PitchImpl(5), 9, 0),
        new MusicNote(new PitchImpl(5), 9, 10, 0, 0));
    assertEquals(m1.getNoteAt(new PitchImpl(2), 3, 0),
        new MusicNote(new PitchImpl(2), 3, 5, 0, 0));
  }

  @Test public void testDeleteNote() {
    m1.addNote(new PitchImpl(4), 6, 10, 1, 80);
    m1.addNote(new PitchImpl(7), 9, 11, 1, 80);
    assertEquals(m1.toString(),
        "Measure: 4\n" + " Low Pitch: 4\n" + " High Pitch: 7\n" +
            " finalStartBeat: 9\n" + "Pitch: 4, StartTime: 6, EndTime: 10\n" +
            "Pitch: 7, StartTime: 9, EndTime: 11\n");
    m1.deleteNote(new PitchImpl(4), 6, 1);
    assertEquals(m1.toString(), "Measure: 4\n" + " Low Pitch: 7\n" + " High Pitch: 7\n" +
        " finalStartBeat: 9\n" + "Pitch: 7, StartTime: 9, EndTime: 11\n");
    m1.deleteNote(new PitchImpl(7), 9, 1);
    assertEquals(m1.toString(), "Measure: 4\n" + " Low Pitch: -1\n" +
        " High Pitch: -1\n" + " finalStartBeat: 0\n");
  }

  @Test public void testDeleteNote2() {
    m2.addNote(new PitchImpl(4), 6, 10, 0, 70);
    m2.addNote(new PitchImpl(7), 9, 11, 0, 70);
    assertEquals(m2.toString(),
        "Measure: 3\n" + " Low Pitch: 4\n" + " High Pitch: 7\n" +
            " finalStartBeat: 9\n" + "Pitch: 4, StartTime: 6, EndTime: 10\n" +
            "Pitch: 7, StartTime: 9, EndTime: 11\n");
    m2.deleteNote(new PitchImpl(4), 6, 0);
    assertEquals(m2.toString(),
        "Measure: 3\n" + " Low Pitch: 7\n" + " High Pitch: 7\n" +
            " finalStartBeat: 9\n" + "Pitch: 7, StartTime: 9, EndTime: 11\n");
    m2.deleteNote(new PitchImpl(7), 9, 0);
    assertEquals(m2.toString(), "Measure: 3\n" + " Low Pitch: -1\n" +
        " High Pitch: -1\n" + " finalStartBeat: 0\n");
  }

  @Test public void testDeleteNote3() {
    m2.addNote(new PitchImpl(4), 6, 10, 1, 8);
    m2.addNote(new PitchImpl(7), 9, 11, 1, 9);
    assertEquals(m2.toString(),
        "Measure: 3\n" + " Low Pitch: 4\n" + " High Pitch: 7\n" +
            " finalStartBeat: 9\n" + "Pitch: 4, StartTime: 6, EndTime: 10\n" +
            "Pitch: 7, StartTime: 9, EndTime: 11\n");
    m2.deleteNote(new PitchImpl(4), 6, 1);
    assertEquals(m2.toString(), "Measure: 3\n" + " Low Pitch: 7\n" + " High Pitch: 7\n" +
        " finalStartBeat: 9\n" + "Pitch: 7, StartTime: 9, EndTime: 11\n");
    m2.deleteNote(new PitchImpl(7), 9, 1);
    assertEquals(m2.toString(), "Measure: 3\n" + " Low Pitch: -1\n" +
        " High Pitch: -1\n" + " finalStartBeat: 0\n");
    m2.addNote(new PitchImpl(4), 6, 10, 1, 8);
    assertEquals(m2.toString(), "Measure: 3\n" + " Low Pitch: 4\n" +
        " High Pitch: 4\n" + " finalStartBeat: 6\n" + "Pitch: 4, StartTime: 6, EndTime: 10\n");
    m2.deleteNote(new PitchImpl(4), 6, 1);
    assertEquals(m2.toString(),
        "Measure: 3\n" + " Low Pitch: -1\n" + " High Pitch: -1\n"
            + " finalStartBeat: 0\n");
  }

  @Test public void testDeleteNote4() {
    m1.addNote(new PitchImpl(4), 6, 10, 1, 7);
    m1.addNote(new PitchImpl(7), 9, 11, 1, 7);
    assertEquals(m1.toString(),
        "Measure: 4\n" + " Low Pitch: 4\n" + " High Pitch: 7\n" +
            " finalStartBeat: 9\n" + "Pitch: 4, StartTime: 6, EndTime: 10\n" +
            "Pitch: 7, StartTime: 9, EndTime: 11\n");
    m1.deleteNote(new PitchImpl(4), 6, 1);
    assertEquals(m1.toString(),
        "Measure: 4\n" + " Low Pitch: 7\n" + " High Pitch: 7\n" +
            " finalStartBeat: 9\n" + "Pitch: 7, StartTime: 9, EndTime: 11\n");
    m1.deleteNote(new PitchImpl(7), 9, 1);
    assertEquals(m1.toString(), "Measure: 4\n" + " Low Pitch: -1\n" +
        " High Pitch: -1\n" + " finalStartBeat: 0\n");
    m1.addNote(new PitchImpl(4), 6, 10, 1, 7);
    assertEquals(m1.toString(), "Measure: 4\n" + " Low Pitch: 4\n" +
        " High Pitch: 4\n" + " finalStartBeat: 6\n" + "Pitch: 4, StartTime: 6, EndTime: 10\n");
    m1.deleteNote(new PitchImpl(4), 6, 1);
    assertEquals(m1.toString(),
        "Measure: 4\n" + " Low Pitch: -1\n" + " High Pitch: -1\n"
            + " finalStartBeat: 0\n");
  }

  @Test public void testGetPlayedNext() {
    assertEquals(m1.getNotesAtTime(0), new ArrayList<Note>());
  }

  @Test public void testGetPlayedNext2() {
    assertEquals(m2.getNotesAtTime(0), new ArrayList<Note>());
  }

  @Test public void testGetPlayedNext3() {
    m1.addNote(new PitchImpl(3), 0, 4, 1, 127);
    List<Note> l = new ArrayList<>();
    l.add(new MusicNote(new PitchImpl(3), 0, 4, 1, 127));
    assertEquals(m1.getNotesAtTime(0), l);
  }

  @Test public void testGetPlayedNext4() {
    m2.addNote(new PitchImpl(3), 0, 4, 2, 0);
    List<Note> l = new ArrayList<>();
    l.add(new MusicNote(new PitchImpl(3), 0, 4, 2, 0));
    assertEquals(m2.getNotesAtTime(0), l);
  }

  @Test public void testGetPlayedNext5() {
    m1.addNote(new PitchImpl(3), 0, 4, 3, 78);
    m1.addNote(new PitchImpl(6), 0, 6, 3, 78);
    List<Note> l = new ArrayList<>();
    l.add(new MusicNote(new PitchImpl(3), 0, 4, 3, 78));
    l.add(new MusicNote(new PitchImpl(6), 0, 6, 3, 78));
    assertEquals(m1.getNotesAtTime(0), l);
    assertEquals(m1.getNotesAtTime(1), new ArrayList<>());
  }

  @Test public void testGetPlayedNext7() {
    m2.addNote(new PitchImpl(3), 0, 4, 3, 100);
    m2.addNote(new PitchImpl(6), 0, 6, 3, 100);
    m2.addNote(new PitchImpl(4), 1, 8, 3, 100);
    m2.addNote(new PitchImpl(9), 2, 3, 3, 100);
    List<Note> l = new ArrayList<>();
    l.add(new MusicNote(new PitchImpl(3), 0, 4, 3, 100));
    l.add(new MusicNote(new PitchImpl(6), 0, 6, 3, 100));
    List<Note> l2 = new ArrayList<>();
    l2.add(new MusicNote(new PitchImpl(4), 1, 8, 3, 100));
    List<Note> l3 = new ArrayList<>();
    l3.add(new MusicNote(new PitchImpl(9), 2, 3, 3, 100));
    assertEquals(m2.getNotesAtTime(0), l);
    assertEquals(m2.getNotesAtTime(1), l2);
    assertEquals(m2.getNotesAtTime(2), l3);
    assertEquals(m2.getNotesAtTime(3), new ArrayList<Note>());
  }

  @Test public void testGetPlayedNext8() {
    m1.addNote(new PitchImpl(3), 0, 4, 0, 0);
    m1.addNote(new PitchImpl(6), 0, 6, 1, 10);
    m1.addNote(new PitchImpl(4), 1, 8, 0, 45);
    m1.addNote(new PitchImpl(9), 2, 3, 1, 89);
    List<Note> l = new ArrayList<>();
    l.add(new MusicNote(new PitchImpl(3), 0, 4, 0, 0));
    l.add(new MusicNote(new PitchImpl(6), 0, 6, 1, 10));
    List<Note> l2 = new ArrayList<>();
    l2.add(new MusicNote(new PitchImpl(4), 1, 8, 0, 45));
    List<Note> l3 = new ArrayList<>();
    l3.add(new MusicNote(new PitchImpl(9), 2, 3, 1, 89));
    assertEquals(m1.getNotesAtTime(0), l);
    assertEquals(m1.getNotesAtTime(1), l2);
    assertEquals(m1.getNotesAtTime(2), l3);
    assertEquals(m1.getNotesAtTime(3), new ArrayList<Note>());
  }

  @Test public void testAddAll() {
    m1.addNote(new PitchImpl(3), 2, 5, 2, 89);
    List<Note> l = new ArrayList<>();
    l.add(n1);
    l.add(n2);
    m1.addAllNotes(l, true);
    assertEquals(m1.toString(),
        "Measure: 4\n" + " Low Pitch: 2\n" + " High Pitch: 4\n" +
            " finalStartBeat: 7\n" + "Pitch: 3, StartTime: 2, EndTime: 5\n"
            + "Pitch: 2, StartTime: 5, EndTime: 6\n" + "Pitch: 4, StartTime: 7, EndTime: 11\n");
  }

  @Test public void testAddAll2() {
    m2.addNote(new PitchImpl(3), 2, 5, 0, 65);
    List<Note> l = new ArrayList<>();
    l.add(n1);
    l.add(n2);
    m2.addAllNotes(l, true);
    assertEquals(m2.toString(),
        "Measure: 3\n" + " Low Pitch: 2\n" + " High Pitch: 4\n" +
            " finalStartBeat: 7\n" + "Pitch: 3, StartTime: 2, EndTime: 5\n"
            + "Pitch: 2, StartTime: 5, EndTime: 6\n" + "Pitch: 4, StartTime: 7, EndTime: 11\n");
  }

  @Test public void testAddAll3() {
    m1.addNote(new PitchImpl(3), 2, 5, 0, 123);
    List<Note> l = new ArrayList<>();
    l.add(n1);
    l.add(n2);
    m1.addAllNotes(l, false);
    assertEquals(m1.toString(),
        "Measure: 4\n" + " Low Pitch: 2\n" + " High Pitch: 4\n" +
            " finalStartBeat: 5\n" + "Pitch: 3, StartTime: 2, EndTime: 5\n"
            + "Pitch: 2, StartTime: 3, EndTime: 4\n" + "Pitch: 4, StartTime: 5, EndTime: 9\n");
  }

  @Test public void testAddAll4() {
    m2.addNote(new PitchImpl(3), 2, 5, 0, 0);
    List<Note> l = new ArrayList<>();
    l.add(n1);
    l.add(n2);
    m2.addAllNotes(l, false);
    assertEquals(m2.toString(),
        "Measure: 3\n" + " Low Pitch: 2\n" + " High Pitch: 4\n" +
            " finalStartBeat: 5\n" + "Pitch: 3, StartTime: 2, EndTime: 5\n"
            + "Pitch: 2, StartTime: 3, EndTime: 4\n" + "Pitch: 4, StartTime: 5, EndTime: 9\n");
  }

  @Test public void testAddAll5() {
    m1.addNote(new PitchImpl(3), 2, 5, 0, 24);
    List<Note> l = new ArrayList<>();
    l.add(new MusicNote(new PitchImpl(3), 2, 7, 2, 2));
    l.add(new MusicNote(new PitchImpl(4), 6, 9, 2, 9));
    m1.addAllNotes(l, false);
    assertEquals(m1.toString(),
        "Measure: 4\n" + " Low Pitch: 3\n" + " High Pitch: 4\n" +
            " finalStartBeat: 6\n" + "Pitch: 3, StartTime: 2, EndTime: 5\n"
            + "Pitch: 3, StartTime: 2, EndTime: 7\n" + "Pitch: 4, StartTime: 6, EndTime: 9\n");
  }

  @Test public void testAddAll6() {
    m2.addNote(new PitchImpl(3), 2, 5, 0, 100);
    List<Note> l = new ArrayList<>();
    l.add(new MusicNote(new PitchImpl(3), 2, 7, 0, 1));
    l.add(new MusicNote(new PitchImpl(4), 6, 9, 0, 34));
    m2.addAllNotes(l, false);
    assertEquals(m2.toString(),
        "Measure: 3\n" + " Low Pitch: 3\n" + " High Pitch: 4\n" +
            " finalStartBeat: 6\n"
            + "Pitch: 3, StartTime: 2, EndTime: 5\n"
            + "Pitch: 4, StartTime: 6, EndTime: 9\n");
  }

  @Test public void testGetTempo() {
    assertEquals(m1.getTempo(), 120);
    assertEquals(m2.getTempo(), 100);
  }

  @Test public void testSetTempo() {
    assertEquals(m1.getTempo(), 120);
    m1.setTempo(100000);
    assertEquals(m1.getTempo(), 100000);
  }

  @Test public void testSetTempo2() {
    assertEquals(m2.getTempo(), 100);
    m2.setTempo(20000);
    assertEquals(m2.getTempo(), 20000);
    m2.setTempo(70000);
    assertEquals(m2.getTempo(), 70000);
  }

  @Test public void testPitchValue() {
    Pitch p = new PitchImpl(60);
    assertEquals(p.getValue(), 60);
    assertEquals(p.toString(), "  C4");
  }

  @Test public void testPitchValue2() {
    Pitch p = new PitchImpl(2);
    assertEquals(p.getValue(), 2);
    assertEquals(p.toString(), " D-1");
  }

  @Test public void testPitchValue3() {
    Pitch p = new PitchImpl(127);
    assertEquals(p.getValue(), 127);
    assertEquals(p.toString(), "  G9");
  }

  @Test public void testPitchValue4() {
    Pitch p = new PitchImpl(6);
    assertEquals(p.getValue(), 6);
    assertEquals(p.toString(), "F#-1");
  }

  @Test public void testPitchValue5() {
    Pitch p = new PitchImpl(85);
    assertEquals(p.getValue(), 85);
    assertEquals(p.toString(), " C#6");
  }

  @Test public void testAddNoteInstrument() {
    m1.addNote(new PitchImpl(9), 4, 11, 13, 9);
    m1.addNote(new PitchImpl(9), 4, 11, 70, 3);
    assertEquals(m1.toString(),
        "Measure: 4\n" + " Low Pitch: 9\n" + " High Pitch: 9\n" +
            " finalStartBeat: 4\n" + "Pitch: 9, StartTime: 4, EndTime: 11\n"
            + "Pitch: 9, StartTime: 4, EndTime: 11\n");

  }

  @Test public void testAddNoteInstrument2() {
    m2.addNote(new PitchImpl(9), 4, 11, 13, 9);
    m2.addNote(new PitchImpl(9), 4, 11, 70, 3);
    assertEquals(m2.toString(),
        "Measure: 3\n" + " Low Pitch: 9\n" + " High Pitch: 9\n" +
            " finalStartBeat: 4\n" + "Pitch: 9, StartTime: 4, EndTime: 11\n"
            + "Pitch: 9, StartTime: 4, EndTime: 11\n");

  }

  @Test public void testAddNoteInstrument3() {
    m1.addNote(new PitchImpl(9), 4, 11, 13, 9);
    m1.addNote(new PitchImpl(9), 4, 13, 70, 3);
    m1.addNote(new PitchImpl(9), 4, 17, 89, 2);
    assertEquals(m1.toString(),
        "Measure: 4\n" + " Low Pitch: 9\n" + " High Pitch: 9\n" +
            " finalStartBeat: 4\n" + "Pitch: 9, StartTime: 4, EndTime: 11\n"
            + "Pitch: 9, StartTime: 4, EndTime: 13\n" + "Pitch: 9, StartTime: 4, EndTime: 17\n");

  }

  @Test public void testAddNoteInstrument4() {
    m2.addNote(new PitchImpl(9), 4, 11, 13, 9);
    m2.addNote(new PitchImpl(9), 4, 13, 70, 3);
    m2.addNote(new PitchImpl(9), 4, 17, 89, 2);
    assertEquals(m2.toString(),
        "Measure: 3\n" + " Low Pitch: 9\n" + " High Pitch: 9\n" +
            " finalStartBeat: 4\n" + "Pitch: 9, StartTime: 4, EndTime: 11\n"
            + "Pitch: 9, StartTime: 4, EndTime: 13\n" + "Pitch: 9, StartTime: 4, EndTime: 17\n");

  }

  @Test
  public void testGetEndNotes() {
    m1.addNote(new PitchImpl(34), 9, 23, 12, 2);
    ArrayList<Note> a = new ArrayList<>();
    a.add(new MusicNote(new PitchImpl(34), 9, 23, 12, 2));
    assertEquals(m1.getEndNotesAtTime(23), a);
  }
  @Test
  public void testGetEndNotes2() {
    m1.addNote(new PitchImpl(34), 9, 23, 12, 2);
    m1.addNote(new PitchImpl(56), 4, 70, 3, 1);
    ArrayList<Note> a = new ArrayList<>();
    a.add(new MusicNote(new PitchImpl(34), 9, 23, 12, 2));
    assertEquals(m1.getEndNotesAtTime(23), a);
    ArrayList<Note> b = new ArrayList<>();
    b.add(new MusicNote(new PitchImpl(56), 4, 70, 3, 1));
    assertEquals(m1.getEndNotesAtTime(70), b);
  }

  @Test
  public void testGetEndNotes3() {
    m1.addNote(new PitchImpl(34), 9, 23, 12, 2);
    m1.addNote(new PitchImpl(56), 4, 70, 3, 1);
    m1.addNote(new PitchImpl(43), 2, 23, 9, 3);
    ArrayList<Note> a = new ArrayList<>();
    a.add(new MusicNote(new PitchImpl(43), 2, 23, 9, 3));
    a.add(new MusicNote(new PitchImpl(34), 9, 23, 12, 2));
    assertEquals(m1.getEndNotesAtTime(23), a);
    ArrayList<Note> b = new ArrayList<>();
    b.add(new MusicNote(new PitchImpl(56), 4, 70, 3, 1));
    assertEquals(m1.getEndNotesAtTime(70), b);
  }

  @Test
  public void testGetEndNotes4() {
    m1.addNote(new PitchImpl(34), 9, 23, 12, 2);
    m1.addNote(new PitchImpl(56), 4, 70, 3, 1);
    m1.addNote(new PitchImpl(43), 2, 23, 9, 3);
    m1.addNote(new PitchImpl(11), 9, 70, 34, 1);
    ArrayList<Note> a = new ArrayList<>();
    a.add(new MusicNote(new PitchImpl(43), 2, 23, 9, 3));
    a.add(new MusicNote(new PitchImpl(34), 9, 23, 12, 2));
    assertEquals(m1.getEndNotesAtTime(23), a);
    ArrayList<Note> b = new ArrayList<>();
    b.add(new MusicNote(new PitchImpl(56), 4, 70, 3, 1));
    b.add(new MusicNote(new PitchImpl(11), 9, 70, 34, 1));
    assertEquals(m1.getEndNotesAtTime(70), b);
  }

  @Test
  public void testGetEndNotes5() {
    m1.addNote(new PitchImpl(34), 9, 23, 12, 2);
    m1.addNote(new PitchImpl(56), 4, 70, 3, 1);
    m1.addNote(new PitchImpl(43), 2, 23, 9, 3);
    m1.addNote(new PitchImpl(11), 9, 70, 34, 1);
    ArrayList<Note> a = new ArrayList<>();
    assertEquals(m1.getEndNotesAtTime(9), a);
  }

  @Test
  public void testGetNoteIn() {
    m1.addNote(new PitchImpl(34), 9, 23, 12, 2);
    m1.addNote(new PitchImpl(56), 4, 70, 3, 1);
    m1.addNote(new PitchImpl(43), 2, 23, 9, 3);
    m1.addNote(new PitchImpl(11), 9, 70, 34, 1);
    assertEquals(m1.getNoteIn(new PitchImpl(34), 13),
        new MusicNote(new PitchImpl(34), 9, 23, 12, 2));
    assertEquals(m1.getNoteIn(new PitchImpl(43), 2), new MusicNote(new PitchImpl(43), 2, 23, 9, 3));
    assertEquals(m1.getNoteIn(new PitchImpl(11), 69),
        new MusicNote(new PitchImpl(11), 9, 70, 34, 1));
    assertEquals(m1.getNoteIn(new PitchImpl(56), 60),
        new MusicNote(new PitchImpl(56), 4, 70, 3, 1));
  }

  @Test
  public void testGetNoteInInstrument() {
    m1.addNote(new PitchImpl(34), 9, 23, 12, 2);
    m1.addNote(new PitchImpl(56), 4, 70, 3, 1);
    m1.addNote(new PitchImpl(43), 2, 23, 9, 3);
    m1.addNote(new PitchImpl(11), 9, 70, 34, 1);
    assertEquals(m1.getNoteIn(new PitchImpl(34), 13, 12),
        new MusicNote(new PitchImpl(34), 9, 23, 12, 2));
    assertEquals(m1.getNoteIn(new PitchImpl(43), 2, 9),
        new MusicNote(new PitchImpl(43), 2, 23, 9, 3));
    assertEquals(m1.getNoteIn(new PitchImpl(11), 69, 34),
        new MusicNote(new PitchImpl(11), 9, 70, 34, 1));
    assertEquals(m1.getNoteIn(new PitchImpl(56), 60, 3),
        new MusicNote(new PitchImpl(56), 4, 70, 3, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void noteConstructorException() {
    new MusicNote(new PitchImpl(-2), 3, 4, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void noteConstructorException2() {
    new MusicNote(new PitchImpl(2), -3, 7, 1, 9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void noteConstructorException3() {
    new MusicNote(new PitchImpl(5), 4, -10, 1, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void noteConstructorException4() {
    new MusicNote(new PitchImpl(-1), -1, -1, 1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void noteConstructorException5() {
    new MusicNote(new PitchImpl(2), 7, 3, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void noteConstructorException6() {
    new MusicNote(new PitchImpl(2), 7, 7, 1, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void modelConstructorException() {
    new ModelImpl(-1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void modelConstructorException2() {
    new ModelImpl(-4, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void modelConstructorException3() {
    new ModelImpl(0, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNoteException() {
    m1.addNote(new PitchImpl(-1), 2, 3, 0, 32);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNoteException2() {
    m2.addNote(new PitchImpl(-10), 2, 3, 0, 101);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNoteException3() {
    m2.addNote(new PitchImpl(10), 3, -6, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNoteException4() {
    m1.addNote(new PitchImpl(10), -3, 6, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNoteException5() {
    m1.addNote(new PitchImpl(-1), -3, -6, 1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNoteException6() {
    m2.addNote(new PitchImpl(4), 3, 3, 0, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNoteException7() {
    m2.addNote(new PitchImpl(9), 2, 2, 1, 90);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNoteException8() {
    m1.addNote(new PitchImpl(5), 9, 2, 1, 43);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNoteException9() {
    m2.addNote(new PitchImpl(3), 11, 7, 1, 53);
  }

  @Test(expected = Model.IllegalAddException.class)
  public void addNoteException10() {
    m2.addNote(new PitchImpl(3), 4, 7, 1, 46);
    m2.addNote(new PitchImpl(3), 4, 19, 1, 78);
  }

  @Test(expected = Model.IllegalAddException.class)
  public void addNoteException11() {
    m1.addNote(new PitchImpl(5), 6, 9, 0, 125);
    m1.addNote(new PitchImpl(5), 6, 13, 0, 125);
  }

  @Test(expected = Model.IllegalAddException.class)
  public void addNoteException12() {
    m1.addNote(new PitchImpl(4), 10, 12, 0, 126);
    m1.addNote(new PitchImpl(7), 11, 20, 0, 127);
    m1.addNote(new PitchImpl(4), 10, 16, 0, 89);
  }

  @Test(expected = IllegalArgumentException.class)
  public void editStartTimeException() {
    m1.addNote(new PitchImpl(2), 3, 5, 0, 34);
    m1.editNoteStartTime(new PitchImpl(2), 3, 5, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void editStartTimeException2() {
    Pitch p = new PitchImpl(2);
    m1.addNote(p, 3, 5, 0, 19);
    m1.editNoteStartTime(p, 3, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void editStartTimeException3() {
    m1.addNote(new PitchImpl(2), 3, 5, 0, 15);
    m1.editNoteStartTime(new PitchImpl(2), 3, -4, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void editStartTimeException4() {
    m1.addNote(new PitchImpl(2), 3, 5, 0, 3);
    m1.addNote(new PitchImpl(2), 1, 2, 0, 3);
    m1.editNoteStartTime(new PitchImpl(2), 3, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void editStartTimeException5() {
    m2.addNote(new PitchImpl(2), 3, 5, 0, 2);
    m2.addNote(new PitchImpl(2), 1, 2, 0, 0);
    m2.editNoteStartTime(new PitchImpl(2), 3, 1, 0);
  }

  @Test(expected = Model.IllegalAccessNoteException.class)
  public void editStartTimeException6() {
    m2.addNote(new PitchImpl(2), 3, 5, 0, 6);
    m2.addNote(new PitchImpl(2), 1, 2, 0, 3);
    m2.editNoteStartTime(new PitchImpl(3), 3, 9, 0);
  }

  @Test(expected = Model.IllegalAccessNoteException.class)
  public void editStartTimeException7() {
    m2.addNote(new PitchImpl(2), 3, 5, 0, 9);
    m2.addNote(new PitchImpl(2), 1, 2, 0, 8);
    m2.editNoteStartTime(new PitchImpl(2), 6, 10, 0);
  }

  @Test(expected = Model.IllegalAccessNoteException.class)
  public void editStartTimeException8() {
    m1.addNote(new PitchImpl(2), 3, 5, 0, 6);
    m1.addNote(new PitchImpl(2), 1, 2, 0, 4);
    m1.editNoteStartTime(new PitchImpl(2), 6, 10, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void editEndTimeException() {
    m1.addNote(new PitchImpl(2), 3, 6, 0, 6);
    m1.addNote(new PitchImpl(5), 6, 7, 0, 4);
    m1.editNoteEndTime(new PitchImpl(2), 3, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void editEndTimeException2() {
    m1.addNote(new PitchImpl(2), 3, 6, 0, 8);
    m1.addNote(new PitchImpl(5), 6, 7, 0, 9);
    m1.editNoteEndTime(new PitchImpl(2), 3, 3, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void editEndTimeException3() {
    m1.addNote(new PitchImpl(2), 3, 6, 0, 3);
    m1.addNote(new PitchImpl(5), 6, 7, 0, 6);
    m1.editNoteEndTime(new PitchImpl(2), 3, 2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void editEndTimeException4() {
    m2.addNote(new PitchImpl(2), 3, 6, 0, 4);
    m2.addNote(new PitchImpl(5), 6, 7, 0, 6);
    m2.editNoteEndTime(new PitchImpl(2), 3, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void editEndTimeException5() {
    m2.addNote(new PitchImpl(2), 3, 6, 0, 23);
    m2.addNote(new PitchImpl(5), 6, 7, 0, 90);
    m2.editNoteEndTime(new PitchImpl(2), 3, 3, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void editEndTimeException6() {
    m2.addNote(new PitchImpl(2), 3, 6, 0, 45);
    m2.addNote(new PitchImpl(5), 6, 7, 0, 87);
    m2.editNoteEndTime(new PitchImpl(2), 3, 2, 0);
  }

  @Test(expected = Model.IllegalAccessNoteException.class)
  public void editEndTimeException7() {
    m2.addNote(new PitchImpl(2), 3, 6, 0, 75);
    m2.addNote(new PitchImpl(5), 6, 7, 0, 76);
    m2.editNoteEndTime(new PitchImpl(3), 3, 7, 0);
  }

  @Test(expected = Model.IllegalAccessNoteException.class)
  public void editEndTimeException8() {
    m1.addNote(new PitchImpl(2), 3, 6, 0, 100);
    m1.addNote(new PitchImpl(5), 6, 7, 0, 100);
    m1.editNoteEndTime(new PitchImpl(3), 3, 7, 0);
  }

  @Test(expected = Model.IllegalAccessNoteException.class)
  public void editEndTimeException9() {
    m2.addNote(new PitchImpl(2), 3, 6, 0, 9);
    m2.addNote(new PitchImpl(5), 6, 7, 0, 100);
    m2.editNoteEndTime(new PitchImpl(5), 8, 10, 0);
  }

  @Test(expected = Model.IllegalAccessNoteException.class)
  public void deleteNodeException() {
    m1.deleteNote(new PitchImpl(2), 3, 0);
  }

  @Test(expected = Model.IllegalAccessNoteException.class)
  public void deleteNodeException2() {
    m2.deleteNote(new PitchImpl(4), 6, 0);
  }

  @Test(expected = Model.IllegalAccessNoteException.class)
  public void deleteNodeException3() {
    m1.addNote(new PitchImpl(5), 9, 13, 0, 5);
    m1.deleteNote(new PitchImpl(5), 9, 0);
    m1.deleteNote(new PitchImpl(5), 9, 0);
  }

  @Test(expected = Model.IllegalAccessNoteException.class)
  public void deleteNodeException4() {
    m2.addNote(new PitchImpl(5), 9, 13, 0, 8);
    m2.deleteNote(new PitchImpl(5), 9, 0);
    m2.deleteNote(new PitchImpl(5), 9, 0);
  }

  @Test(expected = Model.IllegalAccessNoteException.class)
  public void deleteNodeException5() {
    m2.addNote(new PitchImpl(5), 9, 13, 0, 8);
    m2.deleteNote(new PitchImpl(6), 9, 0);
  }

  @Test(expected = Model.IllegalAccessNoteException.class)
  public void deleteNodeException6() {
    m1.addNote(new PitchImpl(5), 9, 13, 0, 7);
    m1.deleteNote(new PitchImpl(6), 9, 0);
  }

  @Test(expected = Model.IllegalAccessNoteException.class)
  public void deleteNodeException7() {
    m2.addNote(new PitchImpl(7), 2, 8, 0, 7);
    m2.deleteNote(new PitchImpl(7), 6, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNodeException7() {
    m2.addNote(new PitchImpl(7), 2, 8, -1, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNodeException8() {
    m2.addNote(new PitchImpl(7), 2, 8, 9, -89);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNodeException10() {
    m2.addNote(new PitchImpl(7), 2, 8, 145, 89);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNodeException11() {
    m2.addNote(new PitchImpl(7), 2, 8, 50, 870);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNodeException12() {
    m2.addNote(new PitchImpl(7), 2, 8, 50, 128);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNodeException13() {
    m2.addNote(new PitchImpl(7), 2, 8, 128, 23);
  }

  @Test(expected = IllegalArgumentException.class)
  public void makeNoteException1() {
    new MusicNote(new PitchImpl(8), 17, 18, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void makeNoteException2() {
    new MusicNote(new PitchImpl(8), 17, 18, 90, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void makeNoteException3() {
    new MusicNote(new PitchImpl(8), 17, 18, 128, 80);
  }

  @Test(expected = IllegalArgumentException.class)
  public void makeNoteException4() {
    new MusicNote(new PitchImpl(8), 17, 18, 18, 180);
  }

  @Test(expected = Model.IllegalAccessNoteException.class)
  public void testGetNoteInException() {
    m1.getNoteIn(new PitchImpl(24), 6);
  }

  @Test(expected = Model.IllegalAccessNoteException.class)
  public void testGetNoteInException2() {
    m1.getNoteIn(new PitchImpl(24), 6, 8);
  }

  @Test(expected = Model.IllegalAccessNoteException.class)
  public void testGetNoteInException3() {
    m1.addNote(new PitchImpl(24), 2, 6, 9, 10);
    m1.getNoteIn(new PitchImpl(24), 6);
  }

  @Test(expected = Model.IllegalAccessNoteException.class)
  public void testGetNoteInException4() {
    m1.addNote(new PitchImpl(24), 2, 6, 9, 10);
    m1.getNoteIn(new PitchImpl(24), 6, 9);
  }

  @Test(expected = Model.IllegalAccessNoteException.class)
  public void testGetNoteInException5() {
    m1.addNote(new PitchImpl(24), 2, 6, 9, 10);
    m1.getNoteIn(new PitchImpl(32), 4, 1);
  }

  @Test(expected = Model.IllegalAccessNoteException.class)
  public void testGetNoteInException6() {
    m1.addNote(new PitchImpl(24), 2, 6, 9, 10);
    m1.getNoteIn(new PitchImpl(32), 4);
  }
}
