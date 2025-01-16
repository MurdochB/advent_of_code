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

    Set<Set<String>> networkedComps = new HashSet<>();

    for (Entry<String, Set<String>> thisCompNetwork : computerToAllConnections.entrySet()) {
      printEntry(thisCompNetwork);
      String comp = thisCompNetwork.getKey();
      for (String connection : thisCompNetwork.getValue()) {
        Set<String> otherConns = computerToAllConnections.get(connection);
        Set<String> combo = new HashSet<>();
        combo.add(comp);
        combo.add(connection);
        for (String aaConnection : thisCompNetwork.getValue()) {
          if (otherConns.contains(aaConnection)) {
            Set<String> newSet = new HashSet<>(combo);
            newSet.add(aaConnection);
            networkedComps.add(newSet);
          }
        }
      }
    }

    long count = networkedComps.stream().filter(this::method).count();
    log.info(count);
  }

  private boolean method(Set<String> computersInNetwork) {
    for (String s : computersInNetwork) {
      if (s.startsWith("t")) {
        return true;
      }
    }
    return false;
  }

  private void printEntry(Entry<String, Set<String>> networks) {
    StringBuilder sb = new StringBuilder();
    sb.append(networks.getKey());
    sb.append(" -> ");
    for (String s : networks.getValue()) {
      sb.append(s);
      sb.append(" ");
    }
    log.info(sb);
  }

  public void partTwo() {
    log.info("# Part 2 #");
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

    Set<Set<String>> networkedComps = new HashSet<>();

    for (Entry<String, Set<String>> thisCompNetwork : computerToAllConnections.entrySet()) {
      printEntry(thisCompNetwork);
      String comp = thisCompNetwork.getKey();
      for (String connection : thisCompNetwork.getValue()) {
        Set<String> otherConns = computerToAllConnections.get(connection);
        Set<String> combo = new HashSet<>();
        combo.add(comp);
        combo.add(connection);
        for (String aaConnection : thisCompNetwork.getValue()) {
          if (otherConns.contains(aaConnection)) {
            combo.add(aaConnection);
          }
        }
        networkedComps.add(combo);
      }
    }
    int biggest = 0;
    for (Set<String> networkedComp : networkedComps) {
      if (networkedComp.size() > biggest){
        biggest = networkedComp.size();
      }
    }
    log.info(networkedComps.size());
  }

  public void lore() {
    log.info(lore);
  }
}