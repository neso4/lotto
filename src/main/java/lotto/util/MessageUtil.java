package lotto.util;


import java.util.Comparator;
import java.util.List;

import static lotto.constant.message.InputMessage.*;
import static lotto.constant.message.OutputMessage.*;


public class MessageUtil {

    public void printPurchaseInput() {
        System.out.println(PURCHASE.getMessage());
    }

    public void printWinningInput() {
        System.out.println(WINNING.getMessage());
    }

    public void printBonusInput() {
        System.out.println(BONUS.getMessage());
    }

    public void printPurchaseCount(int purchaseCount) {
        System.out.println(purchaseCount + PURCHASE_COUNT.getMessage());
    }

    public void printPurchaseInfo(List<Integer> lottoNums) {
        lottoNums.sort(Comparator.naturalOrder());
        StringBuilder lottoNum = new StringBuilder(" ");

        lottoNum.append("[");
        for (Integer number : lottoNums) {
            lottoNum.append(number).append(",");
        }
        lottoNum.deleteCharAt(lottoNum.lastIndexOf(",")).append("]");
        System.out.println(lottoNum);
    }

    public void printWinningStats() {
        System.out.println(WINNING_STATS.getMessage());
    }

    public void printWinningStatsResult(int number, int price, int matchCount) {
        String priceWithComma = String.format("%,d", price);
        if (number == FIVE_COUNT_WITH_BONUS.getNumber()) {
            System.out.printf(WINNING_STATS_BONUS_RESULT.getMessage(), priceWithComma, matchCount);
            return;
        }
        System.out.printf(WINNING_STATS_RESULT.getMessage(), number, priceWithComma, matchCount);
    }

    public void printEarningRate(Double rate) {
        System.out.printf(EARNING_RATE.getMessage(), rate);
    }
}