package challenge._2025;

import base.Solution;
import base.utils.Pair;
import base.utils.SplitPair;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D05 extends Solution {

  private final Logger log = LogManager.getLogger(D05.class);

  private static final String INPUT_FILE = "2025/inputs/05.txt";
  private static final String LORE_FILE = "2025/lore/05.txt";

  private D05(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D05 solution = new D05(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();
    SplitPair<List<Pair<Long>>, List<Long>> in = parseInput(lines);
    List<Pair<Long>> freshRanges = in.getLeft();
    List<Long> available = in.getRight();

    long freshIngrediants = 0L;
    for (Long ingredient : available) {
      for (Pair<Long> freshRange : freshRanges) {
        if (ingredient >= freshRange.getLeft() && ingredient <= freshRange.getRight()) {
          freshIngrediants++;
          break;
        }
      }
    }
    log.info("Fresh ingredients: {}", freshIngrediants);
  }

  public void partTwo() {
    log.info("# Part 2 #");

    SplitPair<List<Pair<Long>>, List<Long>> in = parseInput(lines);
    List<Pair<Long>> allFreshRanges = in.getLeft();
    List<Pair<Long>> minimisedRanges = new ArrayList<>();

    for (Pair<Long> incoming : allFreshRanges) {
      List<Pair<Long>> newMinimisedRange = new ArrayList<>();
      for (Pair<Long> m : minimisedRanges) {
        // this range:  [ ----- ]           [ ---- ]
        // m         :             [ ---- ]
        if (incoming.getRight() < m.getLeft() ||
            incoming.getLeft() > m.getRight()) {
          // outside this minimised range, maybe still add it
          newMinimisedRange.add(m);
        } else {
          // this range:       [ ----- ]
          // m         :            [ ------ ]
          incoming = new Pair<>(Math.min(incoming.getLeft(), m.getLeft()),
              Math.max(incoming.getRight(), m.getRight()));
        }
      }
      newMinimisedRange.add(incoming);
      minimisedRanges = newMinimisedRange;
    }
    long grandTotal = 0L;
    for (Pair<Long> r : minimisedRanges) {
      grandTotal += (r.getRight() - r.getLeft()) + 1;
    }

    log.info("Fresh IDs: {}", grandTotal);

  }

  private SplitPair<List<Pair<Long>>, List<Long>> parseInput(List<String> lines) {
    List<Pair<Long>> freshRanges = new ArrayList<>();
    List<Long> available = new ArrayList<>();
    boolean flip = false;
    for (String line : lines) {
      if (line.isEmpty()) {
        flip = true;
        continue;
      }
      if (!flip) {
        String[] split = line.split("-");
        freshRanges.add(new Pair<>(Long.parseLong(split[0]), Long.parseLong(split[1])));
      } else {
        available.add(Long.parseLong(line));
      }
    }

    return new SplitPair<>(freshRanges, available);
  }

  public void lore() {
    log.info(lore);
  }
}