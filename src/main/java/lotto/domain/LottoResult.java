package lotto.domain;

import static lotto.globar.LottoResultInfo.*;

import java.util.List;
import lotto.globar.LottoResultInfo;

public class LottoResult {

    LottoResultInfo lottoResultInfo;
    private LottoResult() {
    }

    private LottoResult(LottoResultInfo lottoResultInfo) {
        this.lottoResultInfo = lottoResultInfo;
    }

    public static LottoResult createLottoResult(Lotto lotto, LottoWinningCombination lottoWinningCombination) {
        List<Integer> lottoNumbers = lotto.getNumbers();
        List<Integer> winnerNums = lottoWinningCombination.getWinnerNums();
        int bonusNum = lottoWinningCombination.getBonusNum();

        int matchingNumbersCount = matchLottoWithWinnerNums(lottoNumbers, winnerNums);

        if (matchingNumbersCount == 6) {
            return new LottoResult(SIX_MATCH);
        }
        if (matchingNumbersCount == 5 && bonusNumMatched(lottoNumbers, bonusNum)) {
            return new LottoResult(FIVE_MATCH_WITH_BONUS);
        }
        if (matchingNumbersCount == 5) {
            return new LottoResult(FIVE_MATCH);
        }
        if (matchingNumbersCount == 4) {
            return new LottoResult(FOUR_MATCH);
        }
        if (matchingNumbersCount == 3) {
            return new LottoResult(THREE_MATCH);
        }
        return null;
    }


    private static boolean bonusNumMatched(List<Integer> lottoNumbers, int bonusNum) {
        return lottoNumbers.contains(bonusNum);
    }

    private static int matchLottoWithWinnerNums(List<Integer> lottoNumbers, List<Integer> winnerNums) {
        return (int) lottoNumbers
                .stream()
                .filter(winnerNums::contains)
                .count();
    }
}
