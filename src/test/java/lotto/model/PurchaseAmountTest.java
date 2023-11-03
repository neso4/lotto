package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmountTest {

    @DisplayName("구입금액이 1000 ~ 100000 사이의 값이 아닌 경우 예외가 발생한다.")
    @ParameterizedTest(name = "{displayName} value:{0}")
    @ValueSource(ints = {0, 1000000, 987654321})
    void createOutOfRange(Integer value) {
        assertThatThrownBy(() -> PurchaseAmount.of(value).exchangeLottoTicket())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입금액이 1000으로 나누어 떨어지지 않는 경우 예외가 발생한다.")
    @ParameterizedTest(name = "{displayName} value:{0}")
    @ValueSource(ints = {1234, 3300, 10001, 2002})
    void createInvalidUnit(Integer value) {
        assertThatThrownBy(() -> PurchaseAmount.of(value).exchangeLottoTicket())
                .isInstanceOf(IllegalArgumentException.class);
    }
}