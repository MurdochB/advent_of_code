package challenge._2024;

import base.Solution;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D11 extends Solution {

  private final Logger log = LogManager.getLogger(D11.class);

  private static final String INPUT_FILE = "2024/inputs/11.txt";
  private static final String LORE_FILE = "2024/lore/11.txt";

  private D11(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D11 solution = new D11(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();
    Map<String, Long> stones = new HashMap<>();
    for (String s : lines.get(0).split(" ")) {
      stones.put(s, 1L);
    }
    int blinksWanted = 25;
    for (int i = 0; i < blinksWanted; i++) {
      printStoneCount(i, stones);
      stones = blink(stones);
    }
    printStoneCount(blinksWanted, stones);
  }

  public void partTwo() {
    log.info("# Part 2 #");
    Map<String, Long> stones = new HashMap<>();
    for (String s : lines.get(0).split(" ")) {
      stones.put(s, 1L);
    }
    int blinksWanted = 75;
    for (int i = 0; i < blinksWanted; i++) {
      printStoneCount(i, stones);
      stones = blink(stones);
    }
    printStoneCount(blinksWanted, stones);
  }

  private Map<String, Long> blink(Map<String, Long> stones) {
    Map<String, Long> afterBlinkStones = new HashMap<>();
    for (Entry<String, Long> blinking : stones.entrySet()) {
      String stoneType = blinking.getKey();
      if (stoneType.equals("0")) {
        afterBlinkStones.merge("1", blinking.getValue(), Long::sum);
      } else if (stoneType.length() % 2 == 0) {
        // Even length stones split into two stones
        String newStone1 = trimZeros(stoneType.substring(0, stoneType.length() / 2));
        String newStone2 = trimZeros(stoneType.substring(stoneType.length() / 2));
        afterBlinkStones.merge(newStone1, blinking.getValue(), Long::sum);
        afterBlinkStones.merge(newStone2, blinking.getValue(), Long::sum);
      } else {
        long l = Long.parseLong(stoneType);
        l *= 2024;
        afterBlinkStones.merge(Long.toString(l), blinking.getValue(), Long::sum);
      }
    }
    return afterBlinkStones;
  }

  private String trimZeros(String word) {
    long l = Long.parseLong(word);
    return Long.toString(l);
  }

  private void printStoneCount(int step, Map<String, Long> stones) {
    StringBuilder sb = new StringBuilder();
    sb.append(step).append(" : ").append(
        stones.values().stream().mapToLong(Long::longValue).sum());
    log.info(sb);
  }

  public void lore() {
    log.info(lore);
  }
}