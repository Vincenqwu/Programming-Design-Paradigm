package test;

import org.junit.Before;
import org.junit.Test;

import questions.MultiChoice;
import questions.MultiSelect;
import questions.Question;
import questions.TrueFalse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MultiChoiceTest {
  Question q;

  @Before
  public void setUp() throws Exception {
    q = new MultiChoice("What is one hour equal to?",
            "1",
            "60 minutes", "60 seconds", "3601 seconds", "half a day");

  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    new MultiChoice("What is one hour equal to?",
            "1",
            "60 minutes");

  }
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    new MultiSelect("What is one hour equal to?",
            "1",
            "1", "2", "3", "4", "5", "6", "7", "8", "9");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    new MultiSelect("What is one hour equal to?",
            "1, 2",
            "1", "2", "3", "4", "5", "6", "7");
  }

  @Test
  public void answer() {
    assertEquals(q.answer("1"), Question.CORRECT);
    assertEquals(q.answer("2"), Question.INCORRECT);
    assertEquals(q.answer("3"), Question.INCORRECT);
    assertEquals(q.answer("4"), Question.INCORRECT);
    assertEquals(q.answer(""), Question.INCORRECT);
    assertEquals(q.answer("1 2 3"), Question.INCORRECT);
  }

  @Test
  public void equalsMultiChoice() {
    Question other = new MultiChoice("What is one hour equal to?",
            "1",
            "60 minutes", "60 seconds", "3601 seconds", "half a day");
    assertTrue(q.equals(other));
  }

  /*

  @Test
  public void testEquals() {
    Question a = new MultiChoice("What is one hour equal to?",
            "1",
            "60 minutes", "60 seconds", "3601 seconds", "half a day");
    assertTrue(q.equals(a));
    Question b = new TrueFalse("Test", "False");
    assertFalse(q.equals(b));
    Question c = new MultiSelect("", "", "");
    assertFalse(q.equals(c));
    //Question d = new Likert("", "", "");
    //assertFalse(q.equals(d));
  }

  @Test
  public void compareTo() {
    assertTrue(q.compareTo(q) == 0);
    Question a = new MultiChoice("What is one hour equal to?",
            "1",
            "60 minutes", "60 seconds", "3601 seconds", "half a day");
    assertTrue(q.compareTo(a) > 0);
    Question b = new TrueFalse("Test", "False");
    assertTrue(q.compareTo(b) > 0);
    Question c = new MultiSelect("", "", "");
    assertTrue(q.compareTo(c) > 0);
    //Question d = new Likert("", "", "");
    //assertTrue(q.compareTo(d) < 0);
  }

*/
}