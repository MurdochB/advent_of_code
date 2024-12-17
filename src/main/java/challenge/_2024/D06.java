package challenge._2024;

import static base.utils.Direction.quickString;
import static base.utils.FileUtil.inputToGrid;

import base.Solution;
import base.utils.Coord;
import base.utils.Direction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D06 extends Solution {

  private final Logger log = LogManager.getLogger(D06.class);

  private static final String INPUT_FILE = "2024/inputs/06.txt";
  private static final String LORE_FILE = "2024/lore/06.txt";

  private D06(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D06 solution = new D06(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    String[][] grid = inputToGrid(lines);
    Coord curr = findStart(grid);
    Direction dir = Direction.N;
    Set<Coord> guardNormalPath = findGuardPath(grid, curr, dir);

    log.info("Guard steps: " + guardNormalPath.size());
    printGrid(grid);
  }

  private Set<Coord> findGuardPath(String[][] grid, Coord curr, Direction dir) {
    Set<Coord> visited = new HashSet<>();
    while (!peakNextStep(grid, curr, dir).equals("D")) {
      visited.add(curr);
      String next = peakNextStep(grid, curr, dir);
      if (next.equals("#")) {
        dir = Direction.right(dir);
      } else {
        curr = curr.relative(dir);
      }
    }
    visited.add(curr);
    return visited;
  }

  public void partTwo() {
    log.info("# Part 2 #");
    String[][] grid = inputToGrid(lines);
    Coord curr = findStart(grid);
    Direction dir = Direction.N;
    Set<Coord> guardNormalPath = findGuardPath(grid, curr, dir);
    guardNormalPath.remove(findStart(grid));
    int loopedPaths = 0;

    guardNormalPath.remove(findStart(grid));
    for (Coord newBlocker : guardNormalPath) {
      grid = inputToGrid(lines);
      curr = findStart(grid);
      dir = Direction.N;
      if (doesGuardLoop(grid, curr, dir, newBlocker)) {
        loopedPaths++;
      }
    }

    log.info("Guard loops: " + loopedPaths);
  }

  private boolean doesGuardLoop(String[][] grid, Coord curr, Direction dir, Coord newBlockage) {
    grid[newBlockage.r()][newBlockage.c()] = "@";
    List<String> path = new ArrayList<>();
    while (!peakNextStep(grid, curr, dir).equals("D")) {
      grid[curr.r()][curr.c()] = "x";
      String curAndDir = curr + quickString(dir);
      if (path.contains(curAndDir)) {
        return true;
      }
      path.add(curAndDir);
      String next = peakNextStep(grid, curr, dir);
      if (next.equals("#") || next.equals("@")) {
        dir = Direction.right(dir);
      } else {
        curr = curr.relative(dir);
      }
    }
    return false;
  }

  private Coord findStart(String[][] grid) {
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid.length; c++) {
        if (grid[r][c].equals("^")) {
          return new Coord(r, c);
        }
      }
    }
    return new Coord(-1, -1);
  }

  private String peakNextStep(String[][] grid, Coord cur, Direction dir) {
    Coord newCoord = cur.relative(dir);
    if (newCoord.r() < 0 || newCoord.r() >= grid.length ||
        newCoord.c() < 0 || newCoord.c() >= grid[0].length) {
      return "D";
    }
    return grid[newCoord.r()][newCoord.c()];
  }

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