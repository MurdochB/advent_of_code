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

  // part 1: 198336 too high
  // part 1: 188384 correct
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

    List<String> doorSteps = new ArrayList<>();
    for (Pair<String> p : fromToList) {
      String steps = doorStep(p.getLeft(), p.getRight());
      log.info("{} -> {} | {}", p.getLeft(), p.getRight(), steps);
      doorSteps.add(steps);
    }

    // the buttons on the arrow key pad that need to be pressed to get the desired door code:
    StringBuilder sb = new StringBuilder();
    for (String doorStep : doorSteps) {
      sb.append(doorStep);
    }
    log.info(sb);
    //    +---+---+ robot 2
    //    | ^ | A |
    //+---+---+---+
    //| < | v | > |
    //+---+---+---+
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
    // 029A
    // <A^A >^^A vvvA
    // v<<A

    log.info("DOORCODE: " + sb);
    String arrows1 = arrowTheArrows(sb.toString());
    log.info("Arrows 1: " + arrows1);
    String arrows2 = arrowTheArrows(arrows1);
    log.info("Arrows 2: " + arrows2);
    return arrows2.length();
  }

  private String arrowTheArrows(String codeToArrow) {
    StringBuilder sb = new StringBuilder();
    List<Pair<String>> ftL = new ArrayList<>();
    String[] split = codeToArrow.split("");
    ftL.add(new Pair<>("A", split[0]));
    for (int i = 0; i < split.length - 1; i++) {
      ftL.add(new Pair<>(split[i], split[i + 1]));
    }
    for (Pair<String> p : ftL) {
      String ar = arrowStep(p.getLeft(), p.getRight());
      sb.append(ar);
    }
    return sb.toString();
  }

  //    +---+---+ robot 2
  //    | ^ | A |
  //+---+---+---+
  //| < | v | > |
  //+---+---+---+
  //+---+---+---+ door
  //| 7 | 8 | 9 |
  //+---+---+---+
  //| 4 | 5 | 6 |
  //+---+---+---+
  //| 1 | 2 | 3 |
  //+---+---+---+
  //    | 0 | A |
  //    +---+---+
  private String doorStep(String from, String to) {
    //expensive <, v, ^, > cheapest
    //
    // they do:                   <A>A v<<AA>^AA>AvAA^A <vAAA>^A
    // which translates to:       ^A<<^^A >>AvvvA
    // I have:                    ^A^^<<A>>AvvvA

    // theres:                    ^A<<^^A >>AvvvA
    //                            379A




    // all starts I need:
    // they do:                   <v<A>>^AvA^A<vA<AA>>^AAvA<^A >AAvA^A<vA>^AA<A>A<v<A>A>^A AAvA <^A>A
    // which translates to:       <A>A v<<AA>^AA> AvAA^A<vAAA>^A
    // mine is:                   <A>A <AAv<AA>>^ AvAA^Av<AAA>^A
    //actual 379A: <v<A >>^AvA^A <vA <AA>>^AAvA<^A>AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A
    //mine   379A: v<<A >>^AvA^A v<<A>>^AAv<A<A>>^AAvAA^<A>Av<A>^AA<A>Av<A<A>>^AAAvA^<A>A
    if (from.equals("A")) {
      if (to.equals("0")) {
        return "<A";
      }
      if (to.equals("1")) {
        return "^<<A";
      }
      if (to.equals("3")) {
        return "^A";
      }
      if (to.equals("4")) {
        return "^^<<A";
      }
      if (to.equals("5")) {
        return "<^^A";
      }
      if (to.equals("6")) {
        return "^^A";
      }
      if (to.equals("8")) {
        return "<^^^A";
      }
      if (to.equals("9")) {
        return "^^^A";
      }
    }
    //    029A
    if (from.equals("0") && to.equals("2")) {
      return "^A";
    }
    if (from.equals("2") && to.equals("9")) {
      return ">^^A";
    }
    if (from.equals("9") && to.equals("A")) {
      return "vvvA";
    }
    //879A
    if (from.equals("8") && to.equals("7")) {
      return "<A";
    }
    if (from.equals("7") && to.equals("9")) {
      return ">>A";
    }
    //508A
    if (from.equals("5") && to.equals("0")) {
      return "vvA";
    }
    if (from.equals("0") && to.equals("8")) {
      return "^^^A";
    }
    if (from.equals("8") && to.equals("A")) {
      return "vvv>A";
    }
    //463A
    if (from.equals("4") && to.equals("6")) {
      return ">>A";
    }
    if (from.equals("6") && to.equals("3")) {
      return "vA";
    }
    if (from.equals("3") && to.equals("A")) {
      return "vA";
    }
    //593A
    if (from.equals("5") && to.equals("9")) {
      return "^>A";
    }
    if (from.equals("9") && to.equals("3")) {
      return "vvA";
    }
    //189A
    if (from.equals("1") && to.equals("8")) {
      return "^^>A";
    }
    if (from.equals("8") && to.equals("9")) {
      return ">A";
    }


    if (from.equals("9") && to.equals("8")) {
      return "<A";
    }
    if (from.equals("8") && to.equals("0")) {
      return "vvvA";
    }
    if (from.equals("0") && to.equals("A")) {
      return ">A";
    }
    if (from.equals("1") && to.equals("7")) {
      return "^^A";
    }
    if (from.equals("4") && to.equals("5")) {
      return ">A";
    }
    if (from.equals("5") && to.equals("6")) {
      return ">A";
    }
    if (from.equals("6") && to.equals("A")) {
      return "vvA";
    }
    if (from.equals("3") && to.equals("7")) {
      return "<<^^A";
    }

    return "X";
  }

  private String arrowStep(String from, String to) {
    //expensive <, v, ^, > cheapest
    if (from.equals("A")) {
      return switch (to) {
        case "^" -> "<A";
        case ">" -> "vA";
        case "v" -> "v<A";
        case "<" -> "v<<A";
        case "A" -> "A";
        default -> null;
      };
    }
    if (from.equals("^")) {
      return switch (to) {
        case "A" -> ">A";
        case ">" -> ">vA";
        case "v" -> "vA";
        case "<" -> "v<A";
        case "^" -> "A";
        default -> null;
      };
    }
    if (from.equals(">")) {
      return switch (to) {
        case "A" -> "^A";
        case "^" -> "^<A";
        case "v" -> "<A";
        case "<" -> "<<A";
        case ">" -> "A";
        default -> null;
      };
    }
    if (from.equals("v")) {
      return switch (to) {
        case "A" -> ">^A";
        case "^" -> "^A";
        case ">" -> ">A";
        case "<" -> "<A";
        case "v" -> "A";
        default -> null;
      };
    }
    if (from.equals("<")) {
      return switch (to) {
        case "A" -> ">>^A";
        case "^" -> ">^A";
        case ">" -> ">>A";
        case "v" -> ">A";
        case "<" -> "A";
        default -> null;
      };
    }
    return "X";
  }
  //    +---+---+ robot 2
  //    | ^ | A |
  //+---+---+---+
  //| < | v | > |
  //+---+---+---+
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
  // 029A
  // <A^A >^^A vvvA

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
//029A