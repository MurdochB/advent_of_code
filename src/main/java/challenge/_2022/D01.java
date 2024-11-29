package challenge._2022;

import base.Solution;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D01 extends Solution {

  private final Logger log = LogManager.getLogger(D01.class);

  private static final String INPUT_FILE = "2022/inputs/01.txt";
  private static final String LORE_FILE = "2022/lore/01.txt";

  private D01(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D01 solution = new D01(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    int currentMax = 0;
    int thisElf = 0;
    for (String line : lines) {
      if (!line.isEmpty()) {
        thisElf += Integer.parseInt(line);
      } else {
        if (thisElf > currentMax) {
          currentMax = thisElf;
        }
        thisElf = 0;
      }
    }
    log.info(currentMax);
  }

  public void partTwo() {
    log.info("# Part 2 #");
    List<Integer> totalCalories = new ArrayList<>();

    int thisElf = 0;
    for (String line : lines) {
      if (!line.isEmpty()) {
        thisElf += Integer.parseInt(line);
      } else {
        totalCalories.add(thisElf);
        thisElf = 0;
      }

    }
    int top3TotalCalories = totalCalories.stream()
        .sorted(Collections.reverseOrder())
        .limit(3)
        .mapToInt(Integer::intValue)
        .sum();
    log.info(top3TotalCalories);
  }

  @Override
  public void lore() {
    log.info(lore);
  }
}