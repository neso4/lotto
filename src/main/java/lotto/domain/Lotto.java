package lotto.domain;

import java.util.Collections;
import java.util.List;
import lotto.util.Validator;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public int countMatchingLottoNumbers(Lotto winnigLotto) {
        return (int) numbers.stream()
                .filter(winnigLotto::containsNumber)
                .count();
    }

    public boolean containsNumber(int number) {
        return numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    private void validate(List<Integer> numbers) {
        numbers.forEach(Validator::validateInputRange);
        Validator.validateLottoCount(numbers);
        Validator.validateUniqueLottoNumbers(numbers);
    }

}
