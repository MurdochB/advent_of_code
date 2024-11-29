package challenge._2022;

import base.Solution;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D06 extends Solution {

  private final Logger log = LogManager.getLogger(D06.class);

  private static final String INPUT_FILE = "2022/inputs/06.txt";
  private static final String LORE_FILE = "2022/lore/06.txt";

  private D06(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D06 solution = new D06(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");

    int signalSize = 4;
    log.info(getFirstTimeSignal(signalSize));
  }

  public void partTwo() {
    log.info("# Part 2 #");

    int signalSize = 14;
    log.info(getFirstTimeSignal(signalSize));
  }

  private int getFirstTimeSignal(int signalSize) {
    for (String line : lines) {
      for (int i = 0; i < line.length() - signalSize - 1; i++) {
        String checkMsg = line.substring(i, i + signalSize);
        if (uniqCharsInString(checkMsg)) {
          return i + signalSize;
        }
      }
    }
    return -1;
  }

  private boolean uniqCharsInString(String string) {
    int length = string.length();
    String[] split = string.split("");
    Set<String> set = new HashSet<>(List.of(split));
    return length == set.size();
  }

  @Override
  public void lore() {
    log.info(lore);
  }
}