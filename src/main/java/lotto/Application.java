package lotto;

import java.util.List;
import lotto.controller.InputProcessor;
import lotto.controller.Lotto;
import lotto.controller.LottoController;
import lotto.view.Input;
import lotto.view.Output;

public class Application {

    public static void main(String[] args) {
        int purcahsedLottoAmount = getPurcahsedLottoAmount();
        List<Lotto> lottos = LottoController.generateLottos(purcahsedLottoAmount);
        Output.printPurchasedDetails(lottos);
        List<Integer> winningNumbers = getWinningNumbers();
        int bonusNumber = getBonusNumber(winningNumbers);

        List<Integer> counts = LottoController.makeLottoResult(winningNumbers, bonusNumber, lottos);
        double earningRate = LottoController.calculateEarningRate(purcahsedLottoAmount, counts);
        Output.printWinningStatistics(counts, earningRate);
    }

    private static int getBonusNumber(List<Integer> winningNumbers) {
        int bonusNumber;
        while (true) {
            try {
                Output.printPromptForBonusNumber();
                bonusNumber = InputProcessor.processBonusNumberInput(Input.getInput(),
                        winningNumbers);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }
        }
        return bonusNumber;
    }

    private static List<Integer> getWinningNumbers() {
        List<Integer> winningNumbers;
        while (true) {
            try {
                Output.printPromptForWinningNumbers();
                winningNumbers = InputProcessor.processWinningNumbersInput(Input.getInput());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }
        }
        return winningNumbers;
    }

    private static int getPurcahsedLottoAmount() {
        int purcahsedLottoAmount;
        while (true) {
            try {
                Output.printPromptForPurchaseAmount();
                purcahsedLottoAmount = InputProcessor.processPurchaseAmountInput(Input.getInput());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e);
                System.out.println();
            }
        }
        return purcahsedLottoAmount;
    }

}
