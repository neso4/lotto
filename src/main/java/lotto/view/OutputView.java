package lotto.view;

import static lotto.consts.ViewMessage.BONUS_PROMPT_MESSAGE;
import static lotto.consts.ViewMessage.NUMBER_OF_PURCHASED_FORMAT;
import static lotto.consts.ViewMessage.PAYMENT_PROMPT_MESSAGE;
import static lotto.consts.ViewMessage.RATE_OF_REVENUE_FORMAT;
import static lotto.consts.ViewMessage.WINNING_NUMBERS_PROMPT_MESSAGE;
import static lotto.consts.ViewMessage.WINNING_RESULT_MESSAGE;

import java.util.List;

public class OutputView {

    private static final OutputView INSTANCE = new OutputView();

    private OutputView() {

    }

    public static OutputView getInstance() {
        return INSTANCE;
    }

    public void paymentPrompt() {
        System.out.println(PAYMENT_PROMPT_MESSAGE.get());
    }

    public void bonusPrompt() {
        System.out.println(BONUS_PROMPT_MESSAGE.get());
    }

    public void winningNumberPrompt() {
        System.out.println(WINNING_NUMBERS_PROMPT_MESSAGE.get());
    }

    public void printNumberOfPurchased(int numberOfLottoTickets) {
        System.out.println(String.format(NUMBER_OF_PURCHASED_FORMAT.get(), numberOfLottoTickets));
    }

    public void printLottoTickets(String lottoNumbers) {
        System.out.println(lottoNumbers);
    }

    public void printWinningResult(List<String> winningResult) {
        System.out.println(WINNING_RESULT_MESSAGE.get());
        winningResult.forEach(System.out::println);
    }

    public void printRateOfRevenue(double rateOfRevenue) {
        System.out.println(String.format(RATE_OF_REVENUE_FORMAT.get(), rateOfRevenue));
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
