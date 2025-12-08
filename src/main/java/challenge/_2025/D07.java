package challenge._2025;

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

public class D07 extends Solution {

  private final Logger log = LogManager.getLogger(D07.class);

  private static final String INPUT_FILE = "2025/inputs/07.txt";
  private static final String LORE_FILE = "2025/lore/07.txt";

  private D07(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D07 solution = new D07(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();

    String[][] grid = inputToGrid(lines);
    Set<Coord> beams = new HashSet<>();
    beams.add(findStart(grid));

    int splitCount = 0;

    for (int i = 0; i < lines.size() - 1; i++) {
      Set<Coord> updatedBeams = new HashSet<>();
      for (Coord beam : beams) {
        if (isSplitter(grid, beam.relative(Direction.S))) {
          splitCount++;
          updatedBeams.add(beam.relative(Direction.SW));
          updatedBeams.add(beam.relative(Direction.SE));
        } else {
          updatedBeams.add(beam.relative(Direction.S));
        }
      }
      beams = updatedBeams;
    }
    log.info("Splits: {}", splitCount);
  }

  public void partTwo() {
    log.info("# Part 2 #");

    String[][] grid = inputToGrid(lines);
    List<Coord> beams = new ArrayList<>();
    beams.add(findStart(grid));

    long timelines = 0L;

    for (int i = 0; i < lines.size() - 1; i++) {
      List<Coord> updatedBeams = new ArrayList<>();
      for (Coord beam : beams) {
        if (isSplitter(grid, beam.relative(Direction.S))) {
          timelines++;
          updatedBeams.add(beam.relative(Direction.SW));
          updatedBeams.add(beam.relative(Direction.SE));
        } else {
          updatedBeams.add(beam.relative(Direction.S));
        }
      }
      beams = updatedBeams;
    }
    log.info("Timelines: {}", timelines + 1);
  }

  private Coord findStart(String[][] grid) {
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid.length; c++) {
        if (grid[r][c].equals("S")) {
          return new Coord(r, c);
        }
      }
    }
    return null;
  }

  private boolean isSplitter(String[][] grid, Coord c) {
    return grid[c.r()][c.c()].equals("^");
  }


  public void lore() {
    log.info(lore);
  }
}