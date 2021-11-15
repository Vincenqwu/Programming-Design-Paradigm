package sentence;

import java.util.regex.Pattern;

/**
 * This represents a non-empty punctuation node of the list, It contains a piece of data along with
 * the rest of the list.
 */
public class PunctuationNode extends AbstractSentence {

  /**
   * Constructor.
   *
   * @param punctuation the punctuation at this node
   * @param rest        the rest of the list
   */
  public PunctuationNode(String punctuation, Sentence rest) {
    super(punctuation, rest);
    if (!Pattern.matches("[\\p{Punct}\\p{IsPunctuation}]", punctuation)) {
      throw new IllegalArgumentException("the element you input is not a punctuation");
    }
  }

  @Override
  public int getNumberOfWords() {
    return rest.getNumberOfWords();
  }

  @Override
  public String longestWord() {
    return rest.longestWord();
  }

  @Override
  public Sentence clone() {
    return new PunctuationNode(this.element, this.rest.clone());
  }
}
