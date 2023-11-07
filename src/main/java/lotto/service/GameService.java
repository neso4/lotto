package lotto.service;

import java.util.List;
import java.util.Map;
import lotto.domain.BonusNumber;
import lotto.domain.LotteryOffice;
import lotto.domain.LottoStore;
import lotto.domain.YieldCalculator;
import lotto.domain.Rankings;
import lotto.domain.lotto.Lotto;

public class GameService {

    private final List<Lotto> issuedLotto;
    private final int userPurchaseAmount;
    private LotteryOffice lotteryOffice;

    public GameService(List<Lotto> issuedLotto, int userPurchaseAmount) {
        this.issuedLotto = issuedLotto;
        this.userPurchaseAmount = userPurchaseAmount;
    }

    public static GameService setUpGame(int userPurchaseAmount) {
        LottoStore lottoStore = new LottoStore(userPurchaseAmount);
        while (lottoStore.isOpen()) {
            lottoStore.issueLotto();
        }
        return new GameService(lottoStore.getIssuedLotto(), userPurchaseAmount);
    }

    public void storeUserInput(Lotto winningTicket, BonusNumber bonusNumber) {
        this.lotteryOffice = new LotteryOffice(issuedLotto, winningTicket, bonusNumber);
    }

    public Map<Rankings,Integer> getStatistics() {
        return lotteryOffice.getWinningsAndCounts();
    }

    public String getCalculatedYield() {
        YieldCalculator yieldCalculator = new YieldCalculator(
                lotteryOffice.getWinningsAndCounts(), userPurchaseAmount);
        return yieldCalculator.getYield();
    }


    public List<Lotto> getIssuedLotto() {
        return issuedLotto;
    }

}

