package cs3500.music;


import cs3500.music.model.*;
import cs3500.music.view.*;
import cs3500.music.util.*;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Tests the ConsoleView and the ModelToView
 */
public class ConsoleViewTest extends AbstractViewTest {

  /**
   * The ConsoleView, containing a ModelToView object
   */
  private ConsoleView v;

  /**
   * The ModelToView object, containing a model
   */
  private ModelToView mtv;

  /**
   * Creates the appropriate views with the given model
   *
   * @param m the model that the views will represent
   */
  private void create(Model m) {
    this.mtv = new ModelToViewImpl(m);
    this.v = new ConsoleView(mtv);
  }

  @Test public void testMtv1() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    create(mary);
    assertEquals(mtv.getLength(), 64);
  }

  @Test public void testMtv2() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    create(mary);
    assertEquals(mtv.printRow(0),
        "              X                                   X  " + "           ");
    assertEquals(mtv.printRow(1),
        "              |                                   |  " + "           ");
    assertEquals(mtv.printRow(2),
        "              |                           X          " + "           ");
    assertEquals(mtv.printRow(3),
        "              |                           |          " + "           ");
    assertEquals(mtv.printRow(4),
        "              |                   X                  " + "           ");
    assertEquals(mtv.printRow(5),
        "              |                   |                  " + "           ");
    assertEquals(mtv.printRow(6),
        "              |                           X          " + "           ");
    assertEquals(mtv.printRow(7),
        "                                          |           " + "          ");
    assertEquals(mtv.printRow(8),
        "              X                                   X   " + "          ");
    assertEquals(mtv.printRow(9),
        "              |                                   |   " + "          ");
    assertEquals(mtv.printRow(10),
        "              |                                   X   " + "          ");
    assertEquals(mtv.printRow(11),
        "              |                                   |   " + "          ");
    assertEquals(mtv.printRow(12),
        "              |                                   X   " + "          ");
    assertEquals(mtv.printRow(13),
        "              |                                   |   " + "          ");
    assertEquals(mtv.printRow(14),
        "              |                                   |   " + "          ");
    assertEquals(mtv.printRow(15),
        "                                                      " + "          ");
    assertEquals(mtv.printRow(16),
        "              X                           X           " + "          ");
    assertEquals(mtv.printRow(17),
        "              |                           |           " + "          ");
    assertEquals(mtv.printRow(18),
        "              |                           X           " + "          ");
    assertEquals(mtv.printRow(19),
        "              |                           |           " + "          ");
    assertEquals(mtv.printRow(20),
        "              |                           X           " + "          ");
    assertEquals(mtv.printRow(21),
        "              |                           |           " + "          ");
    assertEquals(mtv.printRow(22),
        "              |                           |           " + "          ");
    assertEquals(mtv.printRow(23),
        "              |                           |            " + "         ");
    assertEquals(mtv.printRow(24),
        "              X                                   X   " + "          ");
    assertEquals(mtv.printRow(25),
        "              |                                   |   " + "          ");
    assertEquals(mtv.printRow(26),
        "                                                       " + "       X ");
    assertEquals(mtv.printRow(27),
        "                                                       " + "       | ");
    assertEquals(mtv.printRow(28),
        "                                                        " + "      X ");
    assertEquals(mtv.printRow(29),
        "                                                       " + "       | ");
    assertEquals(mtv.printRow(30),
        "                                                       " + "       | ");
    assertEquals(mtv.printRow(31),
        "                                                        " + "      | ");
    assertEquals(mtv.printRow(32),
        "              X                                   X     " + "        ");
    assertEquals(mtv.printRow(33),
        "              |                                   |    " + "         ");
    assertEquals(mtv.printRow(34),
        "              |                           X            " + "         ");
    assertEquals(mtv.printRow(35),
        "              |                           |            " + "         ");
    assertEquals(mtv.printRow(36),
        "              |                   X                    " + "         ");
    assertEquals(mtv.printRow(37),
        "              |                   |                    " + "         ");
    assertEquals(mtv.printRow(38),
        "              |                           X           " + "          ");
    assertEquals(mtv.printRow(39),
        "              |                           |           " + "          ");
    assertEquals(mtv.printRow(40),
        "              X                                   X   " + "          ");
    assertEquals(mtv.printRow(41),
        "              |                                   |   " + "          ");
    assertEquals(mtv.printRow(42),
        "              |                                   X    " + "         ");
    assertEquals(mtv.printRow(43),
        "              |                                   |  " + "           ");
    assertEquals(mtv.printRow(44),
        "              |                                   X  " + "           ");
    assertEquals(mtv.printRow(45),
        "              |                                   |   " + "          ");
    assertEquals(mtv.printRow(46),
        "              |                                   X  " + "           ");
    assertEquals(mtv.printRow(47),
        "              |                                   |  " + "           ");
    assertEquals(mtv.printRow(48),
        "              X                           X          " + "           ");
    assertEquals(mtv.printRow(49),
        "              |                           |          " + "           ");
    assertEquals(mtv.printRow(50),
        "              |                           X          " + "           ");
    assertEquals(mtv.printRow(51),
        "              |                           |          " + "           ");
    assertEquals(mtv.printRow(52),
        "              |                                   X  " + "           ");
    assertEquals(mtv.printRow(53),
        "              |                                   |  " + "           ");
    assertEquals(mtv.printRow(54),
        "              |                           X          " + "           ");
    assertEquals(mtv.printRow(55),
        "              |                           |          " + "           ");
    assertEquals(mtv.printRow(56),
        "  X                               X                  " + "           ");
    assertEquals(mtv.printRow(57),
        "  |                               |                  " + "           ");
    assertEquals(mtv.printRow(58),
        "  |                               |                  " + "           ");
    assertEquals(mtv.printRow(59),
        "  |                               |                  " + "           ");
    assertEquals(mtv.printRow(60),
        "  |                               |                   " + "          ");
    assertEquals(mtv.printRow(61),
        "  |                               |                  " + "           ");
    assertEquals(mtv.printRow(62),
        "  |                               |                  " + "           ");
    assertEquals(mtv.printRow(63),
        "  |                               |                  " + "           ");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMtv3() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    create(mary);
    mtv.printRow(64);
  }

  @Test public void testMtv4() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    create(mary);
    assertEquals(mtv.printPitches(),
        "      E3  F3 F#3  G3 G#3  A3 A#3  B3  C4 " + "C#4  D4 D#4  E4  F4 F#4  G4");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMtv5() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    create(mary);
    mtv.printRow(-1);
  }

  @Test public void testMtv6() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    create(smb);
    assertEquals(mtv.printRow(0),
        "  X                               X                                 "
            + "                              " +
            "X                                       X                                 ");
    assertEquals(mtv.printRow(1),
        "  |                               |                " + "                           " +
            "                    |                                       |          "
            + "                       ");
    assertEquals(mtv.printRow(2),
        "  |                                             " + "                              " +
            "                                                                       "
            + "                       ");
    assertEquals(mtv.printRow(3),
        "  |                               X      " + "                                     " +
            "                    X                                       X              "
            + "                   ");

    assertEquals(mtv.printRow(4),
        "                                  |          " + "                                   " +
            "                  |                                       |   "
            + "                              ");
    assertEquals(mtv.printRow(5),
        "                                              " + "                                " +
            "                                                      "
            + "                                        ");
    assertEquals(mtv.printRow(1771),
        "  |                                " + "                                      " +
            "                                                               "
            + "                                    ");

  }

  @Test public void testMtv7() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    create(scale);
    assertEquals(mtv.printPitches(), "      C4 C#4  D4 D#4  E4  F4 F#4  G4 G#4  A4 A#4  B4  C5");
    assertEquals(mtv.printRow(0), "  X                                                 ");
    assertEquals(mtv.printRow(1), "  |                                                 ");
    assertEquals(mtv.printRow(2), "          X                                         ");
    assertEquals(mtv.printRow(3), "          |                                         ");
    assertEquals(mtv.printRow(4), "                  X                                 ");
    assertEquals(mtv.printRow(5), "                  |                                 ");
    assertEquals(mtv.printRow(6), "                      X                             ");
    assertEquals(mtv.printRow(7), "                      |                             ");
    assertEquals(mtv.printRow(8), "                              X                     ");
    assertEquals(mtv.printRow(9), "                              |                     ");
    assertEquals(mtv.printRow(10), "                                      X             ");
    assertEquals(mtv.printRow(11), "                                      |             ");
    assertEquals(mtv.printRow(12), "                                              X     ");
    assertEquals(mtv.printRow(13), "                                              |     ");
    assertEquals(mtv.printRow(14), "                                                  X ");
    assertEquals(mtv.printRow(15), "                                                  | ");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMtv8() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    create(scale);
    mtv.printRow(16);
  }

  @Test public void testMtv9() {
    try {
      initialize();
    } catch (IOException e) {
      e.printStackTrace();
    }

    create(test1);
    assertEquals(mtv.printPitches(), "     C-1C#-1 D-1D#-1 E-1 F-1F#-1 G-1G#-1 A-1A#-1 B-1  "
        + "C0 C#0  D0 D#0  E0  F0 F#0  G0 G#0  A0 A#0  B0  C1 C#1  D1 D#1  E1  F1 F#1  G1 G#1 "
        + " A1 A#1  B1  C2 C#2  D2 D#2  E2  F2 F#2  G2 G#2  A2 A#2  B2  C3 C#3  D3 D#3  E3  F3"
        + " F#3  G3 G#3  A3 A#3  B3  C4 C#4  D4 D#4  E4  F4 F#4  G4 G#4  A4 A#4  B4  C5 C#5  D5"
        + " D#5  E5  F5 F#5  G5 G#5  A5 A#5  B5  C6 C#6  D6 D#6  E6  F6 F#6  G6 G#6  A6 A#6  B6"
        + "  C7 C#7  D7 D#7  E7  F7 F#7  G7 G#7  A7 A#7  B7  C8 C#8  D8 D#8  E8  F8 F#8  G8 G#8"
        + "  A8 A#8  B8  C9 C#9  D9 D#9  E9  F9 F#9  G9");
  }

}
