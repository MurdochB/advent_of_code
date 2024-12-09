package challenge._2024;

import base.Solution;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D09 extends Solution {

  private final Logger log = LogManager.getLogger(D09.class);

  private static final String INPUT_FILE = "2024/inputs/09.txt";
  private static final String LORE_FILE = "2024/lore/09.txt";

  private D09(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D09 solution = new D09(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");

    String diskMap = lines.get(0);

    String[] arr = parseIntoFileArray(diskMap);
    long checkSum = 0L;
    for (int i = 0; i < arr.length; i++) {
      if (!arr[i].equals(".")) {
        checkSum += (long) i * Integer.parseInt(arr[i]);
      }
    }
    log.info("Checksum: " + checkSum);
  }

  public void partTwo() {
    log.info("# Part 2 #");

  }

  private String[] parseIntoFileArray(String diskMap) {
    List<String> files = new ArrayList<>();
    int id = 0;
    for (int i = 0; i < diskMap.length(); i++) {
      String reading = diskMap.substring(i, i + 1);
      int file = Integer.parseInt(reading);
      if (i % 2 == 0) {
        for (int j = 0; j < file; j++) {
          files.add(String.valueOf(id));
        }
        id++;
      } else {
        for (int j = 0; j < file; j++) {
          files.add(".");
        }
      }
    }

    String[] arr = files.toArray(new String[0]);
    for (int i = arr.length - 1; i >= 0; i--) {
      if (arr[i].equals(".") || findFirstSpaceIndex(arr) > i) {
        continue;
      }
      int firstSpace = findFirstSpaceIndex(arr);
      if (firstSpace != -1) {
        arr[firstSpace] = arr[i];
        arr[i] = ".";
      }
    }
    return arr;
  }

  private int findFirstSpaceIndex(String[] a) {
    for (int i = 0; i < a.length; i++) {
      if (a[i].equals(".")) {
        return i;
      }
    }
    return -1;
  }

  public void lore() {
    log.info(lore);
  }
}