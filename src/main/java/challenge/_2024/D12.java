package challenge._2024;

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

public class D12 extends Solution {

  private final Logger log = LogManager.getLogger(D12.class);

  private static final String INPUT_FILE = "2024/inputs/12.txt";
  private static final String LORE_FILE = "2024/lore/12.txt";

  private D12(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D12 solution = new D12(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();
    String[][] grid = inputToGrid(lines);
    List<List<Coord>> fields = getAllFields(grid);

    int priceForFields = fields.stream()
        .mapToInt(f -> priceForField(grid, f))
        .sum();

    log.info(priceForFields);
  }

  private int priceForField(String[][] grid, List<Coord> field) {
    int price = 0;
    Coord first = field.get(0);
    String type = grid[first.r()][first.c()];
    for (Coord coord : field) {
      for (Direction dir : Direction.CARDINAL_DIRECTIONS) {
        Coord next = coord.relative(dir);
        if (isCoordOffTheGrid(grid, next) || !grid[next.r()][next.c()].equals(type)) {
          price++;
        }
      }
    }
    return price * field.size();
  }

  public void partTwo() {
    log.info("# Part 2 #");
    String[][] grid = inputToGrid(lines);
    List<List<Coord>> fields = getAllFields(grid);

    int priceForFields = fields.stream()
        .mapToInt(f -> bulkDiscountPriceForField(grid, f))
        .sum();
    log.info(priceForFields);
    // ignore fields, price fences by row / col
  }

  private int bulkDiscountPriceForField(String[][] grid, List<Coord> field) {
    int price = 0;


    // Tricky shape:
    //  ..A..
    //  A.A.A
    //  AAAAA
    for (int r = 0; r < grid.length; r++) {
      List<Coord> inRow = new ArrayList<>();
      for (Coord f : field) {
        if (f.r() == 0){
          inRow.add(f);
        }
      }

    }

    return price * field.size();
  }

  private List<List<Coord>> getAllFields(String[][] grid) {
    List<List<Coord>> fields = new ArrayList<>();
    Set<Coord> visited = new HashSet<>();

    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[0].length; c++) {
        Coord startingField = new Coord(r, c);
        if (!visited.contains(startingField)) {
          List<Coord> fullField = dfs(grid, visited, startingField, grid[r][c]);
          if (!fullField.isEmpty()) {
            fields.add(fullField);
          }
        }
      }
    }
    return fields;
  }

  private List<Coord> dfs(String[][] grid, Set<Coord> visited, Coord field, String type) {
    if (!isValidCoord(grid, visited, field, type)) {
      return new ArrayList<>();
    }
    visited.add(field);

    List<Coord> allField = new ArrayList<>();
    allField.add(field);

    for (Direction dir : Direction.CARDINAL_DIRECTIONS) {
      allField.addAll(dfs(grid, visited, field.relative(dir), type));
    }
    return allField;
  }

  private boolean isValidCoord(String[][] grid, Set<Coord> visited, Coord coord, String type) {
    return !isCoordOffTheGrid(grid, coord) &&
        !visited.contains(coord) &&
        grid[coord.r()][coord.c()].equals(type);
  }

  private boolean isCoordOffTheGrid(String[][] grid, Coord coord) {
    return (coord.r() < 0 || coord.r() >= grid.length ||
        coord.c() < 0 || coord.c() >= grid.length);
  }

  public void lore() {
    log.info(lore);
  }
}