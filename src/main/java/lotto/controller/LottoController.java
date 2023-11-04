package lotto.controller;

import lotto.domain.*;
import lotto.io.Input;
import lotto.io.Output;

import java.util.List;

public class LottoController {

    final Input input;
    final Output output;

    public LottoController(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public void start() {
        int money = getUserAmount();
        int lottoQuantity = getLottoQuantity(money);

        List<Lotto> myLottos = buyLottos(lottoQuantity);
        List<Integer> winningNumbers = generateWinningNumbers();
        int bonusNumber = generateBonusNumber();

        List<Integer> winningLottoCounts = getWinningLottosCount(myLottos, winningNumbers, bonusNumber);
        double profit = getLottoProfit(winningLottoCounts, money);

        output.printResult(winningLottoCounts, profit);
    }

    private int getUserAmount() {
        output.printInputPurchaseAmountMessage();
        return input.getUserAmount();
    }

    private int getLottoQuantity(int money) {
        int lottoQuantity = money / Constants.LOTTO_PRICE;
        output.printPurchaseQuantity(lottoQuantity);
        return lottoQuantity;
    }

    private List<Lotto> buyLottos(int lottoQuantity) {
        Lottos lottos = new Lottos(lottoQuantity);
        List<Lotto> myLottos = lottos.getMyLottos();
        output.printMyLottos(myLottos);
        return myLottos;
    }

    private List<Integer> generateWinningNumbers() {
        output.printInputWinningNumbersMessage();
        return input.getWinningNumbers();
    }

    private int generateBonusNumber() {
        output.printInputBonusNumberMessage();
        return input.getBonusNumber();
    }

    private List<Integer> getWinningLottosCount(List<Lotto> myLottos, List<Integer> winningNumbers, int bonusNumber) {
        WinningChecker winningChecker = new WinningChecker(myLottos, winningNumbers, bonusNumber);
        return winningChecker.countWinningLottos();
    }

    private double getLottoProfit(List<Integer> winningLottoCounts, int money) {
        ProfitCalculator profitCalculator = new ProfitCalculator(winningLottoCounts);
        double profit = profitCalculator.calculateProfit(money);
        System.out.println(profit);
        return profit;
    }
}