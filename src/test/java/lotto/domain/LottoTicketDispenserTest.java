package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottoTicketDispenserTest {

    private LottoTicketDispenser lottoTicketDispenser;

    @BeforeEach
    void setUp() {
        this.lottoTicketDispenser = new LottoTicketDispenser(new AutoLottoNumberGenerator());
    }

    @Test
    void 로또를_구매_할_수_있는_금액으로_로또_티켓을_구매할_수_있다() {
        // given
        int cost = 3_000;
        // when
        List<Lotto> purchasedLottoTickets = lottoTicketDispenser.buyAutoCreatedTicket(cost);
        // then
        assertThat(purchasedLottoTickets.size()).isEqualTo(3);
    }

    @Test
    void 로또를_구매_할_수_없는_금액이_들어오면_예외가_발생한다() {
        // given
        int cost = 2_500;
        // when
        // then
        assertThatThrownBy(() -> lottoTicketDispenser.buyAutoCreatedTicket(cost))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("로또 구입 금액은 %d 단위입니다.", Lotto.PRICE));
    }

    @Test
    void 주어진_숫자_생성_결과로_로또_티켓을_발행할_수_있다() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto expectedLotto = new Lotto(numbers);
        // when
        Lotto actualLotto = lottoTicketDispenser.createWinningTicket(() -> numbers);
        // then
        assertThat(actualLotto.confirmLottoWinning(expectedLotto, 7))
                .isEqualTo(LottoWinning.FIRST);
    }
}