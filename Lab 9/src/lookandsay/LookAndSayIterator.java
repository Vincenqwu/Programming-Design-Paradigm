package lookandsay;

import java.math.BigInteger;
import java.util.NoSuchElementException;

/**
 * This is a Iterator that can produce a look-and-say sequence.
 * The look-and-say sequence can also be computed in reverse.
 */
public class LookAndSayIterator implements RIterator<BigInteger> {
  private final BigInteger end;
  private BigInteger current;
  private boolean flag;

  public LookAndSayIterator(BigInteger seed, BigInteger end) {
    if (seed == null || end == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }

    if (seed.compareTo(BigInteger.valueOf(0)) < 0 || seed.compareTo(end) > 0 || seed.toString().contains("0")) {
      throw new IllegalArgumentException("The seed is illegal.");
    }

    this.end = end;
    this.current = seed;
  }

  public LookAndSayIterator(BigInteger seed) {
    this(seed, new BigInteger("9".repeat(100)));
  }

  public LookAndSayIterator() {
    this(BigInteger.valueOf(1), new BigInteger("9".repeat(100)));
  }

  @Override
  public BigInteger previous() throws NoSuchElementException {
    if (!hasPrevious()) {
      throw new NoSuchElementException("There is no previous element.");
    }

    if (!flag) {
      flag = true;
      return getPrevious(current);
    }

    current = getPrevious(current);
    return getPrevious(current);
  }

  private BigInteger getPrevious(BigInteger n) {
    String currentString = n.toString();

    StringBuilder result = new StringBuilder();

    for (int i = 0; i < currentString.length(); i = i + 2) {
      for (int j = 0; j < (int) currentString.charAt(i) - '0'; j++) {
        result.append(currentString.charAt(i + 1));
      }
    }

    return new BigInteger(result.toString());
  }

  @Override
  public boolean hasPrevious() {
    // TODO:
    return current.toString().length() > 0
            && current.toString().length() % 2 == 0;
  }

  @Override
  public boolean hasNext() {
    return current.compareTo(end) < 0;
  }

  @Override
  public BigInteger next() {
    if (!hasNext()) {
      throw new NoSuchElementException("There is no next element.");
    }

    if (!flag) {
      flag = true;
    }

    String currentString = current.toString();
    // Store the current result
    BigInteger currentVal = new BigInteger(currentString);

    // Calculate the next element
    StringBuilder result = new StringBuilder();

    char currentChar = currentString.charAt(0);
    int counter = 1;
    for (int i = 1; i < currentString.length(); i++) {
      if (currentChar == currentString.charAt(i)) {
        counter++;
      }
      // If we encounter a different number
      else {
        result.append(counter).append(currentChar);

        currentChar = currentString.charAt(i);
        counter = 1;
      }
    }

    // Add the last number
    result.append(counter).append(currentChar);

    current = new BigInteger(result.toString());

    return currentVal;
  }
}
