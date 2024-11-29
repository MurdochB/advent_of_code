package challenge._2022;

import base.Solution;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D02 extends Solution {

  private final Logger log = LogManager.getLogger(D02.class);

  private static final String INPUT_FILE = "2022/inputs/02.txt";
  private static final String LORE_FILE = "2022/lore/02.txt";

  private D02(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D02 solution = new D02(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    // - Assuming -
    // Rock:      A & X   (gain 1 for picking)
    // Paper:     B & Y   (gain 2 for picking)
    // Scissors:  C & Z   (gain 3 for picking)
    //
    // 0 for a loss
    // 3 for a draw
    // 6 for a win

    Map<String, Integer> strategyMapping = Map.of(
        "A X", 4,
        "A Y", 8,
        "A Z", 3,
        "B X", 1,
        "B Y", 5,
        "B Z", 9,
        "C X", 7,
        "C Y", 2,
        "C Z", 6);
    int strategyResult = lines.stream().mapToInt(strategyMapping::get).sum();
    log.info(strategyResult);
  }

  public void partTwo() {
    System.out.println("# Part 2 #");
    // - Assuming -
    // Rock:      A
    // Paper:     B
    // Scissors:  C
    //
    // 0 for a loss   (X)
    // 3 for a draw   (Y)
    // 6 for a win    (Z)
    // But you gain 1,2,3 for the item picked.

    Map<String, Integer> strategyMapping = Map.of(
        "A X", 3,
        "A Y", 4,
        "A Z", 8,
        "B X", 1,
        "B Y", 5,
        "B Z", 9,
        "C X", 2,
        "C Y", 6,
        "C Z", 7);
    int strategyResult = lines.stream().mapToInt(strategyMapping::get).sum();
    log.info(strategyResult);
  }

  @Override
  public void lore() {
    log.info(lore);
  }
}