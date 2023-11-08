package lotto.model;

import java.util.List;
import lotto.constant.LottoPrize;
import lotto.validator.LottoNumberValidator;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        LottoNumberValidator.validateLottoNumbers(numbers);
        this.numbers = numbers;
    }

    public LottoPrize findPrize(WinningNumberAndBonusNumber winningNumberAndBonusNumber) {
        final Integer matchCount = getMatchCount(winningNumberAndBonusNumber.getWinningNumber());
        final Boolean isBonusCorrect = isContains(winningNumberAndBonusNumber.getBonusNumber());
        return LottoPrize.findPrize(matchCount, isBonusCorrect);
    }

    private Integer getMatchCount(List<Integer> winningNumber) {
        Integer matchCount = 0;
        for (Integer number : winningNumber) {
            if (isContains(number)) {
                matchCount++;
            }
        }
        return matchCount;
    }

    private Boolean isContains(Integer number) {
        return numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
