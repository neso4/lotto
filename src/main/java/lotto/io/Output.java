package lotto.io;

import lotto.domain.Constants;

import java.util.List;

public class Output {

    public void printInputPurchaseAmountMessage() {
        System.out.println(Constants.PURCHASE_AMOUNT_MESSAGE);
    }

    public void printPurchaseQuantity(int lottoQuantity) {
        System.out.println();
        System.out.printf(Constants.QUANTITY_MESSAGE, lottoQuantity);
    }

    public void printMyLottos(List<String> myLottos) {
        for (String lottoNumbers : myLottos) {
            System.out.println(lottoNumbers);
        }
    }

    public void printInputWinningNumbersMessage() {
        System.out.println();
        System.out.println(Constants.INPUT_WINNING_NUMBERS);
    }

    public void printInputBonusNumberMessage() {
        System.out.println();
        System.out.println(Constants.INPUT_BONUS_NUMBER);
    }

    public void printResult(List<Integer> winningLottoCounts, double profit) {
        printWinningResultMessage();
        printWinningCounts(winningLottoCounts);
        printProfit(profit);
    }

    private static void printWinningResultMessage() {
        System.out.println();
        System.out.println(Constants.WINNING_STATS_MESSAGE);
        System.out.println(Constants.THREE_HYPHEN);
    }

    private static void printWinningCounts(List<Integer> winningLottoCounts) {
        System.out.printf(Constants.WINNING_THREE_NUMBERS, winningLottoCounts.get(Constants.FIFTH_RANK));
        System.out.printf(Constants.WINNING_FOUR_NUMBERS, winningLottoCounts.get(Constants.FOURTH_RANK));
        System.out.printf(Constants.WINNING_FIVE_NUMBERS, winningLottoCounts.get(Constants.THIRD_RANK));
        System.out.printf(Constants.WINNING_FIVE_ADD_BONUS_NUMBERS, winningLottoCounts.get(Constants.SECOND_RANK));
        System.out.printf(Constants.WINNING_SIX_NUMBERS, winningLottoCounts.get(Constants.FIRST_RANK));
    }

    private static void printProfit(double profit) {
        System.out.printf(Constants.PROFIT, profit);
    }
}

