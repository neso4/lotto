package lotto.service;

import lotto.domain.*;

import java.util.List;
import java.util.Map;

public class LottoGameService {
    private LottoGenerator lottoGenerator;
    private Roulette roulette;
    private WinningLotto winningLotto;

    public LottoGameService() {
        this.lottoGenerator = new LottoGenerator();
    }

    public Lottos buyLottos(LottoPurchase lottoPurchase) {
        return lottoGenerator.buyLottos(lottoPurchase);
    }

    public WinningLotto getWinningLotto(List<Integer> numbers, int bonusNumber) {
        return WinningLotto.of(numbers, bonusNumber);
    }

    public Map<RouletteResult, Integer> match(WinningLotto winningLotto, Lottos lottos) {
        roulette = Roulette.of(winningLotto, lottos);
        roulette.getRouletteLottos();
        return roulette.getResult();
    }

    public double getRate(LottoPurchase lottoPurchase) {
        return roulette.getRateOfResult(lottoPurchase);
    }
}
