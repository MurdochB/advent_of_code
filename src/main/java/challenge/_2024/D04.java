package challenge._2024;

import static base.utils.FileUtil.inputToGrid;

import base.Solution;
import base.utils.Coord;
import base.utils.Direction;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D04 extends Solution {

  private final Logger log = LogManager.getLogger(D04.class);

  private static final String INPUT_FILE = "2024/inputs/04.txt";
  private static final String LORE_FILE = "2024/lore/04.txt";

  private D04(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D04 solution = new D04(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    log.info(lore);
    String[][] grid = inputToGrid(lines);
    List<Coord> allXs = findAllChars(grid, "X");

    int countAppearances = 0;
    for (Coord x : allXs) {
      for (Direction dir : Direction.ALL_DIRECTIONS) {
        if (directionStaysInBounds(x, dir, grid) &&
            (gridValueAtCoord(grid, x.relative(dir)).equals("M") &&
                gridValueAtCoord(grid, x.relative(dir, 2)).equals("A") &&
                gridValueAtCoord(grid, x.relative(dir, 3)).equals("S"))) {
          countAppearances++;
        }
      }
    }
    log.info(countAppearances);
  }

  private String gridValueAtCoord(String[][] grid, Coord c) {
    return grid[c.r()][c.c()];
  }

  public void partTwo() {
    log.info("# Part 2 #");
    String[][] grid = inputToGrid(lines);
    List<Coord> allAs = findAllChars(grid, "A");

    int countAppearances = 0;
    for (Coord a : allAs) {
      if (xIsInBounds(a, grid)) {
        Coord ne = a.relative(Direction.NE);
        Coord sw = a.relative(Direction.SW);
        Coord nw = a.relative(Direction.NW);
        Coord se = a.relative(Direction.SE);
        String diag1 = gridValueAtCoord(grid, ne) + gridValueAtCoord(grid, sw);
        String diag2 = gridValueAtCoord(grid, nw) + gridValueAtCoord(grid, se);
        if (diag1.contains("M") && diag1.contains("S") &&
            diag2.contains("M") && diag2.contains("S")) {
          countAppearances++;
        }
      }
    }
    log.info(countAppearances);
  }

  private List<Coord> findAllChars(String[][] grid, String charToFind) {
    List<Coord> found = new ArrayList<>();
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[0].length; c++) {
        if (grid[r][c].equals(charToFind)) {
          found.add(new Coord(r, c));
        }
      }
    }
    return found;
  }

  private boolean xIsInBounds(Coord a, String[][] grid) {
    for (Direction dir : Direction.ORDINAL_DIRECTIONS) {
      if (!isCoordInGrid(grid, a.relative(dir))) {
        return false;
      }
    }
    return true;
  }

  private boolean directionStaysInBounds(Coord x, Direction dir, String[][] grid) {
    return isCoordInGrid(grid, x.relative(dir, 3));
  }

  private boolean isCoordInGrid(String[][] grid, Coord coord) {
    return !(coord.r() < 0 || coord.r() >= grid.length ||
        coord.c() < 0 || coord.c() >= grid.length);
  }

  public void lore() {
    log.info(lore);
  }
}