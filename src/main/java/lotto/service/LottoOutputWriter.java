package lotto.service;

import static lotto.exception.ExceptionConstant.ERROR_PREFIX;
import static lotto.exception.ExceptionConstant.EXCEPTION_MESSAGE_FORMAT;
import static lotto.view.read.InputMessage.READ_LOTTO_BONUS_NUMBER;
import static lotto.view.read.InputMessage.READ_LOTTO_WIN_NUMBERS;
import static lotto.view.read.InputMessage.READ_PURCHASE_AMOUNT_MESSAGE;
import static lotto.view.write.OutputMessage.LOTTO_PROFIT_MESSAGE;
import static lotto.view.write.OutputMessage.LOTTO_PURCHASE_AMOUNT_MESSAGE;
import static lotto.view.write.OutputMessage.LOTTO_RESULT_MESSAGE;
import static lotto.view.write.OutputMessage.LOTTO_STATISTICS_MESSAGE;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.number.Lotto;
import lotto.domain.result.Grade;
import lotto.view.write.OutputView;

public final class LottoOutputWriter {

    private final OutputView outputView;

    public static LottoOutputWriter of(OutputView outputView) {
        return new LottoOutputWriter(outputView);
    }

    private LottoOutputWriter(OutputView outputView) {
        this.outputView = outputView;
    }

    public void showPurchaseAmountInputMessage() {
        outputView.write(READ_PURCHASE_AMOUNT_MESSAGE.getMessage());
    }

    public void showLottoWinNumbersInputMessage() {
        outputView.write(READ_LOTTO_WIN_NUMBERS.getMessage());
    }

    public void showPurchaseLottoSize(int count) {
        outputView.write(String.format(LOTTO_PURCHASE_AMOUNT_MESSAGE.getMessage(), count));
    }

    public void showLottoBonusNumberInputMessage() {
        outputView.write(READ_LOTTO_BONUS_NUMBER.getMessage());
    }

    public void showExceptionMessage(String message) {
        outputView.write(String.format(EXCEPTION_MESSAGE_FORMAT, ERROR_PREFIX, message));
    }

    public void showStatistics(Grade grade, Integer count) {
        outputView.write(String.format(LOTTO_STATISTICS_MESSAGE.getMessage(), grade.getDescription(), count));
    }

    public void showProfit(double percentage) {
        outputView.write(String.format(LOTTO_PROFIT_MESSAGE.getMessage(), percentage));
    }

    public void showResult() {
        outputView.write(LOTTO_RESULT_MESSAGE.getMessage());
    }

    public void showLottos(List<Lotto> lottos) {
        String allLottos = lottos.stream()
                .map(it -> it.getNumbers()
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ", "[", "]")))
                .collect(Collectors.joining("\n"));

        outputView.write(allLottos);
    }
}
