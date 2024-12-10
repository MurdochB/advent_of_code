package challenge._2024;

import static base.utils.FileUtil.inputToGrid;

import base.Solution;
import base.utils.Coord;
import java.util.ArrayList;
import java.util.List;
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
      totalScore += scoreTrailhead(trailhead);
    }
    log.info("Total score: " + totalScore);
  }

  private int scoreTrailhead(Coord trailhead) {

    return 1;
  }

  public void partTwo() {
    log.info("# Part 2 #");
  }

  private List<Coord> findTrailheads(String[][] grid) {
    List<Coord> trailheads = new ArrayList<>();
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid.length; c++) {
        if(grid[r][c].equals("0")){
          trailheads.add(new Coord(r, c));
        }
      }
    }
    return trailheads;
  }

  public void lore() {
    log.info(lore);
  }
}