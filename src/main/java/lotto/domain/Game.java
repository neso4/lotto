package lotto.domain;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.constant.LottoNumber;
import lotto.constant.Rank;
import lotto.ui.Input;
import lotto.ui.Output;

public class Game {
    private final LottoMachine lottoMachine = new LottoMachine();
    private final WinningNumber winningNumber = new WinningNumber();
    private final Amount amount = new Amount();

    private List<Lotto> createLotteries() {
        while (true) {
            Output.printMessage(Input.PURCHASE);
            try {
                amount.setPurchase(Input.readPurchase(Console.readLine()));
                return lottoMachine.issue(amount.getCount());
            } catch (IllegalArgumentException exception) {
                Output.printMessage(exception.getMessage());
            }
        }
    }

    private Lotto createWinningNumber() {
        while (true) {
            Output.printMessage(Input.WINNING_NUMBER);
            try {
                List<Integer> numbers = Input.readWinningNumber(Console.readLine());
                return new Lotto(numbers);
            } catch (IllegalArgumentException exception) {
                Output.printMessage(exception.getMessage());
            }
        }
    }

    private int createBonus() {
        while (true) {
            Output.printMessage(Input.BONUS);
            try {
                return Input.readBonus(Console.readLine(), winningNumber.getNumbers());
            } catch (IllegalArgumentException exception) {
                Output.printMessage(exception.getMessage());
            }
        }
    }

    public void start() {
        List<Lotto> lotteries = createLotteries();
        Output.printLotteries(lotteries);
        winningNumber.setNumbers(createWinningNumber());
        winningNumber.setBonus(createBonus());

        List<Rank> result = lottoMachine.draw(lotteries, winningNumber);
        Output.printResult(result);
        amount.setWinnings(lottoMachine.combineWinnings(result));
        Output.printEarnings(amount.getEarnings());
    }
}
