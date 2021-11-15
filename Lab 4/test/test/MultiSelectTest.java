package test;

import org.junit.Before;
import org.junit.Test;

import questions.MultiSelect;
import questions.Question;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MultiSelectTest {
  Question q;

  @Before
  public void setUp() throws Exception {
    q = new MultiSelect("What is one hour equal to?",
            "1 3",
            "60 minutes", "60 seconds", "3600 seconds", "half a day");

  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorLess() {
    new MultiSelect("What is one hour equal to?",
            "1 3",
            "60 minutes");

  }
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorMore() {
    new MultiSelect("What is one hour equal to?",
            "1 3",
            "1", "2", "3", "4", "5", "6", "7", "8", "9");
  }

  @Test
  public void answer() {
    assertEquals(q.answer("1 3"), Question.CORRECT);
    assertEquals(q.answer("3 1"), Question.CORRECT);
    assertEquals(q.answer("1"), Question.INCORRECT);
    assertEquals(q.answer("4"), Question.INCORRECT);
    assertEquals(q.answer(""), Question.INCORRECT);
  }

  @Test
  public void equalsMultiSelect() {
    Question other = new MultiSelect("What is one hour equal to?",
            "1 3",
            "60 minutes", "60 seconds", "3600 seconds", "half a day");
    assertTrue(q.equals(other));
  }

  /*
  @Test
  public void testEquals() {
    Question a = new MultiSelect("What is one hour equal to?",
            "1 3",
            "60 minutes", "60 seconds", "3600 seconds", "half a day");
    assertTrue(q.equals(a));
    Question b = new TrueFalse("", "", "");
    assertFalse(q.equals(b));
    Question c = new MultiChoice("", "", "");
    assertFalse(q.equals(c));
    Question d = new Likert("", "", "");
    assertFalse(q.equals(d));
  }

  @Test
  public void compareTo() {
    assertTrue(q.compareTo(q) == 0);
    Question a = new MultiSelect("AAA",
            "1 3",
            "60 minutes", "60 seconds", "3600 seconds", "half a day");
    assertTrue(q.compareTo(a) > 0);
    Question b = new TrueFalse("", "", "");
    assertTrue(q.compareTo(b) > 0);
    Question c = new MultiChoice("", "", "");
    assertTrue(q.compareTo(c) > 0);
    Question d = new Likert("", "", "");
    assertTrue(q.compareTo(d) < 0);
  }

   */
}