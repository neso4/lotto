package lotto.controller;

import static lotto.utils.CalculationUtils.isDivisible;
import static lotto.utils.StringUtils.parseInt;
import static lotto.view.ErrorMessage.*;
import static lotto.view.InputView.*;
import static lotto.view.OutputView.*;

import lotto.domain.Lotto;
import lotto.service.LottoService;
import lotto.utils.CalculationUtils;
import java.util.ArrayList;
import java.util.List;

public class LottoController {
    public static final int ONE_LOTTO_PRICE = 1000;
    private final LottoService lottoService;

    public LottoController() {
        this.lottoService = new LottoService();
    }

    public void play() {
        int totalPurchaseMoney = receiveMoneyUntilPass();

        List<Lotto> lottoList = lottoService.generateLottoList(totalPurchaseMoney / ONE_LOTTO_PRICE);

        printPurchaseResult(lottoList);

        Lotto answer = registerWinningLottoCombinationUntilPass();

        int bonusNumber = registerBonusNumberUntilPass(answer);

        showStatisticsResult(lottoList, answer, bonusNumber);
    }

    public int receiveMoney() {
        String userInput = readMoney();
        validateReceivedMoney(userInput);
        return parseInt(userInput);
    }

    public int receiveMoneyUntilPass() {
        return receiveInputUntilPass(this::receiveMoney);
    }

    public int registerBonusNumber(Lotto answer) {
        String userInput = readBonusNumber();
        validateLottoNumber(userInput);
        validateBonusLottoNumber(userInput, answer);
        return parseInt(userInput);
    }

    public int registerBonusNumberUntilPass(Lotto lotto) {
        int result = 0;

        while (true) {
            try {
                result = registerBonusNumber(lotto);
                break;
            } catch (IllegalArgumentException e) {
                printResult(e.getMessage());
            }
        }

        return result;
    }

    public Lotto registerWinningLottoCombination() {
        String userInput = readWinningLottoCombination();
        validateWinningLottoCombination(userInput);
        List<Integer> lottoCombination = userInputToLottoCombination(userInput);

        return new Lotto(lottoCombination);
    }

    public Lotto registerWinningLottoCombinationUntilPass() {
        return receiveInputUntilPass(this::registerWinningLottoCombination);
    }

    public void showStatisticsResult(List<Lotto> lottoList, Lotto answer, int bonusNumber) {
        String result = lottoService.makeStatisticsResultOutputStatement(lottoList, answer, bonusNumber);
        printResult(result);
    }

    private void validateReceivedMoney(String userInput) {
        // 숫자 인지 검증
        int number = parseInt(userInput);

        if (!isDivisible(number, ONE_LOTTO_PRICE)) {
            throw new IllegalArgumentException(RECEIVED_MONEY_NOT_MULTIPLE_OF_1000.getErrorMessage());
        }
    }

    private void validateLottoNumber(String userInput) {
        int number = parseInt(userInput);

        if (!CalculationUtils.isNumberInRange(number, 1, 45)) {
            throw new IllegalArgumentException(LOTTO_NUMBER_RANGE_IS_BETWEEN_ONE_AND_FORTYFIVE.getErrorMessage());
        }
    }

    private void validateBonusLottoNumber(String userInput, Lotto answer) {
        int number = parseInt(userInput);
        if (answer.isNumberIn(number)) {
            throw new IllegalArgumentException(THAT_NUMBER_IS_ALREADY_CONTAINS_ANSWER_COMBINATION.getErrorMessage());
        }
    }

    private void validateWinningLottoCombination(String userInput) {
        String[] strArr = userInput.replace(" ", "").split(",");

        for (String str : strArr) {
            validateLottoNumber(str); // 숫자인지 체크, 1~45인지 체크
        }
    }

    private List<Integer> userInputToLottoCombination(String userInput) {
        List<Integer> result = new ArrayList<>();
        String[] strArr = userInput.replace(" ", "").split(",");

        for (String str : strArr) {
            result.add(parseInt(str));
        }

        result.sort(null);

        return result;
    }

    public <T> T receiveInputUntilPass(ExceptionSupplier<T> inputMethod) {
        T result = null;

        while (true) {
            try {
                result = inputMethod.get();
                break;
            } catch (IllegalArgumentException e) {
                printResult(e.getMessage());
            }
        }

        return result;
    }
}