package challenge._2024;

import base.Solution;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D23 extends Solution {

  private final Logger log = LogManager.getLogger(D23.class);

  private static final String INPUT_FILE = "2024/inputs/23.txt";
  private static final String LORE_FILE = "2024/lore/23.txt";

  private D23(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D23 solution = new D23(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();
    Map<String, Set<String>> computerToAllConnections = computerToAllConnections();

    Set<Set<String>> networkedComps = new HashSet<>();

    for (Entry<String, Set<String>> thisCompNetwork : computerToAllConnections.entrySet()) {
      String thisComp = thisCompNetwork.getKey();

      // for all connections this computer has
      for (String connection : thisCompNetwork.getValue()) {
        // The connection's networked computers:
        Set<String> connNetwork = computerToAllConnections.get(connection);
        Set<String> computersInNetwork = new HashSet<>();
        computersInNetwork.add(thisComp);
        computersInNetwork.add(connection);
        for (String sharedConnection : thisCompNetwork.getValue()) {
          if (connNetwork.contains(sharedConnection)) {
            // We want groups of 3
            Set<String> newSet = new HashSet<>(computersInNetwork);
            newSet.add(sharedConnection);
            networkedComps.add(newSet);
          }
        }
      }
    }

    long setOf3Computers = networkedComps.stream().filter(this::networkContainsTComp).count();
    log.info(setOf3Computers);
  }

  public void partTwo() {
    log.info("# Part 2 #");
    
  }

  private Map<String, Set<String>> computerToAllConnections() {
    Map<String, Set<String>> computerToAllConnections = new HashMap<>();
    for (String line : lines) {
      String[] comps = line.split("-");
      if (!computerToAllConnections.containsKey(comps[0])) {
        computerToAllConnections.put(comps[0], new HashSet<>());
      }
      if (!computerToAllConnections.containsKey(comps[1])) {
        computerToAllConnections.put(comps[1], new HashSet<>());
      }
      computerToAllConnections.get(comps[0]).add(comps[1]);
      computerToAllConnections.get(comps[1]).add(comps[0]);
    }
    return computerToAllConnections;
  }

  private boolean networkContainsTComp(Set<String> computersInNetwork) {
    for (String s : computersInNetwork) {
      if (s.startsWith("t")) {
        return true;
      }
    }
    return false;
  }

  private void printEntry(Entry<String, Set<String>> networks) {
    // Prints a computer and the networked computers...
    StringBuilder sb = new StringBuilder();
    sb.append(networks.getKey());
    sb.append(" -> ");
    for (String s : networks.getValue()) {
      sb.append(s);
      sb.append(" ");
    }
    log.info(sb);
  }

  public void lore() {
    log.info(lore);
  }
}