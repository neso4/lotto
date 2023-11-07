package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.HashMap;
import java.util.List;
import lotto.purchasing.Generator;
import lotto.purchasing.Printer;
import lotto.purchasing.PurchaseAmount;
import lotto.winning.Analyst;
import lotto.winning.BonusNumber;
import lotto.winning.Prize;
import lotto.winning.PrizeRankChecker;
import lotto.winning.ResultBoard;
import lotto.winning.Tally;
import lotto.winning.WinningNumbers;

public class SequenceManager {
    PurchaseAmount purchaseAmount;
    Printer printer;
    WinningNumbers winningNumbers;
    BonusNumber bonusNumber;
    PrizeRankChecker prizeRankChecker;
    Analyst analyst;
    ResultBoard resultBoard;
    public SequenceManager() {
        this.purchaseAmount = new PurchaseAmount();
        this.winningNumbers = new WinningNumbers();
        this.bonusNumber = new BonusNumber();
        this.prizeRankChecker = new PrizeRankChecker();
        this.analyst = new Analyst();
        this.resultBoard = new ResultBoard();
    }

    public void proceed() {
        Integer totalPurchaseAmount = purchaseAmount.ask();

        printer = new Printer(new Generator(totalPurchaseAmount));
        List<Lotto> lottos = printer.print();

        HashMap<Prize, HashMap<Tally, Integer>> updatedPrizes =
                analyst.updatePrizes(
                        prizeRankChecker.computeMatchedNumberCounts(lottos, winningNumbers.ask()),
                        prizeRankChecker.computeMatchedNumberCounts(lottos, List.of(bonusNumber.ask())));

        Console.close();

        double returnRatio = analyst.calculateReturnRatio(updatedPrizes, totalPurchaseAmount);

        resultBoard.show(updatedPrizes, returnRatio);
    }
}
