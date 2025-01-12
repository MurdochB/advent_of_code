package base.utils;

import java.util.Objects;

public class SplitPair<T, T2> {

  private T left;
  private T2 right;

  public SplitPair(T l, T2 r) {
    this.left = l;
    this.right = r;
  }

  public T getLeft() {
    return left;
  }

  public void setLeft(T left) {
    this.left = left;
  }

  public T2 getRight() {
    return right;
  }

  public void setRight(T2 right) {
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
    SplitPair<?, ?> o = (SplitPair<?, ?>) other;
    return this.getLeft().equals(o.getLeft()) && this.getRight().equals(o.getRight());
  }
}