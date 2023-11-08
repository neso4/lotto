package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import lotto.controller.validator.BonusNumberValidator;
import lotto.controller.validator.WinningNumbersValidator;
import lotto.domain.*;
import lotto.io.Input;
import lotto.io.Output;

import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    private final Input input;
    private final Output output;

    public LottoController(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public void start() {
        Money money = generateMoney();
        LottoQuantity lottoQuantity = new LottoQuantity(money.getQuantity());
        output.printPurchaseQuantity(lottoQuantity.getQuantity());

        List<Lotto> myLottos = buyLottos(lottoQuantity);
        WinningNumbers winningNumbers = new WinningNumbers(generateWinningNumbers());
        BonusNumber bonusNumber = new BonusNumber(generateBonusNumber(winningNumbers));
        Console.close();

        WinningLottoCounts winningLottoCounts = new WinningLottoCounts(myLottos, winningNumbers, bonusNumber);
        Profit profit = new Profit(winningLottoCounts, money);
        output.printResult(winningLottoCounts, profit.calculate());
    }

    private Money generateMoney() {
        try {
            output.printInputPurchaseAmountMessage();
            return new Money(input.getUserAmount());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return (generateMoney());
        }
    }

    private List<Lotto> buyLottos(LottoQuantity lottoQuantity) {
        Lottos lottos = new Lottos(lottoQuantity);
        List<Lotto> myLottos = lottos.getMyLottos();
        output.printMyLottos(myLottos);
        return myLottos;
    }

    private List<Integer> generateWinningNumbers() {
        try {
            output.printInputWinningNumbersMessage();
            return getWinningNumbers();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return generateWinningNumbers();
        }
    }

    private List<Integer> getWinningNumbers() {
        List<String> userInput = input.getWinningNumbers();
        WinningNumbersValidator winningNumbersValidator = new WinningNumbersValidator();
        winningNumbersValidator.validateWinningNumbers(userInput);
        return userInput.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    private int generateBonusNumber(WinningNumbers winningNumbers) {
        System.out.println();
        try {
            output.printInputBonusNumberMessage();
            return getBonusNumber(winningNumbers);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return generateBonusNumber(winningNumbers);
        }
    }

    private int getBonusNumber(WinningNumbers winningNumbers) {
        String userInput = input.getBonusNumber();
        BonusNumberValidator bonusNumberValidator = new BonusNumberValidator();
        bonusNumberValidator.validateBonusNumber(userInput);
        int bonusNumber = Integer.parseInt(userInput);
        winningNumbers.validateDuplication(bonusNumber);
        return bonusNumber;
    }
}