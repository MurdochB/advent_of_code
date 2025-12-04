package challenge._2025;

import static base.utils.Direction.ALL_DIRECTIONS;
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

  private static final String INPUT_FILE = "2025/inputs/04.txt";
  private static final String LORE_FILE = "2025/lore/04.txt";

  private D04(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D04 solution = new D04(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();
    String[][] grid = inputToGrid(lines);
    List<Coord> allPaper = findAllPaper(grid);
    int reachablePaper = 0;
    for (Coord coord : allPaper) {
      reachablePaper += paperReachable(grid, coord);
    }
    log.info(reachablePaper);
  }

  public void partTwo() {
    log.info("# Part 2 #");

    String[][] grid = inputToGrid(lines);
    List<Coord> allPaper = findAllPaper(grid);
    List<Coord> removedPaper = new ArrayList<>();

    boolean removedFlag = true;

    while (removedFlag) {
      removedFlag = false;
      allPaper.removeAll(removedPaper);

      for (Coord coord : allPaper) {
        if (isPaperReachable(grid, coord)) {
          removedPaper.add(coord);
          grid[coord.r()][coord.c()] = ".";
          removedFlag = true;
        }
      }
    }

    log.info(removedPaper.size());
  }

  private List<Coord> findAllPaper(String[][] grid) {
    List<Coord> found = new ArrayList<>();
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid.length; c++) {
        if (grid[r][c].equals("@")) {
          found.add(new Coord(r, c));
        }
      }
    }
    return found;
  }

  private boolean isPaperReachable(String grid[][], Coord paper) {
    return (allSurroundingPaper(grid, paper).size() < 4);
  }

  private int paperReachable(String[][] grid, Coord paper) {
    return isPaperReachable(grid, paper) ? 1 : 0;
  }

  private List<Coord> allSurroundingPaper(String[][] grid, Coord target) {
    List<Coord> surrounding = new ArrayList<>();
    for (Direction d : ALL_DIRECTIONS) {
      Coord next = target.relative(d);
      if (isPaper(grid, next)) {
        surrounding.add(next);
      }
    }
    return surrounding;
  }

  private boolean isPaper(String[][] grid, Coord coord) {
    if (isCoordInInGrid(grid, coord)) {
      return "@".equals(grid[coord.r()][coord.c()]);
    }
    return false;
  }

  private boolean isCoordInInGrid(String[][] grid, Coord coord) {
    return !(coord.r() < 0 || coord.r() >= grid.length ||
        coord.c() < 0 || coord.c() >= grid[0].length);
  }

  public void lore() {
    log.info(lore);
  }
}