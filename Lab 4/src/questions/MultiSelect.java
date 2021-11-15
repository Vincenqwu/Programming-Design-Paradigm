package questions;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MultiSelect extends AbstractMultiSelect{
  public MultiSelect(String text, String answers, String... options) {
    super(text, answers, options);
    if (options.length < 3 || options.length > 8) {
      throw new IllegalArgumentException("Options cannot be lower than 3 or more than 8.");
    }
  }

  @Override
  public String answer(String answer) {
    List<Integer> answers = parseString(answer);
    Collections.sort(answers);

    if (answers.equals(this.getAnswers())) {
      return Question.CORRECT;
    }
    return Question.INCORRECT;
  }

  @Override
  public boolean equalsMultiSelect(MultiSelect o) {
    return Objects.equals(getText(), o.getText())
            && Objects.equals(getAnswers(), o.getAnswers())
            && Objects.equals(getOptions(), o.getOptions());
  }


  @Override
  public boolean equals(Object o) {
    if (o instanceof AbstractMultiSelect) {
      AbstractMultiSelect aMultiSelect = (AbstractMultiSelect) o;
      return aMultiSelect.equalsMultiSelect(this);
    }
    return false;
  }

  @Override
  public int compareToLikert(Likert o) {
    return -1;
  }
}
