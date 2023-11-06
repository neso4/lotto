package lotto.view;


import lotto.domain.LottoTicket;
import lotto.view.constants.RequestMessage;
import lotto.view.constants.ResponseMessage;

public class OutputView {
    private OutputView() {
    }

    public static void printRequestPurchaseAmount() {
        System.out.println(RequestMessage.PURCHASE_AMOUNT.getMessage());
    }

    public static void printRequestWinningNumber() {
        System.out.println(RequestMessage.WINNING_NUMBER.getMessage());
    }

    public static void printRequestBonusNumber() {
        System.out.println(RequestMessage.BONUS_NUMBER.getMessage());
    }

    public static void printPurchaseResult(LottoTicket lottoTicket) {
        String formattedMessage = String.format(ResponseMessage.PURCHASE_COUNT.getMessage(),
                lottoTicket.getLottoCount());
        System.out.println(formattedMessage);

        lottoTicket.getLottoTicket().stream()
                .map(lotto -> lotto.getNumbers().toString())
                .forEach(System.out::println);

        System.out.println();
    }

    public static void printResponseWinningStats() {
        String formattedMessage = String.format(ResponseMessage.WINNING_STATS.getMessage(), 1, 2, 3, 4, 5);
        System.out.println(formattedMessage);
    }

    public static void printResponseProfitRate() {
        String formattedMessage = String.format(ResponseMessage.WINNING_PROFIT_RATE.getMessage(), 1.1);
        System.out.println(formattedMessage);
    }
}
