package lotto.controller;


import static lotto.domain.LottoConfig.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.InputHandler;
import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoPurchaseCalculator;
import lotto.domain.LottoResultCalculator;
import lotto.domain.LottoResults;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.PrintMessages;
import lotto.domain.RevenueCalculator;
import lotto.domain.WinningNumbers;
import lotto.strategy.NumberGenerationStrategy;
import lotto.view.Input;
import lotto.view.Output;

public class LottoController {

    private final Input input;
    private final Output output;
    private final LottoGenerator lottoGenerator;

    private final InputHandler inputHandler;

    private Money money;

    private String winNumbers;

    private int bonusNumber;

    public LottoController(NumberGenerationStrategy numberGenerationStrategy, Input input,
            Output output) {
        this.input = input;
        this.output = output;
        this.lottoGenerator = new LottoGenerator(numberGenerationStrategy);
        this.inputHandler = new InputHandler(input, output);
    }

    public void run() {
        inputPurchaseAmount();
        Lottos purchasedLottos = purchaseLottos();
        WinningNumbers winningNumbers = requestWinningNumbers();
        displayResults(purchasedLottos, winningNumbers);
    }

    private void inputPurchaseAmount() {
        money = new Money(inputHandler.requestNumberInput(
                PrintMessages.INPUT_PURCHASE_AMOUNT.getMessage()));
    }


    private Lottos purchaseLottos() {
        int lottoCount = calculateLottoCount(money);
        List<Lotto> lottos = generateLottos(lottoCount);
        Lottos purchasedLottos = new Lottos(lottos);
        output.printPurchasedLottos(purchasedLottos);
        return purchasedLottos;
    }

    private int calculateLottoCount(Money money) {
        return LottoPurchaseCalculator.calculateLottoCount(1000, money);
    }

    private List<Lotto> generateLottos(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> lottoGenerator.generateLotto())
                .collect(Collectors.toList());
    }

    private WinningNumbers requestWinningNumbers() {
        inputWiningNumbers();
        inputBonusNumbers();
        return WinningNumbers.of(winNumbers, bonusNumber);
    }

    private void inputWiningNumbers() {
        winNumbers = inputHandler.requestStringInput(
                PrintMessages.INPUT_WINNING_NUMBERS.getMessage());
    }

    private void inputBonusNumbers() {
        bonusNumber = inputHandler.requestNumberInput(
                PrintMessages.INPUT_BONUS_NUMBER.getMessage());
    }


    private void displayResults(Lottos lottos, WinningNumbers winningNumbers) {
        LottoResults results = calculateLottoResults(lottos, winningNumbers);
        printLottoResults(results);
    }

    private LottoResults calculateLottoResults(Lottos lottos, WinningNumbers winningNumbers) {
        return LottoResultCalculator.calculateResults(lottos, winningNumbers);
    }

    private void printLottoResults(LottoResults results) {
        output.displayInputRequest(PrintMessages.WINNING_STATISTICS.getMessage());
        output.displayInputRequest(PrintMessages.SEPARATOR.getMessage());
        output.printWinningStatistics(results);
        output.printTotalEarningsRate(calculateTotalEarningsRate(results));
    }

    private double calculateTotalEarningsRate(LottoResults results) {
        return RevenueCalculator.calculateRevenueRate(
                results.calculateTotalEarnings(), money);
    }
}
