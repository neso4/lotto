package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;

import java.util.List;

public class UserView {

    public static String inputPaidAmount() {
        System.out.println("구입금액을 입력해 주세요.(천원 단위)");
        return Console.readLine();
    }


    public static void outputBuyLotto(List<Lotto> lottos) {
        System.out.println("\n"+ lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
        System.out.println();
    }


    public static String inputWinNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        return Console.readLine();
    }

    public static String inputBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
        return Console.readLine();
    }


    public static void outputProfitRate(double profitRate) {
        System.out.printf("총 수익률은 %.1f%%입니다.\n", profitRate);
    }

    public static void outputErrorLog(String message) {
        System.out.println("[ERROR] " + message);
    }

    public static void printResults(List<LottoResult> results) {
        System.out.println("\n당첨 통계");
        System.out.println("---");
        for (LottoResult result : LottoResult.values()) {
            if (result.getMatchCount() >= 3) {
                long count = countMatchingResults(results, result);
                String resultString = formatResultString(result, count);
                System.out.println(resultString);
            }
        }
    }

    private static long countMatchingResults(List<LottoResult> results, LottoResult result) {
        return results.stream().filter(r -> r == result).count();
    }

    private static String formatResultString(LottoResult result, long count) {
        String matchText = result.getMatchCount() + "개 일치";
        if (result == LottoResult.FIVE_WITH_BONUS) {
            matchText += ", 보너스 볼 일치";
        }
        String price = String.format("%,d원", result.getPrice());
        return String.format("%s (%s) - %d개", matchText, price, count);
    }
}
