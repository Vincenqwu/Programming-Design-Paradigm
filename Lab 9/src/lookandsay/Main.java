package lookandsay;

import java.math.BigInteger;

public class Main {

  public static void main(String[] args) {
    BigInteger seed = new BigInteger("111221");
    LookAndSayIterator iter = new LookAndSayIterator(seed);

    System.out.println(iter.previous());
    System.out.println(iter.next());
    System.out.println(iter.previous());
    System.out.println(iter.previous());
    System.out.println(iter.next());
  }
}
