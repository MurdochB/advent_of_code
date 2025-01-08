package base.utils;

public class Direction extends Coord {

  public static final Direction N = new Direction(-1, 0);
  public static final Direction E = new Direction(0, 1);
  public static final Direction S = new Direction(1, 0);
  public static final Direction W = new Direction(0, -1);
  public static final Direction NE = new Direction(-1, 1);
  public static final Direction SE = new Direction(1, 1);
  public static final Direction SW = new Direction(1, -1);
  public static final Direction NW = new Direction(-1, -1);

  public static final Direction[] ALL_DIRECTIONS = {N, NE, E, SE, S, SW, W, NW};
  public static final Direction[] CARDINAL_DIRECTIONS = {N, E, S, W};
  public static final Direction[] ORDINAL_DIRECTIONS = {NE, SE, SW, NW};

  public static Direction normalise(Direction dir){
    // ignore the magnitude of a direction
    int row = dir.dR();
    int col = dir.dC();
    if (row > 1)
      row = 1;
    if (row < 0)
      row = -1;
    if (col > 1)
      col = 1;
    if (col < 0)
      col = -1;
    return new Direction(row, col);
  }

  public static Direction right(Direction dir) {
    if (dir.equals(Direction.N)) {
      return Direction.E;
    }
    if (dir.equals(Direction.E)) {
      return Direction.S;
    }
    if (dir.equals(Direction.S)) {
      return Direction.W;
    }
    if (dir.equals(Direction.W)) {
      return Direction.N;
    }
    return null;
  }

  public static Direction flip(Direction dir) {
    if (dir.equals(Direction.N)) {
      return Direction.S;
    }
    if (dir.equals(Direction.E)) {
      return Direction.W;
    }
    if (dir.equals(Direction.S)) {
      return Direction.N;
    }
    if (dir.equals(Direction.W)) {
      return Direction.E;
    }
    return null;
  }

  public static String quickString(Direction d){
    if (d.equals(N)){
      return "^";
    } else if (d.equals(E)){
      return ">";
    } else if (d.equals(S)){
      return "v";
    } else if (d.equals(W)) {
      return "<";
    }
    return "X";
  }

  public static Direction fromArrow(String arrow){
    return switch (arrow) {
      case "^" -> Direction.N;
      case ">" -> Direction.E;
      case "v" -> Direction.S;
      case "<" -> Direction.W;
      default -> null;
    };
  }

  public Direction(int dR, int dC) {
    super(dR, dC);
  }

  public int dR() {
    return this.r();
  }

  public int dC() {
    return this.c();
  }
}