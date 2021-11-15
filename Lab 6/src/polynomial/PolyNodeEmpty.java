package polynomial;

/**
 * Implement the PolyNode interface for empty node.
 */
public class PolyNodeEmpty implements PolyNode {


  @Override
  public PolyNode addTerm(PolyTerm term) {
    return new PolyNodeElement(term, this);
  }

  @Override
  public PolyTerm getTerm() {
    return new PolyTerm(0, 0);
  }

  @Override
  public PolyNode getRest() {
    return null;
  }

  @Override
  public boolean isSame(PolyNode node) {
    return (node instanceof PolyNodeEmpty);
  }

  @Override
  public int getCoefficient(int power) {
    return 0;
  }

  @Override
  public int getDegree() {
    return 0;
  }

  @Override
  public double evaluate(double x) {
    return 0;
  }

  @Override
  public String toString() {
    return "";
  }

}
