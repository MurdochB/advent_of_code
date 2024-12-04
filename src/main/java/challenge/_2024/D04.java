package challenge._2024;

import static base.utils.FileUtil.inputToGrid;

import base.Solution;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    String[][] grid = inputToGrid(lines);
    List<int[]> allXs = findAllChars(grid, "X");

    List<int[]> directions = new ArrayList<>();
    directions.add(new int[]{-1, 0});
    directions.add(new int[]{-1, -1});
    directions.add(new int[]{-1, 1});
    directions.add(new int[]{0, -1});
    directions.add(new int[]{0, 1});
    directions.add(new int[]{1, 0});
    directions.add(new int[]{1, -1});
    directions.add(new int[]{1, 1});

    int countAppearances = 0;
    for (int[] x : allXs) {
      for (int[] dir : directions) {
        if (!directionGoesOutOfBounds(x, dir, grid)) {
          if (grid[x[0]][x[1]].equals("X") &&
              grid[x[0] + dir[0]][x[1] + dir[1]].equals("M") &&
              grid[x[0] + dir[0] + dir[0]][x[1] + dir[1] + dir[1]].equals("A") &&
              grid[x[0] + dir[0] + dir[0] + dir[0]][x[1] + dir[1] + dir[1] + dir[1]].equals("S")) {
            countAppearances++;
          }
        }
      }
    }
    log.info(countAppearances);
  }

  private boolean directionGoesOutOfBounds(int[] x, int[] dir, String[][] grid) {
    int totalRow = x[0] + dir[0] + dir[0] + dir[0];
    int totalCol = x[1] + dir[1] + dir[1] + dir[1];
    return totalRow < 0 || totalRow >= grid.length ||
        totalCol < 0 || totalCol >= grid.length;
  }

  private List<int[]> findAllChars(String[][] grid, String charToFind) {
    List<int[]> nums = new ArrayList<>();
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid.length; c++) {
        if (grid[r][c].equals(charToFind)) {
          nums.add(new int[]{r, c});
        }
      }
    }
    return nums;
  }

  public void partTwo() {
    log.info("# Part 2 #");
    String[][] grid = inputToGrid(lines);
    List<int[]> allAs = findAllChars(grid, "A");

    int countAppearances = 0;
    for (int[] a : allAs) {
      if (!xWouldBeOutOfBounds(a, grid)){
        String tl = grid[a[0]-1][a[1]-1];
        String tr = grid[a[0]-1][a[1]+1];
        String bl = grid[a[0]+1][a[1]-1];
        String br = grid[a[0]+1][a[1]+1];
        String diag1= tl + br;
        String diag2=tr+bl;
        if (diag1.contains("M") && diag1.contains("S") &&
            diag2.contains("M") && diag2.contains("S")){
          countAppearances++;
        }
      }
    }
    log.info(countAppearances);
  }
  private boolean xWouldBeOutOfBounds(int[] a, String[][] grid) {
    int maxR = a[0] + 1;
    int minR = a[0] - 1;
    int maxC = a[1] + 1;
    int minC = a[1] - 1;
    return minR < 0 || maxR >= grid.length ||
        minC < 0 || maxC >= grid.length;
  }

  public void lore() {
    log.info(lore);
  }
}