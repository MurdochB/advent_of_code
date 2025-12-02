package challenge._2025;

import base.Solution;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D02 extends Solution {

  private final Logger log = LogManager.getLogger(D02.class);

  private static final String INPUT_FILE = "2025/inputs/02.txt";
  private static final String LORE_FILE = "2025/lore/02.txt";

  private D02(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D02 solution = new D02(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();
    List<String> ranges = getRanges(wholeLines);
    long sumInvalidIds = 0L;

    for (String range : ranges) {
      String[] split = range.split("-");
      split[1] = split[1].split("\n")[0];
      sumInvalidIds += countInvalidIds(split[0], split[1]);
    }
    log.info(sumInvalidIds);
  }

  private long countInvalidIds(String start, String end) {
    long total = 0L;
    while (!start.equals(end)) {
      if (!isValidId(start)) {
        total += Long.parseLong(start);
      }
      start = String.valueOf(Long.parseLong(start) + 1);
    }
    return total;
  }

  public void partTwo() {
    log.info("# Part 2 #");
    List<String> ranges = getRanges(wholeLines);
    long sumInvalidIds = 0L;

    for (String range : ranges) {
      String[] split = range.split("-");
      split[1] = split[1].split("\n")[0];
      sumInvalidIds += countInvalidIdsPt2(split[0], split[1]);
    }
    log.info(sumInvalidIds);
  }

  private long countInvalidIdsPt2(String start, String end) {
    long total = 0L;
    while (!start.equals(end)) {
      if (!isValidIdPt2(start)) {
        total += Long.parseLong(start);
      }
      start = String.valueOf(Long.parseLong(start) + 1);
    }
    return total;
  }

  private List<String> getRanges(String input) {
    return Arrays.stream(input.split(",")).toList();
  }

  private boolean isValidId(String input) {
    if (input.length() % 2 != 0) {
      return true;
    }
    String firstHalf = input.substring(0, input.length() / 2);
    String secondHalf = input.substring(input.length() / 2);
    return !firstHalf.equals(secondHalf);
  }

  private boolean isValidIdPt2(String input) {
    for (int i = 0; i < input.length() / 2; i++) {
      String substring = input.substring(0, i + 1);
      StringBuilder sb = new StringBuilder();
      sb.append(substring);
      while (sb.length() < input.length()) {
        sb.append(substring);
      }
      if (sb.toString().equals(input)) {
        return false;
      }
    }
    return true;
  }

  public void lore() {
    log.info(lore);
  }
}