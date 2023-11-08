package lotto.model;

import lotto.constants.Constants;

import java.util.List;
import java.util.Objects;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        countNumbers(numbers);
        duplicateNumbers(numbers);
    }

    private void countNumbers(List<Integer> numbers) {
        if (numbers.size() != Constants.NUMBER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 "
                    + Constants.NUMBER_COUNT + "개여야 합니다.");
        }
    }

    private void duplicateNumbers(List<Integer> numbers) {
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (Objects.equals(numbers.get(i), numbers.get(i + 1))) {
                throw new IllegalArgumentException("[ERROR] 로또 번호에 중복된 숫자가 있으면 안됩니다.");
            }
        }
    }

    public String[] stringLotto() {
        String[] lottoNumbers = new String[numbers.size()];

        for (int i = 0; i < numbers.size(); i++) {
            lottoNumbers[i] = String.valueOf(numbers.get(i));
        }
        return lottoNumbers;
    }

    public Integer compareAnswer(Lotto answer) {
        return compareNumbers(numbers, answer.numbers);
    }

    private Integer compareNumbers(List<Integer> lotto, List<Integer> answer) {
        Integer count = 0;

        for (Integer number : answer) {
            if (lotto.contains(number)) {
                count++;
            }
        }
        return count;
    }

    public boolean compareBonus(Integer bonus) {
        return numbers.contains(bonus);
    }
}
