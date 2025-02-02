package challenge._2024;

import static base.utils.FileUtil.inputToGrid;

import base.Solution;
import base.utils.Coord;
import base.utils.Direction;
import com.sun.jdi.connect.ListeningConnector;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D10 extends Solution {

  private final Logger log = LogManager.getLogger(D10.class);

  private static final String INPUT_FILE = "2024/inputs/10.txt";
  private static final String LORE_FILE = "2024/lore/10.txt";

  private D10(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D10 solution = new D10(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    String[][] grid = inputToGrid(lines);
    List<Coord> trailheads = findTrailheads(grid);

    int totalScore = 0;
    for (Coord trailhead : trailheads) {
      totalScore += scoreTrailHead(grid, trailhead, 0, new HashSet<>());
    }
    log.info("Total score: " + totalScore);
  }

  private int scoreTrailHead(String[][] grid, Coord pos, int cur, Set<Coord> visited) {
    visited.add(pos);
    if (cur == 9) {
      return 1;
    }
    int score = 0;
    for (Direction dir : Direction.CARDINAL_DIRECTIONS) {
      Coord step = pos.relative(dir);
      if (isNextNumber(cur + 1, step, grid) && !visited.contains(step)) {
        score += scoreTrailHead(grid, step, Integer.parseInt(grid[step.r()][step.c()]), visited);
      }
    }
    return score;
  }

  private int rateTrailHead(String[][] grid, Coord pos, int cur) {
    if (cur == 9) {
      return 1;
    }
    int rating = 0;
    for (Direction dir : Direction.CARDINAL_DIRECTIONS) {
      Coord step = pos.relative(dir);
      if (isNextNumber(cur + 1, step, grid)) {
        rating += rateTrailHead(grid, step, Integer.parseInt(grid[step.r()][step.c()]));
      }
    }
    return rating;
  }

  private boolean isNextNumber(int current, Coord step, String[][] grid) {
    if (isCoordInInGrid(grid, step)) {
      int val = Integer.parseInt(grid[step.r()][step.c()]);
      return val == current;
    }
    return false;
  }

  public void partTwo() {
    log.info("# Part 2 #");
    String[][] grid = inputToGrid(lines);
    List<Coord> trailheads = findTrailheads(grid);

    int totalRating = 0;
    for (Coord trailhead : trailheads) {
      totalRating += rateTrailHead(grid, trailhead, 0);
    }
    log.info("Total rating: " + totalRating);
  }

  private List<Coord> findTrailheads(String[][] grid) {
    List<Coord> trailheads = new ArrayList<>();
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid.length; c++) {
        if (grid[r][c].equals("0")) {
          trailheads.add(new Coord(r, c));
        }
      }
    }
    return trailheads;
  }

  private boolean isCoordInInGrid(String[][] grid, Coord coord) {
    return !(coord.r() < 0 || coord.r() >= grid.length ||
        coord.c() < 0 || coord.c() >= grid.length);
  }

  public void lore() {
    log.info(lore);
  }
}