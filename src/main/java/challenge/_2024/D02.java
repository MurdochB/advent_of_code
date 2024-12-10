package challenge._2024;

import base.Solution;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D02 extends Solution {

  private final Logger log = LogManager.getLogger(D02.class);

  private static final String INPUT_FILE = "2024/inputs/02.txt";
  private static final String LORE_FILE = "2024/lore/02.txt";

  private D02(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D02 solution = new D02(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    log.info(lore);
    int safeReports = 0;
    for (String line : lines) {
      if (reportIsSafe(line)) {
        safeReports++;
      }
    }
    log.info("Safe reports: {}", safeReports);
  }

  public void partTwo() {
    log.info("# Part 2 #");
    int safeReports = 0;
    for (String line : lines) {
      List<String> potentialReports = createPotentialReports(line);
      boolean safeReportFound = false;
      for (String potentialReport : potentialReports) {
        if (reportIsSafe(potentialReport)) {
          safeReportFound = true;
        }
      }
      if (safeReportFound) {
        safeReports++;
      }
    }
    log.info("Safe reports: {}", safeReports);
  }

  private List<String> createPotentialReports(String line) {
    // Creates a list of each report that could exist - with one of the levels missing.
    List<String> potentialReports = new ArrayList<>();
    potentialReports.add(line);
    String[] split = line.split(" ");
    for (int i = 0; i < split.length; i++) {
      StringBuilder newPotential = new StringBuilder();
      for (int n = 0; n < split.length; n++) {
        if (n == i) {
          continue;
        }
        newPotential.append(split[n]).append(" ");
      }
      potentialReports.add(newPotential.toString().trim());
    }
    return potentialReports;
  }

  private boolean reportIsSafe(String line) {
    String[] levels = line.split(" ");

    boolean shouldIncrease = Integer.parseInt(levels[0]) < Integer.parseInt(levels[1]);
    for (int i = 1; i < levels.length; i++) {
      int prev = Integer.parseInt(levels[i - 1]);
      int curr = Integer.parseInt(levels[i]);
      if (prev == curr) {
        return false;
      }
      if ((shouldIncrease && prev > curr) || (!shouldIncrease && prev < curr)) {
        return false;
      }
      if (Math.abs(prev - curr) > 3) {
        return false;
      }
    }
    return true;
  }

  public void lore() {
    log.info(lore);
  }
}