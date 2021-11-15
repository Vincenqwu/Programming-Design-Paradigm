package test;

import org.junit.Before;
import org.junit.Test;

import polynomial.Polynomial;
import polynomial.PolynomialImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Class for testing methods in the Polynomial class.
 */
public class PolynomialTest {
  Polynomial polynomial;
  Polynomial polynomial2;
  Polynomial polynomial3;
  Polynomial polynomial4;
  Polynomial constPolynomial;
  Polynomial zeroPolynomial;

  @Before
  public void setUp() {
    polynomial = new PolynomialImpl("-2x^2 +3x^1 -4");
    polynomial2 = new PolynomialImpl("2x^2 +10x^2 +1x^1 -2x^1");
    polynomial3 = new PolynomialImpl("-9x^4 +10x^5 +8x^3");
    polynomial4 = new PolynomialImpl("2x^0");
    constPolynomial = new PolynomialImpl("100");
    zeroPolynomial = new PolynomialImpl("0");
  }

  @Test
  public void testEmptyConstructor() {
    Polynomial polynomial1 = new PolynomialImpl();
    assertEquals(0, polynomial1.getCoefficient(2));
  }

  @Test
  public void testStringInput() {
    assertEquals(-2, polynomial.getCoefficient(2));
    assertEquals(3, polynomial.getCoefficient(1));
    assertEquals(-4, polynomial.getCoefficient(0));
    assertEquals(2, polynomial.getDegree());
  }

  @Test
  public void testStringInput2() {
    assertEquals(12, polynomial2.getCoefficient(2));
    assertEquals(-1, polynomial2.getCoefficient(1));
    assertEquals(0, polynomial2.getCoefficient(0));
    assertEquals(2, polynomial.getDegree());

  }

  @Test
  public void testConstPolynomial() {
    assertEquals(100, constPolynomial.getCoefficient(0));
    assertEquals(0, constPolynomial.getDegree());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyInput() {
    new PolynomialImpl("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullInput() {
    new PolynomialImpl(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInput() {
    new PolynomialImpl("2x^-5");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInput2() {
    new PolynomialImpl("x^2 +2");
  }

  @Test
  public void testToString() {
    assertEquals("-2x^2 +3x^1 -4", polynomial.toString());
    assertEquals("12x^2 -1x^1", polynomial2.toString());
    assertEquals("10x^5 -9x^4 +8x^3", polynomial3.toString());
    assertEquals("0", zeroPolynomial.toString());
  }

  @Test
  public void testAdd() {
    Polynomial combinedPoly = polynomial.add(polynomial3);
    assertEquals("10x^5 -9x^4 +8x^3 -2x^2 +3x^1 -4", combinedPoly.toString());
  }

  @Test
  public void testAdd2() {
    Polynomial pSamePower = new PolynomialImpl("1x^2 -3x^1 -4");
    Polynomial combinedPoly = polynomial.add(pSamePower);
    assertEquals("-1x^2 -8", combinedPoly.toString());
  }

  @Test
  public void testAddZero() {
    Polynomial combinedPoly = polynomial.add(zeroPolynomial);
    assertEquals("-2x^2 +3x^1 -4", combinedPoly.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAddTerm() {
    polynomial.addTerm(1, -1);
  }

  @Test
  public void testAddTerm() {
    polynomial.addTerm(3, 1);
    assertEquals("-2x^2 +6x^1 -4", polynomial.toString());
  }

  @Test
  public void testAddTerm2() {
    polynomial.addTerm(5, 4);
    assertEquals("5x^4 -2x^2 +3x^1 -4", polynomial.toString());
  }

  @Test
  public void testIsSame() {
    assertFalse(polynomial2.isSame(polynomial3));
    assertFalse(polynomial.isSame(polynomial2));
    assertFalse(zeroPolynomial.isSame(polynomial3));
  }

  @Test
  public void testEvaluate() {
    assertEquals(-9, polynomial.evaluate(-1), 0);
    assertEquals(46, polynomial2.evaluate(2), 0);
  }

  @Test
  public void testZeroPower() {
    assertEquals("2", polynomial4.toString());
  }

  @Test
  public void testPolyContainsZero() {
    Polynomial zerooo = new PolynomialImpl("1x^5 -0x^2 -1x^1 +0");
    assertEquals("1x^5 -1x^1", zerooo.toString());
  }
}