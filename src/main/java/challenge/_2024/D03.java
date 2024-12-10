package challenge._2024;

import base.Solution;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D03 extends Solution {

  private final Logger log = LogManager.getLogger(D03.class);

  private static final String INPUT_FILE = "2024/inputs/03.txt";
  private static final String LORE_FILE = "2024/lore/03.txt";

  private D03(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D03 solution = new D03(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    log.info(lore);
    Pattern r = Pattern.compile("mul\\((\\d{1,3},\\d{1,3})\\)");
    int sum = parseIntoCommands(r).stream().mapToInt(this::calculateMulValue).sum();
    log.info(sum);
  }

  public void partTwo() {
    log.info("# Part 2 #");
    Pattern r = Pattern.compile("mul\\((\\d{1,3},\\d{1,3})\\)|do\\(\\)|don't\\(\\)");
    List<String> result = parseIntoCommands(r);
    int sum = 0;
    boolean active = true;
    for (String s : result) {
      if (s.equals("don't()")) {
        active = false;
      }
      if (s.equals("do()")) {
        active = true;
        continue;
      }
      if (active) {
        sum += calculateMulValue(s);
      }
    }
    log.info(sum);
  }

  private List<String> parseIntoCommands(Pattern r) {
    List<String> commands = new ArrayList<>();
    for (String line : lines) {
      Matcher m = r.matcher(line);
      while (m.find()) {
        commands.add(m.group());
      }
    }
    return commands;
  }

  private int calculateMulValue(String mulString) {
    mulString = mulString.replace("mul(", "").replace(")", "");
    String[] split = mulString.split(",");
    int num1 = Integer.parseInt(split[0]);
    int num2 = Integer.parseInt(split[1]);
    return num1 * num2;
  }

  public void lore() {
    log.info(lore);
  }
}