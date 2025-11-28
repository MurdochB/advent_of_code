package challenge._2015;

import base.Solution;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D02 extends Solution {

  private final Logger log = LogManager.getLogger(D02.class);

  private static final String INPUT_FILE = "2015/inputs/02.txt";
  private static final String LORE_FILE = "2015/lore/02.txt";

  private D02(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D02 solution = new D02(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    int totalWrappingNeeded = 0;
    for (String line : lines) {
      totalWrappingNeeded += calculateWrapping(line);
    }
    log.info("Total wrapping needed: {}", totalWrappingNeeded);
  }

  public void partTwo() {
    log.info("# Part 2 #");
    int totalRibbonNeeded = 0;
    for (String line : lines) {
      totalRibbonNeeded += calculateRibbon(line);
    }
    log.info("Total ribbon needed: {}", totalRibbonNeeded);

  }

  private int calculateWrapping(String measurements) {
    String[] split = measurements.split("x");
    int l = Integer.parseInt(split[0]);
    int w = Integer.parseInt(split[1]);
    int h = Integer.parseInt(split[2]);

    int side1 = l * w;
    int side2 = w * h;
    int side3 = h * l;

    return Math.min(side1, Math.min(side2, side3)) + (2 * side1) + (2 * side2) + (2 * side3);
  }

  private int calculateRibbon(String measurements) {
    String[] split = measurements.split("x");
    int l = Integer.parseInt(split[0]);
    int w = Integer.parseInt(split[1]);
    int h = Integer.parseInt(split[2]);

    int biggestFace = Math.max(l, Math.max(w, h));
    int bow = l * w * h;
    int perimeter = (2 * l) + (2 * w) + (2 * h) - (2 * biggestFace);

    return perimeter + bow;
  }

  @Override
  public void lore() {
    log.info(lore);
  }
}