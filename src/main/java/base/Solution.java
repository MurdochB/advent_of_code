package base;

import base.utils.FileUtil;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Solution {

  private static final Logger log = LogManager.getLogger(Solution.class);

  private static final String FINISH_MSG = "Part {} took: {} seconds.";
  private static final float NANOS = 1000 * 1000 * 1000f;

  protected List<String> lines;
  protected String lore;

  protected Solution(String inputFile, String loreFile) {
    lines = FileUtil.readLines(inputFile);
    lore = FileUtil.readWholeFile(loreFile);
  }

  public void run() {
    long startTime = System.nanoTime();

    partOne();
    long partOneEndTime = System.nanoTime();

    partTwo();
    long partTwoEndTime = System.nanoTime();

    log.info(FINISH_MSG, "one", (partOneEndTime - startTime) / NANOS);
    log.info(FINISH_MSG, "two", (partTwoEndTime - partOneEndTime) / NANOS);
    log.info(FINISH_MSG, "total", (partTwoEndTime - startTime) / NANOS);
  }

  public abstract void partOne();

  public abstract void partTwo();

  public abstract void lore();
}