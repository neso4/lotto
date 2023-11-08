package lotto.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RankingTest {

    @ParameterizedTest
    @MethodSource(value = "provideRankingTestData")
    void of_메서드는_번호_일치_갯수와_보너스_일치_여부를_확인해_Ranking_을_반환한다(int matchCount, boolean isBonus, Ranking result) {
        assertThat(Ranking.of(matchCount, isBonus)).isEqualTo(result);
    }

    @Test
    void isSecond_메서드는_자신이_SECOND_인지를_판별한다() {
        assertThat(Ranking.SECOND.isSecond()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"NONE, 0", "FIFTH, 5_000", "FOURTH, 50_000", "THIRD, 1_500_000", "SECOND, 30_000_000", "FIRST, 2_000_000_000",})
    void getReward_메서드는_자신의_상금을_반환한다(Ranking ranking, long reward) {
        assertThat(ranking.getReward()).isEqualTo(reward);
    }

    //매칭 갯수, 보너스 일치여부, 등수 를 제공한다.
    static List<Arguments> provideRankingTestData() {
        return Arrays.asList(
                Arguments.of(6, false, Ranking.FIRST),
                Arguments.of(6, true, Ranking.FIRST),
                Arguments.of(5, true, Ranking.SECOND),
                Arguments.of(5, false, Ranking.THIRD),
                Arguments.of(4, true, Ranking.FOURTH),
                Arguments.of(4, false, Ranking.FOURTH),
                Arguments.of(3, true, Ranking.FIFTH),
                Arguments.of(3, false, Ranking.FIFTH),
                Arguments.of(2, false, Ranking.NONE),
                Arguments.of(1, false, Ranking.NONE),
                Arguments.of(1, false, Ranking.NONE)
        );
    }
}
