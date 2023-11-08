package lotto.model;

import lotto.config.LottoPrize;

import java.util.List;

public class LottoDraw {
    private static final int USE_BONUS_NUMBER_MATCH_COUNT = 5;

    private final int matchCount;
    private final boolean matchBonus;

    public LottoDraw(List<Integer> lottoNumbers, List<Integer> winningNumbers, int bonusNumber) {
        this.matchCount = compareWinningNumber(lottoNumbers, winningNumbers);
        this.matchBonus = isContainBonusNumber(lottoNumbers, bonusNumber);
    }

    private int compareWinningNumber(List<Integer> lottoNumbers, List<Integer> winningNumbers) {
        return (int) lottoNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private boolean isContainBonusNumber(List<Integer> lottoNumbers, int bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    private boolean isUseBonusNumber() {
        return matchCount == USE_BONUS_NUMBER_MATCH_COUNT;
    }

    public LottoPrize getPrize() {
        return LottoPrize.getPrize(matchCount, matchBonus && isUseBonusNumber());
    }
}