package base.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileUtil {

  private static final Logger log = LogManager.getLogger(FileUtil.class);

  private static final String RESOURCES = "src/main/resources/";

  private FileUtil() {
    throw new IllegalArgumentException("Utility class. Should not be instantiated.");
  }

  public static List<String> readLines(String filePath) {
    List<String> lines = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(RESOURCES + filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        lines.add(line);
      }
    } catch (FileNotFoundException e) {
      log.info("File not found [{}]", filePath);
    } catch (IOException e) {
      log.info("File IO exception [{}]", filePath);
    }
    return lines;
  }

  public static String readWholeFile(String filePath) {
    StringBuilder wholeFile = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(RESOURCES + filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        wholeFile.append(line).append("\n");
      }
    } catch (FileNotFoundException e) {
      log.info("File not found [{}]", filePath);
    } catch (IOException e) {
      log.info("File IO exception [{}]", filePath);
    }
    return wholeFile.toString();
  }

  public static String[][] inputToGrid(List<String> input) {
    String[][] grid = new String[input.size()][];
    for (int i = 0; i < input.size(); i++) {
      grid[i] = input.get(i).split("");
    }
    return grid;
  }
  // ... Special readers can be added here if needed.
}