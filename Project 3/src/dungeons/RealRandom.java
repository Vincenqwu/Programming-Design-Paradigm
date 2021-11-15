package dungeons;

/**
 * RealRandom class that calls the java.util.Random methods.
 */
public class RealRandom implements Random {

  private final java.util.Random random;

  /**
   * Construct the java.util.Random object.
   */
  public RealRandom() {
    random = new java.util.Random();
  }

  @Override
  public int nextInt() {
    return random.nextInt();
  }

  @Override
  public int nextInt(int range) {
    return random.nextInt(range);
  }
}
