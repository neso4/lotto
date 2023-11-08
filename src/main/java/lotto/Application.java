package lotto;

import lotto.domain.LottoResultAnalyzer;
import lotto.domain.RandomNumberGenerator;
import lotto.domain.RateOfReturn;
import lotto.input.BonusNumber;
import lotto.output.LottoResultOutput;
import lotto.output.RandomNumberGeneratorOutput;
import lotto.output.PurchaseResultHandler;

import java.util.List;
import java.util.Set;

import static lotto.domain.RateOfReturn.calculateRateOfReturn;
import static lotto.domain.RateOfReturn.rateOfReturnOutput;
import static lotto.input.WinningNumbers.getWinningNumbers;

public class Application {
    public static void main(String[] args) {
        int numberOfLotto = PurchaseResultHandler.requestPurchase();

        RandomNumberGenerator randomNumbers = new RandomNumberGenerator();
        RandomNumberGeneratorOutput numbersOutput = new RandomNumberGeneratorOutput();
        numbersOutput.printLotteryNumbers(randomNumbers, numberOfLotto);

        Set<Integer> winningNumbers = getWinningNumbers();
        int bonusNum = BonusNumber.bonusInput(winningNumbers);

        List<Integer> winnings = LottoResultAnalyzer.calculateWinnings(randomNumbers.getLotteries(), winningNumbers, bonusNum);
        LottoResultOutput.printResult(winnings);

        double result = calculateRateOfReturn(numberOfLotto, winnings);
        rateOfReturnOutput(result);

    }
}
