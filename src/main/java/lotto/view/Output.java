package lotto.view;

import static lotto.message.Message.*;

public class Output {
    public static void printWinningStatisticsMessage(){
        System.out.println(WINNING_STATISTICS.message());
        System.out.println(SEPARATOR.message());
    }

    public static void printPurchaseAmountMessage(){
        System.out.println(PURCHASE_AMOUNT.message());
    }

    public static void printPurchaseLottoQuantityMessage(int lottoQuantity){
        System.out.println(lottoQuantity+"개를 구매했습니다.");
    }
}
