package lotto.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.lotto.Lotties;
import lotto.domain.lotto.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;

    // 테스트 Given 상횡: 10,000을 가진 Player 를 생성
    @BeforeEach
    void initGivenCondition() {
        player = new Player(10000);
    }

    @DisplayName("생성한 Player 의 금액 10,000원을 전부 사용하면, 티켓 10장을 발급받는다.")
    @Test
    void playerTest_1() {
        // When
        player.consumeAllMoneyToLottoTicket();
        // Then
        assertThat(player.getTicketNumber()).isEqualTo(10);
    }

    @DisplayName("10,000원으로 10장의 티켓을 발급받은 후 로또를 구매하면, 티켓은 0장이 된다.")
    @Test
    void playerTest_2() {
        //given
        Lotto boughtLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotties lotties = new Lotties();
        player.consumeAllMoneyToLottoTicket();
        //when
        player.buyRandomLottoWithAllTicket(boughtLotto, lotties);
        //then
        assertThat(player.getTicketNumber()).isEqualTo(0);
    }
}
