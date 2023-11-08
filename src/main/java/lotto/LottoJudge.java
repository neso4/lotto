package lotto;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.dto.LottoResult;
import lotto.exception.lottojudge.JudgeNotConfiguredException;

public class LottoJudge {
    private WinningLotto winningLotto;

    public void configure(WinningLotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    private void validateConfiguration() {
        if (!isConfigured()) {
            throw new JudgeNotConfiguredException();
        }
    }

    private boolean isConfigured() {
        return winningLotto != null;
    }

    public LottoResult judge(LottoBundle lottoBundle) {
        validateConfiguration();

        Map<LottoRank, Long> rankMap = lottoBundle.getLottos().stream()
                .collect(Collectors.groupingBy(
                        lotto -> LottoRank.judge(lotto, winningLotto),
                        () -> new EnumMap<>(LottoRank.class),
                        Collectors.counting()
                ));

        double profitRate = calculateProfitRate(rankMap);

        return LottoResult.from(rankMap, profitRate);
    }

    private double calculateProfitRate(Map<LottoRank, Long> rankMap) {
        return rankMap.entrySet().stream()
                .mapToDouble(entry -> entry.getKey()
                        .getPrize()
                        .multiply(entry.getValue().intValue()))
                .sum() / LottoSeller.LOTTO_PRICE;
    }
}
