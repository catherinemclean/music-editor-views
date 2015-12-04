package cs3500.music.model;

/**
 * Created by catherinemclean1 on 12/4/15.
 */
public class Tone {
  public static String pitchNumToString(int pitch) {
    String result = "";
    int octave = (pitch / 12) - 1;
    int p = pitch % 12;
    if (octave >= 0 && octave <= 9) {
      result += " ";
    }
    switch (p) {
      case 0:
        result += " C";
        break;
      case 1:
        result += "C#";
        break;
      case 2:
        result += " D";
        break;
      case 3:
        result += "D#";
        break;
      case 4:
        result += " E";
        break;
      case 5:
        result += " F";
        break;
      case 6:
        result += "F#";
        break;
      case 7:
        result += " G";
        break;
      case 8:
        result += "G#";
        break;
      case 9:
        result += " A";
        break;
      case 10:
        result += "A#";
        break;
      case 11:
        result += " B";
        break;
      default:
        result += "";
        break;
    }
    result += octave;
    return result;
  }
}
