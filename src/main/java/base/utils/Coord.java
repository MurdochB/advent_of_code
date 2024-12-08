package base.utils;

public class Coord extends Pair<Integer> {

  public Coord(int r, int c) {
    super(r, c);
  }

  public Direction distance(Coord other) {
    return new Direction(other.r() - r(), other.c() - c());
  }

  public int r() {
    return this.getLeft();
  }

  public int c() {
    return this.getRight();
  }

  public Coord relative(Direction d) {
    return new Coord(r() + d.dR(), c() + d.dC());
  }

  @Override
  public String toString() {
    return "r: " + this.r() + ", c:" + this.c();
  }
}