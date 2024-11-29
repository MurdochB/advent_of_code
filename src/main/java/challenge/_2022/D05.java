package challenge._2022;

import base.Solution;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D05 extends Solution {

  private final Logger log = LogManager.getLogger(D05.class);

  private static final String INPUT_FILE = "2022/inputs/05.txt";
  private static final String LORE_FILE = "2022/lore/05.txt";

  private D05(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D05 solution = new D05(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    Map<Integer, List<String>> stacks = buildStackMap();
    List<String> operations = buildOperationsList();

    for (String op : operations) {
      String[] split = op.substring(5).split(" from ");
      String[] splitTwo = split[1].split(" to ");

      int countToMove = Integer.parseInt(split[0]);
      int from = Integer.parseInt(splitTwo[0]);
      int to = Integer.parseInt(splitTwo[1]);

      for (int i = 0; i < countToMove; i++) {
        String lastItem = removeLastItem(stacks, from);
        addItemToList(stacks, to, lastItem);
      }
    }

    printResult(stacks);
  }

  public void partTwo() {
    log.info("# Part 2 #");
    Map<Integer, List<String>> stacks = buildStackMap();
    List<String> operations = buildOperationsList();

    int opCount = 0;
    for (String op : operations) {
      opCount++;
      String[] split = op.substring(5).split(" from ");
      String[] splitTwo = split[1].split(" to ");

      int countToMove = Integer.parseInt(split[0]);
      int from = Integer.parseInt(splitTwo[0]);
      int to = Integer.parseInt(splitTwo[1]);

      List<String> itemsToMove = removeItemsInOrder(stacks, from, countToMove);
      addItemsToList(stacks, to, itemsToMove);
    }

    printResult(stacks);
  }


  private Map<Integer, List<String>> buildStackMap() {
    Map<Integer, List<String>> stacks = new HashMap<>();
    List<String> stackInput = new ArrayList<>();

    // empty stacks
    for (int i = 1; i <= 9; i++) {
      List<String> newList = new ArrayList<>();
      stacks.put(i, newList);
    }

    // get stack input
    for (String line : lines) {
      if (line.startsWith(" 1")) {
        break;
      }
      stackInput.add(line);
    }
    Collections.reverse(stackInput);

    // add stacks to lists
    for (String line : stackInput) {
      for (int i = 1; i <= 9; i++) {
        int mappedIndex = 4 * i - 3;
        if (line.length() >= mappedIndex) {
          String crate = line.substring(mappedIndex, mappedIndex + 1);
          if (!crate.isBlank()) {
            stacks.get(i).add(crate);
          }
        }
      }
    }
    return stacks;
  }

  private List<String> buildOperationsList() {
    List<String> operations = new ArrayList<>();
    boolean start = false;
    for (String line : lines) {
      if (start) {
        operations.add(line);
      }
      if (line.isEmpty()) {
        start = true;
      }
    }
    return operations;
  }

  private String removeLastItem(Map<Integer, List<String>> stacks, int fromIndex) {
    List<String> strings = stacks.get(fromIndex);
    String s = strings.get(strings.size() - 1);
    strings.remove(strings.size() - 1);
    return s;
  }

  private void addItemToList(Map<Integer, List<String>> stacks, int toIndex, String item) {
    List<String> strings = stacks.get(toIndex);
    strings.add(item);
  }

  private List<String> removeItemsInOrder(Map<Integer, List<String>> stacks, int fromIndex,
      int count) {
    List<String> strings = stacks.get(fromIndex);
    List<String> removed = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      removed.add(strings.get(strings.size() - 1));
      strings.remove(strings.size() - 1);
    }

    return removed;
  }

  private void addItemsToList(Map<Integer, List<String>> stacks, int toIndex, List<String> items) {
    List<String> strings = stacks.get(toIndex);
    Collections.reverse(items);
    strings.addAll(items);
  }

  private void printResult(Map<Integer, List<String>> stacks) {
    StringBuilder sb = new StringBuilder();
    for (Entry<Integer, List<String>> i : stacks.entrySet()) {
      List<String> strings = i.getValue();
      if (!strings.isEmpty()) {
        sb.append(strings.get(strings.size() - 1));
      }
    }
    log.info(sb);
  }

  @Override
  public void lore() {
    log.info(lore);
  }
}