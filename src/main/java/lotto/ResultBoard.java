package lotto;

import java.util.HashMap;
import java.util.List;
import lotto.purchasing.Printer;
import lotto.purchasing.PurchaseAmount;
import lotto.winning.Analyst;
import lotto.winning.BonusNumber;
import lotto.winning.PrizeIndex;
import lotto.winning.PrizeRankChecker;
import lotto.winning.WinningNumbers;

public class ResultBoard implements Showable {
    private static final int COUNT = PrizeIndex.COUNT.getNumber();

    PurchaseAmount purchaseAmount;
    Printer printer;
    WinningNumbers winningNumbers;
    BonusNumber bonusNumber;
    PrizeRankChecker prizeRankChecker;
    Analyst analyst;

    public ResultBoard(PurchaseAmount purchaseAmount, Printer printer,
                       WinningNumbers winningNumbers,
                       BonusNumber bonusNumber, PrizeRankChecker prizeRankChecker, Analyst analyst) {
        this.purchaseAmount = purchaseAmount;
        this.printer = printer;
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
        this.prizeRankChecker = prizeRankChecker;
        this.analyst = analyst;
    }

    @Override
    public void show() {
        List<Lotto> lottos = printer.print();

        HashMap<String, List<Integer>> updatedPrizes = analyst.updatePrizes(
                prizeRankChecker.computeMatchedNumberCounts(lottos, winningNumbers.ask()),
                prizeRankChecker.computeMatchedNumberCounts(lottos, List.of(bonusNumber.ask())));

        double returnRatio = analyst.calculateReturnRatio(updatedPrizes, Integer.parseInt(purchaseAmount.getInput()));

        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + updatedPrizes.get("fifth").get(COUNT) + "개");
        System.out.println("4개 일치 (50,000원) - " + updatedPrizes.get("fourth").get(COUNT) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + updatedPrizes.get("third").get(COUNT) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + updatedPrizes.get("second").get(COUNT) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + updatedPrizes.get("first").get(COUNT) + "개");
        System.out.println("총 수익률은 " + returnRatio + "%입니다.");
    }
}
