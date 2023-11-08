package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoCalc;
import lotto.domain.LottoMatching;
import lotto.domain.LottoPurchase;
import lotto.domain.LottoRate;
import lotto.domain.LottoResult;
import lotto.util.InputUtil;
import lotto.view.OutputView;
import lotto.domain.LottoWinningNumber;
import lotto.util.ValidationUtil;

public class LottoPlay {
    
    private final OutputView outputView;

    private final LottoPurchase lottoPurchase;

    private final LottoWinningNumber lottoWinningNumber;

    private final LottoResult lottoResult;

    private final LottoMatching lottoMatching;

    private final LottoCalc lottoCalc;

    public LottoPlay() {
        this.outputView = new OutputView();
        this.lottoPurchase = new LottoPurchase();
        this.lottoWinningNumber = new LottoWinningNumber();
        this.lottoResult = new LottoResult();
        this.lottoMatching = new LottoMatching();
        this.lottoCalc = new LottoCalc();
    }

    public void play() {
        int userMoney = printUserMoney();
        lottoPurchase.calculateLottoCount(userMoney);
        lottoPurchase.createLottos();
        showPurchaseMessage();
        getWinningNumber();
        getBonusNumber();
        lottoMatchingAndCalc();
        showResult();
        showRate(userMoney);
    } 

    private int printUserMoney() {
        outputView.printPriceMessage();
        return getUserMoney();
    }

    private int getUserMoney() {
        try {
            return ValidationUtil.validatePurchase(InputUtil.getInput());
        } catch (IllegalArgumentException e) {
            outputView.printMessage(e.getMessage());
            return getUserMoney();
        }
    }

    private void showPurchaseMessage() {
        outputView.printPurchaseMessage(lottoPurchase.getLottoCount());
        outputView.printUserLotto(lottoPurchase.getLottos());
    }

    private void getWinningNumber() {
        outputView.winnerNumberMessage();
        lottoWinningNumber.createWinningNumber();
    }

    private void getBonusNumber() {
        outputView.bonusNumberMessage();
        lottoWinningNumber.createBonusNumber(lottoWinningNumber.getWinningNumber());
    }

    private void showResult() {
        outputView.resultIntroductionMessage();
        outputView.resultMessage(lottoResult);
    }

    private void lottoMatchingAndCalc() {
        for (Lotto lotto : lottoPurchase.getLottos()) {
            lottoMatching.matchingNumber(lotto.getNumbers(), lottoWinningNumber.getWinningNumber().getNumbers());
            lottoMatching.checkBonusNumber(lotto.getNumbers(), lottoWinningNumber.getBonusNumber());
            lottoCalc.calculateLotto(lottoMatching.getMatchingCount(), lottoResult);
        }
    }

    private void showRate(int userMoney) {
        String rate = LottoRate.calculateRate(userMoney, lottoResult);
        outputView.resultRateMessage(rate);
    }
}
