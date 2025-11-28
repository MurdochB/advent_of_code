package challenge._2025;

import base.Solution;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D00 extends Solution {

  private final Logger log = LogManager.getLogger(D00.class);

  private static final String INPUT_FILE = "2025/inputs/00.txt";
  private static final String LORE_FILE = "2025/lore/00.txt";

  private D00(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D00 solution = new D00(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();

    // ...
  }

  public void partTwo() {
    log.info("# Part 2 #");

    // ...
  }

  public void lore() {
    log.info(lore);
  }
}