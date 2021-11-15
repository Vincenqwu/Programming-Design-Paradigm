package questions;

import java.util.List;
import java.util.Objects;

public class MultiChoice extends AbstractMultiSelect {
  public MultiChoice(String text, String answers, String... options) {
    super(text, answers, options);
    if (options.length < 3 || options.length > 8) {
      throw new IllegalArgumentException("Options cannot be lower than 3 or more than 8.");
    }
    List<Integer> answersList = parseString(answers);
    if (answersList.size() != 1) {
      throw new IllegalArgumentException("Only one correct answer.");
    }
  }

  @Override
  public String answer(String answer) {
    List<Integer> answers = parseString(answer);
    if (answers.equals(this.getAnswers())) {
      return Question.CORRECT;
    }
    return Question.INCORRECT;
  }

  @Override
  public boolean equalsMultiChoice(MultiChoice o) {
    return Objects.equals(getText(), o.getText())
            && Objects.equals(getAnswers(), o.getAnswers())
            && Objects.equals(getOptions(), o.getOptions());
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof AbstractMultiSelect) {
      AbstractMultiSelect aMultiSelect = (AbstractMultiSelect) o;
      return aMultiSelect.equalsMultiChoice(this);
    }
    return false;
  }

  @Override
  public int compareToLikert(Likert o) {
    return -1;
  }

  @Override
  public int compareToMultiSelect(MultiSelect o) {
    return -1;
  }

}

