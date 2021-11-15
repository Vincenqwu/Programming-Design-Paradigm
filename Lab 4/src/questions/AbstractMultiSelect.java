package questions;

import java.awt.desktop.QuitEvent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractMultiSelect implements Question {
  private String text;
  private List<Integer> answers;
  private List<String> options;

  public AbstractMultiSelect(String text, String answers, String... options) {
    this.text = text;
    this.answers = this.parseString(answers);
    this.options = List.of(options);
  }

  // ===================Getters===================
  @Override
  public String getText() {
    return text;
  }

  public List<Integer> getAnswers() {
    return answers;
  }


  public List<String> getOptions() {
    return options;
  }
  // ==============================================


  /**
   * Convert String answer to List of integers.
   * @param s
   * @return
   */
  protected List<Integer> parseString(String s) {
    List<Integer> answers = new LinkedList<>();

    for (String a : s.split(" ")) {
      if (a.length() > 0) {
        answers.add(Integer.parseInt(a));
      }
    }

    return answers;
  }


  // ========compareTo() and equals() methods for double dispatch========
  public boolean equalsLikert(Likert o) {
    return false;
  }

  public boolean equalsMultiChoice(MultiChoice o) {
    return false;
  }

  public boolean equalsMultiSelect(MultiSelect o) {
    return false;
  }

  public boolean equalsTrueFalse(TrueFalse o) {
    return false;
  }

  @Override
  public int compareTo(Question o) {
    if (this == o) { return 0; }
    Class c = o.getClass();
    if (getClass() != c) {
      if (c == Likert.class) {
        return this.compareToLikert((Likert) o);
      }
      else if (c == MultiChoice.class) {
        return this.compareToMultiChoice((MultiChoice) o);
      }
      else if (c == MultiSelect.class) {
        return this.compareToMultiSelect((MultiSelect) o);
      }
      else if (c == TrueFalse.class) {
        return this.compareToTrueFalse((TrueFalse) o);
      }
    }
    return getText().compareTo(o.getText());
  }

  public int compareToLikert(Likert o) {
    return 1;
  }

  public int compareToMultiChoice(MultiChoice o) {
    return 1;
  }

  public int compareToMultiSelect(MultiSelect o) {
    return 1;
  }

  public int compareToTrueFalse(TrueFalse o) {
    return 1;
  }
  // =====================================================

  @Override
  public int hashCode() {
    return Objects.hash(text, answers, options);
  }
}