package lotto.data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.message.ErrorMessage;

public class WinningCombination extends Lotto {
    private final int bonusNumber;

    public WinningCombination(List<Integer> numbers, int bonusNumber) {
        super(numbers);
        validateLottoNum(bonusNumber);
        validateContains(numbers, bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public Map<LottoPrize, BigDecimal> getResultWith(List<Lotto> lottos) {
        Map<LottoPrize, BigDecimal> result = initResult();
        for (Lotto lotto : lottos) {
            int matchCount = lotto.getMatchCountWith(getNumbers());
            LottoPrize lottoResult = LottoPrize.of(matchCount, lotto.contains(bonusNumber));
            BigDecimal count = result.get(lottoResult).add(BigDecimal.ONE);
            result.put(lottoResult, count);
        }
        return result;
    }

    private void validateContains(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.WINNING_NUMBERS_CONTAINS_BONUS_NUMBER.getMessage());
        }
    }

    private HashMap<LottoPrize, BigDecimal> initResult() {
        HashMap<LottoPrize, BigDecimal> result = new HashMap<>();
        result.put(LottoPrize.FIRST, BigDecimal.ZERO);
        result.put(LottoPrize.SECOND, BigDecimal.ZERO);
        result.put(LottoPrize.THIRD, BigDecimal.ZERO);
        result.put(LottoPrize.FOURTH, BigDecimal.ZERO);
        result.put(LottoPrize.FIFTH, BigDecimal.ZERO);
        result.put(LottoPrize.NONE, BigDecimal.ZERO);
        return result;
    }

}
