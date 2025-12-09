package challenge._2025;

import base.Solution;
import base.utils.Pair;
import base.utils.SplitPair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D01 extends Solution {

  private final Logger log = LogManager.getLogger(D01.class);

  private static final String INPUT_FILE = "2025/inputs/01.txt";
  private static final String LORE_FILE = "2025/lore/01.txt";

  private static final String OUTPUT = "Zeroes count: {}";

  private D01(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D01 solution = new D01(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();
    Pair<Integer> dialAndZeroes = new Pair<>(50, 0);

    lines.forEach(l -> processLine(dialAndZeroes, l));

    log.info(OUTPUT, dialAndZeroes.getRight());
  }

  public void partTwo() {
    log.info("# Part 2 #");
    Pair<Integer> dialAndZeroes = new Pair<>(50, 0);

    lines.forEach(l -> processLinePt2(dialAndZeroes, l));

    log.info(OUTPUT, dialAndZeroes.getRight());
  }

  private void processLine(Pair<Integer> dialAndZeroes, String line) {
    // parse step into ["L/R operation", rotation count]
    SplitPair<String, Integer> step = parseStep(line);
    int val = step.getRight() % 100;
    int dial = dialAndZeroes.getLeft();

    if (step.getLeft().equals("R")) {
      dial += val;
    } else {
      dial -= val;
    }
    dial = normaliseDial(dial);
    dialAndZeroes.setLeft(dial);
    if (dial == 0) {
      dialAndZeroes.setRight(dialAndZeroes.getRight() + 1);
    }
  }

  private void processLinePt2(Pair<Integer> dialAndZeroes, String line) {
    // parse step into ["L/R operation", rotation count]
    SplitPair<String, Integer> step = parseStep(line);
    int val = step.getRight() % 100;
    int magnitude = step.getRight() / 100;
    dialAndZeroes.setRight(dialAndZeroes.getRight() + magnitude);

    int dial = dialAndZeroes.getLeft();
    int prevDial = dial;

    if (step.getLeft().equals("R")) {
      dial += val;
    } else {
      dial -= val;
    }
    Pair<Integer> newDial = normaliseAndCountCrosses(prevDial, dial);

    dialAndZeroes.setLeft(newDial.getLeft());
    dialAndZeroes.setRight(dialAndZeroes.getRight() + newDial.getRight());
  }

  private SplitPair<String, Integer> parseStep(String line) {
    // Could actually parse this into just the change to make:
    // E.g R10 = 10, L10 = -10
    String op = line.substring(0, 1);
    int val = Integer.parseInt(line.substring(1));

    return new SplitPair<>(op, val);
  }

  private int normaliseDial(int dial) {
    if (dial < 0) {
      return 100 + dial;
    }
    return dial % 100;
  }

  private Pair<Integer> normaliseAndCountCrosses(int prevDial, int dial) {
    if (dial < 0) {
      return new Pair<>(100 + dial, (prevDial == 0) ? 0 : 1);
    } else if (dial > 100) {
      dial %= 100;
      return new Pair<>(dial, 1);
    } else if (dial == 100 || dial == 0) {
      return new Pair<>(0, 1);
    }
    return new Pair<>(dial, 0);
  }

  public void lore() {
    log.info(lore);
    // Hard coded answers:
    // Part 1: 1007
    // Part 2: 5820
  }
}