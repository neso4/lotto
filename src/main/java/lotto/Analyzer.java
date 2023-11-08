package lotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.Prize.*;

public class Analyzer {
    public int check(List<Integer> winningNumbers, List<Integer> ticketNumbers, int bonusNumber) {
        int matchedCount = countMatchingNumbers(winningNumbers, ticketNumbers);
        boolean bonusNumberMatched = ticketNumbers.contains(bonusNumber);
        return checkRanking(matchedCount, bonusNumberMatched);
    }

    public Map<Integer, Integer> fillTicketResults(int ticket, List<Integer> winningNumbers,
                                                   List<List<Integer>> allTicketNumbers, int bonusNumber) {
        Map<Integer, Integer> ticketResults = new HashMap<>();
        for (int i = 0; i < ticket; i++) {
            ticketResults.putIfAbsent(i, 0);
            int rank = check(winningNumbers, allTicketNumbers.get(i), bonusNumber);
            ticketResults.put(rank, ticketResults.get(rank) + 1);
        }
        return ticketResults;
    }

    public double calculateYield(int purchaseAmount, Map<Integer, Integer> ticketResults) {
        int totalPrize = 0;
        double yieldRate;
        for (int i = 1; i < 6; i++) {
            ticketResults.putIfAbsent(i, 0);
            totalPrize += getPrizeMoney(i) * ticketResults.get(i);
        }
        yieldRate = (double) totalPrize / purchaseAmount * 100;
        return Math.round(yieldRate * 100.0) / 100.0;
    }

    private int checkRanking(int matchedCount, boolean bonusNumberMatched) {
        if (matchedCount == 6) {
            return 1;
        }
        if (matchedCount == 5 && bonusNumberMatched) {
            return 2;
        }
        if (matchedCount == 5) {
            return 3;
        }
        if (matchedCount == 4) {
            return 4;
        }
        if (matchedCount == 3) {
            return 5;
        }
        return 0;
    }

    private int countMatchingNumbers(List<Integer> winningNumbers, List<Integer> ticketNumbers) {
        int count = 0, i = 0, j = 0;
        while (i < winningNumbers.size() && j < ticketNumbers.size()) {
            int compare = Integer.compare(winningNumbers.get(i), ticketNumbers.get(j));
            if (compare == 0) {
                count++;
                i++;
                j++;
            } else if (compare < 0) i++;
            else j++;
        }
        return count;
    }

    private int getPrizeMoney(int rank) {
        if (rank == 1) {
            return FIRST.getPrizeMoney();
        }
        if (rank == 2) {
            return SECOND.getPrizeMoney();
        }
        if (rank == 3) {
            return THIRD.getPrizeMoney();
        }
        if (rank == 4) {
            return FOURTH.getPrizeMoney();
        }
        if (rank == 5) {
            return FIFTH.getPrizeMoney();
        }
        return 0;
    }
}