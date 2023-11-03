package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TicketTest {

    private final int ticketPrice = 1000;

    @DisplayName("티켓_개수")
    @Test
    void 티켓_개수() {
        int money = 10000;
        assertThat(money / ticketPrice).isEqualTo(10);
    }


}
