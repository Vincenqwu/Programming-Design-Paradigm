package sentence;

/**
 * This represents a non-empty word node of the list, It contains a piece of data along with the
 * rest of the list.
 */
public class WordNode extends AbstractSentence {

  /**
   * Constructor.
   *
   * @param word the single word at this node
   * @param rest the rest of the list
   */
  public WordNode(String word, Sentence rest) {
    super(word, rest);
  }

  @Override
  public int getNumberOfWords() {
    return 1 + this.rest.getNumberOfWords();
  }

  @Override
  public String longestWord() {
    if (element.length() >= rest.longestWord().length()) {
      return element;
    }
    return rest.longestWord();
  }

  @Override
  public Sentence clone() {
    return new WordNode(this.element, this.rest.clone());
  }
}
