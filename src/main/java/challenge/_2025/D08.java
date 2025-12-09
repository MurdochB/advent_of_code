package challenge._2025;

import base.Solution;
import base.utils.Coord3D;
import base.utils.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D08 extends Solution {

  private final Logger log = LogManager.getLogger(D08.class);

  private static final String INPUT_FILE = "2025/inputs/08.txt";
  private static final String LORE_FILE = "2025/lore/08.txt";

  private D08(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D08 solution = new D08(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();

    List<Coord3D> coords = inputToCoords(lines);

    Map<Double, Pair<Coord3D>> shortest = new HashMap<>();

    for (Coord3D coord : coords) {
      for (Coord3D other : coords) {
        if (coord.equals(other)) {
          continue;
        }
        shortest.put(coord.distanceToCoord3D(other), new Pair<>(coord, other));
      }
    }
    List<Double> closeJunctions = shortest.keySet().stream().sorted().toList();
    List<Set<Coord3D>> circuits = new ArrayList<>();
    int count = 0;
    for (Double d : closeJunctions) {
      if (count < 10) {
        boolean found = false;
        for (Set<Coord3D> circuit : circuits) {
          if (circuit.contains(shortest.get(d).getLeft()) &&
              circuit.contains(shortest.get(d).getRight())) {
            break;
          }
          if (circuit.contains(shortest.get(d).getLeft()) ||
              circuit.contains(shortest.get(d).getRight())) {
            found = true;
            circuit.add(shortest.get(d).getLeft());
            circuit.add(shortest.get(d).getRight());
          }
        }
        if (!found) {
          Set<Coord3D> newCircuit = new HashSet<>();
          newCircuit.add(shortest.get(d).getLeft());
          newCircuit.add(shortest.get(d).getRight());
          circuits.add(newCircuit);
        }
        count++;
//      log.info("{} closest to {} by {}", shortest.get(d).getLeft(), shortest.get(d).getRight(), d);
      }
    }
    log.info("done");
  }


  public void partTwo() {
    log.info("# Part 2 #");

  }

  private List<Coord3D> inputToCoords(List<String> in) {
    List<Coord3D> coords = new ArrayList<>();
    for (String s : in) {
      String[] split = s.split(",");
      coords.add(new Coord3D(
          Integer.parseInt(split[0]),
          Integer.parseInt(split[1]),
          Integer.parseInt(split[2])));
    }
    return coords;
  }
//  162,817,812 and 425,690,689.
//  162,817,812 and 431,825,988
//  906,360,560 and 805,96,715
// 431,825,988 and 425,690,689
//

  public void lore() {
    log.info(lore);
  }
}