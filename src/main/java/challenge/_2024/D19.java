package challenge._2024;

import base.Solution;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D19 extends Solution {

  private final Logger log = LogManager.getLogger(D19.class);

  private static final String INPUT_FILE = "2024/inputs/19.txt";
  private static final String LORE_FILE = "2024/lore/19.txt";

  private D19(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D19 solution = new D19(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();

    List<String> towels = getTowels();
    List<String> designs = getDesigns();
    Map<String, Long> designCache = new HashMap<>();

    int count = 0;
    for (String design : designs) {
      if (canMake(design, towels, designCache) > 0) {
        count++;
      }
    }
    log.info(count);
  }

  private long canMake(String toMake, List<String> towels, Map<String, Long> cache) {
    if (cache.containsKey(toMake)) {
      return cache.get(toMake);
    }
    if (toMake.isEmpty()) {
      return 1;
    }
    long found = 0;
    for (String towel : towels) {
      if (toMake.startsWith(towel)) {
        found += canMake(toMake.substring(towel.length()), towels, cache);
      }
    }
    cache.put(toMake, found);
    return found;
  }

  public void partTwo() {
    log.info("# Part 2 #");

    List<String> towels = getTowels();
    List<String> designs = getDesigns();
    Map<String, Long> designCache = new HashMap<>();

    long total = 0;
    for (String design : designs) {
      total += canMake(design, towels, designCache);
    }
    log.info(total);
  }

  private List<String> getTowels() {
    String[] split = lines.get(0).split(", ");
    return new ArrayList<>(List.of(split));
  }

  private List<String> getDesigns() {
    List<String> designs = new ArrayList<>();
    for (int i = 2; i < lines.size(); i++) {
      designs.add(lines.get(i));
    }
    return designs;
  }

  public void lore() {
    log.info(lore);
  }
}