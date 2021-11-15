package polynomial;

/**
 * Implement PolyNode for element node.
 */
public class PolyNodeElement implements PolyNode {
  private PolyNode rest;
  private PolyTerm term;

  public PolyNodeElement(PolyTerm term, PolyNode rest) {
    this.term = term;
    this.rest = rest;
  }

  @Override
  public PolyNode addTerm(PolyTerm term) {
    if (term.getCoefficient() == 0) {
      return this;
    }
    if (this.term.getPower() > term.getPower()) {
      return new PolyNodeElement(this.term, this.rest.addTerm(term));
    } else if (this.term.getPower() < term.getPower()) {

      return new PolyNodeElement(term, this);
    } else {
      this.term.addCoefficient(term);
      if (this.term.getCoefficient() == 0) {
        return this.rest;
      }
      return this;
    }

  }

  @Override
  public boolean isSame(PolyNode node) {
    if (!(node instanceof PolyNodeElement)) {
      return false;
    }
    if (this.getTerm().getCoefficient() == node.getTerm().getCoefficient()
            && this.getTerm().getPower() == node.getTerm().getPower()) {
      return this.rest.isSame(node.getRest());
    }
    return false;
  }

  @Override
  public int getCoefficient(int power) {
    if (this.term.getPower() == power) {
      return this.term.getCoefficient();
    } else {
      return this.rest.getCoefficient(power);
    }
  }

  @Override
  public int getDegree() {
    return Math.max(this.term.getPower(), this.rest.getTerm().getPower());
  }

  @Override
  public double evaluate(double x) {
    double thisTerm = this.term.getCoefficient() * Math.pow(x, this.term.getPower());
    return thisTerm + rest.evaluate(x);
  }

  @Override
  public String toString() {
    if (this.term.getCoefficient() < 0) {
      return " " + this.term.toString() + rest.toString();
    }
    return " +" + this.term.toString() + rest.toString();
  }

  @Override
  public PolyTerm getTerm() {
    return new PolyTerm(this.term.getCoefficient(), this.getDegree());
  }

  @Override
  public PolyNode getRest() {
    if (this.rest instanceof PolyNodeEmpty) {
      return new PolyNodeEmpty();
    }
    return new PolyNodeElement(this.rest.getTerm(), this.rest.getRest());
  }
}
