package lotto.view;

import java.util.List;

public class OutputView {

    private static final String REQUEST_PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String LOTTO_COUNT_MESSAGE = "개를 구매했습니다.";

    private void printWithNewLine(String s) {
        System.out.println(s);
    }

    private void print(String s) {
        System.out.print(s);
    }

    public void printPurchaseAmountMessage() {
        printWithNewLine(REQUEST_PURCHASE_AMOUNT_MESSAGE);
    }

    public void printLottoCount(int lottoCount) {
        printWithNewLine(lottoCount + LOTTO_COUNT_MESSAGE);
    }

    public void printLottoTicketNumbers(List<String> lottoNumbers) {
        lottoNumbers.forEach(System.out::print);
    }

    public void printErrorMessage(String message) {
        printWithNewLine(message);
    }
}
