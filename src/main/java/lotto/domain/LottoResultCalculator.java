package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoResultCalculator {


    public static LottoResults calculateResults(Lottos lottos, WinningNumbers winningNumbers) {
        List<MatchResult> matchResults = calculateMatchResultsForAllLottos(lottos, winningNumbers);
        Map<LottoRank, Integer> rankCounts = aggregateRankCounts(matchResults);
        return new LottoResults(rankCounts);
    }

    private static List<MatchResult> calculateMatchResultsForAllLottos(Lottos lottos,
            WinningNumbers winningNumbers) {
        return lottos.getLottos().stream()
                .map(lotto -> calculateMatchResult(lotto, winningNumbers))
                .toList();
    }

    private static MatchResult calculateMatchResult(Lotto lotto, WinningNumbers winningNumbers) {
        int matchCount = MatchCalculator.calculateMatchCount(lotto, winningNumbers);
        boolean  matchBonus = MatchCalculator.isBonusNumberMatched(lotto, winningNumbers);
        return new MatchResult(matchCount, matchBonus);
    }

    private static Map<LottoRank, Integer> aggregateRankCounts(List<MatchResult> matchResults) {
        return matchResults.stream()
                .map(MatchResult::getRank)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.summingInt(rank -> 1)));
    }
}
