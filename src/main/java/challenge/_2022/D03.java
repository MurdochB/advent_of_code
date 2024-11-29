package challenge._2022;

import base.Solution;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D03 extends Solution {

  private final Logger log = LogManager.getLogger(D03.class);

  private static final String INPUT_FILE = "2022/inputs/03.txt";
  private static final String LORE_FILE = "2022/lore/03.txt";

  private D03(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D03 solution = new D03(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    List<String> repeatedItems = new ArrayList<>();

    for (String line : lines) {
      int compartmentSize = line.length() / 2;
      String compartment1 = line.substring(0, compartmentSize);
      String compartment2 = line.substring(compartmentSize);
      String[] compartment1Array = compartment1.split("");
      String[] compartment2Array = compartment2.split("");
      Set<String> compartment1Set = new HashSet<>(List.of(compartment1Array));
      Set<String> compartment2Set = new HashSet<>(List.of(compartment2Array));

      compartment1Set.retainAll(compartment2Set);
      String item = compartment1Set.stream().findFirst().get();
      repeatedItems.add(item);
    }

    int sum = repeatedItems.stream().mapToInt(this::convertItemIntoPriority).sum();
    log.info(sum);
  }

  public void partTwo() {
    log.info("# Part 2 #");

    List<List<Set<String>>> groups = new ArrayList<>();
    int groupSize = 3;
    int curGroup = 0;
    List<Set<String>> thisGroup = new ArrayList<>();
    for (String line : lines) {
      Set<String> thisBag = new HashSet<>(List.of(line.split("")));
      thisGroup.add(thisBag);
      curGroup++;
      if (curGroup >= groupSize) {
        curGroup = 0;
        groups.add(thisGroup);
        thisGroup = new ArrayList<>();
      }
    }

    int result = 0;

    for (List<Set<String>> group : groups) {
      Set<String> first = group.get(0);
      for (Set<String> set : group) {
        first.retainAll(set);
      }
      String badge = first.stream().findFirst().get();
      result += convertItemIntoPriority(badge);
    }

    log.info(result);
  }

  private int convertItemIntoPriority(String item) {
    char c = item.charAt(0);
    if (Character.isUpperCase(c)) {
      return item.charAt(0) - 'A' + 27;
    } else {
      return item.charAt(0) - 'a' + 1;
    }
  }

  @Override
  public void lore() {
    log.info(lore);
  }
}