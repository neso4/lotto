package lotto.model;

import java.util.List;
import lotto.constants.Constants;
import lotto.constants.ResultMessage;
import lotto.view.OutputView;

public class Calculation {
    private List<Integer> result;
    private int purchaseAmount;

    public Calculation(List<Integer> result, int purchaseAmount) {
        this.result = result;
        this.purchaseAmount = purchaseAmount;
    }

    public void displayStatistics() {
        double sumPrize = calculateSumPrize();
        double beforeRate = sumPrize / purchaseAmount * Constants.PERCENTAGE.getConstants();
        double rate = Math.round(beforeRate * 10.0) / 10.0;
        OutputView.printResult(result, rate);
    }

    private double calculateSumPrize() {
        double sumPrize = 0;
        for (ResultMessage resultMessage : ResultMessage.values()) {
            int winningAmount = resultMessage.getWinningAmount();
            int index = resultMessage.ordinal();
            sumPrize += winningAmount * result.get(index);
        }
        return sumPrize;
    }
}
