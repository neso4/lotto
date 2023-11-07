package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoList;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.domain.dto.LottoPurchaseDto;
import lotto.service.LottoService;
import lotto.view.LottoView;

public class LottoController {
    private final LottoService lottoService = new LottoService();
    private final LottoView lottoView = new LottoView();

    public void purchaseLottos() {
        Money money = lottoView.getLottoPurchasingCost();
        LottoList lottoList = LottoList.generateRandomLottoSetSizeWith(money.getLottoCount());
        printPublishedLottos(money.getLottoCount(), lottoList.getLottos());
        List<LottoNumber> winningNumbers = lottoView.getWinningNumbers();
        LottoNumber bonusNumber = lottoView.getBonusNumberWithDuplicationCheck(winningNumbers);

        LottoPurchaseDto lottoPurchaseDto = LottoPurchaseDto.Of(lottoList, winningNumbers, bonusNumber);

        printStatistics(lottoPurchaseDto);
        calculateRateOfReturn(lottoPurchaseDto);
        printRateOfReturn(lottoPurchaseDto);
    }

    private void printStatistics(LottoPurchaseDto lottoPurchaseDto) {
        lottoView.printWinningStatistics(lottoService.totalWinnings(lottoPurchaseDto));
    }

    private void printPublishedLottos(int lottoCount, List<Lotto> lottos) {
        lottoView.printPublishedLottos(lottoCount, lottos);
    }

    private void printRateOfReturn(LottoPurchaseDto lottoPurchaseDto) {
        lottoView.printRateOfReturn(calculateRateOfReturn(lottoPurchaseDto));
    }

    private double calculateRateOfReturn(LottoPurchaseDto lottoPurchaseDto) {
        return lottoService.calculateRateOfReturn(lottoPurchaseDto);
    }

}
