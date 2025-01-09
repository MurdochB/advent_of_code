package challenge._2024;

import base.Solution;
import base.utils.Coord;
import base.utils.Direction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    printGrid(grid);
    List<Coord> list = new ArrayList<>();
    list.add(new Coord(4, 4));
    list.add(new Coord(4, 3));
    moveItemsInGrid(grid, list, Direction.W);
    log.info("-------");
    printGrid(grid);
//    for (Direction step : steps) {
//      List<Coord> coordsToMove = checkNextStep(grid, robot, step);
//      if (!coordsToMove.isEmpty()){
//
//      }
//      Coord nextStep = robot.relative(step);
//
//    }

  }

  private List<Coord> checkNextStep(String[][] grid, Coord coord, Direction dir) {
    List<Coord> coordsToMove = new ArrayList<>();
    Coord next = coord.relative(dir);
    String nextStepType = grid[next.r()][next.c()];
    switch (nextStepType) {
      case "#" -> {
        return new ArrayList<>();
      }
      case "O" -> {
        coordsToMove.add(next);
        List<Coord> nextCoordsToMove = checkNextStep(grid, next, dir);
        if (nextCoordsToMove.isEmpty()) {
          return new ArrayList<>();
        } else {
          return coordsToMove;
        }
      }
      case "." -> {
        coordsToMove.add(next);
        return coordsToMove;
      }
    }
    return coordsToMove;
  }

  public void partTwo() {
    log.info("# Part 2 #");

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