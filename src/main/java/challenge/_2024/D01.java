package challenge._2024;

import base.Solution;
import base.utils.Pair;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D01 extends Solution {

  private final Logger log = LogManager.getLogger(D01.class);

  private static final String INPUT_FILE = "2024/inputs/01.txt";
  private static final String LORE_FILE = "2024/lore/01.txt";

  private D01(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D01 solution = new D01(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    log.info(lore);
    Pair<List<Integer>> listPair = genLists(lines);
    List<Integer> list1Sorted = listPair.getLeft().stream().sorted().toList();
    List<Integer> list2Sorted = listPair.getRight().stream().sorted().toList();
    int sumDistance = 0;
    for (int i = 0; i < list1Sorted.size(); i++) {
      sumDistance += Math.abs(list1Sorted.get(i) - list2Sorted.get(i));
    }
    log.info("Total distance: {}", sumDistance);
  }

  public void partTwo() {
    log.info("# Part 2 #");
    Pair<List<Integer>> listPair = genLists(lines);
    int sumSimilarity = 0;
    for (Integer i : listPair.getLeft()) {
      sumSimilarity += calculateSimilarityScore(i, listPair.getRight());
    }
    log.info("Total similarity: {}", sumSimilarity);
  }

  public int calculateSimilarityScore(Integer value, List<Integer> input) {
    int appearances = 0;
    for (Integer i : input) {
      if (i.equals(value)) {
        appearances++;
      }
    }
    return appearances * value;
  }

  private Pair<List<Integer>> genLists(List<String> input) {
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    for (String line : input) {
      String[] split = line.split(" {3}");
      list1.add(Integer.valueOf(split[0]));
      list2.add(Integer.valueOf(split[1]));
    }
    return new Pair<>(list1, list2);
  }

  public void lore() {
    log.info(lore);
  }
}