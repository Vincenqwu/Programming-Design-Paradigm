package polynomial;

import java.util.Scanner;

/**
 * Implement the methods in the polynomial interface .
 */
public class PolynomialImpl implements Polynomial {

  private PolyNode head;

  /**
   * constructor 1.
   */
  public PolynomialImpl() {
    this.head = new PolyNodeEmpty();
  }

  /**
   * constructor 2.
   */
  public PolynomialImpl(String polynomial) {
    int coefficient;
    int power;
    this.head = new PolyNodeEmpty();

    if (polynomial == null || polynomial.isEmpty()) {
      throw new IllegalArgumentException("The polynomial string cannot be null or empty");
    }
    Scanner scanInput = new Scanner(polynomial);
    scanInput.useDelimiter(" ");

    while (scanInput.hasNext()) {
      String term = scanInput.next();
      if (term.length() > 1 && (term.charAt(0) == 'x'
              || (term.charAt(0) == '-' && term.charAt(1) == 'x'))) {
        throw new IllegalArgumentException("The polynomial string is not valid");
      }
      Scanner scanTerm = new Scanner(term);
      scanTerm.useDelimiter("x\\^");
      String left = scanTerm.next();
      coefficient = isValidInteger(left);

      if (scanTerm.hasNext()) {
        String right = scanTerm.next();
        power = isValidInteger(right);
      } else {
        power = 0;
      }
      if (power < 0) {
        throw new IllegalArgumentException("The polynomial string is not valid");
      }
      PolyTerm newTerm = new PolyTerm(coefficient, power);
      head = head.addTerm(newTerm);
    }
  }

  private int isValidInteger(String input) {
    if (input.matches("[+-]?\\d+")) {
      return Integer.parseInt(input);
    }
    throw new IllegalArgumentException("The polynomial form is not valid");
  }


  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    if (!(other instanceof PolynomialImpl)) {
      throw new IllegalArgumentException("Input type must be a Polynomial");
    }
    PolyNode newNode = this.head;
    while (!(newNode instanceof PolyNodeEmpty)) {
      other.addTerm(newNode.getTerm().getCoefficient(), newNode.getTerm().getPower());
      newNode = newNode.getRest();
    }
    return other;
  }

  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Power should be greater than 0.");
    }
    this.head = head.addTerm(new PolyTerm(coefficient, power));
  }

  @Override
  public boolean isSame(Polynomial poly) {
    if (!(poly instanceof PolynomialImpl)) {
      return false;
    }
    return this.head.isSame(((PolynomialImpl) poly).head);
  }

  @Override
  public double evaluate(double x) {
    return head.evaluate(x);
  }

  @Override
  public int getCoefficient(int power) {
    return head.getCoefficient(power);
  }

  @Override
  public int getDegree() {
    return head.getDegree();
  }

  @Override
  public String toString() {
    String getString = head.toString();
    if (getString.equals("")) {
      return "0";
    }
    getString = head.toString().substring(1);
    if (getString.charAt(0) == '+') {
      getString = getString.substring(1);
    }
    getString = getString.substring(0, getString.length());
    return getString;
  }

}
