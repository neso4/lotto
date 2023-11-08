package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class LottoAgency {
    public Map<LottoRank, Integer> calculateLottoRank(List<Lotto> lottos, List<Integer> winningNumbers,
                                                      Integer bonusNumber) {
        Map<LottoRank, Integer> winningResult = new HashMap<>();

        for (Lotto lotto : lottos) {
            LottoRank lottoRank = compareLottoNumbers(lotto, winningNumbers, bonusNumber);
            winningResult.put(lottoRank, winningResult.getOrDefault(lottoRank, 0) + 1);
        }

        return winningResult;
    }

    public Long calculateTotalWinningAmount(Map<LottoRank, Integer> winningResult) {
        long sum = 0L;

        for (Entry<LottoRank, Integer> lottoRankIntegerEntry : winningResult.entrySet()) {
            Integer winningAmount = lottoRankIntegerEntry.getKey().getWinningAmount();
            Integer winningCount = lottoRankIntegerEntry.getValue();

            sum += ((long) winningAmount * winningCount);
        }

        return sum;
    }

    public Double calculateTotalReturnRate(Long winningAmount, Integer purchasePrice) {
        return ((double) winningAmount / purchasePrice) * 100;
    }

    private LottoRank compareLottoNumbers(Lotto lotto, List<Integer> winningNumbers, Integer bonusNumber) {
        int hit = 0;
        int bonusHit = 0;
        List<Integer> lottoNumbers = lotto.getNumbers();

        for (Integer lottoNumber : lottoNumbers) {
            if (winningNumbers.contains(lottoNumber)) {
                hit++;
            } else if (lottoNumber.equals(bonusNumber)) {
                bonusHit++;
            }
        }

        return getLottoRank(hit, bonusHit);
    }

    private LottoRank getLottoRank(int hit, int bonusHit) {
        if (hit == 3) {
            return LottoRank.FIFTH_PLACE;
        }
        if (hit == 4) {
            return LottoRank.FOURTH_PLACE;
        }
        if (hit == 5) {
            if (bonusHit == 1) {
                return LottoRank.SECOND_PLACE;
            }
            return LottoRank.THIRD_PLACE;
        }
        if (hit == 6) {
            return LottoRank.FIRST_PLACE;
        }
        return LottoRank.NO_PRIZE;
    }
}
