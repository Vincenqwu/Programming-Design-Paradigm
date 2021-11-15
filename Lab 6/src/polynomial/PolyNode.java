package polynomial;

/**
 * This interface represents a single node in polynomials, it is implemented
 * by element node and empty node. Each node contains the current term and a reference to
 * the rest of the nodes.
 */
public interface PolyNode {
  /**
   * Add a term to from the head.
   *
   * @param term New term
   * @return a new polyNode
   */
  PolyNode addTerm(PolyTerm term);

  /**
   * Get the coefficient given the power.
   *
   * @param power power of the term
   * @return coefficient
   */
  int getCoefficient(int power);

  /**
   * Get the greatest power in the polynomial.
   *
   * @return degree
   */
  int getDegree();

  /**
   * Evaluate the value of this polynomial at the given value of the variable.
   *
   * @param x the value at which the polynomial is to be evaluated.
   * @return the value of the current node at x
   */
  double evaluate(double x);

  /**
   * Return the term of the node.
   *
   * @return term object
   */
  PolyTerm getTerm();

  /**
   * Return the rest of the polynode.
   *
   * @return Polynode object
   */
  PolyNode getRest();

  /**
   * Determines if this node is the same as the parameter node.
   *
   * @param node node to be compared
   * @return true or false
   */
  boolean isSame(PolyNode node);
}
