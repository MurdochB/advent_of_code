package challenge._2024;

import base.Solution;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
    Map<String, Set<String>> orderRules = getOrderRulesMap(lines);
    List<String> updates = getUpdates(lines);

    int countMiddleValues = 0;
    for (String update : updates) {
      if (isValidUpdate(update, orderRules)) {
        countMiddleValues += getMiddleValue(update);
      }
    }
    log.info(countMiddleValues);
  }

  public void partTwo() {
    log.info("# Part 2 #");
    Map<String, Set<String>> orderRules = getOrderRulesMap(lines);
    List<String> updates = getUpdates(lines);

    int countMiddleValues = 0;
    for (String update : updates) {
      if (!isValidUpdate(update, orderRules)) {
        countMiddleValues += getMiddleValue(fixInvalidUpdate(update, orderRules));
      }
    }
    log.info(countMiddleValues);
  }

  private String fixInvalidUpdate(String update, Map<String, Set<String>> rules) {
    List<String> curOrder = new LinkedList<>();
    for (String s : update.split(",")) {
      Set<String> mustBeBefore = rules.get(s);

      int earliestIndex = 999;
      if (mustBeBefore != null) {
        for (String must : mustBeBefore) {
          if (curOrder.contains(must)) {
            earliestIndex = Math.min(earliestIndex, curOrder.indexOf(must));
          }
        }
      }
      if (earliestIndex == 999) {
        curOrder.add(s);
      } else {
        curOrder.add(earliestIndex, s);
      }
    }
    return String.join(",", curOrder);
  }

  private Map<String, Set<String>> getOrderRulesMap(List<String> in) {
    Map<String, Set<String>> rules = new HashMap<>();
    List<String> order = getOrderRulesList(in);
    for (String o : order) {
      String[] split = o.split("\\|");
      if (rules.containsKey(split[0])) {
        rules.get(split[0]).add(split[1]);
      } else {
        Set<String> newSet = new HashSet<>();
        newSet.add(split[1]);
        rules.put(split[0], newSet);
      }
    }
    return rules;
  }

  private List<String> getOrderRulesList(List<String> in) {
    List<String> orderRules = new ArrayList<>();
    for (String s : in) {
      if (s.isEmpty()) {
        return orderRules;
      }
      orderRules.add(s);
    }
    return orderRules;
  }

  private List<String> getUpdates(List<String> in) {
    List<String> updates = new ArrayList<>();
    boolean readFromNow = false;
    for (String s : in) {
      if (s.isEmpty()) {
        readFromNow = true;
        continue;
      }
      if (readFromNow) {
        updates.add(s);
      }
    }
    return updates;
  }

  private int getMiddleValue(String update) {
    String[] split = update.split(",");
    return Integer.parseInt(split[(split.length - 1) / 2]);
  }

  private boolean isValidUpdate(String update, Map<String, Set<String>> rules) {
    StringBuilder inCurrentUpdate = new StringBuilder();
    for (String s : update.split(",")) {
      Set<String> mustBeBefore = rules.get(s);
      if (mustBeBefore != null) {
        for (String must : mustBeBefore) {
          if (inCurrentUpdate.toString().contains(must)) {
            return false;
          }
        }
      }
      inCurrentUpdate.append(s).append(",");
    }
    return true;
  }

  public void lore() {
    log.info(lore);
  }
}