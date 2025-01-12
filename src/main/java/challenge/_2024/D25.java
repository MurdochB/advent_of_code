package challenge._2024;

import base.Solution;
import base.utils.SplitPair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D25 extends Solution {

  private final Logger log = LogManager.getLogger(D25.class);

  private static final String INPUT_FILE = "2024/inputs/25.txt";
  private static final String LORE_FILE = "2024/lore/25.txt";

  private D25(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D25 solution = new D25(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();
    List<SplitPair<String, int[]>> lockOrKeys = new ArrayList<>();

    List<String> nextLockOrKey = new ArrayList<>();
    for (String line : lines) {
      if (line.isEmpty()) {
        lockOrKeys.add(buildLockOrKey(nextLockOrKey));
        nextLockOrKey = new ArrayList<>();
      } else {
        nextLockOrKey.add(line);
      }
    }
    lockOrKeys.add(buildLockOrKey(nextLockOrKey));
    List<int[]> locks = new ArrayList<>();
    List<int[]> keys = new ArrayList<>();
    for (SplitPair<String, int[]> lockOrKey : lockOrKeys) {
      if (lockOrKey.getLeft().equals("L")){
        locks.add(lockOrKey.getRight());
      } else {
        keys.add(lockOrKey.getRight());
      }
    }
    log.info("LOCKS:");
    for (int[] lock : locks) {
      log.info(Arrays.toString(lock));
    }
    log.info("KEYS:");
    for (int[] key : keys) {
      log.info(Arrays.toString(key));
    }

    long count = 0;
    for (int[] lock : locks) {
      for (int[] key : keys) {
        boolean keyFits = true;
        for (int i = 0; i < lock.length; i++) {
          if (key[i] > lock[i]) {
            keyFits = false;
            break;
          }
        }
        if (keyFits)
          count++;
      }
    }
    log.info(count);


  }

  private SplitPair<String, int[]> buildLockOrKey(List<String> lockOrKey) {
    int[] pins = new int[]{-1, -1, -1, -1, -1};
    if (lockOrKey.get(0).equals("#####")) {
      // lock
      for (int i = 1; i < lockOrKey.size(); i++) {
        String[] thisRow = lockOrKey.get(i).split("");
        for (int r = 0; r < thisRow.length; r++) {
          if (thisRow[r].equals(".") && pins[r] == -1){
              pins[r] = 6 - i;
            }
        }
      }
      SplitPair<String, int[]> lock = new SplitPair<>("L", pins);
      return lock;
    } else {
      // key
      for (int i = 1; i < lockOrKey.size(); i++) {
        String[] thisRow = lockOrKey.get(i).split("");
        for (int r = 0; r < thisRow.length; r++) {
          if (thisRow[r].equals("#") && pins[r] == -1){
            pins[r] = 6 - i;
          }
        }
      }
      SplitPair<String, int[]> key = new SplitPair<>("K", pins);
      return key;
    }
  }

  public void partTwo() {
    log.info("# Part 2 #");

  }

  public void lore() {
    log.info(lore);
  }
}