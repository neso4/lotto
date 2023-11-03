package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {
    @DisplayName("구입 금액이 정수가 아닐 경우 예외가 발생한다.")
    @Test
    void createNameByNonNumericInput() {
        assertThatThrownBy(() -> new Money("1000t"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 1000원 단위가 아닐 경우 예외가 발생한다.")
    @Test
    void createNameByNonThousandUnit() {
        assertThatThrownBy(() -> new Money("5100"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
