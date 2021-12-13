package random;

/**
 * Random interface implemented by realRandom and pseudoRandom.
 */
public interface Random {
  /**
   * Return a random integer between 0 and 10 (Exclusive).
   *
   * @return Integer
   */
  int nextInt();

  /**
   * Return a random integer between 0 and range (Exclusive).
   *
   * @param range Upper bound
   * @return Integer
   */
  int nextInt(int range);
}
