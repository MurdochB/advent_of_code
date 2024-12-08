package base.utils;

public class Direction extends Coord {

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