package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Judge {
    Generator generator = new Generator();
    Printer printer = new Printer();

    int matchingThreeNumbers;
    int matchingFourNumbers;
    int matchingFiveNumbers;
    int matchingFiveAndBonusNumbers;
    int matchingSixNumbers;


    public void calculateResult(List<Lotto> lotto, List<Integer> winningNumbers, int bonusNumber) {
        for (int count = 0; count < lotto.size(); count++) {
            int matchingCount = compareWinningNumber(lotto.get(count),winningNumbers);

            if (matchingCount == 3) matchingThreeNumbers++;
            if (matchingCount == 4) matchingFourNumbers++;
            //5개가 일치한다면, 보너스 번호와 비교
            if (matchingCount == 5) {
                if(compareBonusNumber(lotto.get(count), bonusNumber)) {
                    matchingFiveAndBonusNumbers++;
                }
                matchingFiveNumbers++;
            }
            if (matchingCount == 6) matchingSixNumbers++;
        }
        printer.showResult(matchingThreeNumbers, matchingFourNumbers, matchingFiveNumbers, matchingFiveAndBonusNumbers, matchingSixNumbers);
    }

    public int compareWinningNumber(Lotto lotteryNumbers, List<Integer> winningNumbers) {
        List<Integer> matchingNumbers = new ArrayList<>(winningNumbers);
        matchingNumbers.retainAll(lotteryNumbers.getLotteryNumbers());
        return matchingNumbers.size();
    }

    private boolean compareBonusNumber(Lotto lotteryNumbers, int bonusNumber) {
        // 보너스 번호와 일치하는지 비교하는 코드 구현
        if (lotteryNumbers.getLotteryNumbers().contains(bonusNumber)) {
            return true;
        }
        return false;
    }

    public void calculateProfit() {
        long prizeMoney = calculatePrizeMoney();
        double profitPercent = ((double)prizeMoney/generator.getMoney()) * 100;
        printer.showProfit(profitPercent);
    }

    private long calculatePrizeMoney() {
        long prizeMoney = (2_000_000_000 * matchingSixNumbers) + (30_000_000 * matchingFiveAndBonusNumbers) + (1_500_000 * matchingFiveNumbers)
                + (50_000 * matchingFourNumbers) + (5_000 * matchingThreeNumbers);
        if (prizeMoney == 0) return 0;
        return prizeMoney;
    }
}
