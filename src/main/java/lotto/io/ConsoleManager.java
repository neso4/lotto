package lotto.io;

import static lotto.model.WinningGrade.NONE_GRADE;

import java.util.Arrays;
import java.util.List;
import lotto.io.processor.InputProcessor;
import lotto.io.processor.OutputProcessor;
import lotto.model.BonusNumber;
import lotto.model.Lotto;
import lotto.model.ProfitRate;
import lotto.model.PurchasePrice;
import lotto.model.WinningGrade;
import lotto.model.WinningStatics;

public class ConsoleManager {

    private final InputProcessor inputProcessor;
    private final OutputProcessor outputProcessor;

    public ConsoleManager() {
        this.inputProcessor = new InputProcessor();
        this.outputProcessor = new OutputProcessor();
    }

    public void close() {
        inputProcessor.closeConsole();
    }

    public PurchasePrice inputPurchasePrice() {
        while (true) {
            outputProcessor.printPurchasePriceHint();

            String inputPrice = inputProcessor.inputValue();

            try {
                return new PurchasePrice(inputPrice);
            } catch (IllegalArgumentException e) {
                outputProcessor.outputErrorMessage(e);
            }
        }
    }

    public void printLottoCount(final int count) {
        outputProcessor.outputLottoCount(count);
    }

    public void printLottos(final List<Lotto> lottos) {
        lottos.forEach(lotto -> {
            List<Integer> numbers = lotto.getNumbers();
            outputProcessor.outputLottoNumbers(numbers);
        });
    }

    public Lotto inputWinningLottoNumbers() {
        while (true) {
            outputProcessor.outputWinningNumbersHint();

            String inputWinningNumbers = inputProcessor.inputValue();

            try {
                return new Lotto(inputWinningNumbers);
            } catch (IllegalArgumentException e) {
                outputProcessor.outputErrorMessage(e);
            }
        }
    }

    public BonusNumber inputBonusNumber() {
        while (true) {
            try {
                outputProcessor.outputBonusNumberHint();

                String inputBonusNumber = inputProcessor.inputValue();

                return new BonusNumber(inputBonusNumber);
            } catch (IllegalArgumentException e) {
                outputProcessor.outputErrorMessage(e);
            }
        }
    }

    public void printStatics(final WinningStatics statics) {
        outputProcessor.outputStaticsHint();

        Arrays.stream(WinningGrade.values())
                .filter(type -> type != NONE_GRADE)
                .forEach(type -> {
                    int count = statics.getWinningGradeCount(type);
                    outputProcessor.outputWinningStatics(type, count);
                });
    }

    public void printProfitRate(final ProfitRate profitRate) {
        outputProcessor.outputProfitRate(profitRate.getRate());
    }

}
