package challenge._2024;

import base.Solution;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D05 extends Solution {

  private final Logger log = LogManager.getLogger(D05.class);

  private static final String INPUT_FILE = "2024/inputs/05.txt";
  private static final String LORE_FILE = "2024/lore/05.txt";

  private D05(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D05 solution = new D05(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    List<String> orderRules = new ArrayList<>();
    List<String> updates = new ArrayList<>();
    boolean firstMode = true;
    for (String line : lines) {
      if (line.isEmpty()) {
        firstMode = false;
        continue;
      }
      if (firstMode) {
        orderRules.add(line);
      } else {
        updates.add(line);
      }
    }
    int countMiddleValues = 0;
    for (String update : updates) {
      if (checkUpdate(update, orderRules)) {
        countMiddleValues += getMiddleValue(update);
      }
    }
    log.info(countMiddleValues);
  }

  private int getMiddleValue(String update) {
    String[] split = update.split(",");
    return Integer.parseInt(split[(split.length - 1) / 2]);
  }

  private boolean checkUpdate(String update, List<String> ordering) {
    Map<String, Set<String>> orders = new HashMap<>();
    for (String o : ordering) {
      String[] split = o.split("\\|");
      if (orders.containsKey(split[0])){
        orders.get(split[0]).add(split[1]);
      } else {
        Set<String> newSet = new HashSet<>();
        newSet.add(split[1]);
        orders.put(split[0], newSet);
      }
    }

    StringBuilder inCurrentUpdate = new StringBuilder();
    for (String s : update.split(",")) {
      Set<String> mustBeBefore = orders.get(s);
      for (String must : mustBeBefore) {
        if (inCurrentUpdate.toString().contains(must)){
          return false;
        }
      }
      inCurrentUpdate.append(s).append(",");
    }

    return true;
  }

  public void partTwo() {
    log.info("# Part 2 #");
  }

  public void lore() {
    log.info(lore);
  }
}