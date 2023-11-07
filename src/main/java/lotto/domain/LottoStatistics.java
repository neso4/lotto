package lotto.domain;

import java.util.Arrays;
import java.util.Map;
import lotto.global.constant.WinningType;

public record LottoStatistics(Map<WinningType, Integer> statistics) {

    public LottoStatistics(Map<WinningType, Integer> statistics) {
        this.statistics = statistics;
        Arrays.stream(WinningType.values())
                .forEach(winningType -> statistics.put(winningType, 0));
    }

    public Long getTotalWinningMoney() {
        return statistics.entrySet()
                .stream()
                .reduce(
                        0L,
                        (sum, entry) -> sum + (entry.getKey().getRevenue() * entry.getValue()),
                        Long::sum
                );
    }
}
