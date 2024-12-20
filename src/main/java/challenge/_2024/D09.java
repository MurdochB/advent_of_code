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

    String disk = lines.get(0);
    String[] arr = defragWithSingleMove(disk);
    long checkSum = calculateChecksum(arr);
    log.info("Checksum: {}", checkSum);
  }

  private String[] defragWithSingleMove(String diskMap) {
    List<String> files = parseIntoFilesList(diskMap);

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

  public void partTwo() {
    log.info("# Part 2 #");

    String disk = lines.get(0);
    String[] arr = defragWithWholeFileMoves(disk);
    long checkSum = calculateChecksum(arr);
    log.info("Checksum: {}", checkSum);
  }

  private String[] defragWithWholeFileMoves(String disk) {
    List<String> files = parseIntoFilesList(disk);

    String[] arr = files.toArray(new String[0]);
    String fileToMove = null;
    List<String> lockedFiles = new ArrayList<>();
    int fileToMoveSize = -1;
    for (int i = arr.length - 1; i >= 0; i--) {
      // log.info("Looking at index {} withvalue:{} file:{}", i, arr[i], String.join("", arr));
      if (fileToMove == null) {
        fileToMove = arr[i];
        fileToMoveSize = 1;
        continue;
      }
      if (arr[i].equals(fileToMove)) {
        fileToMoveSize++;
      } else {
        // we're now at the end of that file.
        if (lockedFiles.contains(fileToMove)) {
          fileToMove = arr[i];
          fileToMoveSize = 1;
          continue;
        }
        int firstSpace = findFirstSpaceIndexWithGapSize(arr, fileToMoveSize);
        // log.info("I want to move {} to {}", fileToMove, firstSpace);
        if (firstSpace != -1 && firstSpace < i) {
          lockedFiles.add(fileToMove);
          for (int m = 0; m < fileToMoveSize; m++) {
            arr[firstSpace + m] = fileToMove;
            arr[1 + i + m] = ".";
          }
        }
        if (arr[i].equals(".")) {
          fileToMove = null;
          fileToMoveSize = -1;
        } else {
          fileToMove = arr[i];
          fileToMoveSize = 1;
        }
        // log.info("file:{}", String.join("", arr));
      }
    }
    return arr;
  }

  private int findFirstSpaceIndexWithGapSize(String[] a, int size) {
    int curGapSize = 0;
    for (int i = 0; i < a.length; i++) {
      if (a[i].equals(".")) {
        curGapSize++;
        if (curGapSize == size) {
          return i - size + 1;
        }
      } else {
        curGapSize = 0;
      }
    }
    return -1;
  }

  private List<String> parseIntoFilesList(String diskMap) {
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
    return files;
  }

  private long calculateChecksum(String[] arr) {
    long checkSum = 0L;
    for (int i = 0; i < arr.length; i++) {
      if (!arr[i].equals(".")) {
        checkSum += (long) i * Integer.parseInt(arr[i]);
      }
    }
    return checkSum;
  }

  public void lore() {
    log.info(lore);
  }
}