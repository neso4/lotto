package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoModel {
    public static List<Integer> generateRandomLottoNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }

    public static List<Integer> calculateMatches(List<List<Integer>> bought, List<Integer> winner, int bonus) {
        List<Integer> matchCounts = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            matchCounts.add(0);
        }

        for (List<Integer> lottoNumbers : bought){
            int matchCount = LottoModel.countMatch(lottoNumbers, winner);
            boolean hasBonusMatch = LottoModel.hasBonusMatch(lottoNumbers, bonus);

            if (matchCount == 6){
                matchCounts.set(4, matchCounts.get(4) + 1);
            }
            if (matchCount == 5 && hasBonusMatch) {
                matchCounts.set(3, matchCounts.get(3) + 1);
            }
            if (matchCount >= 3 && matchCount <= 5) {
                matchCounts.set(matchCount - 3, matchCounts.get(matchCount - 3) + 1);
            }
        }
        return matchCounts;
    }

    public static int countMatch(List<Integer> userNumbers, List<Integer> winningNumbers) {
        List<Integer> calNumbers = new ArrayList<>(userNumbers);
        calNumbers.retainAll(winningNumbers);
        return calNumbers.size();
    }

    public static boolean hasBonusMatch(List<Integer> userNumbers, int bonusNumber) {
        return userNumbers.contains(bonusNumber);
    }

    public static int prize(List<Integer> matchCounts) {
        int[] prizes = {5000, 50000, 1500000, 30000000, 2000000000};
        int totalValue = 0;

        for (int i = 0; i < matchCounts.size(); i++) {
            totalValue += matchCounts.get(i) * prizes[i];
        }

        return totalValue;
    }
}