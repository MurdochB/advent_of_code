package base.utils;

public class Coord3D {

  private int x;
  private int y;
  private int z;

  public Coord3D(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public double distanceToCoord3D(Coord3D other) {
    double xSide = Math.pow(Math.abs(this.x - other.x()), 2);
    double ySide = Math.pow(Math.abs(this.y - other.y()), 2);
    double zSide = Math.pow(Math.abs(this.z - other.z()), 2);
    return Math.sqrt(xSide + ySide + zSide);
  }

  public int x() {
    return this.x;
  }

  public int y() {
    return this.y;
  }

  public int z() {
    return this.z;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void setZ(int z) {
    this.z = z;
  }

  @Override
  public String toString() {
    return String.format("%d, %d, %d", this.x(), this.y(), this.z());
  }

}
