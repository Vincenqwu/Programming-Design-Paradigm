package dungeons;

import java.util.ArrayList;
import java.util.List;

/**
 * PseudoRandom class that create a list of user defined fake random numbers for testing purpose.
 */
public class PseudoRandom implements Random {

  private final List<Integer> inputList;
  private int currIdx;

  /**
   * Construct a list of user defined fake random numbers.
   *
   * @param inputs a list of int
   */
  public PseudoRandom(int... inputs) {
    inputList = new ArrayList<>();
    currIdx = 0;
    for (int i : inputs) {
      inputList.add(i);
    }
  }

  @Override
  public int nextInt() {
    if (currIdx == inputList.size()) {
      Random realRandom = new RealRandom();
      return realRandom.nextInt();
    }
    currIdx += 1;
    return inputList.get(currIdx - 1);
  }

  @Override
  public int nextInt(int range) {
    if (currIdx == inputList.size()) {
      Random realRandom = new RealRandom();
      return realRandom.nextInt(range);
    }
    currIdx += 1;
    return inputList.get(currIdx - 1);
  }
}
