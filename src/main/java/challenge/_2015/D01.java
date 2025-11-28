package challenge._2015;

import base.Solution;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D01 extends Solution {

  private final Logger log = LogManager.getLogger(D01.class);

  private static final String INPUT_FILE = "2015/inputs/01.txt";
  private static final String LORE_FILE = "2015/lore/01.txt";

  private D01(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D01 solution = new D01(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    int upInstructions = wholeLines.split("\\(").length;
    int downInstructions = wholeLines.split("\\)").length;
    int floorEnd = upInstructions - downInstructions;
    log.info("Floor {}", floorEnd);
  }

  public void partTwo() {
    log.info("# Part 2 #");
    List<String> instructions = Arrays.stream(wholeLines.split("")).toList();
    int index = 0;
    int curFloor = 0;

    while (curFloor >= 0) {
      curFloor = changeFloor(curFloor, instructions.get(index));
      index++;
    }
    log.info("Instruction {} sends us to the basement", index);
  }

  private int changeFloor(int currentFloor, String instruction) {
    return instruction.equals("(") ? currentFloor + 1 : currentFloor - 1;
  }

  @Override
  public void lore() {
    log.info(lore);
  }
}