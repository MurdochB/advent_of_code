package challenge._2024;

import base.Solution;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    int wiresSize = 0;
    while (wiresSize < wires.size()) {
      wiresSize = wires.size();
      for (String con : connections) {
        String[] split = con.split(" -> ");
        String[] op = split[0].split(" ");
        String firstWire = op[0];
        String operator = op[1];
        String secondWire = op[2];
        String resultant = split[1];
        log.info("testing {}({}) {} {}({}) -> {}", firstWire, wires.get(firstWire), operator,
            secondWire, wires.get(secondWire), resultant);
        Optional<Integer> result = getResult(wires, firstWire, secondWire, operator);
        result.ifPresent(integer -> wires.put(resultant, integer));
      }
    }

    printAllWires(wires);
    List<String> zOutputs = wires.keySet().stream().filter(s -> s.startsWith("z")).sorted()
        .toList();
    StringBuilder sb = new StringBuilder();
    for (int z = zOutputs.size() - 1; z >= 0; z--) {
      sb.append(wires.get(zOutputs.get(z)));
    }
    printBinaryToInt(sb.toString());
  }

  public void partTwo() {
    log.info("# Part 2 #");

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

  private List<String> getConnectionSteps() {
    List<String> connections = new ArrayList<>();
    boolean ready = false;
    for (String line : lines) {
      if (line.isEmpty()) {
        ready = true;
        continue;
      }
      if (ready) {
        connections.add(line);
      }
    }
    return connections;
  }

  public Optional<Integer> getResult(Map<String, Integer> wires, String operand1, String operand2,
      String operator) {
    if (wires.containsKey(operand1) && wires.containsKey(operand2)) {
      return switch (operator) {
        case "AND" -> Optional.of(wires.get(operand1) & wires.get(operand2));
        case "OR" -> Optional.of(wires.get(operand1) | wires.get(operand2));
        case "XOR" -> Optional.of(wires.get(operand1) ^ wires.get(operand2));
        default -> throw new IllegalArgumentException("Unknown operator: " + operator);
      };
    }
    return Optional.empty();
  }

  private void printBinaryToInt(String binary) {
    log.info("binary: {}", binary);
    String[] split = binary.split("");
    long value = 0;
    int step = 0;
    for (int i = split.length - 1; i >= 0; i--) {
      value += (long) Integer.parseInt(split[i]) * (Math.pow(2, step));
      step++;
    }
    log.info(value);
  }

  // Debugging
  private void printAllWires(Map<String, Integer> wires) {
    List<String> sorted = wires.keySet().stream().sorted().toList();
    for (String k : sorted) {
      StringBuilder sb = new StringBuilder();
      sb.append(k);
      sb.append(": ");
      sb.append(wires.get(k));
      log.info(sb);
    }
  }

  public void lore() {
    log.info(lore);
  }
}