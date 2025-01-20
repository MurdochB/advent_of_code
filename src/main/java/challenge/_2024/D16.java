package challenge._2024;

import static base.utils.FileUtil.inputToGrid;

import base.Solution;
import base.utils.Coord;
import base.utils.Direction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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

    Set<State> visited = new HashSet<>();
    while (!priorityQueue.isEmpty()) {
      State curr = priorityQueue.poll();

      if (visited.contains(curr)) {
        continue;
      }
      visited.add(curr);

      if (curr.getCoord().equals(end)) {
        return distances.get(curr);
      }

      for (Direction dir : Direction.CARDINAL_DIRECTIONS) {
        Coord next = curr.getCoord().relative(dir);
        if (!isCoordAWallOrOutOfBounds(grid, next) && !visited.contains(new State(next, dir))) {
          int newDistance = distances.get(curr) + 1;
          if (!curr.getDirection().equals(dir)) {
            newDistance += 1000;
          }
          if (newDistance < distances.getOrDefault(new State(next, dir), Integer.MAX_VALUE)) {
            State nextState = new State(next, dir);
            nextState.setCost(newDistance);
            nextState.setPrev(curr);
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
        coord.c() < 0 || coord.c() >= grid[0].length);
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
    String[][] grid = inputToGrid(lines);
    Coord start = findChar(grid, "S");
    Coord end = findChar(grid, "E");

    List<State> cheap = findAllCheapPaths(grid, start, end);
    log.info("----------------------");
    log.info(cheap.size());
    for (State state : cheap) {
      state.printState(grid);
    }
    Set<Coord> coordsOnBestPath = new HashSet<>();
    for (State state : cheap) {
      coordsOnBestPath.addAll(state.getCoordsInPath());
    }
    log.info(coordsOnBestPath.size());
  }

  private List<State> findAllCheapPaths(String[][] grid, Coord start, Coord end) {
    State startState = new State(start, Direction.W);

    Map<State, Integer> distances = new HashMap<>();
    distances.put(startState, 0);

    PriorityQueue<State> priorityQueue = new PriorityQueue<>(
        Comparator.comparingInt(State::getCost));
    priorityQueue.add(startState);

    Set<State> visited = new HashSet<>();
    List<State> cheapestPaths = new ArrayList<>();
    int bestPathSize = Integer.MAX_VALUE;
    while (!priorityQueue.isEmpty()) {
      printPrioQ(priorityQueue);
      State curr = priorityQueue.poll();
      log.info("checking {} cost {} dir {}", curr.getCoord(), curr.getCost(),
          Direction.quickString(curr.getDirection()));
      printCoordInGrid(grid, curr.getCoord(), curr.getDirection());
      if (curr.getCost() > bestPathSize) {
        continue;
      }

      if (curr.getCoord().equals(end)) {
        log.info("found the end");
        if (distances.get(curr) <= bestPathSize) {
          bestPathSize = distances.get(curr);
          cheapestPaths.add(curr);
        }
        continue;
      }

      if (visited.contains(curr)) {
        continue;
      }
      visited.add(curr);

      for (Direction dir : Direction.CARDINAL_DIRECTIONS) {
        if (!dir.equals(Direction.flip(curr.getDirection()))) {
          Coord next = curr.getCoord().relative(dir);
          if (!isCoordAWallOrOutOfBounds(grid, next)) {
            int newDistance = distances.get(curr) + 1;
            if (!curr.getDirection().equals(dir)) {
              newDistance += 1000;
            }
            if (newDistance <= checkDistanceForCoord(next, dir, distances)) {
              log.info("next potential cold be {} {} {}", next, Direction.quickString(dir),
                  newDistance);

              State nextState = new State(next, dir);
              if (priorityQueue.contains(nextState)){
                // merge the two states to share the visited nodes
              }
              nextState.setCost(newDistance);
              nextState.setPrev(curr);
              nextState.addVisited(curr.getCoord());
              distances.put(nextState, newDistance);
              priorityQueue.add(nextState);
            }
          }
        }
      }
      log.info("checked all other directions...");
    }
    return cheapestPaths;
  }

  private int checkDistanceForCoord(Coord next, Direction dir, Map<State, Integer> distances) {
    int lowestCost = Integer.MAX_VALUE;
    for (State state : distances.keySet()) {
      if (next.equals(state.getCoord()) && state.getDirection().equals(dir)) {
        if (state.getCost() < lowestCost) {
          lowestCost = state.getCost();
        }
      }
    }
    return lowestCost;
  }

  private Coord findChar(String[][] grid, String charToFind) {
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[0].length; c++) {
        if (grid[r][c].equals(charToFind)) {
          return new Coord(r, c);
        }
      }
    }
    return null;
  }

  public static class State {

    private final Logger log = LogManager.getLogger(State.class);

    Set<Coord> visited;
    State prev;
    Coord coord;
    Direction direction;
    int cost;

    public State(Coord coord, Direction direction) {
      this.coord = coord;
      this.direction = direction;
      this.visited = new HashSet<>();
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

    public State getPrev() {
      return prev;
    }

    public void setPrev(State prev) {
      this.prev = prev;
    }

    public void printState(String[][] grid) {
      List<Coord> steps = new ArrayList<>();
      steps.add(coord);
      State tmpPrev = prev;
      while (tmpPrev != null) {
        steps.add(tmpPrev.getCoord());
        tmpPrev = tmpPrev.getPrev();
      }
      for (int r = 0; r < grid.length; r++) {
        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < grid[0].length; c++) {
          if (steps.contains(new Coord(r, c))) {
            sb.append("o");
          } else {
            sb.append(grid[r][c]);
          }
        }
        log.info(sb);
      }
    }

    public List<Coord> getCoordsInPath() {
      List<Coord> steps = new ArrayList<>();
      steps.add(coord);
      State tmpPrev = prev;
      while (tmpPrev != null) {
        steps.add(tmpPrev.getCoord());
        tmpPrev = tmpPrev.getPrev();
      }
      return steps;
    }

    public void addVisited(Coord coord) {
      visited.add(coord);
    }

    public void mergeVisited(State otherState) {
      visited.addAll(otherState.visited);
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
      return Objects.hash(coord, direction);
    }

  }

  private void printPrioQ(PriorityQueue<State> priorityQueue) {
    StringBuilder sb = new StringBuilder();
    for (State item : priorityQueue) {
      sb.append("[").append(item.getCoord()).append(" ").append(item.getCost()).append(" ")
          .append(Direction.quickString(item.getDirection()))
          .append("] | ");
    }
    log.info("Q has: {}", sb);
  }

  private void printCoordInGrid(String[][] grid, Coord coord, Direction dir) {
    for (int r = 0; r < grid.length; r++) {
      StringBuilder sb = new StringBuilder();
      for (int c = 0; c < grid[0].length; c++) {
        if (coord.equals(new Coord(r, c))) {
          sb.append(Direction.quickString(dir));
        } else {
          sb.append(grid[r][c]);
        }
      }
      log.info(sb);
    }
  }

  public void lore() {
    log.info(lore);
  }
}