package lotto.view;

import lotto.domain.*;
import lotto.enums.Rank;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

/* *
    - 발행한 로또 수량 및 번호를 출력한다. 로또 번호는 오름차순으로 정렬하여 보여준다.
    - 당첨 내역을 출력한다.
    - 수익률은 소수점 둘째 자리에서 반올림한다. (ex. 100.0%, 51.5%, 1,000,000.0%)
 */
public class OutputView {
    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printNumberOfPurchasedLottoTickets(int numberOfTickets) {
        System.out.printf("%d개를 구매했습니다.%n", numberOfTickets);
    }

    public static void printPurchasedLottoNumbers(List<Lotto> lottoTickets) {
        System.out.println();
        printNumberOfPurchasedLottoTickets(lottoTickets.size());
        lottoTickets.forEach(lotto -> System.out.println(lotto.toString()));
    }

    public static void printWinningResults(WinningResult winningResult) {
        System.out.println();
        DecimalFormat formatter = new DecimalFormat("###,###");

        Arrays.stream(Rank.valuesByReverseOrder()).forEach(rank -> {
            System.out.printf("%s개 일치", rank.getMatchedCount());
            if(rank.containsBonus()) System.out.print(", 보너스 볼 일치");
            System.out.printf(" (%s원) - %s개%n", formatter.format(rank.getWinningAmount()), winningResult.getCount(rank));
        });
    }

    public static void printTotalReturn(TotalWinning totalWinning) {
        System.out.printf("총 수익률은 %.1f%%입니다.%n", totalWinning.getTotalReturn() * 100);
    }
}