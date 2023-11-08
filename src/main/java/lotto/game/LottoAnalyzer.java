package lotto.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.constant.LottoMatch;
import lotto.model.Lotto;

public class LottoAnalyzer {
    private static final String STATISTIC_FORMATTER = "총 수익률은 %.1f%%입니다.";
    private static final String INIT_MESSAGE = "당첨 통계\n---";
    public static final double PERCENTAGE = 100.0;
    public static final int ENABLE_BONUS = 5;

    private final Lotto target;
    private final int bonusNumber;
    private final int cost;

    private Map<LottoMatch, Integer> matchCounts;

    public LottoAnalyzer(Lotto target, int bonusNumber, int cost) {
        this.target = target;
        this.bonusNumber = bonusNumber;
        this.cost = cost;
    }

    public void analyze(List<Lotto> lottos) {
        matchCounts = new HashMap<>();

        Arrays.stream(LottoMatch.values())
                .forEach(lottoMatch -> matchCounts.put(lottoMatch, 0));

        lottos.forEach(this::analyze);
    }

    public String report() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(INIT_MESSAGE);
        stringBuilder.append(System.lineSeparator());

        for (LottoMatch lottoMatch : LottoMatch.values()) {
            stringBuilder.append(result(lottoMatch));
            stringBuilder.append(System.lineSeparator());
        }

        stringBuilder.append(statistic());

        return stringBuilder.toString();
    }

    private void analyze(Lotto lotto) {
        int count = target.match(lotto);

        if (isBonus(count, lotto)) {
            count = LottoMatch.BONUS.count();
        }

        LottoMatch lottoMatch = LottoMatch.get(count);
        int prevCount = matchCounts.get(lottoMatch);

        matchCounts.put(lottoMatch, prevCount + 1);
    }

    private boolean isBonus(int count, Lotto lotto) {
        return count == ENABLE_BONUS && !target.contains(bonusNumber) && lotto.contains(bonusNumber);
    }

    private String result(LottoMatch lottoMatch) {
        int count = matchCounts.get(lottoMatch);

        return lottoMatch.result(count);
    }

    private String statistic() {
        long totalAmount = 0L;

        for (LottoMatch lottoMatch : LottoMatch.values()) {
            int count = matchCounts.get(lottoMatch);

            totalAmount += lottoMatch.calculate(count);
        }

        return STATISTIC_FORMATTER.formatted(earningRate(totalAmount));
    }

    private double earningRate(long totalAmount) {
        return PERCENTAGE * totalAmount / cost;
    }
}
