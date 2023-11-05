package lotto.domain;

import static lotto.domain.constant.DomainConstant.FIVE;
import static lotto.domain.constant.DomainConstant.FOUR;
import static lotto.domain.constant.DomainConstant.SIX;
import static lotto.domain.constant.DomainConstant.THREE;

import java.util.List;

public record WinningLotto(List<Integer> numbers, int bonusNumber) {
    public LottoPrizes compare(Lotto lotto) {
        long matchCount = getMatchCount(lotto);
        if (matchCount == THREE) {
            return LottoPrizes.THREE_NUMBERS_MATCHED;
        }
        if (matchCount == FOUR) {
            return LottoPrizes.FOUR_NUMBERS_MATCHED;
        }
        if (matchCount == FIVE && lotto.isContain(bonusNumber)) {
            return LottoPrizes.FIVE_NUMBER_AND_BONUS_MATCHED;
        }
        if (matchCount == FIVE) {
            return LottoPrizes.FIVE_NUMBER_MATCHED;
        }
        if (matchCount == SIX) {
            return LottoPrizes.SIX_NUMBER_MATCHED;
        }
        return null;
    }

    public long getMatchCount(Lotto lotto) {
        return numbers.stream()
                .filter(lotto::isContain)
                .count();
    }
}
