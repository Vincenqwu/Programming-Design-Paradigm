package questions;

import java.util.List;
import java.util.Objects;

public class TrueFalse extends AbstractMultiSelect {

  public TrueFalse(String text, String answer) {
    super(text, parseAnswer(answer));
    if (parseAnswer(answer) == "-1") {
      throw new IllegalArgumentException("The answer should be \"True\" or \"False\"");
    }
  }

  private static String parseAnswer(String answer) {
    if (answer == "True") {
      return "1"; // The answer is "1" when answering "true"
    } else if (answer == "False") {
      return "0"; // The answer is "0" when answering "false"
    } else {
      return "-1"; // The answer is "-1" when answering others
    }
  }

  @Override
  public String answer(String answer) {
    List<Integer> answers = parseString(parseAnswer(answer));
    if (answers.equals(this.getAnswers())) {
      return Question.CORRECT;
    }
    return Question.INCORRECT;
  }

  @Override
  public boolean equalsTrueFalse(TrueFalse o) {
    return Objects.equals(getText(), o.getText()) && Objects.equals(getAnswers(), o.getAnswers());
  }
  
  @Override
  public boolean equals(Object o) {
    if (o instanceof AbstractMultiSelect) {
      AbstractMultiSelect aMultiSelect = (AbstractMultiSelect) o;
      return aMultiSelect.equalsTrueFalse(this);
    }
    return false;
  }
  
  @Override
  public int compareToLikert(Likert o) {
    return -1;
  }

  @Override
  public int compareToMultiChoice(MultiChoice o) {
    return -1;
  }

  @Override
  public int compareToMultiSelect(MultiSelect o) {
    return -1;
  }
}
