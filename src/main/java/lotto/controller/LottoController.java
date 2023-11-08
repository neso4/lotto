package lotto.controller;

import lotto.model.*;
import lotto.view.OutputView;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class LottoController {
    private static final int RATE = 100; //수익률 구할 때 사용
    private final InputController inputController;

    public LottoController() {
        this.inputController = new InputController();
    }

    public void initLotto() {
        LottoPurchasingAmount lottoPurchasingAmount =
                inputController.getLottoPurchasingAmountFromUser();
        Lottos lottos = new Lottos(lottoPurchasingAmount.getLottoAmount());

        OutputView.showPurchasedLottoQuantity(lottoPurchasingAmount.getLottoAmount());
        for (Lotto lotto : lottos.getLottoList()) {
            OutputView.showPurchasedLotto(lotto.getLottoNumbers());
        }

        NumbersFromUser numbersFromUser = inputController.getNumbersFromUser();
        lottery(lottoPurchasingAmount, lottos, numbersFromUser);
    }

    public void lottery(LottoPurchasingAmount lottoPurchasingAmount, Lottos lottos, NumbersFromUser numbersFromUser) {
        Map<Prize, Integer> prizeCount = getLotteryPrizeCount(lottos, numbersFromUser);
        double earningRate = 0;

        OutputView.showLottoResultHead();

        for (int i = Prize.values().length - 1; i >= 0; i--) {
            if (!Prize.values()[i].equals(Prize.NOTHING))
                OutputView.showLottoResult(Prize.values()[i].getMessage(), prizeCount.get(Prize.values()[i]));
            earningRate += getEarningRate(Prize.values()[i].getPrizeOfLottery(), lottoPurchasingAmount, prizeCount.get(Prize.values()[i]));
        }
        OutputView.showEarningRate(setEarningRateFormat(earningRate));
    }

    public Map<Prize, Integer> getLotteryPrizeCount(Lottos lottos, NumbersFromUser numbersFromUser) {   //당첨금 등수별 횟수 구하기
        Map<Prize, Integer> prizeCount = initPrizeCount();
        for (Lotto lotto : lottos.getLottoList()) {
            int numberMatchCount = compareLotto(lotto, numbersFromUser);
            boolean bonusCheck = lotto.getLottoNumbers().contains(numbersFromUser.getBonusNumber());

            Prize prize = Prize.getPrize(numberMatchCount, bonusCheck);     //당첨금 등수 구하기
            prizeCount.put(prize, prizeCount.get(prize) + 1);
        }

        return prizeCount;
    }

    public int compareLotto(Lotto lotto, NumbersFromUser numbersFromUser) {
        return (int) lotto.getLottoNumbers().stream().filter(
                number -> numbersFromUser.getLotto().getLottoNumbers().contains(number)).count();
    }

    private Map<Prize, Integer> initPrizeCount() {
        Map<Prize, Integer> prizeCount = new LinkedHashMap<>();

        for (Prize prize : Prize.values()) {
            prizeCount.put(prize, 0);
        }

        return prizeCount;
    }

    private double getEarningRate(int prizeValue, LottoPurchasingAmount lottoPurchasingAmount, int numberOfWins) {
        return (double) prizeValue / lottoPurchasingAmount.getMoney() * numberOfWins * RATE;
    }

    private String setEarningRateFormat(double earningRate) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###.0");
        return decimalFormat.format(earningRate);
    }
}
