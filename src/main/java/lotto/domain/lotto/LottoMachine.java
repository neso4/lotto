package lotto.domain.lotto;

import lotto.domain.lotto.random.LottoNumberCreator;

import java.util.List;
import java.util.stream.IntStream;

public class LottoMachine {

    private final LottoNumberCreator lottoNumberCreator;

    public LottoMachine(LottoNumberCreator lottoNumberCreator) {
        this.lottoNumberCreator = lottoNumberCreator;
    }

    public LottoCount getTotalCount(Price price) {
        return new LottoCount(price);
    }

    public Lottos createLottoNumbers(LottoCount count) {
        List<Lotto> lottos = IntStream.range(0, count.getCount())
                .mapToObj(i -> lottoNumberCreator.createLotto())
                .toList();
        return new Lottos(lottos);
    }

    public LottoStatistic getLottoStatistic(Lottos lottos, WinningNumber winningNumber) {
        LottoStatistic lottoStatistic = new LottoStatistic();
        for (Lotto lotto : lottos.getLottos()) {
            LottoResult result = winningNumber.compare(lotto);
            lottoStatistic.addLottoResult(result);
        }
        return lottoStatistic;
    }

    public EarningRate getEarningRate(LottoStatistic lottoStatistic, Price price) {
        Long totalPrize = lottoStatistic.getTotalMoney();
        return new EarningRate(totalPrize, price);
    }
}
