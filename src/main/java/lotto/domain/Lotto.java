package lotto.domain;

import lotto.exception.ErrorMessage;
import lotto.validator.ValidationUtils;

import java.util.List;

import static lotto.constants.LottoConstants.NUMBERS_SIZE;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != NUMBERS_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBERS_INVALID_SIZE.getMessage());
        }
        ValidationUtils.validateNumbersInRange(numbers);
        ValidationUtils.validateDuplicate(numbers);
    }

    public PrizeCondition getPrizeCondition(WinningLotto winningLotto) {
        int countMatchingNumbers = countMatchingNumbers(winningLotto.getWinningNumbers());
        boolean containsBonusNumber = containsBonusNumber(winningLotto.getBonusNumber());
        return PrizeCondition.findPrizeCondition(countMatchingNumbers, containsBonusNumber);
    }

    private int countMatchingNumbers(WinningNumbers comparingNumbers) {
        return (int) numbers.stream()
                .filter(number -> comparingNumbers.getNumbers().contains(number))
                .count();
    }

    private boolean containsBonusNumber(BonusNumber bonusNumber) {
        return numbers.contains(bonusNumber.getNumber());
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }
}
