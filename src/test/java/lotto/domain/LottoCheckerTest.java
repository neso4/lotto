package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoCheckerTest {

    LottoChecker lottoChecker;

    @BeforeEach
    void setUp() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Bonus bonus = new Bonus(7);
        lottoChecker = new LottoChecker(winningLotto, bonus);
    }

    @ParameterizedTest
    @CsvSource({
            "ONE, 1",
            "TWO, 1",
            "THREE, 1",
            "FOUR, 0",
            "FIVE, 0",
            "NONE, 0"
    })
    @DisplayName("로또의 당첨 결과를 확인한다.")
    void checkWinningResult(Rank rank, int count) {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto lotto3 = new Lotto(List.of(1, 2, 3, 4, 5, 9));
        LottoTickets lottoTickets = new LottoTickets(List.of(lotto1, lotto2, lotto3));

        Map<Rank, Integer> winningResult = lottoChecker.checkWinningResult(lottoTickets);

        assertThat(winningResult).containsEntry(rank, count);
    }

    @Test
    @DisplayName("로또의 총 수익률을 구한다.")
    void calculateTotalReturn() {
        //given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 8, 9, 10));
        LottoTickets lottoTickets = new LottoTickets(List.of(lotto));
        lottoChecker.checkWinningResult(lottoTickets);

        //when
        Money money = new Money(5000);
        double totalReturn = lottoChecker.calculateTotalReturn(money);

        //then
        assertThat(totalReturn).isEqualTo(100.0);
    }
}
