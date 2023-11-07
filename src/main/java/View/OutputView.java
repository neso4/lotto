package View;

import java.util.List;
import java.util.Map;
import lotto.Lotto;
import lotto.Rank;
import lotto.WinningResult;

public class OutputView {
    public void printUserLottoNumbers(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.forEach(lotto -> System.out.println(lotto.getNumbers()));
    }

    public void printWinningResult(WinningResult winningResult) {
        System.out.println("당첨 통계");
        System.out.println("---");
        print(winningResult.winningStatistics());
        print(winningResult.winningRate());
    }

    private void print(Map<Rank, Integer> winningResult) {
        for (Rank rank : Rank.values()) {
            if (rank == Rank.NOTHING) {
                continue;
            }
            Integer count = winningResult.get(rank);
            String result = String.format(rank.getPrintFormat(), count);
            System.out.println(result);
        }
    }

    private void print(Double winningRate) {
        System.out.println(String.format("총 수익률은 %.1f%%입니다.", winningRate));
    }
}
