package challenge._2024;

import static base.utils.FileUtil.inputToGrid;

import base.Solution;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D08 extends Solution {

  private final Logger log = LogManager.getLogger(D08.class);

  private static final String INPUT_FILE = "2024/inputs/08.txt";
  private static final String LORE_FILE = "2024/lore/08.txt";

  private D08(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D08 solution = new D08(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    String[][] grid = inputToGrid(lines);
    Map<String, List<Integer[]>> antennas = findAntennas(grid);
    Set<Integer[]> antinodes = new HashSet<>();
    for (List<Integer[]> group : antennas.values()) {
      antinodes.addAll(findAntinodes(grid, group));
    }

    for (Integer[] antinode : antinodes) {
      log.info(antinode[0] + " " + antinode[1]);
    }
    log.info("Antinodes: " + antinodes.size());
  }

  private Set<Integer[]> findAntinodes(String[][] grid, List<Integer[]> group) {
    Set<Integer[]> antinodes = new HashSet<>();
    for (int i = 0; i < group.size(); i++) {
      for (int j = 0; j < group.size(); j++) {
        if (i == j) {
          continue;
        }
        Integer[] pair1 = group.get(i);
        Integer[] pair2 = group.get(j);
        List<Integer[]> integers = generatePossibleAntinodeCoords(pair1, pair2);
      }
    }
    return antinodes;
  }

  private boolean isAntinodeInGrid(String[][] grid, Integer[] antinode) {
    return !(antinode[0] < 0 || antinode[0] >= grid.length ||
        antinode[1] < 0 || antinode[1] >= grid.length);
  }

  private List<Integer[]> generatePossibleAntinodeCoords(Integer[] p1, Integer[] p2){
    List<Integer[]> anti = new ArrayList<>();
    if (p1[0] == p2[0]){
      // on same row
      // up and down antinodes only possible.
      int dist = Math.abs(p1[1] - p2[1]);
      anti.add(new Integer[]{p1[0], })
    }

    return null;
  }

  public void partTwo() {
    log.info("# Part 2 #");

  }

  private Map<String, List<Integer[]>> findAntennas(String[][] grid) {
    Map<String, List<Integer[]>> antennaToCoords = new HashMap<>();
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid.length; c++) {
        if (!grid[r][c].equals(".")) {
          if (antennaToCoords.containsKey(grid[r][c])) {
            antennaToCoords.get(grid[r][c]).add(new Integer[]{r, c});
          } else {
            List<Integer[]> list = new ArrayList<>();
            list.add(new Integer[]{r, c});
            antennaToCoords.put(grid[r][c], list);
          }
        }
      }
    }
    return antennaToCoords;
  }

  public void lore() {
    log.info(lore);
  }
}