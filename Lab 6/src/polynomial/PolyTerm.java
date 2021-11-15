package polynomial;

/**
 * Class for the term object that include coefficient and power.
 */
public class PolyTerm {
  private int coefficient;
  private int power;

  /**
   * Constructor for a term of polynomial.
   *
   * @param coefficient The coefficient of the term
   * @param power       The power of the term
   */
  public PolyTerm(int coefficient, int power) {
    this.coefficient = coefficient;
    if (power < 0) {
      throw new IllegalArgumentException("Power must be greater than 0");
    }
    this.power = power;
  }

  /**
   * Get the coefficient of the term.
   *
   * @return the coefficient
   */
  public int getCoefficient() {
    return this.coefficient;
  }

  /**
   * Get the power of the term.
   *
   * @return the power
   */
  public int getPower() {
    return this.power;
  }

  /**
   * Override the toString.
   *
   * @return String
   */
  @Override
  public String toString() {
    if (this.power == 0) {
      return Integer.toString(this.coefficient);
    } else {
      return Integer.toString(this.coefficient) + "x^" + Integer.toString(this.power);
    }

  }

  /**
   * Sum this coefficient with the given coefficient in the term.
   *
   * @param term Term to be added
   */
  public void addCoefficient(PolyTerm term) {
    this.coefficient += term.getCoefficient();
  }
}
