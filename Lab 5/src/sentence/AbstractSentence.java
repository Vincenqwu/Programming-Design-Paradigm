package sentence;

/**
 * abstract class to implement some common code for interface Sentence.
 */
public abstract class AbstractSentence implements Sentence {

  protected String element;
  protected Sentence rest;

  protected AbstractSentence(String element, Sentence rest) throws IllegalArgumentException {
    if (element.isEmpty()) {
      throw new IllegalArgumentException("word or punctuation cannot be empty.");
    }
    this.element = element;
    this.rest = rest;
  }

  @Override
  public Sentence merge(Sentence other) throws IllegalArgumentException {
    if (other == null) {
      throw new IllegalArgumentException("other cannot be null");
    }
    Sentence sentence1 = this.clone();
    Sentence sentence2 = other.clone();
    Sentence sentence1Tmp = sentence1;
    while (!(sentence1Tmp.getRest() instanceof EmptyNode)) {
      sentence1Tmp = sentence1Tmp.getRest();
    }
    sentence1Tmp.setRest(sentence2);
    return sentence1;
  }

  @Override
  public abstract Sentence clone();

  @Override
  public String toString() {
    if (this.rest instanceof WordNode) {
      return this.element + " " + rest.toString();
    }
    return this.element + rest.toString();
  }

  @Override
  public Sentence getRest() {
    return rest;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Sentence) {
      return this.toString().equals(obj.toString());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

  @Override
  public void setRest(Sentence rest) {
    this.rest = rest;
  }
}
