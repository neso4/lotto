package lotto;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        Input input = new Input();
        input.setMoney();
        input.setNumberOfLotto();

        int money = input.getMoney();
        int numberOfLotto = input.getNumberOfLotto();

        LottoMachine lottoMachine = new LottoMachine(numberOfLotto);
        List<Lotto> lottos = lottoMachine.getLottos();

        WinningNumber winningNumberInput = new WinningNumber();
        List<Integer> winningNumbers = winningNumberInput.getWinningNumber();

        int bonusNumber = input.getBonusNumber();

        Prize totalReturn = new Prize(money, winningNumbers, lottos, bonusNumber);
        totalReturn.printResult();

    }
}
