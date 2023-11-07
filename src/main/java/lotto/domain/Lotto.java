package lotto.domain;

import static lotto.constant.GameRule.*;
import static lotto.exception.ExceptionMessage.*;

import java.util.Collections;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.exception.LottoException;

public class Lotto {
    private final List<Integer> lotto;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        sortAscending(numbers);
        this.lotto = numbers;
    }

    public static Lotto createLotto() {
        List<Integer> randomNumbers = Randoms.pickUniqueNumbersInRange(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER, LOTTO_SIZE);
        return new Lotto(randomNumbers);
    }

    public void sortAscending(List<Integer> numbers) {
        Collections.sort(numbers);
    }

    public List<Integer> getLotto() {
        return this.lotto;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new LottoException(LOTTO_INVALID_SIZE.getMessage());
        }

        if (isDuplicated(numbers)) {
            throw new LottoException(LOTTO_DUPLICATED_ERROR.getMessage());
        }

        if (isNumbersNotInRange(numbers)) {
            throw new LottoException(LOTTO_OUT_OF_RANGE_ERROR.getMessage());
        }
    }

    private boolean isDuplicated(List<Integer> numbers) {
        return numbers.stream()
            .distinct()
            .count() != numbers.size();
    }

    private boolean isNumbersNotInRange(List<Integer> numbers) {
        return numbers.stream()
            .anyMatch(this::isNumberNotInRange);
    }

    private boolean isNumberNotInRange(int number) {
        return number < LOTTO_MIN_NUMBER || number > LOTTO_MAX_NUMBER;
    }
}
