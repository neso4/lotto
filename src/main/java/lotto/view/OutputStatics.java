package lotto.view;

import java.util.Arrays;
import lotto.domain.LottoResult;
import lotto.domain.Prize;
import lotto.domain.Rate;

public class OutputStatics {
    private static final String NEWLINE = "\n";
    private static final String OUTPUT_TOP_MESSAGE = "당첨 통계" + NEWLINE + "---";
    private static final String OUTPUT_RESULT_MESSAGE = "%d개 일치 (%s원) - %d개";
    private static final String OUTPUT_SECOND_RESULT_MESSAGE = "%d개 일치, 보너스 볼 일치 (%s원) - %d개";
    private static final String OUTPUT_RATE_OF_RETURN = "총 수익률은 %.1f%%입니다.";

    public static void printStatistics(LottoResult lottoResult, Rate rate) {
        System.out.println(OUTPUT_TOP_MESSAGE);
        Arrays.stream(Prize.values())
                .filter(prize -> !prize.equals(Prize.EMPTY))
                .forEach(prize -> System.out.println(getPrintResultPrize(prize, lottoResult)));
        System.out.printf((OUTPUT_RATE_OF_RETURN) + NEWLINE, rate.getRate());
    }

    private static String getPrintResultPrize(Prize prize, LottoResult lottoResult) {
        if (prize == Prize.SECOND) {
            return String.format(OUTPUT_SECOND_RESULT_MESSAGE
                    , prize.getMatchLottoNumber()
                    , String.format("%,d", prize.getMoney())
                    , lottoResult.getPrizeCount(prize));
        }

        return String.format(OUTPUT_RESULT_MESSAGE
                , prize.getMatchLottoNumber()
                , String.format("%,d", prize.getMoney())
                , lottoResult.getPrizeCount(prize));
    }
}
