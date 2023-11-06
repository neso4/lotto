package lotto.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import lotto.utils.WinningRank;

public class WinningStatistics {
    private Map<WinningRank, Integer> winningRecords;
    private int totalWinningAmount = 0;
    private double profitRate;
    private int purchaseAmount;

    public WinningStatistics(int purchaseAmount) {
        winningRecords = new HashMap<>();
        for (WinningRank rank : WinningRank.values()) {
            winningRecords.put(rank, 0);
        }
        this.purchaseAmount = purchaseAmount;
    }

    public void calculateStatistics(WinningNumbers winningNumbers, Iterator<Lotto> lottoIterator) {
        RankEvaluator rankEvaluator = new RankEvaluator(winningNumbers);
        while (lottoIterator.hasNext()) {
            Lotto lotto = lottoIterator.next();
            WinningRank winningRank = rankEvaluator.getRank(lotto.getIterator());
            updateWinningCount(winningRank);
            calculateTotalAmount(winningRank.getWinningAmount());
        }
        calculateProfitRate();
    }

    private void updateWinningCount(WinningRank winningRank) {
        int updatedWinningCount = winningRecords.get(winningRank) + 1;
        winningRecords.put(winningRank, updatedWinningCount);
    }

    private void calculateProfitRate() {
        profitRate = ((double) totalWinningAmount) / purchaseAmount * 100;
    }

    private void calculateTotalAmount(int additionalAmount) {
        this.totalWinningAmount += additionalAmount;
    }


    @Override
    public String toString() {
        return winningRecordsToString() + profitRateToString();
    }

    private String winningRecordsToString() {
        StringBuilder formattedWinningRecords = new StringBuilder();
        for (WinningRank rank : WinningRank.values()) {
            if (rank == WinningRank.OUT_OF_RANK) {
                continue;
            }
            formattedWinningRecords.append(rank);
            formattedWinningRecords.append(winningCountToString(rank));
            formattedWinningRecords.append("\n");
        }
        return formattedWinningRecords.toString();
    }

    private String winningCountToString(WinningRank rank) {
        return " - " + winningRecords.get(rank) + "개";
    }

    private String profitRateToString() {
        double roundedProfitRate = Math.round(profitRate) / 10.0;

        return "총 수익률은 " + roundedProfitRate + "%입니다. ";
    }

    // for test

    public Map<WinningRank, Integer> getWinningRecords() {
        return winningRecords;
    }

    public int getTotalWinningAmount() {
        return totalWinningAmount;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
