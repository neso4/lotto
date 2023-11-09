package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoRepository;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.dto.WinningStatistics;
import lotto.dto.PurchasedLottosDto;
import lotto.service.LottoFactoryService;
import lotto.service.RankCalculateService;

public class LotteryStore {


    LottoFactoryService lottoFactoryService = new LottoFactoryService();
    LottoRepository lottoRepository = LottoRepository.getInstance();
    RankCalculateService rankCalculateService = new RankCalculateService();

    public void getLottoOrderUpTo(int count) {

        List<Lotto> orderedLottos = lottoFactoryService.createRandomLottoUpto(count);

        lottoRepository.saveAll(orderedLottos);
    }

    public PurchasedLottosDto showPurchasedLottos() {

        List<Lotto> purchasedLottos = lottoRepository.showAllLottos();

        return PurchasedLottosDto.from(purchasedLottos);
    }

    public WinningStatistics calculateStatistics() {

        List<Rank> ranks = rankCalculateService.calculateRanks();

        return WinningStatistics.from(ranks);
    }

    public void decideWinningLottoFrom(List<String> winningLottoNumbers, String bonusNumber) {

        WinningLotto winningLotto = lottoFactoryService.createWinningLottoFrom(winningLottoNumbers, bonusNumber);

        lottoRepository.save(winningLotto);
    }


}
