package lotto.view;

import java.util.List;
import java.util.Map;

public class OutputView {

    public static void responseCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void responseLottoNumber(List<Integer> lotto) {
        System.out.println(lotto);
    }

    public static void responseResult(Map<String, Object> result) {
        double profitPercentage = (double) result.get("총 수익률");

        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + result.get("5등") + "개");
        System.out.println("4개 일치 (50,000원) - " + result.get("4등") + "개");
        System.out.println("5개 일치 (1,500,000원) - " + result.get("3등") + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + result.get("2등") + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + result.get("1등") + "개");
        System.out.printf("총 수익률은 %.1f%%입니다.\n", profitPercentage);
    }


}