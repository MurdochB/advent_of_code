package challenge._2024;

import base.Solution;
import base.utils.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D13 extends Solution {

  private final Logger log = LogManager.getLogger(D13.class);

  private static final String INPUT_FILE = "2024/inputs/13.txt";
  private static final String LORE_FILE = "2024/lore/13.txt";

  private D13(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D13 solution = new D13(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();
    long tokenCost = 0L;
    for (String machine : wholeLines.split("\n\n")) {
      tokenCost += parseMachine(machine, 0L);
    }
    log.info(tokenCost);
  }

  private long parseMachine(String machine, long offset) {
    String[] split = machine.split("\n");
    //Button A: X+94, Y+34
    //Button B: X+22, Y+67
    //Prize: X=8400, Y=5400
    // Becomes two simultaneous equations:
    // aX + bX = X
    // bY + bY = Y
    // e.g:
    // 94a + 22b=8400
    // 34a + 67b=5400

    Pair<Long> buttonA = parseButton(split[0]);
    Pair<Long> buttonB = parseButton(split[1]);
    long aX = buttonA.getLeft();
    long aY = buttonA.getRight();
    long bX = buttonB.getLeft();
    long bY = buttonB.getRight();
    String[] resSplit = split[2].substring(9).split(", Y=");
    long rX = Long.parseLong(resSplit[0]) + offset;
    long rY = Long.parseLong(resSplit[1]) + offset;

    double a = ((double) (bX * rY) - (bY * rX)) / ((bX * aY) - (bY * aX));
    double b = (rY - (aY * a)) / bY;

    if (a % 1 == 0 && b % 1 == 0) {
      return (long) (a * 3 + b);
    }
    return 0L;
  }

  private Pair<Long> parseButton(String button) {
    String[] split = button.split(": X+");
    String[] bothValues = split[1].split(", Y+");
    return new Pair<>(Long.parseLong(bothValues[0]), Long.parseLong(bothValues[1]));
  }

  public void partTwo() {
    log.info("# Part 2 #");
    long tokenCost = 0L;
    long offset = 10000000000000L;
    for (String machine : wholeLines.split("\n\n")) {
      tokenCost += parseMachine(machine, offset);
    }
    log.info(tokenCost);
  }

  public void lore() {
    log.info(lore);
  }
}