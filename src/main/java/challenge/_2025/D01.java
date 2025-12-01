package challenge._2025;

import base.Solution;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D01 extends Solution {

  private final Logger log = LogManager.getLogger(D01.class);

  private static final String INPUT_FILE = "2025/inputs/01.txt";
  private static final String LORE_FILE = "2025/lore/01.txt";

  private D01(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D01 solution = new D01(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();
    int dial = 50;
    int zeroCount = 0;

    for (String line : lines) {
      dial = processLine(dial, line);
      log.info("DIAL: {}", dial);
      if (dial == 0){
        zeroCount++;
      }
    }
    log.info("Zero count: {}", zeroCount);
  }

  private int processLine(int currentDial, String line) {
    String operation = line.substring(0, 1);
    int val = Integer.parseInt(line.substring(1));
    val = val % 100;

    if (operation.equals("R")){
      currentDial += val;
    } else {
      currentDial -= val;
    }
    if (currentDial < 0){
      currentDial = 100 + currentDial;
    }
    return currentDial % 100;
  }

  public void partTwo() {
    log.info("# Part 2 #");

  }

  public void lore() {
    log.info(lore);
  }
}