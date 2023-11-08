package lotto.domain;

import java.util.HashMap;

public class Judgement {
    private HashMap<WinningRule, Integer> results = new HashMap<>();
    private static final double PERCENT = 100.0;
    private static final double ROUND_NUMBER = 100.0;

    public Judgement() {
        results.put(WinningRule.NOTHING, 0);
        results.put(WinningRule.FIFTH_PRIZE, 0);
        results.put(WinningRule.FOURTH_PRIZE, 0);
        results.put(WinningRule.THIRD_PRIZE, 0);
        results.put(WinningRule.SECOND_PRIZE, 0);
        results.put(WinningRule.FIRST_PRIZE, 0);
    }

    public HashMap<WinningRule, Integer> getResults(Buyer buyer, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        for (Lotto lotto : buyer.getPurchaseLotteries()) {
            int matchNumbers = countMatchNumbers(lotto, winningNumbers);
            boolean matchBonusNumber = checkContainsBonusNumber(lotto, bonusNumber.getBonusNumber());
            WinningRule rank = determineRank(matchNumbers, matchBonusNumber);
            int count = results.get(rank) + 1;
            results.put(rank, count);
        }
        return results;
    }

    public int countMatchNumbers(Lotto lotto, WinningNumbers winningNumbers) {
        int matchCount = 0;
        for (int number : winningNumbers.getWinningNumbers()) {
            if (lotto.getNumbers().contains(number)) {
                matchCount++;
            }
        }
        return matchCount;
    }

    public boolean checkContainsBonusNumber(Lotto lotto, int bonusNumber) {
        if (lotto.getNumbers().contains(bonusNumber)) {
            return true;
        }
        return false;
    }

    public WinningRule determineRank(int matchNumbers, boolean matchBonusNumber) {
        if (matchNumbers == 6) {
            return WinningRule.FIRST_PRIZE;
        }
        if (matchNumbers == 5 && matchBonusNumber) {
            return WinningRule.SECOND_PRIZE;
        }
        if (matchNumbers == 5) {
            return WinningRule.THIRD_PRIZE;
        }
        if (matchNumbers == 4) {
            return WinningRule.FOURTH_PRIZE;
        }
        if (matchNumbers == 3) {
            return WinningRule.FIFTH_PRIZE;
        }
        return WinningRule.NOTHING;
    }

    public double getReturnRate(int purchasePrice) {
        double returnRate = ((double) getTotalPrize() / purchasePrice) * PERCENT;
        return Math.round(returnRate * ROUND_NUMBER) / ROUND_NUMBER;
    }

    private int getTotalPrize() {
        int totalPrize = 0;
        for (WinningRule rank : results.keySet()) {
            totalPrize += rank.getPrize() * results.get(rank);
        }
        return totalPrize;
    }
}
