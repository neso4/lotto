package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import lotto.exception.Exceptions;
import lotto.model.Customer;
import lotto.model.Lotto;
import lotto.model.WinningNumber;
import lotto.service.LottoService;
import lotto.view.InputMessage;
import lotto.view.OutputMessage;

import java.util.List;

public class Controller {

    private LottoService lottoService = new LottoService();
    private Exceptions exceptions = new Exceptions();
    private WinningNumber winningNumber;
    private Customer customer;

    public void start() {
        try {
            getBuyLottoMoney();
            winningNumberShow();
            gameResult();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getBuyLottoMoney() {
        InputMessage.inputMoney();
        String money = Console.readLine().trim();
        exceptions.isInvalidStringMoney(money);
        int inputMoney = Integer.parseInt(money);
        customer = new Customer(inputMoney);
        lottoService.buyLottoByTicket(customer);
        resultLotto(customer);
    }

    private void gameResult() {
        lottoService.checkLottoRanking(customer, winningNumber);
        OutputMessage.winningMessage();
        OutputMessage.showRank(customer.getLottoResult());
        OutputMessage.showYieldResult(customer.getYield());
    }

    private void resultLotto(Customer customer) {
        OutputMessage.purchaseMessage(customer.getPurchaseMoney() / 1000);
        for (Lotto lotto : customer.getPurchaseLotteries()) {
            OutputMessage.lottoNumber(lotto.getNumbers());
        }
    }

    private void winningNumberShow() {
        winningNumber = new WinningNumber(getWinningNumber(), getBonusNumber());
    }

    private List<Integer> getWinningNumber() {
        InputMessage.winningNumberInput();
        String input = Console.readLine();
        exceptions.isInvalidNumberOfLotteries(input);
        exceptions.isInvalidDuplicatedLottoNumber(lottoService.integerList(input));
        return lottoService.integerList(input);
    }

    private int getBonusNumber() {
        InputMessage.bonusNumberInput();
        int input = Integer.parseInt(Console.readLine().trim());
        exceptions.isInvalidNumberOfBonusNumber(input);
        return input;
    }

}
