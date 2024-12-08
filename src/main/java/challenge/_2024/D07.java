package challenge._2024;

import base.Solution;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D07 extends Solution {

  private final Logger log = LogManager.getLogger(D07.class);

  private static final String INPUT_FILE = "2024/inputs/07.txt";
  private static final String LORE_FILE = "2024/lore/07.txt";

  private D07(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D07 solution = new D07(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    long sum = lines.stream().mapToLong(this::processLinePartOne).sum();
    log.info(sum);
  }

  private long processLinePartOne(String s) {
    String[] split = s.split(": ");
    long target = Long.parseLong(split[0]);
    List<Integer> operands = Arrays.stream(split[1].split(" ")).map(Integer::parseInt).toList();

    int n = operands.size();
    int totalCombinations = (int) Math.pow(2, n - 1);
    for (int i = 0; i < totalCombinations; i++) {
      long number = operands.get(0);
      int temp = i;
      for (int j = 0; j < n - 1; j++) {
        int opIndex = temp % 2;
        temp /= 2;
        switch (opIndex) {
          case 0:
            number *= operands.get(j + 1);
            break;
          case 1:
            number += operands.get(j + 1);
            break;
          default:
        }
      }
      if (number == target) {
        return target;
      }
    }
    return 0L;
  }

  public void partTwo() {
    log.info("# Part 2 #");
    long sum = lines.stream().mapToLong(this::processLinePartTwo).sum();
    log.info(sum);
  }

  private long processLinePartTwo(String s) {
    String[] split = s.split(": ");
    long target = Long.parseLong(split[0]);
    List<Integer> operands = Arrays.stream(split[1].split(" ")).map(Integer::parseInt).toList();

    int n = operands.size();
    int totalCombinations = (int) Math.pow(3, n - 1);
    for (int i = 0; i < totalCombinations; i++) {
      long number = operands.get(0);
      int temp = i;
      for (int j = 0; j < n - 1; j++) {
        int opIndex = temp % 3;
        temp /= 3;
        switch (opIndex) {
          case 0:
            number *= operands.get(j + 1);
            break;
          case 1:
            number += operands.get(j + 1);
            break;
          case 2:
            number = Long.parseLong(number + "" + operands.get(j + 1));
            break;
          default:
        }
      }
      if (number == target) {
        return target;
      }
    }
    return 0L;
  }

  public void lore() {
    log.info(lore);
  }
}