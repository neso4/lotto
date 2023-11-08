package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CalculateResult {

    public static long calculateTotalMoneySum(List<LottoResultCalculator> compareNumbers) {
        return compareNumbers.stream()
                .mapToLong(LottoResultCalculator::getMoneySum)
                .sum();
    }

    public static List<Integer> calculateTotalWinCounts(List<LottoResultCalculator> compareNumbers) {
        List<Integer> totalWinCount = Arrays.asList(0, 0, 0, 0, 0);
        compareNumbers.forEach(compareNumber ->
                addWinCounts(totalWinCount, compareNumber.getWinCount())
        );
        return totalWinCount;
    }

    private static void addWinCounts(List<Integer> totalWinCount, List<Integer> winCount) {
        IntStream.range(0, winCount.size())
                .forEach(i -> totalWinCount.set(i, totalWinCount.get(i) + winCount.get(i)));
    }

}
