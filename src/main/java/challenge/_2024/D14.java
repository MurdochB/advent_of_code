package challenge._2024;

import base.Solution;
import base.utils.Coord;
import base.utils.Direction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D14 extends Solution {

  private final Logger log = LogManager.getLogger(D14.class);

  private static final String INPUT_FILE = "2024/inputs/14.txt";
  private static final String LORE_FILE = "2024/lore/14.txt";

  private D14(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D14 solution = new D14(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();
    int steps = 100;
    int gridWidth = 101;
    int gridHeight = 103;

    List<Map<Coord, Direction>> robots = lines.stream()
        .map(this::parseIntoRobot)
        .toList();

    for (int i = 0; i < steps; i++) {
      List<Map<Coord, Direction>> newRobots = new ArrayList<>();
      for (Map<Coord, Direction> robot : robots) {
        newRobots.add(stepRobot(robot, gridWidth, gridHeight));
      }
      robots = newRobots;
    }
    int safety = calculateSafety(robots, gridWidth, gridHeight);
    log.info(safety);
  }

  private int calculateSafety(List<Map<Coord, Direction>> robots, int gridWidth, int gridHeight) {
    Map<Direction, Integer> quads = new HashMap<>();

    Coord center = new Coord(((gridWidth - 1) / 2), ((gridHeight - 1) / 2));
    for (Map<Coord, Direction> robot : robots) {
      for (Entry<Coord, Direction> entry : robot.entrySet()) {
        quads.merge(Direction.normalise(center.distance(entry.getKey())), 1, Integer::sum);
      }
    }

    return quads.get(Direction.NW) * quads.get(Direction.NE) * quads.get(Direction.SW) * quads.get(
        Direction.SE);
  }

  private Map<Coord, Direction> stepRobot(Map<Coord, Direction> r, int gridWidth, int gridHeight) {
    Map<Coord, Direction> newRobot = new HashMap<>();
    Set<Entry<Coord, Direction>> entries = r.entrySet();
    for (Entry<Coord, Direction> entry : entries) {
      Coord stepped = entry.getKey().relative(entry.getValue());
      if (stepped.c() < 0) {
        stepped.setC(stepped.c() + gridHeight);
      }
      if (stepped.c() > gridHeight - 1) {
        stepped.setC(stepped.c() - gridHeight);
      }
      if (stepped.r() < 0) {
        stepped.setR(stepped.r() + gridWidth);
      }
      if (stepped.r() > gridWidth - 1) {
        stepped.setR(stepped.r() - gridWidth);
      }
      newRobot.put(stepped, entry.getValue());
    }
    return newRobot;
  }


  public void partTwo() {
    log.info("# Part 2 #");
    int gridWidth = 101;
    int gridHeight = 103;

    List<Map<Coord, Direction>> robots = lines.stream()
        .map(this::parseIntoRobot)
        .toList();

    int steps = 0;
    while (!findOverlaps(robots).isEmpty()) {
      steps++;
      List<Map<Coord, Direction>> newRobots = new ArrayList<>();
      for (Map<Coord, Direction> robot : robots) {
        newRobots.add(stepRobot(robot, gridWidth, gridHeight));
      }
      robots = newRobots;
    }
    logRobots(robots, gridWidth, gridHeight);
    log.info("Steps taken to find tree: {}", steps);
  }

  private List<Coord> findOverlaps(List<Map<Coord, Direction>> robots) {
    Map<Coord, Integer> overlaps = new HashMap<>();
    for (Map<Coord, Direction> robot : robots) {
      for (Coord coord : robot.keySet()) {
        overlaps.merge(coord, 1, Integer::sum);
      }
    }
    List<Coord> overlapsToReturn = new ArrayList<>();
    for (Entry<Coord, Integer> over : overlaps.entrySet()) {
      if (over.getValue() > 1) {
        overlapsToReturn.add(over.getKey());
      }
    }
    return overlapsToReturn;
  }

  private Map<Coord, Direction> parseIntoRobot(String line) {
    Map<Coord, Direction> robot = new HashMap<>();
    String[] split = line.split(" ");
    String[] startPos = split[0].substring(2).split(",");

    Coord robotStartCoord = new Coord(Integer.parseInt(startPos[0]), Integer.parseInt(startPos[1]));
    String[] vel = split[1].substring(2).split(",");
    Direction dir = new Direction(Integer.parseInt(vel[0]), Integer.parseInt(vel[1]));
    robot.put(robotStartCoord, dir);
    return robot;
  }

  private void logRobots(List<Map<Coord, Direction>> robots, int width, int height) {
    List<Coord> allRobots = new ArrayList<>();
    for (Map<Coord, Direction> robot : robots) {
      allRobots.addAll(robot.keySet());
    }
    for (int h = 0; h < height; h++) {
      StringBuilder sb = new StringBuilder();
      for (int w = 0; w < width; w++) {
        if (allRobots.contains(new Coord(w, h))) {
          sb.append("#");
        } else {
          sb.append(".");
        }
      }
      log.info(sb);
    }
  }

  private void logRobot(Map<Coord, Direction> robot) {
    Set<Entry<Coord, Direction>> entries = robot.entrySet();
    for (Entry<Coord, Direction> entry : entries) {
      Coord point = entry.getKey();
      Direction dir = entry.getValue();
      log.info(point.r() + "," + point.c() + " |: " + dir.r() + "," + dir.c());
    }
  }

  public void lore() {
    log.info(lore);
  }
}