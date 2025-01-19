package challenge._2024;

import base.Solution;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
      secret = nextSecret(secret);
    }
    return secret;
  }

  public void partTwo() {
    log.info("# Part 2 #");

    List<List<Long>> changes = lines.stream().map(this::getChangeList).toList();

    log.info("changes!");
  }

  private List<Long> getChangeList(String startingSecret){
    List<Long> priceList = getPriceList(startingSecret);
    List<Long> changes = new ArrayList<>();
    long start = priceList.get(0);
    for (int i = 1; i < priceList.size(); i++) {
      changes.add(priceList.get(i) - start);
      start = priceList.get(i);
    }
    return changes;
  }

  private List<Long> getPriceList(String startingSecret){
    long secret = Long.parseLong(startingSecret);
    List<Long> prices = new ArrayList<>();
    prices.add(secret % 10);

    for (int i = 0; i < 2000; i++) {
      secret = nextSecret(secret);
      prices.add(secret % 10);
    }
    return prices;
  }

  private long nextSecret(Long secret) {
    long tmp = secret * 64;
    secret = prune(mix(secret, tmp));
    tmp = secret / 32;
    secret = prune(mix(secret, tmp));
    tmp = secret * 2048;
    secret = prune(mix(secret, tmp));
    return secret;
  }

  private long mix(long secret, long given) {
    return secret ^ given;
  }

  private long prune(long secret) {
    return secret % 16777216;
  }

  public void lore() {
    log.info(lore);
  }
}