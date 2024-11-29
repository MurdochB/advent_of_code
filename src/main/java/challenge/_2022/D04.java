package challenge._2022;

import base.Solution;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D04 extends Solution {

  private final Logger log = LogManager.getLogger(D04.class);

  private static final String INPUT_FILE = "2022/inputs/04.txt";
  private static final String LORE_FILE = "2022/lore/04.txt";

  private D04(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D04 solution = new D04(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
   log.info("# Part 1 #");
    int fullyWrapped = 0;

    for (String line : lines) {
      String[] split = line.split(",");
      String range1 = split[0];
      String range2 = split[1];

      if(rangeWrapsOtherRange(range1, range2))
        fullyWrapped++;
    }
    log.info(fullyWrapped);
  }

  private boolean rangeWrapsOtherRange(String range1, String range2) {
    int range1Start = Integer.parseInt(range1.split("-")[0]);
    int range1End = Integer.parseInt(range1.split("-")[1]);
    int range2Start = Integer.parseInt(range2.split("-")[0]);
    int range2End = Integer.parseInt(range2.split("-")[1]);
    if (range1Start == range2Start)
      return true;

    if (range1Start < range2Start)
      return range1End >= range2End;
    else
      return range2End >= range1End;
  }

  public void partTwo() {
    log.info("# Part 2 #");
    int overlapAtAll = 0;

    for (String line : lines) {
      String[] split = line.split(",");
      String range1 = split[0];
      String range2 = split[1];

      if(rangeOverlapsAtAll(range1, range2))
        overlapAtAll++;
    }
    log.info(overlapAtAll);
  }

  private boolean rangeOverlapsAtAll(String range1, String range2) {
    int range1Start = Integer.parseInt(range1.split("-")[0]);
    int range1End = Integer.parseInt(range1.split("-")[1]);
    int range2Start = Integer.parseInt(range2.split("-")[0]);
    int range2End = Integer.parseInt(range2.split("-")[1]);

    if (range1Start == range2Start)
      return true;

    if (range1Start < range2Start)
      return range1End >= range2Start;
    else
      return range2End >= range1Start;
  }

  @Override
  public void lore() {
    log.info(lore);
  }
}