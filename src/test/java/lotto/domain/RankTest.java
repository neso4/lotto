package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    @ParameterizedTest(name = "입력값 : [{0}, {1}], 기대값 : {2}")
    @MethodSource("provideDataForFind")
    @DisplayName("로또 순위 검색")
    void givenData_whenFindRank_thenReturnMatchedRank(int matchingCount, boolean bonusNumberExistence, Rank expected) {
        // when
        Rank result = Rank.find(matchingCount, bonusNumberExistence);

        // then
        assertEquals(expected, result);
    }

    static Stream<Arguments> provideDataForFind() {
        return Stream.of(
                Arguments.of(6, false, Rank.FIRST),
                Arguments.of(5, true, Rank.SECOND),
                Arguments.of(5, false, Rank.THIRD),
                Arguments.of(4, true, Rank.FOURTH),
                Arguments.of(4, false, Rank.FOURTH),
                Arguments.of(3, true, Rank.FIFTH),
                Arguments.of(3, false, Rank.FIFTH),
                Arguments.of(2, true, Rank.UNRANKED),
                Arguments.of(1, true, Rank.UNRANKED),
                Arguments.of(0, true, Rank.UNRANKED)
        );
    }

}
