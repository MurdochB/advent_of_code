package challenge._2024;

import static base.utils.FileUtil.inputToGrid;

import base.Solution;
import base.utils.Coord;
import base.utils.Direction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D20 extends Solution {

  private final Logger log = LogManager.getLogger(D20.class);

  private static final String INPUT_FILE = "2024/inputs/20.txt";
  private static final String LORE_FILE = "2024/lore/20.txt";

  private D20(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D20 solution = new D20(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();

    String[][] grid = inputToGrid(lines);
    Coord start = findChar(grid, "S");
    Coord end = findChar(grid, "E");

    int baseScore = findPathSize(grid, start, end, null);

    List<Coord> potentialCheats = findAllChars(grid, "#");
    Map<Coord, Integer> cheatingRaceScores = new HashMap<>();
    for (Coord cheat : potentialCheats) {
      cheatingRaceScores.put(cheat, baseScore - findPathSize(grid, start, end, cheat));
    }
    int wins = 0;
    for (Integer cheatVals : cheatingRaceScores.values()) {
      if (cheatVals >= 100) {
        wins++;
      }
    }
    log.info("Good cheats: {}", wins);

  }

  private int findPathSize(String[][] grid, Coord start, Coord end, Coord cheat) {
    Map<Coord, Integer> distances = new HashMap<>();
    distances.put(start, 0);
    PriorityQueue<Coord> priorityQueue = new PriorityQueue<>(
        Comparator.comparingInt(c -> distances.getOrDefault(c, Integer.MAX_VALUE)));
    priorityQueue.add(start);

    Set<Coord> visited = new HashSet<>();

    while (!priorityQueue.isEmpty()) {
      Coord curr = priorityQueue.poll();

      if (visited.contains(curr)) {
        continue;
      }
      visited.add(curr);

      if (curr.equals(end)) {
        return distances.get(curr);
      }

      for (Direction dir : Direction.CARDINAL_DIRECTIONS) {
        Coord next = curr.relative(dir);
        if ((!isCoordAWallOrOutOfBounds(grid, next) || next.equals(cheat)) &&
            !visited.contains(next)) {
          int newDistance = distances.get(curr) + 1;
          if (newDistance < distances.getOrDefault(next, Integer.MAX_VALUE)) {
            distances.put(next, newDistance);
            priorityQueue.add(next);
          }
        }
      }
    }
    return -1;
  }

  private boolean isCoordInInGrid(String[][] grid, Coord coord) {
    return !(coord.r() < 0 || coord.r() >= grid.length ||
        coord.c() < 0 || coord.c() >= grid[0].length);
  }

  private boolean isCoordAWallOrOutOfBounds(String[][] grid, Coord coord) {
    if (isCoordInInGrid(grid, coord)) {
      return "#".equals(grid[coord.r()][coord.c()]);
    } else {
      return true;
    }
  }

  public void partTwo() {
    log.info("# Part 2 #");

  }

  private Coord findChar(String[][] grid, String charToFind) {
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[0].length; c++) {
        if (grid[r][c].equals(charToFind)) {
          return new Coord(r, c);
        }
      }
    }
    return null;
  }

  private List<Coord> findAllChars(String[][] grid, String charToFind) {
    List<Coord> found = new ArrayList<>();
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid.length; c++) {
        if (grid[r][c].equals(charToFind)) {
          found.add(new Coord(r, c));
        }
      }
    }
    return found;
  }

  public void lore() {
    log.info(lore);
  }
}