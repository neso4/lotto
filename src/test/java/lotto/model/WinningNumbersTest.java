package lotto.model;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningNumbersTest {

    @Test
    public void 보너스_번호가_당첨_번호와_중복되면_예외가_발생한다() {
        // given
        final WinningNumbersData winningNumbersData = new WinningNumbersData(List.of(1, 2, 3, 4, 5, 6));
        final BonusNumber bonusNumber = new BonusNumber(6);

        // then
        Assertions.assertThatThrownBy(() -> new WinningNumbers(winningNumbersData, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("lottoNumbersAndMatchingNumber")
    public void 로또와_일치하는_숫자_개수_테스트(Lotto lotto, Rank rank) {
        // given
        final WinningNumbersData winningNumbersData = new WinningNumbersData(List.of(1, 2, 3, 4, 5, 6));
        final BonusNumber bonusNumber = new BonusNumber(7);
        final WinningNumbers winningNumbers = new WinningNumbers(winningNumbersData, bonusNumber);

        // then
        Assertions.assertThat(winningNumbers.findRank(lotto)).isEqualTo(rank);
    }

    static Stream<Arguments> lottoNumbersAndMatchingNumber() {
        return Stream.of(
                Arguments.arguments(new Lotto(List.of(1, 2, 3, 4, 5, 6)), Rank.FIRST),
                Arguments.arguments(new Lotto(List.of(1, 2, 3, 4, 5, 7)), Rank.SECOND),
                Arguments.arguments(new Lotto(List.of(1, 2, 3, 4, 5, 8)), Rank.THIRD),
                Arguments.arguments(new Lotto(List.of(1, 2, 3, 4, 7, 8)), Rank.FOURTH),
                Arguments.arguments(new Lotto(List.of(1, 2, 3, 7, 8, 9)), Rank.FIFTH),
                Arguments.arguments(new Lotto(List.of(1, 2, 7, 8, 9, 10)), Rank.NONE),
                Arguments.arguments(new Lotto(List.of(1, 7, 8, 9, 10, 11)), Rank.NONE),
                Arguments.arguments(new Lotto(List.of(7, 8, 9, 10, 11, 12)), Rank.NONE)
        );
    }
}