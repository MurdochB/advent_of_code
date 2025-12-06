package challenge._2025;

import base.Solution;
import base.utils.SplitPair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D06 extends Solution {

  private final Logger log = LogManager.getLogger(D06.class);

  private static final String INPUT_FILE = "2025/inputs/06.txt";
  private static final String LORE_FILE = "2025/lore/06.txt";

  private D06(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D06 solution = new D06(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();
    SplitPair<Map<Integer, List<Integer>>, List<String>> in = parseInput(lines);
    long grandTotal = 0L;

    Set<Entry<Integer, List<Integer>>> nums = in.getLeft().entrySet();
    List<String> ops = in.getRight();
    for (Entry<Integer, List<Integer>> num : nums) {
      long total = 0L;
      String op = ops.get(num.getKey());
      for (Integer i : num.getValue()) {
        if (op.equals("+")) {
          total += i;
        } else {
          if (total != 0) {
            total *= i;
          } else {
            total = i;
          }
        }
      }
      grandTotal += total;
    }
    log.info("Grand total: {}", grandTotal);
  }

  public void partTwo() {
    log.info("# Part 2 #");

  }

  private SplitPair<Map<Integer, List<Integer>>, List<String>> parseInput(List<String> lines) {
    Map<Integer, List<Integer>> numbers = new HashMap<>();
    List<String> operands = new ArrayList<>();

    for (String line : lines) {
      line = line.strip();
      if (line.contains("*") || line.contains("+")) {
        operands = Arrays.stream(line.split("\\s+")).toList();
      } else {
        String[] split = line.split("\\s+");
        for (int i = 0; i < split.length; i++) {
          if (numbers.containsKey(i)) {
            numbers.get(i).add(Integer.parseInt(split[i]));
          } else {
            List<Integer> newList = new ArrayList<>();
            newList.add(Integer.parseInt(split[i]));
            numbers.put(i, newList);
          }
        }
      }
    }
    return new SplitPair<>(numbers, operands);

  }

  public void lore() {
    log.info(lore);
  }
}