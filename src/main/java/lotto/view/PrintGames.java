package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Prize;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PrintGames {
    Map<String, Integer> result = null;
    int count = 0;

    public void purchaseHistory(List<Lotto> lottoGames) {
        count = lottoGames.size();
        System.out.println(count + "개를 구매했습니다.");
        for (int i = 0; i < count; i++) {
            List<Integer> lotto = lottoGames.get(i).getNumbers();
            lotto.stream().sorted().collect(Collectors.toList());
            System.out.println(lotto);
        }
        System.out.println();
    }

    public String resultMessage(int rank) {
        String message = "";
        for (Prize prize : Prize.values()) {
            if (rank == prize.getRank()) {
                message = prize.getDescription() + " - " + result.get(prize.name()) + "개";
            }
        }
        return message;
    }

    public String returnRate(int resultAmount) {
        double rate = (resultAmount / (count * 1000.0)) * 100;
        return "총 수익률은 " + String.format("%.1f", rate) + "%입니다.";
    }

    public void finalResults(Map<String, Integer> result, int resultAmount) {
        this.result = result;
        System.out.println("당첨 통계");
        System.out.println("---");
        for (int rank = Prize.values().length; rank > 0; rank--) {
            System.out.println(resultMessage(rank));
        }
        System.out.println(returnRate(resultAmount));
    }
}
