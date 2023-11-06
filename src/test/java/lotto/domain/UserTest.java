package lotto.domain;

import lotto.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private static final int TICKET_PRICE = 1000;

    @Test
    @DisplayName("티켓가격의 배수로 금액을 입력 받으면 로또를 살 수 있다.")
    void validAmount() {
        int buyCount = 4;
        assertDoesNotThrow(() -> {
            new User(TICKET_PRICE*buyCount);
        });
    }

    @Test
    @DisplayName("티켓가격의 배수가 아닌 금액을 입력 받으면 로또를 살 수 없다.")
    void invalidAmount() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new User(TICKET_PRICE+TICKET_PRICE/2))
                .withMessageContaining(ErrorMessage.INVALID_TICKET_AMOUNT.getMessage());
    }
}