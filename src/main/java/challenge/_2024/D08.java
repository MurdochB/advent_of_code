package challenge._2024;

import static base.utils.FileUtil.inputToGrid;

import base.Solution;
import base.utils.Coord;
import base.utils.Direction;
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
    Map<String, List<Coord>> antennas = findAntennas(grid);
    Set<Coord> antinodes = new HashSet<>();
    for (List<Coord> group : antennas.values()) {
      antinodes.addAll(findFirstAntinodes(grid, group));
    }
    log.info("Unique antinodes: " + antinodes.size());
  }

  private Set<Coord> findFirstAntinodes(String grid[][], List<Coord> group) {
    Set<Coord> antinodes = new HashSet<>();
    for (int i = 0; i < group.size(); i++) {
      for (int j = 0; j < group.size(); j++) {
        if (i == j) {
          continue;
        }
        List<Coord> coords = oneStepAntinodeCoords(group.get(i), group.get(j));
        for (Coord coord : coords) {
          if (isAntinodeInGrid(grid, coord)) {
            antinodes.add(coord);
          }
        }
      }
    }
    return antinodes;
  }

  private List<Coord> oneStepAntinodeCoords(Coord a, Coord b) {
    List<Coord> anti = new ArrayList<>();
    Direction one = a.distance(b);
    Direction two = b.distance(a);
    anti.add(b.relative(one));
    anti.add(a.relative(two));
    return anti;
  }

  public void partTwo() {
    log.info("# Part 2 #");
    String[][] grid = inputToGrid(lines);
    Map<String, List<Coord>> antennas = findAntennas(grid);
    Set<Coord> antinodes = new HashSet<>();
    for (List<Coord> group : antennas.values()) {
      antinodes.addAll(findAllAntinodes(grid, group));
    }
    log.info("Unique antinodes: " + antinodes.size());
  }

  private Set<Coord> findAllAntinodes(String grid[][], List<Coord> group) {
    Set<Coord> antinodes = new HashSet<>();
    for (int i = 0; i < group.size(); i++) {
      antinodes.add(group.get(i));
      for (int j = 0; j < group.size(); j++) {
        if (i == j) {
          continue;
        }
        List<Coord> coords = allAntinodes(grid, group.get(i), group.get(j));
        for (Coord coord : coords) {
          if (isAntinodeInGrid(grid, coord)) {
            antinodes.add(coord);
          }
        }
      }
    }

    return antinodes;
  }

  private List<Coord> allAntinodes(String grid[][], Coord a, Coord b) {
    List<Coord> antis = new ArrayList<>();
    Direction one = a.distance(b);
    Direction two = b.distance(a);
    Coord nextAntiNode = b.relative(one);
    while (isAntinodeInGrid(grid, nextAntiNode)) {
      antis.add(nextAntiNode);
      nextAntiNode = nextAntiNode.relative(one);
    }
    nextAntiNode = a.relative(two);
    while (isAntinodeInGrid(grid, nextAntiNode)) {
      antis.add(nextAntiNode);
      nextAntiNode = nextAntiNode.relative(two);
    }
    return antis;
  }

  private Map<String, List<Coord>> findAntennas(String[][] grid) {
    Map<String, List<Coord>> antennaToCoords = new HashMap<>();
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid.length; c++) {
        if (!grid[r][c].equals(".")) {
          String antenna = grid[r][c];
          if (!antennaToCoords.containsKey(antenna)) {
            antennaToCoords.put(antenna, new ArrayList<>());
          }
          antennaToCoords.get(antenna).add(new Coord(r, c));
        }
      }
    }
    return antennaToCoords;
  }

  private boolean isAntinodeInGrid(String[][] grid, Coord antinode) {
    return !(antinode.r() < 0 || antinode.r() >= grid.length ||
        antinode.c() < 0 || antinode.c() >= grid.length);
  }

  public void lore() {
    log.info(lore);
  }
}