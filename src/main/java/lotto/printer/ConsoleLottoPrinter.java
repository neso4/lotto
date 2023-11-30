package lotto.printer;

import java.util.List;

import lotto.lotto.Lotto;

public class ConsoleLottoPrinter implements LottoPrinter {
    private static final String OPENING_SQUARE_BRACKET = "[";
    private static final String CLOSING_SQUARE_BRACKET = "]";
    private static final String COMMA_WITH_SPACE = ", ";
    private static final String ENTER = "\n";
    private static final int ZERO_INDEX = 0;
    private static final int ONE_INDEX = 1;
    private static final int TWO_INDEX = 2;
    private static final int THREE_INDEX = 3;
    private static final int FOUR_INDEX = 4;
    private static final String LOTTO_UNIT = "개\n";

    @Override
    public void askInputPurchasePrice() {
        System.out.println(PrintMessage.ASK_INPUT_PURCHASE_PRICE.getMessage());
    }

    @Override
    public void noticePurchaseLotto(List<Lotto> purchasedLottos) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ENTER).append(purchasedLottos.size()).append(PrintMessage.NOTICE_PURCHASE.getMessage());

        for (Lotto lotto : purchasedLottos) {
            stringBuilder.append(OPENING_SQUARE_BRACKET);
            for (int number : lotto.getNumbers()) {
                stringBuilder.append(number).append(COMMA_WITH_SPACE);
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            stringBuilder.append(CLOSING_SQUARE_BRACKET).append(ENTER);
        }

        System.out.println(stringBuilder);
    }

    @Override
    public void askWinningNumber() {
        System.out.println(PrintMessage.ASK_WINNING_NUMBER.getMessage());
    }

    @Override
    public void askBonusNumber() {
        System.out.println(PrintMessage.ASK_BONUS_NUMBER.getMessage());
    }

    @Override
    public void noticeResult(int[] results, Double returnRate) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PrintMessage.NOTICE_RESULT.getMessage());
        stringBuilder
                .append(PrintMessage.FIFTH_PLACE_RESULT.getMessage()).append(results[ZERO_INDEX]).append(LOTTO_UNIT);
        stringBuilder
                .append(PrintMessage.FOURTH_PLACE_RESULT.getMessage()).append(results[ONE_INDEX]).append(LOTTO_UNIT);
        stringBuilder
                .append(PrintMessage.THIRD_PLACE_RESULT.getMessage()).append(results[TWO_INDEX]).append(LOTTO_UNIT);
        stringBuilder
                .append(PrintMessage.SECOND_PLACE_RESULT.getMessage()).append(results[THREE_INDEX]).append(LOTTO_UNIT);
        stringBuilder
                .append(PrintMessage.FIRST_PLACE_RESULT.getMessage()).append(results[FOUR_INDEX]).append(LOTTO_UNIT);
        stringBuilder
                .append(PrintMessage.RETURN_RATE_FIRST.getMessage())
                .append(returnRate).append(PrintMessage.RETURN_RATE_SECOND.getMessage());

        System.out.println(stringBuilder);
    }

    @Override
    public void printException(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }
}
