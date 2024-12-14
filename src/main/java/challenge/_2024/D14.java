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
import java.util.logging.Handler;
import java.util.stream.Collectors;
import javax.sound.sampled.spi.AudioFileReader;
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
    int gridWidth = 11;
    int gridHeight = 7;

    List<Map<Coord, Direction>> robots = lines.stream()
        .map(this::parseIntoRobot)
        .toList();

    robots.forEach(this::logRobot);
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

  public void partTwo() {
    log.info("# Part 2 #");

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