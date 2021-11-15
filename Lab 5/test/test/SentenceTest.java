package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import sentence.EmptyNode;
import sentence.PunctuationNode;
import sentence.Sentence;
import sentence.WordNode;

/**
 * test class to test methods in interface Sentence.
 */
public class SentenceTest {

  private Sentence emptySentence;
  private Sentence sentence1;
  private Sentence sentence2;
  private Sentence sentenceOnlyWords;
  private Sentence sentence3;

  @Before
  public void setup() {
    emptySentence = new EmptyNode();
    sentence1 = new WordNode("This",
        new WordNode("is", new WordNode("lab5", new PunctuationNode(".", new EmptyNode()))));
    sentence2 = new WordNode("This",
        new WordNode("is",
            new WordNode("lab", new WordNode("5", new PunctuationNode(".", new EmptyNode())))));
    sentenceOnlyWords = new WordNode("cool",
        new WordNode("I", new WordNode("like", new WordNode("it", new EmptyNode()))));
    sentence3 = new WordNode("cool", new PunctuationNode("!",
        new WordNode("I", new WordNode("like", new WordNode("it", new EmptyNode())))));

  }

  @Test
  public void testSentenceConstruct() {
    assertEquals("This is lab5.", sentence1.toString());
  }

  /**
   * test getNumberOfWords with empty sentence.
   */
  @Test
  public void getNumberOfWordsTest1() {
    assertEquals(0, emptySentence.getNumberOfWords());
  }

  /**
   * test getNumberOfWords with non-empty sentence.
   */
  @Test
  public void getNumberOfWordsTest2() {
    assertEquals(3, sentence1.getNumberOfWords());
  }

  /**
   * test longestWord with empty sentence.
   */
  @Test
  public void longestWordTest1() {
    assertEquals("", emptySentence.longestWord());
  }

  /**
   * test longestWord with a sentence with different length words.
   */
  @Test
  public void longestWordTest2() {
    assertEquals("This", sentence2.longestWord());
  }

  /**
   * test longestWord with a sentence with more than one longest words.
   */
  @Test
  public void longestWordTest3() {
    assertEquals("This", sentence1.longestWord());
  }

  /**
   * test merge with the first sentence is an empty sentence.
   */
  @Test
  public void mergeTest1() {
    Sentence sentence4 = emptySentence.merge(sentence1);
    assertEquals("This is lab5.", sentence4.toString());
  }

  /**
   * test merge with the second sentence is an empty sentence.
   */
  @Test
  public void mergeTest2() {
    Sentence sentence4 = sentence1.merge(emptySentence);
    assertEquals("This is lab5.", sentence4.toString());
  }

  /**
   * test merge with the sentence with punctuation at the end of a sentence.
   */
  @Test
  public void mergeTest3() {
    Sentence sentence4 = sentence1.merge(sentence2);
    assertEquals("This is lab5. This is lab 5.", sentence4.toString());
  }

  /**
   * test merge with the sentence with punctuation in the middle of the sentence.
   */
  @Test
  public void mergeTest4() {

    Sentence sentence4 = sentence1.merge(sentence3);
    assertEquals("This is lab5. cool! I like it", sentence4.toString());
  }

  /**
   * test clone with empty sentence.
   */
  @Test
  public void cloneTest() {
    Sentence sentenceClone = emptySentence.clone();
    assertEquals("", sentenceClone.toString());
  }

  /**
   * test clone with all word sentence.
   */
  @Test
  public void cloneTest2() {
    Sentence sentenceClone = sentenceOnlyWords.clone();
    assertEquals("cool I like it", sentenceClone.toString());
  }

  /**
   * test clone with sentence with punctuation.
   */
  @Test
  public void cloneTest3() {
    Sentence sentenceClone = sentence1.clone();
    assertEquals("This is lab5.", sentenceClone.toString());
  }

  /**
   * test toString with empty sentence.
   */
  @Test
  public void toStringTest1() {
    assertEquals("", emptySentence.toString());
  }


  /**
   * test toString with sentence only contains words.
   */
  @Test
  public void toStringTest2() {
    assertEquals("cool I like it", sentenceOnlyWords.toString());
  }

  /**
   * test toString with sentence contains punctuation at the end of the sentence.
   */
  @Test
  public void toStringTest3() {
    assertEquals("cool! I like it", sentence3.toString());
  }

  /**
   * test toString with sentence contains punctuation at the end of the sentence.
   */
  @Test
  public void toStringTest4() {
    assertEquals("This is lab5.", sentence1.toString());
  }


}
