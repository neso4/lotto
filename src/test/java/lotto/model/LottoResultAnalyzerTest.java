package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LottoResultAnalyzerTest {
    @DisplayName("당첨 번호와 몇 개 일치하는지 확인")
    @ParameterizedTest(name = "당첨 번호: {0}, 구매한 로또 번호: {1}, 예상 일치 개수: {2}")
    @MethodSource("matchCase")
    public void 당첨_번호_일치_확인(List<Integer> winningNumbers, List<Integer> purchasedNumbers, int expectedMatchCount) {
        // given
        LottoResultAnalyzer lottoResultAnalyzer = new LottoResultAnalyzer();
        LottoPaper purchasedLottoPaper = new LottoPaper(purchasedNumbers);

        // when
        int matchCount = lottoResultAnalyzer.calculateMatchCount(purchasedLottoPaper, winningNumbers);

        // then
        assertEquals(expectedMatchCount, matchCount);
    }

    static Stream<Arguments> matchCase() {
        return Stream.of(
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(4, 5, 6, 7, 8, 9),
                        3
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(10, 11, 12, 13, 14, 15),
                        0
                )
        );
    }
}
