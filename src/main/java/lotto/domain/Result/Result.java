package lotto.domain.Result;

import lotto.domain.Lotto.Lotto;
import lotto.domain.WinningNumber.WinningNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Result {

    private static final int INITIAL_VALUE = 0;

    List<Integer> rankWinner = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));
    Rank rank;

    private List<Lotto> lotties;
    private WinningNumber winningNumber;
    private double profit;

    public void printResult(List<Lotto> lotties, WinningNumber winningnumber) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        this.lotties = lotties;
        this.winningNumber = winningnumber;
        setAllRank();
        printRankResult();
        calculateProfit();
        printProfit();
    }

    private void setAllRank() {
        for (int i = 0; i < lotties.size(); i++) {
            setRank(lotties.get(i).getNumbers());
        }
    }

    private void setRank(List<Integer> numbers) {
        int matchNumberCount = INITIAL_VALUE;
        boolean matchBonus = false;
        for (int i = 0; i < numbers.size(); i++) {
            if (matchNumber(numbers.get(i))) {
                matchNumberCount++;
            }
            if (matchBonusNumber(numbers.get(i))) {
                matchBonus = true;
            }
        }
        setRankDetail(matchNumberCount, matchBonus);
    }

    private void setRankDetail(int matchNumberCount, boolean matchBonus) {
        if (matchNumberCount == 6) {
            rankWinner.set(0, rankWinner.get(0) + 1);
            return;
        }
        if (matchNumberCount == 5 && matchBonus) {
            rankWinner.set(1, rankWinner.get(1) + 1);
            return;
        }
        if (matchNumberCount == 5) {
            rankWinner.set(2, rankWinner.get(2) + 1);
            return;
        }
        if (matchNumberCount == 4) {
            rankWinner.set(3, rankWinner.get(3) + 1);
            return;
        }
        if (matchNumberCount == 3) {
            rankWinner.set(4, rankWinner.get(4) + 1);
            return;
        }
    }

    private boolean matchNumber(int num) {
        if (winningNumber.getWinningNumbers().contains(num)) {
            return true;
        }
        return false;
    }


    private boolean matchBonusNumber(int num) {
        if (winningNumber.getBonusNumber() == num) {
            return true;
        }
        return false;
    }

    private void printRankResult() {
        int i = 0;
        for (Rank rank : Rank.values()) {
            System.out.printf(rank.getRankOutput(), rankWinner.get(i));
            i++;
        }
    }

    private void calculateProfit() {

        int totalProfit =
                rankWinner.get(0) * rank.FIRSTRANK.getRankPrize()  + rankWinner.get(1) * rank.SECONDRANK.getRankPrize() + rankWinner.get(2) * rank.THIRDRANK.getRankPrize() + rankWinner.get(3) * rank.FORTHRANK.getRankPrize() + rankWinner.get(4) * rank.FIFTHRANK.getRankPrize();
        int totalExpenditure = lotties.size() * 1000;
        this.profit = ((double)totalProfit / totalExpenditure) * 100;
    }

    private void printProfit() {
        System.out.println("총 수익률은 " + String.format("%.1f", profit) + "%입니다.");
    }


}
