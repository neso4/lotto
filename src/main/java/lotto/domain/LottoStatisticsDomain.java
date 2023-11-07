package lotto.domain;

import lotto.Lotto;

import java.util.ArrayList;
import java.util.List;

import static lotto.constants.LottoNumberConstants.LOTTO_NUMBER_RANGE;
import static lotto.constants.LottoNumberConstants.LOTTO_WINNING_NUMBER_RANGE;

public class LottoStatisticsDomain {
    private List<Lotto> purchaseLotto;
    private List<Integer> winningLottoNumber;

    public LottoStatisticsDomain(List<Lotto> purchaseLotto, List<Integer> winningLottoNumber){
        this.purchaseLotto = purchaseLotto;
        this.winningLottoNumber = winningLottoNumber;
    }

    public List<Integer> lottoStatistics() {
        List<Integer> winningNumberCounts = new ArrayList<>();
        for(Lotto lotto : purchaseLotto){
            int winningNumberCount = oneLottoCompare(lotto.oneLottoNumberCopy());
            winningNumberCounts.add(winningNumberCount);
        }
        return winningNumberCounts;
    }

    private int oneLottoCompare(List<Integer> oneLotto) {
        int count = (int) oneLotto.stream()
                .filter(winningLottoNumber::contains)
                .count();
        if(count == LOTTO_NUMBER_RANGE && isAdditionNumber(oneLotto)){
            return -count;
        }
        return count;
    }

    private boolean isAdditionNumber(List<Integer> oneLotto) {
        return oneLotto.contains(winningLottoNumber.get(LOTTO_WINNING_NUMBER_RANGE - 1));
    }
}
