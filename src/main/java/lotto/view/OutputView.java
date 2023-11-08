package lotto.view;

public class OutputView {

    public static void printPurchaseLotto() {
        System.out.println(Messages.ENTER_PURCHASE_AMOUNT);
    }

    public static void printPurchaseLottoCount(int count) {
        System.out.println(String.format(Messages.PURCHASE_COUNT_MESSAGE, count));
    }

    public static void printWinningLotto() {
        System.out.println(Messages.LOTTO_NUMBERS_HEADER);
    }

    public static void printBonusNumber() {
        System.out.println(Messages.BONUS_NUMBER_MESSAGE);
    }

    public static void printLottoPrizeHeader() {
        System.out.println(Messages.WINNING_STATISTICS_HEADER);
    }
    public static void printLottoPrize(String description, String prize, int count) {
        System.out.println(String.format(Messages.WINNING_MESSAGE_FORMAT, description, prize, count));
    }

    public static void printProfit(Double profit) {
        System.out.println(String.format(Messages.TOTAL_PROFIT_MESSAGE_FORMAT, profit));
    }

}
