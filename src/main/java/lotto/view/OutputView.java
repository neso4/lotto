package lotto.view;

import lotto.MatchStatus;
import lotto.model.Lotto;

import java.util.List;

public class OutputView {
    public static void printLottos(List<Lotto> lottos) {
        printPurchasedLottoCount(lottos.size());
        lottos.stream()
                .map(Lotto::getNumbers)
                .forEach(number -> System.out.println(number));
    }

    public static void printPurchasedLottoCount(int count) {
        System.out.println();
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printCountResult(int[] result) {
        MatchStatus[] values = MatchStatus.values();

        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i].getName() + " - " + result[i] + "개");
        }
    }

    public static void printTotalPricePercentage(String pricePercentage) {
        System.out.println("총 수익률은 " + pricePercentage + "%입니다.");
    }

    public static void printResultMessage() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
    }
}