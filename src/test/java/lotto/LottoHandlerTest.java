package lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LottoHandlerTest {

    private LottoHandler lottoHandler;

    @BeforeEach
    void setUp() {
        lottoHandler = new LottoHandler();
    }

    @DisplayName("구매 금액을 입력 받아 구입 금액에 해당하는 로또를 발행한다.")
    @Test
    void calculateLottoTicketCount() {
        // given
        String receivedPurchasePrice = "1000";

        // when
        int lottoTicket = lottoHandler.calculateLottoTicketCount(receivedPurchasePrice);

        // then
        assertThat(lottoTicket).isEqualTo(1);
    }

    @DisplayName("구매 금액이 1,000원으로 나누어 떨어지지 않으면 예외가 발생한다.")
    @Test
    void calculateLottoTicketCountByNotDivisibleBy1000() {
        // given
        String receivedPurchasePrice = "1001";

        // when // then
        assertThatThrownBy(() -> lottoHandler.calculateLottoTicketCount(receivedPurchasePrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 1,000원 단위로만 입력해 주세요.");

    @DisplayName("구매 금액에 문자를 입력하면 예외가 발생한다.")
    @Test
    void calculateLottoTicketCountByNotNumber() {
        // given
        String receivedPurchasePrice = "abc";

        // when // then
        assertThatThrownBy(() -> lottoHandler.calculateLottoTicketCount(receivedPurchasePrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 숫자만 입력해 주세요.");
    }
}