package challenge._2025;

import base.Solution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D03 extends Solution {

  private final Logger log = LogManager.getLogger(D03.class);

  private static final String INPUT_FILE = "2025/inputs/03.txt";
  private static final String LORE_FILE = "2025/lore/03.txt";

  private D03(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D03 solution = new D03(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();

    int maxJoltage = 0;

    for (String line : lines) {
      maxJoltage += findMaxJoltage(removeNewLine(line));
    }
    log.info("Max joltage: {}", maxJoltage);
  }

  private int findMaxJoltage(String line) {
    List<Integer> batteries = new ArrayList<>(
        Arrays.stream(line.split(""))
            .map(Integer::parseInt)
            .toList());
    int biggestJolt = batteries.stream().max(Integer::compareTo).orElse(-1);

    if (batteries.lastIndexOf(biggestJolt) != batteries.size() - 1) {
      // max is not at the end
      int secondBiggest = batteries.subList(batteries.indexOf(biggestJolt) + 1, batteries.size())
          .stream()
          .max(Integer::compareTo).orElse(-1);
      log.info("1 JOLTS: {}", (biggestJolt * 10 + secondBiggest));
      return biggestJolt * 10 + secondBiggest;
    } else {
      // max is at the end
      batteries.remove(batteries.lastIndexOf(biggestJolt));
      int nextBiggest = batteries.stream().max(Integer::compareTo).orElse(-1);
      log.info("2 JOLTS: {}", (nextBiggest * 10 + biggestJolt));
      return (nextBiggest * 10 + biggestJolt);
    }
  }

  public void partTwo() {
    log.info("# Part 2 #");

    
  }

  private String removeNewLine(String line) {
    return line.split("\n")[0];
  }

  public void lore() {
    log.info(lore);
  }
}