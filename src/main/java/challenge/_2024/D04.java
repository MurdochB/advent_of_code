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
        if (!directionGoesOutOfBounds(x, dir, grid) &&
            (grid[x.r()][x.c()].equals("X") &&
                grid[x.relative(dir).r()][x.relative(dir).c()].equals("M") &&
                grid[x.relative(dir, 2).r()][x.relative(dir, 2).c()].equals("A") &&
                grid[x.relative(dir, 3).r()][x.relative(dir, 3).c()].equals("S"))) {
          countAppearances++;
        }
      }
    }
    log.info(countAppearances);
  }

  public void partTwo() {
    log.info("# Part 2 #");
    String[][] grid = inputToGrid(lines);
    List<Coord> allAs = findAllChars(grid, "A");

    int countAppearances = 0;
    for (Coord a : allAs) {
      if (!xWouldBeOutOfBounds(a, grid)) {
        Coord ne = a.relative(Direction.NE);
        Coord sw = a.relative(Direction.SW);
        Coord nw = a.relative(Direction.NW);
        Coord se = a.relative(Direction.SE);
        String diag1 = grid[ne.r()][ne.c()] + grid[sw.r()][sw.c()];
        String diag2 = grid[nw.r()][nw.c()] + grid[se.r()][se.c()];
        if (diag1.contains("M") && diag1.contains("S") &&
            diag2.contains("M") && diag2.contains("S")) {
          countAppearances++;
        }
      }
    }
    log.info(countAppearances);
  }

  private boolean xWouldBeOutOfBounds(Coord a, String[][] grid) {
    for (Direction dir : Direction.ORDINAL_DIRECTIONS) {
      if (!isCoordInInGrid(grid, a.relative(dir))) {
        return false;
      }
    }
    return true;
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

  private boolean directionGoesOutOfBounds(Coord x, Direction dir, String[][] grid) {
    Coord relative = x.relative(dir, 4);
    return isCoordInInGrid(grid, relative);
  }

  private boolean isCoordInInGrid(String[][] grid, Coord coord) {
    return !(coord.r() < 0 || coord.r() >= grid.length ||
        coord.c() < 0 || coord.c() >= grid.length);
  }

  public void lore() {
    log.info(lore);
  }
}