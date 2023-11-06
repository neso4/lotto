package lotto.view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.Rank;
import lotto.vo.Lotto;

public class OutputView {
    private static final String LOTTO_COUNT_PRINT_MESSAGE = "개를 구매했습니다.";
    private static final String LOTTO_NUMBERS_DELIMETER = ", ";
    private static final String RESULT_LOTTO_PRIZE_MESSAGE = System.lineSeparator() + "당첨 통계";
    private static final String RESULT_LOTTO_PRIZE_SEPERATOR = "---";
    private static final String RATE_RETURN_MESSASGE = "총 수익률은 %.1f%%입니다.";

    public static void printLottos(List<Lotto> lottos) {
        printLottoCount(lottos.size());
        for (Lotto lotto : lottos) {
            printCurrentLottoList(lotto);
        }
    }

    private static void printLottoCount(int lottoCount) {
        System.out.println();
        System.out.println(lottoCount + LOTTO_COUNT_PRINT_MESSAGE);
    }

    private static void printCurrentLottoList(Lotto lotto) {
        String lottoNumbers = lotto.getNumbers()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(LOTTO_NUMBERS_DELIMETER));

        System.out.println("[" + lottoNumbers + "]");
    }


    /**
     * 결과 출력
     */
    public static void printResultLottoPrize(Map<Rank, Integer> rankIntegerMap) {
        System.out.println(RESULT_LOTTO_PRIZE_MESSAGE);
        System.out.println(RESULT_LOTTO_PRIZE_SEPERATOR);

        for (Rank rank : rankIntegerMap.keySet()) {
            if (rank == Rank.UNRANK) {
                continue;
            }

            int count = rankIntegerMap.get(rank);
            System.out.println(rank.getOutputMessage() + count + "개");
        }
    }

    /**
     * 백분위 수익률 출력
     */
    public static void printPrizePercentResult(BigDecimal out) {
        System.out.printf(RATE_RETURN_MESSASGE, out);
    }


}
