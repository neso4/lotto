package lotto;

import static common.enumtype.ErrorCode.NOT_NUMBER_STRING;
import static lotto.view.InputView.inputBonusNumber;
import static lotto.view.InputView.inputLottoPurchaseAmount;
import static lotto.view.InputView.inputWinningNumbers;
import static lotto.view.OutputView.printLottoNumbers;
import static lotto.view.OutputView.printLottoResult;
import static lotto.view.OutputView.printNewLine;
import static lotto.view.OutputView.printTotalYieldRate;
import static lotto.view.OutputView.printWinningStatistics;

import common.enumtype.ResultType;
import common.exception.InvalidArgumentException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.LottoPurchaseAmount;
import lotto.domain.LottoResult;
import lotto.domain.Lottoes;
import lotto.domain.WinningNumbers;
import lotto.domain.strategy.RandomNumberStrategy;
import lotto.dto.LottoNumbers;

public class LottoGame {

    public void run() {
        LottoPurchaseAmount lottoPurchaseAmount = createAmount();
        Lottoes lottoes = createLottoes(lottoPurchaseAmount.getLottoCount());
        WinningNumbers winningNumbers = createWinningNumbersWithBonusNumber();
        LottoResult lottoResult = createLottoResult(lottoes, winningNumbers);
        printResult(
                lottoResult.getResult(),
                lottoResult.getYieldRate(lottoPurchaseAmount.getAmount())
        );
    }

    private Lottoes createLottoes(int count) {
        Lottoes lottoes = new Lottoes(new RandomNumberStrategy(), count);
        printLottoes(lottoes);
        return lottoes;
    }

    private LottoPurchaseAmount createAmount() {
        try {
            return createAmountFromUser();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createAmount();
        }
    }

    private LottoPurchaseAmount createAmountFromUser() {
        String input = inputLottoPurchaseAmount();
        int amount = parseInt(input);
        return new LottoPurchaseAmount(amount);
    }

    private void printLottoes(Lottoes lottoes) {
        List<LottoNumbers> lottoNumbers = lottoes.getAllLotto().stream()
                .map(lotto -> new LottoNumbers(lotto.getLottoNumbers()))
                .toList();
        printNewLine();
        printLottoNumbers(lottoNumbers);
    }

    private WinningNumbers createWinningNumbersWithBonusNumber() {
        WinningNumbers winningNumbers = createWinningNumbers();
        printNewLine();
        registerBonusNumber(winningNumbers);
        return winningNumbers;
    }

    private WinningNumbers createWinningNumbers() {
        try {
            List<Integer> winningNumbers = createWinningNumbersFromUser();
            return new WinningNumbers(winningNumbers);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createWinningNumbers();
        }
    }

    private List<Integer> createWinningNumbersFromUser() {
        List<String> numbers = inputWinningNumbers();
        return numbers.stream()
                .map(this::parseInt)
                .toList();
    }

    private void registerBonusNumber(WinningNumbers winningNumbers) {
        try {
            winningNumbers.registerBonusNumber(createBonusNumber());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            registerBonusNumber(winningNumbers);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private int createBonusNumber() {
        try {
            return createBonusNumberFromUser();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createBonusNumber();
        }
    }

    private int createBonusNumberFromUser() {
        String input = inputBonusNumber();
        return parseInt(input);
    }

    private LottoResult createLottoResult(Lottoes lottoes, WinningNumbers winningNumbers) {
        List<ResultType> resultTypes = calculateLottoResult(lottoes, winningNumbers);
        return new LottoResult(resultTypes);
    }

    private List<ResultType> calculateLottoResult(Lottoes lottoes, WinningNumbers winningNumbers) {
        return lottoes.getAllLotto().stream()
                .map(lotto -> winningNumbers.matchingResult(lotto.getLottoNumbers()))
                .collect(Collectors.toList());
    }

    private void printResult(Map<ResultType, Integer> result, double yieldRate) {
        printNewLine();
        printWinningStatistics();
        printLottoResult(result);
        printTotalYieldRate(yieldRate);
    }

    private int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(NOT_NUMBER_STRING);
        }
    }
}
