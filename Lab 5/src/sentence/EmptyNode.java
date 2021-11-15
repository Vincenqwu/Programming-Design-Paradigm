package sentence;

/**
 * Represents the EmptyNode which represents the end of a sentence.
 */
public class EmptyNode implements Sentence {

  @Override
  public int getNumberOfWords() {
    return 0;
  }

  @Override
  public String longestWord() {
    return "";
  }

  @Override
  public Sentence merge(Sentence other) throws IllegalArgumentException {
    if (other == null) {
      throw new IllegalArgumentException("other cannot be null");
    }
    return other.clone();
  }

  @Override
  public Sentence clone() {
    return new EmptyNode();
  }

  @Override
  public String toString() {
    return "";
  }

  @Override
  public void setRest(Sentence rest) {
    return;
  }

  @Override
  public Sentence getRest() {
    return null;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof EmptyNode;
  }

  @Override
  public int hashCode() {
    return EmptyNode.class.hashCode();
  }
}
