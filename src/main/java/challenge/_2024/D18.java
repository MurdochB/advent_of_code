package challenge._2024;

import base.Solution;
import base.utils.Coord;
import base.utils.Pair;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D18 extends Solution {

  private final Logger log = LogManager.getLogger(D18.class);

  private static final String INPUT_FILE = "2024/inputs/18.txt";
  private static final String LORE_FILE = "2024/lore/18.txt";

  private D18(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D18 solution = new D18(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();
    List<Coord> coordsInput = parseToCoords();
    Pair<Integer> rowAndColMax = findRowAndColMax(coordsInput);
    String[][] grid = new String[rowAndColMax.getLeft()][rowAndColMax.getRight()];

    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[0].length; c++) {
        grid[r][c] = ".";
      }
    }
    //for (int i = 0; i < 1024 - 1; i++) {
    for (int i = 0; i < 12 - 1; i++) {
      Coord c = coordsInput.get(i);
      grid[c.r()][c.c()] = "#";
    }
    printGrid(grid);

    log.info(rowAndColMax.getLeft() + " " + rowAndColMax.getRight());

  }

  public void partTwo() {
    log.info("# Part 2 #");

  }

  private List<Coord> parseToCoords(){
    List<Coord> coords = new ArrayList<>();
    for (String line : lines) {
      String[] split = line.split(",");
      coords.add(new Coord(Integer.parseInt(split[1]), Integer.parseInt(split[0])));
    }
    return coords;
  }

  private Pair<Integer> findRowAndColMax(List<Coord> coords){
    int rowMax = 0;
    int colMax = 0;
    for (Coord coord : coords) {
      rowMax = Math.max(rowMax, coord.r());
      colMax = Math.max(colMax, coord.c());
    }
    return new Pair<>(rowMax +1, colMax +1);
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