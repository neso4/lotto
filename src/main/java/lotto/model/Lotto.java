package lotto.model;

import static lotto.utils.LottoException.DUPLICATED_LOTTO_NUMBER;
import static lotto.utils.LottoException.LESS_THAN_MINIMUM_LOTTO_NUMBER;
import static lotto.utils.LottoException.MORE_THAN_MAXIMUM_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        validateLottoNumberRange(numbers);
        validateDuplicatedLottoNumber(numbers);
        this.numbers = sortAscendingOrder(numbers);
    }

    public List<Integer> getLotto() {
        return Collections.unmodifiableList(new ArrayList<>(numbers));
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public int calculateSameNumberCount(final Lotto compareLotto) {
        List<Integer> lottoNumbers = compareLotto.getLotto();
        return (int) lottoNumbers.stream()
                .filter(this::contains)
                .count();
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    private void validateLottoNumberRange(List<Integer> numbers) {
        for (int number : numbers) {
            validateIsLessThanMinimumLottoNumber(number);
            validateIsMoreThanMaximumLottoNumber(number);
        }
    }

    private void validateIsLessThanMinimumLottoNumber(int number) {
        if (number < MINIMUM_LOTTO_NUMBER) {
            LESS_THAN_MINIMUM_LOTTO_NUMBER.throwException();
        }
    }

    private void validateIsMoreThanMaximumLottoNumber(int number) {
        if (number > MAXIMUM_LOTTO_NUMBER) {
            MORE_THAN_MAXIMUM_LOTTO_NUMBER.throwException();
        }
    }

    private void validateDuplicatedLottoNumber(List<Integer> numbers) {
        Set<Integer> noneDuplicatedNumbers = new HashSet<>(numbers);
        if (noneDuplicatedNumbers.size() != numbers.size()) {
            DUPLICATED_LOTTO_NUMBER.throwException();
        }
    }

    private List<Integer> sortAscendingOrder(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }
}
