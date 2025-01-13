package challenge._2024;

import base.Solution;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D22 extends Solution {

  private final Logger log = LogManager.getLogger(D22.class);

  private static final String INPUT_FILE = "2024/inputs/22.txt";
  private static final String LORE_FILE = "2024/lore/22.txt";

  private D22(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D22 solution = new D22(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();

    long answer = lines.stream().mapToLong(this::solve).sum();
    log.info("answer: {}", answer);

  }

  private long solve(String s) {
    long secret = Long.parseLong(s);
    for (int i = 0; i < 2000; i++) {
      long tmp = secret * 64;
      secret = prune(mix(secret, tmp));
      tmp = secret / 32;
      secret = prune(mix(secret, tmp));
      tmp = secret * 2048;
      secret = prune(mix(secret, tmp));
    }
    return secret;
  }

  private long mix(long secret, long given) {
    return secret ^ given;
  }

  private long prune(long secret) {
    return secret % 16777216;
  }

  public void partTwo() {
    log.info("# Part 2 #");

  }

  public void lore() {
    log.info(lore);
  }
}