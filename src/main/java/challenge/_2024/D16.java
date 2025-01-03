package challenge._2024;

import static base.utils.FileUtil.inputToGrid;

import base.Solution;
import base.utils.Coord;
import base.utils.Direction;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D16 extends Solution {

  private final Logger log = LogManager.getLogger(D16.class);

  private static final String INPUT_FILE = "2024/inputs/16.txt";
  private static final String LORE_FILE = "2024/lore/16.txt";

  private D16(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D16 solution = new D16(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();
    String[][] grid = inputToGrid(lines);
    Coord start = findChar(grid, "S");
    Coord end = findChar(grid, "E");

    int pathScore = findCheapestPath(grid, start, end);
    log.info("cheapest path: {}", pathScore);
  }

  private int findCheapestPath(String[][] grid, Coord start, Coord end) {
    State startState = new State(start, Direction.E);

    Map<State, Integer> distances = new HashMap<>();
    distances.put(startState, 0);

    PriorityQueue<State> priorityQueue = new PriorityQueue<>(
        Comparator.comparingInt(State::getCost));
    priorityQueue.add(new State(start, Direction.E));

    Set<Coord> visited = new HashSet<>();
    while (!priorityQueue.isEmpty()) {
      State curr = priorityQueue.poll();
      log.info("checking: {} {}", curr.getCoord(), curr.getCost());

      if (visited.contains(curr.getCoord())) {
        continue;
      }
      visited.add(curr.getCoord());

      if (curr.getCoord().equals(end)) {
        return distances.get(curr);
      }

      for (Direction dir : Direction.CARDINAL_DIRECTIONS) {
        Coord next = curr.getCoord().relative(dir);
        if (!isCoordAWallOrOutOfBounds(grid, next) && !visited.contains(next)) {

          int newDistance = distances.get(curr) + 1;
          if (!curr.getDirection().equals(dir)) {
            newDistance += 1000;
          }

          if (newDistance < distances.getOrDefault(new State(next, dir), Integer.MAX_VALUE)) {
            State nextState = new State(next, dir);
            nextState.setCost(newDistance);
            distances.put(nextState, newDistance);
            priorityQueue.add(nextState);
          }
        }
      }
    }
    return -1;
  }

  private boolean isCoordInInGrid(String[][] grid, Coord coord) {
    return !(coord.r() < 0 || coord.r() >= grid.length ||
        coord.c() < 0 || coord.c() >= grid.length);
  }

  private boolean isCoordAWallOrOutOfBounds(String[][] grid, Coord coord) {
    if (isCoordInInGrid(grid, coord)) {
      return "#".equals(grid[coord.r()][coord.c()]);
    } else {
      return true;
    }
  }

  public void partTwo() {
    log.info("# Part 2 #");

  }

  private Coord findChar(String[][] grid, String charToFind) {
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid.length; c++) {
        if (grid[r][c].equals(charToFind)) {
          return new Coord(r, c);
        }
      }
    }
    return null;
  }

  public static class State {

    Coord coord;
    Direction direction;
    int cost;

    public State(Coord coord, Direction direction) {
      this.coord = coord;
      this.direction = direction;
    }

    public Coord getCoord() {
      return coord;
    }

    public void setCoord(Coord coord) {
      this.coord = coord;
    }

    public Direction getDirection() {
      return direction;
    }

    public void setDirection(Direction direction) {
      this.direction = direction;
    }

    public int getCost() {
      return cost;
    }

    public void setCost(int cost) {
      this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      State state = (State) o;
      return Objects.equals(coord, state.coord) && Objects.equals(direction, state.direction);
    }

    @Override
    public int hashCode() {
      return Objects.hash(coord, direction, cost);
    }
  }

  public void lore() {
    log.info(lore);
  }
}