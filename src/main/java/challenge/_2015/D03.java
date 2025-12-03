package challenge._2015;

import base.Solution;
import base.utils.Coord;
import base.utils.Direction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D03 extends Solution {

  private final Logger log = LogManager.getLogger(D03.class);

  private static final String INPUT_FILE = "2015/inputs/03.txt";
  private static final String LORE_FILE = "2015/lore/03.txt";

  private D03(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D03 solution = new D03(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    List<String> instructions = Arrays.stream(wholeLines.split("")).toList();
    Set<Coord> giftedHouses = new HashSet<>();

    Coord current = new Coord(0, 0);
    giftedHouses.add(current);

    for (String instruction : instructions) {
      Direction dir = Direction.fromArrow(instruction);
      if (dir == null) {
        continue;
      }
      current = current.relative(dir);
      giftedHouses.add(current);
    }
    log.info(giftedHouses.size());
  }

  public void partTwo() {
    log.info("# Part 2 #");
    List<String> instructions = Arrays.stream(wholeLines.split("")).toList();
    Set<Coord> giftedHouses = new HashSet<>();

    List<Coord> deliverySantas = new ArrayList<>();
    deliverySantas.add(new Coord(0, 0));
    deliverySantas.add(new Coord(0, 0));

    giftedHouses.add(new Coord(0, 0));

    boolean flip = false;

    for (String instruction : instructions) {
      Direction dir = Direction.fromArrow(instruction);
      if (dir == null) {
        continue;
      }
      if (!flip){
        Coord nextStop = deliverySantas.get(0).relative(dir);
        deliverySantas.set(0, nextStop);
        giftedHouses.add(nextStop);
      } else {
        Coord nextStop = deliverySantas.get(1).relative(dir);
        deliverySantas.set(1, nextStop);
        giftedHouses.add(nextStop);
      }
      flip = !flip;
    }
    log.info(giftedHouses.size());
  }

  @Override
  public void lore() {
    log.info(lore);
  }
}