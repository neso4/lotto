package lotto.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.validator.LottoNumbersValidator;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        LottoNumbersValidator validator = new LottoNumbersValidator();
        validator.validate(numbers);
    }

    public int getDifferenceBetween(Lotto other) {
        Set<Integer> thisNumberSet = new HashSet<>(numbers);
        Set<Integer> otherNumberSet = new HashSet<>(other.numbers);
        thisNumberSet.removeAll(otherNumberSet);
        return thisNumberSet.size();
    }

    public boolean isContainBonusNumber(BonusNumber bonusNumber) {
        return numbers.contains(bonusNumber.number());
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
