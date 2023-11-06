package lotto.global.view;

import java.util.List;
import lotto.domain.LottoResult;
import lotto.domain.LottoResultManager;
import lotto.global.constant.ConsoleType;
import lotto.global.constant.Winning;

public class OutputView {

    private OutputView() {
    }

    public static void commonOutputLine(String output) {
        System.out.println(output);
    }

    public static void outputStatistics(double rateOfReturn, LottoResultManager lottoResultManager) {
        System.out.println(ConsoleType.OUTPUT_STATISTICS.getComment());
        System.out.println(ConsoleType.THREE_MATCH.getComment(lottoResultManager.getStatistics().get(Winning.FIFTH)));
        System.out.println(ConsoleType.FOUR_MATCH.getComment(lottoResultManager.getStatistics().get(Winning.FOURTH)));
        System.out.println(ConsoleType.FIVE_MATCH.getComment(lottoResultManager.getStatistics().get(Winning.THIRD)));
        System.out.println(ConsoleType.FIVE_AND_BONUS_MATCH.getComment(lottoResultManager.getStatistics().get(Winning.SECOND)));
        System.out.println(ConsoleType.ALL_MATCH.getComment(lottoResultManager.getStatistics().get(Winning.FIRST)));
        System.out.println(ConsoleType.OUTPUT_RATE_OF_RETURN.getComment(rateOfReturn + "%"));
    }

    public static void outputLottoNumbers(List<LottoResult> lottoResults) {
        lottoResults.forEach(System.out::println);
        System.out.println(ConsoleType.EMPTY.getComment());
    }
}
