package lotto.view;

import static lotto.settings.Announcement.BUY_LOTTO;
import static lotto.settings.Announcement.REQUEST_LOTTO_BONUS_NUMBERS;
import static lotto.settings.Announcement.REQUEST_LOTTO_WINNING_NUMBERS;
import static lotto.settings.Announcement.REQUEST_PURCHASE_AMOUNT;
import static lotto.settings.Announcement.PURCHASE_NUMBER;
import static lotto.settings.Announcement.WINNING_RESULTS;
import static lotto.settings.Announcement.WINNING_STATISTICS;

import lotto.settings.Announcement;

public class View {

    public static void requestPurchaseAmount(){
        print(REQUEST_PURCHASE_AMOUNT);
    }

    public static void purchaseCount(int count){
        System.out.printf(PURCHASE_NUMBER.getPrint(), count);
    }

    public static void requestWinningNumber(){
        print(REQUEST_LOTTO_WINNING_NUMBERS);
    }

    public static void requestBonusNumber(){
        print(REQUEST_LOTTO_BONUS_NUMBERS);
    }

    public static void winningStatistics(){
        print(WINNING_STATISTICS);
    }

    public static void winningResult(int num, int price, int totalNum){
        System.out.printf(WINNING_RESULTS.getPrint(),num,price,totalNum);
    }
    public static void buyLottos(String lottoNumber){
        System.out.printf(BUY_LOTTO.getPrint(),lottoNumber);}
    private static void print(Announcement announcement){
        System.out.println(announcement.getPrint());
    }
}
