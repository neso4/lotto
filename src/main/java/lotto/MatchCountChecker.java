package lotto;

import java.util.HashMap;
import java.util.List;
import lotto.config.LottoConfig;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.WinningNumber;

public class MatchCountChecker {
    public HashMap<String, Integer> checkRewardByLotto(HashMap<Integer, List<Integer>> checkMatchCountResult) {
        HashMap<String, Integer> countByPrize = new HashMap<>();

        countByPrize.put(LottoConfig.FIRST_PRIZE_REWARD,
                checkMatchCountByLotto(checkMatchCountResult, LottoConfig.FIRST_PRIZE_HAS_WINNING));
        countByPrize.put(LottoConfig.SECOND_PRIZE_REWARD,
                checkMatchCountByLotto(checkMatchCountResult, LottoConfig.SECOND_PRIZE_HAS_WINNING,
                        LottoConfig.SECOND_PRIZE_HAS_BONUS));
        countByPrize.put(LottoConfig.THIRD_PRIZE_REWARD,
                checkMatchCountByLotto(checkMatchCountResult, LottoConfig.THIRD_PRIZE_HAS_WINNING));
        countByPrize.put(LottoConfig.FOURTH_PRIZE_REWARD,
                checkMatchCountByLotto(checkMatchCountResult, LottoConfig.FOURTH_PRIZE_HAS_WINNING));
        countByPrize.put(LottoConfig.FIFTH_PRIZE_REWARD,
                checkMatchCountByLotto(checkMatchCountResult, LottoConfig.FIFTH_PRIZE_HAS_WINNING));

        return countByPrize;
    }

    private int checkMatchCountByLotto(HashMap<Integer, List<Integer>> compareNumberResult, int countWinningInLotto) {
        int count = 0;

        for (int key : compareNumberResult.keySet()) {
            List<Integer> compareNumbersResult = compareNumberResult.get(key);

            if (compareNumbersResult.get(LottoConfig.INDEX_WINNING_NUMBER) == countWinningInLotto) {
                count++;
            }
        }

        return count;
    }

    private int checkMatchCountByLotto(HashMap<Integer, List<Integer>> compareNumberResult,
                                       int countWinningInLotto, int countBonusInLotto) {
        int count = 0;

        for (int key : compareNumberResult.keySet()) {
            List<Integer> compareNumbersResult = compareNumberResult.get(key);

            if (compareNumbersResult.get(LottoConfig.INDEX_WINNING_NUMBER) == countWinningInLotto
                    && compareNumbersResult.get(LottoConfig.INDEX_BONUS_NUMBER) == countBonusInLotto) {
                count++;
            }
        }

        return count;
    }

    public HashMap<Integer, List<Integer>> compareLottoToNumber(HashMap<Integer, Lotto> allLotto,
                                                                 WinningNumber winningNumber, BonusNumber bonusNumber) {
        HashMap<Integer, List<Integer>> countSameNumbers = new HashMap<>();
        int countWinningInLotto;
        int countBonusInLotto;

        for (int key : allLotto.keySet()) {
            List<Integer> randomLottoNumber = allLotto.get(key).getLotto();
            countWinningInLotto = compareLottoToWinningNumber(randomLottoNumber, winningNumber.getWinningNumber());
            countBonusInLotto = compareLottoToBonusNumber(randomLottoNumber, bonusNumber.getBonusNumber());

            countSameNumbers.put(key, List.of(countWinningInLotto, countBonusInLotto));
        }

        return countSameNumbers;
    }

    private int compareLottoToWinningNumber(List<Integer> lottoNumber, List<Integer> winningNumber) {
        int countSameNumber = 0;

        for (int number : lottoNumber) {
            if (winningNumber.contains(number)) {
                countSameNumber++;
            }
        }

        return countSameNumber;
    }

    private int compareLottoToBonusNumber(List<Integer> lottoNumber, int bonusNumber) {
        int countSameNumber = 0;

        for (int number : lottoNumber) {
            if (number == bonusNumber) {
                countSameNumber++;
            }
        }

        return countSameNumber;
    }
}
