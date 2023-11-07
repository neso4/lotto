package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketGeneratorTest {
    @DisplayName("로또 용지가 금액에 맞춰 정확히 나오는지 테스트한다.")
    @Test
    void NumberOfTicketTest1() {
        LottoTicketGenerator lottoTicketGenerator = new LottoTicketGenerator();
        lottoTicketGenerator.insertMoney(10000);
        int result = lottoTicketGenerator.ticketCount;
        assertThat(result).isEqualTo(10);
    }

    @Test
    void NumberOfTicketTest2() {
        LottoTicketGenerator lottoTicketGenerator = new LottoTicketGenerator();
        lottoTicketGenerator.insertMoney(15000);
        int result = lottoTicketGenerator.ticketCount;
        assertThat(result).isEqualTo(15);
    }

    @DisplayName("금액이 1000으로 나누어 떨어지지않으면 예외가 발생한다.")
    @Test
    void invalidAmountErrorTest() {
        assertThatThrownBy(() -> new LottoTicketGenerator().insertMoney(1050))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("금액이 음수일 경우 예외가 발생한다.")
    @Test
    void invalidAmountErrorTest2() {
        assertThatThrownBy(() -> new LottoTicketGenerator().insertMoney(-1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("금액이 0일 경우 예외가 발생한다.")
    @Test
    void invalidAmountErrorTest3() {
        assertThatThrownBy(() -> new LottoTicketGenerator().insertMoney(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLottoTicketTest() {
        LottoTicketGenerator lottoTicketGenerator = new LottoTicketGenerator();
        LottoTicket lottoTicket = lottoTicketGenerator.createLottoTicket();
        List<Integer> result = lottoTicket.lottoNumber;
        for (Integer lottoNumber : result) {
            assertThat(lottoNumber).isBetween(1, 45);
        }
    }

}