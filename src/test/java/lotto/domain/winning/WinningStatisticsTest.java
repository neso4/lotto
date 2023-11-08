package lotto.domain.winning;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.winning.WinningNumbers;
import lotto.domain.winning.WinningResult;
import lotto.domain.winning.WinningStatistics;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WinningStatisticsTest {
    static Stream<Arguments> provideWinningNumbersAndLottos() {
        WinningNumbers winningNumbers = new WinningNumbers(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                LottoNumber.valueOf(7)
        );

        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 8)),
                new Lotto(List.of(1, 2, 3, 7, 8, 9)),
                new Lotto(List.of(1, 2, 3, 10, 11, 12))
        ));

        Map<WinningResult, Integer> expected = Map.of(
                WinningResult.FIRST, 1,
                WinningResult.SECOND, 1,
                WinningResult.THIRD, 1,
                WinningResult.FOURTH, 0,
                WinningResult.FIFTH, 2,
                WinningResult.LOSING, 0
        );

        return Stream.of(Arguments.of(winningNumbers, lottos, expected));
    }

    @ParameterizedTest
    @MethodSource("provideWinningNumbersAndLottos")
    void 당첨_통계를_구할_수_있다(WinningNumbers winningNumbers, Lottos lottos, Map<WinningResult, Integer> expected) {
        // when
        WinningStatistics statistics = WinningStatistics.of(winningNumbers, lottos);

        // then
        assertThat(statistics).extracting("statistics").isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideWinningNumbersAndLottos")
    void 총_로또_당첨_금액을_구할_수_있다(WinningNumbers winningNumbers, Lottos lottos) {
        // when
        WinningStatistics statistics = WinningStatistics.of(winningNumbers, lottos);

        // then
        assertThat(statistics.getTotalWinningMoney())
                .isEqualTo(new Money(2_000_000_000 + 30_000_000 + 1_500_000 + 5_000 * 2));
    }
}
