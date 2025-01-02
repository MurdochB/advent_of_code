package challenge._2024;

import static base.utils.FileUtil.inputToGrid;

import base.Solution;
import base.utils.Coord;
import base.utils.Direction;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D16 extends Solution {

  private final Logger log = LogManager.getLogger(D16.class);

  private static final String INPUT_FILE = "2024/inputs/16.txt";
  private static final String LORE_FILE = "2024/lore/16.txt";

  private D16(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D16 solution = new D16(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();
    String[][] grid = inputToGrid(lines);
    Coord start = findChar(grid, "S");
    Coord end = findChar(grid, "E");

    int pathScore = findCheapestPath(grid, start, end);
    log.info("cheapest path: {}", pathScore);
  }

  private int findCheapestPath(String[][] grid, Coord start, Coord end) {
    Map<Coord, Integer> distances = new HashMap<>();
    distances.put(start, 0);
    Set<Coord> visited = new HashSet<>();

    PriorityQueue<Coord> priorityQueue = new PriorityQueue<>(
        Comparator.comparingInt(coord -> distances.getOrDefault(coord, Integer.MAX_VALUE)));
    priorityQueue.add(start);

    while (!priorityQueue.isEmpty()) {
      Coord curr = priorityQueue.poll();

      if (visited.contains(curr)) {
        continue;
      }
      visited.add(curr);

      if (curr.equals(end)) {
        return distances.get(curr);
      }
      Direction facing = Direction.E;
      for (Direction dir : Direction.CARDINAL_DIRECTIONS) {
        Coord next = curr.relative(dir);
        if (!isCoordAWallOrOutOfBounds(grid, next) && !visited.contains(next)) {
          int newDistance = distances.get(curr) + 1;
          if (!facing.equals(dir)) {
            newDistance += 1000;
            facing = dir;
          }

          if (newDistance < distances.getOrDefault(next, Integer.MAX_VALUE)) {
            distances.put(next, newDistance);// probably need to store the direction as well - instead of the map
            priorityQueue.add(next);
          }
        }
      }
    }
    return -1;
  }

  private boolean isCoordInInGrid(String[][] grid, Coord coord) {
    return !(coord.r() < 0 || coord.r() >= grid.length ||
        coord.c() < 0 || coord.c() >= grid.length);
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
      for (int c = 0; c < grid.length; c++) {
        if (grid[r][c].equals(charToFind)) {
          return new Coord(r, c);
        }
      }
    }
    return null;
  }

  public void lore() {
    log.info(lore);
  }
}