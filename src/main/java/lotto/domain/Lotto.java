package lotto.domain;

import static lotto.constant.ExceptionMessage.REQUIRE_SIX_NUMBERS;
import static lotto.constant.ExceptionMessage.REQUIRE_UNIQUE_NUMBERS;
import static lotto.constant.Number.LOTTO_MAX_NUM;
import static lotto.constant.Number.LOTTO_MIN_NUM;
import static lotto.constant.Number.LOTTO_NUM_COUNT;

import java.util.HashSet;
import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
        validate();
        checkUnique();
    }

    private void validate() {
        if (numbers.size() != LOTTO_NUM_COUNT.getNumber()) {
            throw new IllegalArgumentException(REQUIRE_SIX_NUMBERS.getMessage());
        }
    }

    private void checkUnique() {
        HashSet<Integer> uniqueNumbers = new HashSet<>();
        for (Integer number : numbers) {
            if (number < LOTTO_MIN_NUM.getNumber() || number > LOTTO_MAX_NUM.getNumber()) {
                throw new IllegalArgumentException(REQUIRE_UNIQUE_NUMBERS.getMessage());
            }
            if (uniqueNumbers.contains(number)) {
                throw new IllegalArgumentException(REQUIRE_UNIQUE_NUMBERS.getMessage());
            }
            uniqueNumbers.add(number);
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
