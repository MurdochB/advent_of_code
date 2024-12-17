package challenge._2024;

import base.Solution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D17 extends Solution {

  private final Logger log = LogManager.getLogger(D17.class);

  private static final String INPUT_FILE = "2024/inputs/17.txt";
  private static final String LORE_FILE = "2024/lore/17.txt";

  private D17(String inputFile, String loreFile) {
    super(inputFile, loreFile);
  }

  public static void main(String[] args) {
    D17 solution = new D17(INPUT_FILE, LORE_FILE);
    solution.run();
  }

  public void partOne() {
    log.info("# Part 1 #");
    this.lore();
    Computer comp = buildComputerFromInput();
    comp.execute();
    log.info(comp.getOutput());
  }

  public void partTwo() {
    log.info("# Part 2 #");

  }

  private Computer buildComputerFromInput() {
    int a = Integer.parseInt(lines.get(0).split("Register A: ")[1]);
    int b = Integer.parseInt(lines.get(1).split("Register B: ")[1]);
    int c = Integer.parseInt(lines.get(2).split("Register C: ")[1]);
    String rawProgram = lines.get(4).split("Program: ")[1];
    List<Integer> program = Arrays.stream(rawProgram.split(",")).map(Integer::parseInt).toList();
    return new Computer(a, b, c, program);
  }

  public class Computer {

    private long a;
    private long b;
    private long c;
    private int ip;
    List<Integer> opCodes;
    List<Integer> out;

    public Computer(long a, long b, long c, List<Integer> opcodes) {
      this.a = a;
      this.b = b;
      this.c = c;
      this.ip = 0;
      this.out = new ArrayList<>();
      this.opCodes = opcodes;
    }

    void execute() {
      while (ip < opCodes.size()) {
        int op = opCodes.get(ip);
        int operand = opCodes.get(ip + 1);
        switch (op) {
          case 0 -> a /= (long) Math.pow(2, getCombo(operand));
          case 1 -> b = b ^ operand;
          case 2 -> b = getCombo(operand) % 8;
          case 3 -> {
            if (a != 0) {
              ip = operand - 2;
            }
          }
          case 4 -> b = b ^ c;
          case 5 -> out.add((int) (getCombo(operand) % 8));
          case 6 -> b = a / (long) Math.pow(2, getCombo(operand));
          case 7 -> c = a / (long) Math.pow(2, getCombo(operand));
          default -> throw new IllegalStateException("Unexpected value: " + op);
        }
        ip += 2;
        printComputer(ip);
      }
      printComputer(-1);
    }

    private long getCombo(int in) {
      return switch (in % 8) {
        case 0, 1, 2, 3 -> in;
        case 4 -> a;
        case 5 -> b;
        case 6 -> c;
        default -> -1L;
      };
    }

    private void printComputer(int ip) {
      log.info("ip " + ip + " a " + a + " b " + b + " c " + c + " out: " + String.join(",",
          out.stream().map(Long::toString).toList()));
    }

    private String getOutput(){
      return String.join(",", out.stream().map(Long::toString).toList());
    }
  }

  public void lore() {
    log.info(lore);
  }
}