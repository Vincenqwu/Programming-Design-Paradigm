package test;

import org.junit.Test;

import java.math.BigInteger;
import static org.junit.Assert.assertEquals;
import lookandsay.LookAndSayIterator;
import lookandsay.RIterator;

/**
 * Question 10 - 13
 */
public class LookAndSayIteratorTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSeed()  {
    BigInteger invalidSeed = new BigInteger("-11");
    BigInteger end = new BigInteger("1111");
    new LookAndSayIterator(invalidSeed, end);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullSeed()  {
    BigInteger end = new BigInteger("1111");
    new LookAndSayIterator(null, end);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEndLessThanSeed()  {
    BigInteger validSeed = new BigInteger("1211");
    BigInteger end = new BigInteger("11");
    new LookAndSayIterator(validSeed, end);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOneArgConstructor()  {
    BigInteger invalidSeed = new BigInteger("0");
    new LookAndSayIterator(invalidSeed);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOneArgConstructor2()  {
    new LookAndSayIterator(null);
  }

  @Test
  public void testNextValue() {
    BigInteger validSeed = new BigInteger("2132");
    RIterator<BigInteger> iterator = new LookAndSayIterator(validSeed);
    assertEquals(new BigInteger("2132"), iterator.next());
    assertEquals(new BigInteger("12111312"), iterator.next());
  }
}