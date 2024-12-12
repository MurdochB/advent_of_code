package challenge._2024;

import base.Solution;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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
    List<String> stones = new ArrayList<>();
    Collections.addAll(stones, lines.get(0).split(" "));
    int blinksWanted = 25;

    for (int i = 0; i < blinksWanted; i++) {
      printStones(i, stones);
      stones = step(stones);
    }
    printStones(blinksWanted, stones);
  }

  private List<String> step(List<String> stones) {
    List<String> newStones = new ArrayList<>();
    for (String s : stones) {
      if (s.equals("0")) {
        newStones.add("1");
      } else if (s.length() % 2 == 0) {
        newStones.add(trimZeros(s.substring(0, s.length() / 2)));
        newStones.add(trimZeros(s.substring(s.length() / 2)));
      } else {
        long l = Long.parseLong(s);
        l *= 2024;
        newStones.add(Long.toString(l));
      }
    }

    return newStones;
  }

  public void partTwo() {
    log.info("# Part 2 #");

  }

  private String trimZeros(String word) {
    long l = Long.parseLong(word);
    return Long.toString(l);
  }

  private void printStones(int step, List<String> stones){
    StringBuilder sb = new StringBuilder();
    sb.append(step).append(" : ");
    sb.append(stones.size());
    log.info(sb);
  }

  public void lore() {
    log.info(lore);
  }
}