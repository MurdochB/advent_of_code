package challenge._2024;

import base.Solution;
import base.utils.Coord;
import base.utils.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D21 extends Solution {

  private final Logger log = LogManager.getLogger(D21.class);

  private static final String INPUT_FILE = "2024/inputs/21.txt";
  private static final String LORE_FILE = "2024/lore/21.txt";

  private D21(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D21 solution = new D21(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();

    long sumComplexities = 0;
    for (String code : lines) {
      long sequenceLength = sequence(code);
      long complexity = getNumberFromCode(code) * sequenceLength;
      log.info("complexity of {} is {}", code, complexity);
      sumComplexities += complexity;
    }
    log.info("sum of complexity {}", sumComplexities);
  }

  private long sequence(String code) {
    String[] split = code.split("");
    List<Pair<String>> fromToList = new ArrayList<>();
    fromToList.add(new Pair<>("A", split[0]));
    for (int i = 0; i < split.length - 1; i++) {
      fromToList.add(new Pair<>(split[i], split[i + 1]));
    }
    for (Pair<String> p : fromToList) {
      log.info("{} -> {}", p.getLeft(), p.getRight());
    }
    log.info(fromToList.size());
    ControlPad door = new ControlPad(PadType.NUMBER);
    ControlPad arrow1 = new ControlPad(PadType.ARROW);
    ControlPad arrow2 = new ControlPad(PadType.ARROW);
    //

    return 4L;
  }
  // Keypad layout:
  //+---+---+---+ door
  //| 7 | 8 | 9 |
  //+---+---+---+
  //| 4 | 5 | 6 |
  //+---+---+---+
  //| 1 | 2 | 3 |
  //+---+---+---+
  //    | 0 | A |
  //    +---+---+
  // 023A
  // <A^A >AvA

  //    +---+---+ robot 2
  //    | ^ | A |
  //+---+---+---+
  //| < | v | > |
  //+---+---+---+

  //V<<A  >>^A
  //<A >A
  // vA ^A
  // v<A >^A

  //    +---+---+ robot 1
  //    | ^ | A |
  //+---+---+---+
  //| < | v | > |
  //+---+---+---+
  //879A: v<<A>>^A
  // ME:
  //    +---+---+
  //    | ^ | A |
  //+---+---+---+
  //| < | v | > |
  //+---+---+---+


  private int getNumberFromCode(String code) {
    return Integer.parseInt(code.split("A")[0]);
  }

  public void partTwo() {
    log.info("# Part 2 #");

  }

  enum PadType {
    NUMBER,
    ARROW
  }

  public class ControlPad {

    private PadType type;

    private Coord current;
    private Map<String, Coord> pad;

    public ControlPad(PadType type) {
      this.type = type;
      pad = new HashMap<>();
      if (type.equals(PadType.NUMBER)) {
        pad.put("7", new Coord(0, 0));
        pad.put("8", new Coord(0, 1));
        pad.put("9", new Coord(0, 2));
        pad.put("4", new Coord(1, 0));
        pad.put("5", new Coord(1, 1));
        pad.put("6", new Coord(1, 2));
        pad.put("0", new Coord(2, 1));
        pad.put("A", new Coord(2, 2));
      } else if (type.equals(PadType.ARROW)) {
        pad.put("^", new Coord(0, 1));
        pad.put("A", new Coord(0, 2));
        pad.put("<", new Coord(1, 0));
        pad.put("v", new Coord(1, 1));
        pad.put(">", new Coord(1, 2));
      }
      current = pad.get("A");
    }
  }

  public void lore() {
    log.info(lore);
  }
}