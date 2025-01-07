package challenge._2024;

import base.Solution;
import base.utils.Coord;
import base.utils.Direction;
import base.utils.Pair;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D18 extends Solution {

  private final Logger log = LogManager.getLogger(D18.class);

  private static final String INPUT_FILE = "2024/inputs/18.txt";
  private static final String LORE_FILE = "2024/lore/18.txt";

  private D18(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D18 solution = new D18(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();
    List<Coord> coordsInput = parseToCoords();
    Pair<Integer> rowAndColMax = findRowAndColMax(coordsInput);
    String[][] grid = buildBaseGrid(rowAndColMax);

    for (int i = 0; i < 1024; i++) {
      Coord c = coordsInput.get(i);
      grid[c.r()][c.c()] = "#";
    }
    Coord start = new Coord(0, 0);
    Coord end = new Coord(rowAndColMax.getLeft(), rowAndColMax.getRight());
    printGrid(grid);
    log.info(findPathSize(grid, start, end));
  }

  private int findPathSize(String[][] grid, Coord start, Coord end) {
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
        if (!isCoordAWallOrOutOfBounds(grid, next) && !visited.contains(next)) {
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

  private String[][] buildBaseGrid(Pair<Integer> rowAndColMax) {
    String[][] grid = new String[rowAndColMax.getLeft() + 1][rowAndColMax.getRight() + 1];
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[0].length; c++) {
        grid[r][c] = ".";
      }
    }
    return grid;
  }

  private List<Coord> parseToCoords() {
    List<Coord> coords = new ArrayList<>();
    for (String line : lines) {
      String[] split = line.split(",");
      coords.add(new Coord(Integer.parseInt(split[1]), Integer.parseInt(split[0])));
    }
    return coords;
  }

  private Pair<Integer> findRowAndColMax(List<Coord> coords) {
    int rowMax = 0;
    int colMax = 0;
    for (Coord coord : coords) {
      rowMax = Math.max(rowMax, coord.r());
      colMax = Math.max(colMax, coord.c());
    }
    return new Pair<>(rowMax, colMax);
  }

  private void printGrid(String[][] grid) {
    for (String[] strings : grid) {
      StringBuilder sb = new StringBuilder();
      for (String s : strings) {
        sb.append(s);
      }
      log.info(sb.toString());
    }
  }

  public void lore() {
    log.info(lore);
  }
}