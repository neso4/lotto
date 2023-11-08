package lotto.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.constant.Rank;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningNumbers;
import lotto.error.ErrorMessage;
import lotto.service.LottoService;
import lotto.util.NumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        int lottoCount = getLottoCount(purchaseAmount);
        List<Lotto> lottos = getLottos(lottoCount);
        WinningNumbers winningNumbers = getWinningNumber();
        BonusNumber bonusNumber = getBonusNumber(winningNumbers);
        LottoResult lottoResult = lottoService.getLottoResult(lottos, winningNumbers, bonusNumber);
        float revenue = lottoService.getRevenue(lottoResult);

        announceLottoResult(lottoResult);
        announceRevenue(purchaseAmount, revenue);
    }

    private PurchaseAmount getPurchaseAmount() {
        while (true) {
            try {
                outputView.printPurchaseInputMessage();
                int number = inputView.getNumber();
                outputView.println();
                return new PurchaseAmount(number);
            } catch(IllegalArgumentException e) {
                outputView.printErrorMessage(ErrorMessage.INVALID_LOTTO_PURCHASE_AMOUNT);
            }
        }
    }

    private int getLottoCount(final PurchaseAmount purchaseAmount) {
        int lottoCount = lottoService.getLottoCount(purchaseAmount);
        outputView.printPurchaseCountMessage(lottoCount);
        return lottoCount;
    }

    private List<Lotto> getLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            Lotto lotto = getLotto();
            lottos.add(lotto);
        }
        outputView.println();
        return lottos;
    }

    private Lotto getLotto() {
        while (true) {
            try {
                List<Integer> numbers = getSortedLottoNumbers();
                Lotto lotto = new Lotto(numbers);
                outputView.printLottoNumber(numbers);
                return lotto;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(ErrorMessage.DUPLICATE_LOTTO_NUMBER);
            }
        }
    }

    private WinningNumbers getWinningNumber() {
        while (true) {
            try {
                outputView.printWinningNumberInputMessage();
                List<Integer> numbers = inputView.getNumbers();
                WinningNumbers winningNumber = new WinningNumbers(numbers);
                outputView.println();
                return winningNumber;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(ErrorMessage.INVALID_WINNING_NUMBERS);
            }
        }
    }

    private BonusNumber getBonusNumber(final WinningNumbers winningNumber) {
        while (true) {
            try {
                outputView.printBonusNumberInputMessage();
                int number = inputView.getNumber();
                BonusNumber bonusNumber = new BonusNumber(number, winningNumber);
                outputView.println();
                return bonusNumber;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(ErrorMessage.INVALID_BONUS_NUMBER);
            }
        }
    }

    private void announceLottoResult(final LottoResult lottoResult) {
        outputView.printResultStringMessage();
        for (int rank = Rank.FIRST.getRank(); rank <= Rank.FIFTH.getRank(); rank++) {
            int rankCount = 0;
            if (lottoResult.contains(rank)) {
                rankCount = lottoResult.get(rank);
            }
            outputView.printLottoResult(rankCount, rank);
        }
    }

    private void announceRevenue(final PurchaseAmount purchaseAmount, float revenue) {
        float earningRate = lottoService.getEarningRate(purchaseAmount, revenue);
        outputView.printRevenue(earningRate);
    }

    private List<Integer> getSortedLottoNumbers() {
        List<Integer> numbers = NumberGenerator.generate();
        Collections.sort(numbers);
        return numbers;
    }
}
