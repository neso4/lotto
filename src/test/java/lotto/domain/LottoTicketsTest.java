package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTicketsTest {

    @DisplayName("당첨 결과를 구할 수 있다.")
    @ParameterizedTest
    @MethodSource
    void getRankResult(List<Lotto> tickets, WinningLotto winningLotto, List<Integer> expected) {
        LottoTickets lottoTickets = new LottoTickets(tickets, new PurchaseAmount(1000));

        RankResult rankResult = lottoTickets.getRankResult(winningLotto);
        assertThat(rankResult.values()).containsExactlyElementsOf(expected);
    }

    private static Stream<Arguments> getRankResult() {
        return Stream.of(
                Arguments.of(
                        List.of(createLotto(1, 2, 3, 10, 11, 12),
                                createLotto(1, 2, 3, 4, 11, 12),
                                createLotto(1, 2, 3, 4, 5, 11),
                                createLotto(1, 2, 3, 4, 5, 7),
                                createLotto(1, 2, 3, 4, 5, 6)
                        ),
                        createWinningTicket(7, 1, 2, 3, 4, 5, 6),
                        List.of(1, 1, 1, 1, 1)
                ),
                Arguments.of(
                        List.of(createLotto(1, 2, 3, 4, 5, 6),
                                createLotto(1, 2, 3, 4, 5, 6)
                        ),
                        createWinningTicket(7, 1, 2, 3, 4, 5, 6),
                        List.of(0, 0, 0, 0, 2)
                )
        );
    }

    private static WinningLotto createWinningTicket(int bonus, Integer... nums) {
        return new WinningLotto(createLotto(nums), new LottoNumber(bonus));
    }

    private static Lotto createLotto(Integer... nums) {
        return new Lotto(Arrays.asList(nums));
    }
}
