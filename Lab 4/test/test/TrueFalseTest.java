package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import questions.Likert;
import questions.MultiChoice;
import questions.MultiSelect;
import questions.Question;
import questions.TrueFalse;

public class TrueFalseTest {
  private Question trueFalseTest;

  /**
   * Setup a TrueFalse.
   */
  @Before
  public void setUp() {
    trueFalseTest = new TrueFalse("We have class on Mondays", "True");
  }

  /**
   * Should throw an exception when the answer is not "True" or "False".
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIfIsolationIsFull() {
    new TrueFalse("We have class on Mondays", "I don't know");
  }

  /**
   * Method answer() should work well for TrureFalse when answer is correct.
   */
  @Test
  public void testAnswerCorrectTrueFalse() {
    assertEquals(trueFalseTest.answer("True"), "Correct");
    assertEquals(trueFalseTest.answer("False"), "Incorrect");
    assertEquals(trueFalseTest.answer("T"), "Incorrect");
  }

  /**
   * Method answer() should work well for TrureFalse when answer is incorrect.
   */
  @Test
  public void testAnswerIncorrectTrueFalse() {
    assertEquals(trueFalseTest.answer("False"), "Incorrect");
    assertEquals(trueFalseTest.answer("T"), "Incorrect");
  }

  /**
   * Method getText() should work well for TrureFalse.
   */
  @Test
  public void testGetTextTrueFalse() {
    assertEquals(trueFalseTest.getText(), "We have class on Mondays");
  }

  /**
   * Method equals() for TrueFalse should work well.
   */

  /*
  @Test
  public void testEqualsTrueFalse() {
    Question other1 = new TrueFalse("We have class on Mondays", "True");
    Question other2 = new TrueFalse("We have class on Wednesdays", "True");
    assertTrue(trueFalseTest.equals(other1));
    assertFalse(trueFalseTest.equals(other2));

    Question a = new MultiSelect("What is one hour equal to?", "1 3", "60 minutes", "60 seconds",
        "3600 seconds", "half a day");
    assertFalse(trueFalseTest.equals(a));
    Question c = new MultiChoice("", "", "");
    assertFalse(trueFalseTest.equals(c));
    Question d = new Likert("", "", "");
    assertFalse(trueFalseTest.equals(d));
  }

  /**
   * Method compareTo() for TrueFalse should work well.
   */

  /*
  @Test
  public void testCompareToForTrueFalse() {
    assertTrue(trueFalseTest.compareTo(trueFalseTest) == 0);
    Question a = new MultiSelect("What is one hour equal to?", "1 3", "60 minutes", "60 seconds",
        "3600 seconds", "half a day");
    assertTrue(trueFalseTest.compareTo(a) < 0);
    Question b = new TrueFalse("AAA", "True");
    assertTrue(trueFalseTest.compareTo(b) > 0);
    Question c = new MultiChoice("", "", "");
    assertTrue(trueFalseTest.compareTo(c) < 0);
    Question d = new Likert("", "", "");
    assertTrue(trueFalseTest.compareTo(d) < 0);
  }
  */
}
