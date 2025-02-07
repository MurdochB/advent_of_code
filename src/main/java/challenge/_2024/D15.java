package challenge._2024;

import base.Solution;
import base.utils.Coord;
import base.utils.Direction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D15 extends Solution {

  private final Logger log = LogManager.getLogger(D15.class);

  private static final String INPUT_FILE = "2024/inputs/15.txt";
  private static final String LORE_FILE = "2024/lore/15.txt";

  private D15(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D15 solution = new D15(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();

    String[][] grid = createGrid();
    List<Direction> steps = getDirectionList();

    Coord robot = findInGrid(grid, "@");
    for (Direction step : steps) {
      List<Coord> coordsToMove = checkNextSteps(grid, robot, step);
      if (!coordsToMove.isEmpty()) {
        robot = robot.relative(step);
        moveItemsInGrid(grid, coordsToMove, step);
      }
    }
    printGrid(grid);
    log.info("GPS Total: {}", calculateFullGPS(grid));
  }

  private List<Coord> checkNextSteps(String[][] grid, Coord coord, Direction dir) {
    List<Coord> coordsToMove = new ArrayList<>();
    coordsToMove.add(coord);
    while (true) {
      Coord next = coord.relative(dir);
      String nextStepType = grid[next.r()][next.c()];
      switch (nextStepType) {
        case "O" -> {
          coordsToMove.add(next);
          coord = next;
        }
        case "." -> {
          return coordsToMove;
        }
        default -> {
          return new ArrayList<>();
        }
      }
    }
  }

  public void partTwo() {
    log.info("# Part 2 #");

    String[][] grid = createGrid();
    List<Direction> steps = getDirectionList();

    printGrid(grid);

    grid = extendGrid(grid);
    Coord robot = findInGrid(grid, "@");
    printGrid(grid);
    for (Direction step : steps) {
      List<Coord> coordsToMove = checkNextStepWithBoxes(grid, robot, step);
      if (!coordsToMove.isEmpty()) {
        robot = robot.relative(step);
        moveItemsInGrid(grid, coordsToMove, step);
      }
    }
  }

  private List<Coord> checkNextStepWithBoxes(String[][] grid, Coord coord, Direction dir) {
    List<Coord> coordsToMove = new ArrayList<>();
    coordsToMove.add(coord);
    List<Coord> movingNodes = new ArrayList<>();
    while (true) {
      Coord next = coord.relative(dir);
      String nextStepType = grid[next.r()][next.c()];
      switch (nextStepType) {
        case "[", "]" -> {
          coordsToMove.add(next);
          coord = next;
        }
        case "." -> {
          return coordsToMove;
        }
        default -> {
          return new ArrayList<>();
        }
      }
    }
  }

  private String[][] extendGrid(String[][] grid) {
    String[][] newGrid = new String[grid.length][];
    for (int i = 0; i < grid[0].length; i++) {
      String[] split = grid[i];
      String[] splitDoubled = Arrays.stream(split)
          .flatMap(s ->
              switch (s) {
                case "O": {
                  yield Stream.of("[", "]");
                }
                case "@": {
                  yield Stream.of("@", ".");
                }
                default:
                  yield Stream.of(s, s);
              }
          )
          .toArray(String[]::new);
      newGrid[i] = splitDoubled;
    }
    return newGrid;
  }

  private void moveItemsInGrid(String[][] grid, List<Coord> itemsToMove, Direction dir) {
    Map<Coord, String> movedItem = new HashMap<>();
    for (Coord coord : itemsToMove) {
      Coord next = coord.relative(dir);
      String itemType = grid[coord.r()][coord.c()];
      grid[coord.r()][coord.c()] = ".";
      movedItem.put(next, itemType);
    }
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[0].length; c++) {
        Coord thisStep = new Coord(r, c);
        if (movedItem.containsKey(thisStep)) {
          grid[r][c] = movedItem.get(thisStep);
        }
      }
    }
  }

  private int calculateFullGPS(String[][] grid) {
    List<Coord> boxes = findAllInGrid(grid, "O");
    return boxes.stream().mapToInt(this::calculateGPS).sum();
  }

  private int calculateGPS(Coord c) {
    return 100 * c.r() + c.c();
  }

  // Parsing input
  private String[][] createGrid() {
    int gridSize = lines.get(0).length();

    String[][] grid = new String[gridSize][];
    for (int i = 0; i < gridSize; i++) {
      grid[i] = lines.get(i).split("");
    }
    return grid;
  }

  private List<Direction> getDirectionList() {
    List<Direction> directions = new ArrayList<>();
    boolean ignoreGrid = true;
    for (String line : lines) {
      if (line.isEmpty()) {
        ignoreGrid = false;
        continue;
      }
      if (ignoreGrid) {
        continue;
      }
      String[] split = line.split("");
      for (String dir : split) {
        directions.add(Direction.fromArrow(dir));
      }
    }
    return directions;
  }

  private Coord findInGrid(String[][] grid, String type) {
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid.length; c++) {
        if (grid[r][c].equals(type)) {
          return new Coord(r, c);
        }
      }
    }
    return new Coord(-1, -1);
  }

  private List<Coord> findAllInGrid(String[][] grid, String type) {
    List<Coord> items = new ArrayList<>();
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid.length; c++) {
        if (grid[r][c].equals(type)) {
          items.add(new Coord(r, c));
        }
      }
    }
    return items;
  }

  // debugging tools
  private void printGrid(String[][] grid) {
    for (int r = 0; r < grid.length; r++) {
      StringBuilder sb = new StringBuilder();
      for (String s : grid[r]) {
        sb.append(s);
      }
      log.info(sb.toString());
    }
  }

  public void lore() {
    log.info(lore);
  }
}