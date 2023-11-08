package lotto.manager;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LottoManager {

    private static final int DEFAULT_LOTTO_PRICE = 1000;

    private final NumberGenerator<List<Integer>> lottoNumberGenerator;

    public LottoManager(NumberGenerator<List<Integer>> lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public Lottos createLottos(Payment payment) {
        int affortableLottoCount = payment.countAffortable(DEFAULT_LOTTO_PRICE);
        List<Lotto> lottos = issueLottosByCount(affortableLottoCount);
        return new Lottos(lottos);
    }

    private List<Lotto> issueLottosByCount(int affortableLottoCount) {
        return IntStream.range(0, affortableLottoCount)
                .mapToObj(idx -> {
                    List<Integer> lottoNumbers = new ArrayList<>(lottoNumberGenerator.generate());
                    Collections.sort(lottoNumbers);
                    return new Lotto(lottoNumbers);
                })
                .toList();
    }

    private WinningLotto createWinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private LottoRankings createWinningRankings(Lottos lottos, WinningLotto winningLotto) {
        List<LottoRanking> winRankings = lottos.calculateRankings(winningLotto);
        return new LottoRankings(winRankings);
    }

    public double calculateProfitPercentage(Payment payment, LottoRankings winningRankings) {
        long totalPrize = winningRankings.calculateTotalPrize();
        return payment.calculatePercentageOfProfit(totalPrize);
    }

    public LottoRankings calculateWinningRankings(Lottos lottos, List<Integer> winningNumbers, int bonusNumber) {
        WinningLotto winningLotto = createWinningLotto(winningNumbers, bonusNumber);
        return createWinningRankings(lottos, winningLotto);
    }
}
