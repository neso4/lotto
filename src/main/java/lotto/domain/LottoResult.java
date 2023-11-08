package lotto.domain;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

public record LottoResult(Map<LottoRank, Integer> results) {
    private static final int INIT_NUM = 0;
    private static final int PLUS_NUM = 1;
    private static final String NEW_LINE = "\n";
    private static final String VALUE_UNIT = "개";

    public static LottoResult determineWinnings(final Lottos lottos, final WinningLotto winningLotto) {
        EnumMap<LottoRank, Integer> results = new EnumMap<>(LottoRank.class);
        EnumSet.allOf(LottoRank.class).forEach(rank -> {
            if (rank != LottoRank.NONE) {
                results.put(rank, INIT_NUM);
            }
        });

        for (Lotto lotto : lottos.lottos()) {
            LottoRank rank = winningLotto.match(lotto);
            results.computeIfPresent(rank, (k, v) -> v + PLUS_NUM);
        }
        return new LottoResult(results);
    }

    public String description() {
        return results.entrySet()
                .stream().map(entry -> {
            LottoRank rank = entry.getKey();
            Integer value = entry.getValue();
            String description = rank.getDescription();
            return description + value + VALUE_UNIT;
        })
        .collect(Collectors.joining(NEW_LINE));
    }

}
