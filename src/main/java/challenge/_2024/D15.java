package challenge._2024;

import base.Solution;
import base.utils.Coord;
import base.utils.Direction;
import java.util.ArrayList;
import java.util.List;
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
      robot.relative(step);
      // attempt to move robot & boxes.
    }

  }

  public void partTwo() {
    log.info("# Part 2 #");

  }

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

  public void lore() {
    log.info(lore);
  }
}