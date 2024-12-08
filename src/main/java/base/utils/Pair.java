package base.utils;

import java.util.Objects;

public class Pair<T> {

  private T left;
  private T right;

  public Pair(T l, T r) {
    this.left = l;
    this.right = r;
  }

  public T getLeft() {
    return left;
  }

  public void setLeft(T left) {
    this.left = left;
  }

  public T getRight() {
    return right;
  }

  public void setRight(T right) {
    this.right = right;
  }

  @Override
  public int hashCode() {
    return Objects.hash(left, right);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (other == null || other.getClass() != this.getClass()) {
      return false;
    }
    Pair<?> o = (Pair<?>) other;
    return this.getLeft().equals(o.getLeft()) && this.getRight().equals(o.getRight());
  }
}