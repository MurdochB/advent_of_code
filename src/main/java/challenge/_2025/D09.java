package challenge._2025;

import base.Solution;
import base.utils.Coord;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D09 extends Solution {

  private final Logger log = LogManager.getLogger(D09.class);

  private static final String INPUT_FILE = "2025/inputs/09.txt";
  private static final String LORE_FILE = "2025/lore/09.txt";

  private D09(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D09 solution = new D09(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();

    List<Coord> coords = inputToCoords(lines);
    long maxArea = 0;
    for (Coord a : coords) {
      for (Coord b : coords) {
        maxArea = Math.max(maxArea, calculateArea(a, b));
      }
    }
    log.info(maxArea);
  }

  public void partTwo() {
    log.info("# Part 2 #");

  }

  private long calculateArea(Coord a, Coord b) {
    long row = Math.abs(a.r() - b.r()) + 1L;
    long col = Math.abs(a.c() - b.c()) + 1L;
    return row * col;
  }

  private List<Coord> inputToCoords(List<String> in) {
    List<Coord> coords = new ArrayList<>();
    for (String s : in) {
      String[] split = s.split(",");
      coords.add(new Coord(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
    }
    return coords;
  }

  public void lore() {
    log.info(lore);
  }
}