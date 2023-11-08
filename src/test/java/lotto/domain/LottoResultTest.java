package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import lotto.data.Lotto;
import lotto.data.LottoPrize;
import lotto.data.WinningCombination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoResultTest {
    @ParameterizedTest(name = "[{index}] {0}인 경우")
    @DisplayName("로또 순위 결과를 생성한다.")
    @MethodSource("provideLottoResultSets")
    void createRankResult(String title, List<Integer> winningNumbers, int bonusNumber, List<Lotto> lottos,
                          Map<LottoPrize, BigDecimal> expectedRank, BigDecimal expectedProfitPercent) {
        WinningCombination winningCombination = new WinningCombination(winningNumbers, bonusNumber);

        Map<LottoPrize, BigDecimal> rankResult = winningCombination.getResultWith(lottos);
        BigDecimal purchaseAmount = BigDecimal.valueOf(lottos.size()).multiply(BigDecimal.valueOf(1000));
        LottoResult lottoResult = new LottoResult(rankResult, purchaseAmount);

        assertEquals(lottoResult.getLottoRank(), expectedRank);
    }

    @ParameterizedTest(name = "[{index}] {0}인 경우")
    @DisplayName("수익률을 계산한다.")
    @MethodSource("provideLottoResultSets")
    void getProfitPercent(String title, List<Integer> winningNumbers, int bonusNumber, List<Lotto> lottos,
                          Map<LottoPrize, BigDecimal> expectedRank, BigDecimal expectedProfitPercent) {
        WinningCombination winningCombination = new WinningCombination(winningNumbers, bonusNumber);

        Map<LottoPrize, BigDecimal> rankResult = winningCombination.getResultWith(lottos);
        BigDecimal purchaseAmount = BigDecimal.valueOf(lottos.size()).multiply(BigDecimal.valueOf(1000));
        LottoResult lottoResult = new LottoResult(rankResult, purchaseAmount);

        assertEquals(lottoResult.getProfitPercent(), expectedProfitPercent);
    }

    private static Stream<Arguments> provideLottoResultSets() {
        return Stream.of(
                Arguments.of(
                        "1등(1), 2등(1), 5등(1)",
                        List.of(1, 2, 3, 4, 5, 6), 7,
                        List.of(
                                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                                new Lotto(List.of(1, 20, 3, 4, 7, 8))
                        ),
                        Map.of(
                                LottoPrize.FIRST, BigDecimal.ONE,
                                LottoPrize.SECOND, BigDecimal.ONE,
                                LottoPrize.THIRD, BigDecimal.ZERO,
                                LottoPrize.FOURTH, BigDecimal.ZERO,
                                LottoPrize.FIFTH, BigDecimal.ONE,
                                LottoPrize.NONE, BigDecimal.ZERO
                        ),
                        LottoPrize.FIRST.getPrize()
                                .add(LottoPrize.SECOND.getPrize())
                                .add(LottoPrize.FIFTH.getPrize())
                                .multiply(BigDecimal.valueOf(100))
                                .divide(BigDecimal.valueOf(3000), 5, RoundingMode.DOWN)
                ), Arguments.of(
                        "2등(1), 4등(2)",
                        List.of(1, 2, 3, 4, 5, 6), 7,
                        List.of(
                                new Lotto(List.of(1, 2, 30, 40, 5, 6)),
                                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                                new Lotto(List.of(1, 2, 3, 4, 17, 8))
                        ),
                        Map.of(
                                LottoPrize.FIRST, BigDecimal.ZERO,
                                LottoPrize.SECOND, BigDecimal.ONE,
                                LottoPrize.THIRD, BigDecimal.ZERO,
                                LottoPrize.FOURTH, BigDecimal.valueOf(2),
                                LottoPrize.FIFTH, BigDecimal.ZERO,
                                LottoPrize.NONE, BigDecimal.ZERO
                        ),
                        LottoPrize.SECOND.getPrize()
                                .add(LottoPrize.FOURTH.getPrize().multiply(BigDecimal.valueOf(2)))
                                .multiply(BigDecimal.valueOf(100))
                                .divide(BigDecimal.valueOf(3000), 5, RoundingMode.DOWN)
                ), Arguments.of(
                        "5등(2)",
                        List.of(1, 2, 3, 4, 5, 6), 7,
                        List.of(
                                new Lotto(List.of(1, 20, 3, 40, 43, 6)),
                                new Lotto(List.of(11, 22, 33, 44, 5, 7)),
                                new Lotto(List.of(1, 20, 3, 4, 7, 8))
                        ),
                        Map.of(
                                LottoPrize.FIRST, BigDecimal.ZERO,
                                LottoPrize.SECOND, BigDecimal.ZERO,
                                LottoPrize.THIRD, BigDecimal.ZERO,
                                LottoPrize.FOURTH, BigDecimal.ZERO,
                                LottoPrize.FIFTH, BigDecimal.valueOf(2),
                                LottoPrize.NONE, BigDecimal.ONE
                        ),
                        LottoPrize.FIFTH.getPrize().multiply(BigDecimal.valueOf(2))
                                .multiply(BigDecimal.valueOf(100))
                                .divide(BigDecimal.valueOf(3000), 5, RoundingMode.DOWN)
                )
        );
    }
}
