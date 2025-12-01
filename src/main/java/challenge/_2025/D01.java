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

  private static final String OUTPUT = "Zeros count: {}";

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
    Pair<Integer> dialAndZeros = new Pair<>(50, 0);

    lines.forEach(l -> processLine(dialAndZeros, l));

    log.info(OUTPUT, dialAndZeros.getRight());
  }

  public void partTwo() {
    log.info("# Part 2 #");
    Pair<Integer> dialAndZeros = new Pair<>(50, 0);

    lines.forEach(l -> processLinePt2(dialAndZeros, l));

    log.info(OUTPUT, dialAndZeros.getRight());
  }

  private void processLine(Pair<Integer> dialAndZeros, String line) {
    // parse step into ["L/R", 1-9999]
    SplitPair<String, Integer> step = parseStep(line);
    int val = step.getRight() % 100;

    int dial = dialAndZeros.getLeft();

    if (step.getLeft().equals("R")) {
      dial += val;
    } else {
      dial -= val;
    }
    dial = normaliseDial(dial);
    dialAndZeros.setLeft(dial);
    if (dial == 0) {
      dialAndZeros.setRight(dialAndZeros.getRight() + 1);
    }
  }

  private void processLinePt2(Pair<Integer> dialAndZeros, String line) {
    // parse step into ["L/R", 1-9999]
    SplitPair<String, Integer> step = parseStep(line);
    int val = step.getRight() % 100;

    int magnitude = step.getRight() / 100;
    dialAndZeros.setRight(dialAndZeros.getRight() + magnitude);

    int dial = dialAndZeros.getLeft();
    if (step.getLeft().equals("R")) {
      dial += val;
    } else {
      dial -= val;
    }
    Pair<Integer> newDial = normaliseAndCountCrosses(dial);

    dialAndZeros.setLeft(newDial.getLeft());
    dialAndZeros.setRight(dialAndZeros.getRight() + newDial.getRight());

    log.info(line + " |" + dialAndZeros.getLeft() + " " + dialAndZeros.getRight());
  }

  private SplitPair<String, Integer> parseStep(String line) {
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

  private Pair<Integer> normaliseAndCountCrosses(int dial) {
    if (dial < 0) {
      return new Pair<>(100 + dial, 1);
    } else if (dial >= 100) {
      dial %= 100;
      return new Pair<>(dial, 1);
    }
    return new Pair<>(dial, 0);
  }

  /*
  2025-12-01 09:34:51,722 INFO  Zero count: 1007
  2025-12-01 09:34:51,722 INFO  # Part 2 #
  2025-12-01 09:34:51,723 INFO  Zero count: 5820
   */
  public void lore() {
    log.info(lore);
  }
}