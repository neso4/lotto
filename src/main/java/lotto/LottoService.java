package lotto;

import java.util.Collections;
import java.util.List;

public class LottoService {

    private final WinningNumbers winningNumbers;
    private final LottoMachine lottoMachine;
    private final RankCounter rankCounter;

    public LottoService(WinningNumbers winningNumbers, LottoMachine lottoMachine) {
        this.winningNumbers = winningNumbers;
        this.lottoMachine = lottoMachine;
        this.rankCounter = new RankCounter();
    }

    public List<Lotto> getLottosWith(Money money) {
        return lottoMachine.makeLottosWith(money);
    }

    public RankCounter rank(List<Lotto> lottos) {
        lottos.stream()
                .map(winningNumbers::rank)
                .forEach(rankCounter::increaseCount);
        return rankCounter;
    }
}
