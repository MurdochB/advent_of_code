package challenge._2024;

import base.Solution;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
    long sum = lines.stream().mapToLong(this::processLine).sum();
    log.info(sum);
  }

  private long processLine(String s) {
    String[] split = s.split(": ");
    long target = Long.parseLong(split[0]);
    List<Integer> operands = Arrays.stream(split[1].split(" ")).map(Integer::parseInt).toList();

    return target;
  }

  public void partTwo() {
    log.info("# Part 2 #");
  }

  public void lore() {
    log.info(lore);
  }
}