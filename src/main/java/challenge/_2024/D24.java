package challenge._2024;

import base.Solution;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D24 extends Solution {

  private final Logger log = LogManager.getLogger(D24.class);

  private static final String INPUT_FILE = "2024/inputs/24.txt";
  private static final String LORE_FILE = "2024/lore/24.txt";

  private D24(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D24 solution = new D24(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();

    Map<String, Integer> wires = initialWires();
    List<String> connections = getConnectionSteps();
    
  }


  public void partTwo() {
    log.info("# Part 2 #");

  }

  private List<String> getConnectionSteps(){
    List<String> connections = new ArrayList<>();
    boolean ready = false;
    for (String line : lines) {
      if (line.isEmpty()){
        ready = true;
        continue;
      }
      if (ready){
        connections.add(line);
      }
    }
    return connections;
  }

  private Map<String, Integer> initialWires() {
    Map<String, Integer> wires = new HashMap<>();
    for (String line : lines) {
      if (line.isEmpty()) {
        return wires;
      }
      String[] split = line.split(": ");
      wires.put(split[0], Integer.parseInt(split[1]));
    }
    return wires;
  }

  public void lore() {
    log.info(lore);
  }
}