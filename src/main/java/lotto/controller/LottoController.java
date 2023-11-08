package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.*;
import lotto.io.Input;
import lotto.io.Output;

import java.util.List;

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
        WinningNumbers winningNumbers = generateWinningNumbers();
        BonusNumber bonusNumber = generateBonusNumber();
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

    private WinningNumbers generateWinningNumbers() {
        try {
            output.printInputWinningNumbersMessage();
            return new WinningNumbers(input.getWinningNumbers());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return generateWinningNumbers();
        }
    }

    private BonusNumber generateBonusNumber() {
        System.out.println();
        try {
            output.printInputBonusNumberMessage();
            return new BonusNumber(input.getBonusNumber());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return generateBonusNumber();
        }
    }
}